package controller;

import dao.*;
import javafx.scene.control.Alert;
import model.Order;
import model.OrderDetail;
import model.Product;
import model.User;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@WebServlet(name = "OrderController", value = "/orders")
public class OrderController extends HttpServlet {
    IUserDAO daoU = new IUserDAO();
    IOrderDAO daoO = new IOrderDAO();
    IOrderDetailDAO daoOD = new IOrderDetailDAO();
    IProductDAO daoP = new IProductDAO();
    ITypeDAO daoT = new ITypeDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "view":
                try {
                    showDetailOrder(request, response);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                break;
            case "add":
                try {
                    addToCart(request,response);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                break;
            default:
                try {
                    showListOrder(request, response);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
        }
    }
    List<Product> cart =new ArrayList<>();
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action){
            case "add":{
                try {
                    addProductToCart(request,response);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                break;
            }
        }
    }
    public void showListOrder(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException, ClassNotFoundException {
        HttpSession session= request.getSession();
        User user= (User) session.getAttribute("acc");
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("listOrder.jsp");
        List<Order> orders = daoO.showListOrder(user.getUserID());
        request.setAttribute("orders", orders);
        requestDispatcher.forward(request, response);
    }
    private void addToCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
//        cart=new ArrayList<>();

        HttpSession session = request.getSession();
        int id = (int) session.getAttribute("idProduct");
        Product product = daoP.viewProduct(id);
        if(isOnCart(id)){
            Product p1 = getProductFromCart(id);
            int a = p1.getQuantity()+1;
            p1.setQuantity(a);
        }else{
            product.setQuantity(1);
            cart.add(product);
        }
        try {
            showListProduct(request,response);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public void showDetailOrder(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
//        RequestDispatcher requestDispatcher = request.getRequestDispatcher("Main/shoping-cart.jsp");
//        int id = Integer.parseInt(request.getParameter("id"));
//        List<OrderDetail> orderDs = daoOD.showOrderDetailByIdOrder(id);
//        Order order = daoO.findById(id);
//        List<Product> products = new ArrayList<>();
//        int total=0 ;
//        int index =0;
//        for (int i=0 ;i<orderDs.size();i++) {
//            products.add(daoP.viewProduct(orderDs.get(i).getIdProduct()));
//            total += orderDs.get(i).getQuantity()*products.get(i).getPrice();
//            index++;
//        }
//        request.setAttribute("index",index);
//        request.setAttribute("total",total);
//        request.setAttribute("orderDs", orderDs);
//        request.setAttribute("order", order);
//        request.setAttribute("products", products);
//        requestDispatcher.forward(request, response);
//        cart=new ArrayList<>();
        List<Product> list = cart;
        int total = 0;
        int sum = 0;
        for (Product p:cart) {
            int price = p.getPrice();
            int quantity = p.getQuantity();
            sum += price*quantity;
        }
        request.setAttribute("list", list);
        request.setAttribute("sum", sum);
        RequestDispatcher dispatcher = request.getRequestDispatcher("Main/shoping-cart.jsp");
        try {
            dispatcher.forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void showListProduct(HttpServletRequest request,HttpServletResponse response) throws SQLException, ClassNotFoundException, ServletException, IOException {
        List<Product> listP;
        listP = daoP.showALl();
        request.setAttribute("listP", listP);
        RequestDispatcher rd = request.getRequestDispatcher("Main/index.jsp");
        rd.forward(request, response);
    }
    private boolean isOnCart(int id){
        for (Product p: cart
        ) {
            if(p.getId()==id){
                return true;
            }
        }
        return false;
    }
    private Product getProductFromCart(int id){
        for (Product p: cart
        ) {
            if(p.getId()==id){
                return p;
            }
        }
        return null;
    }
    private void addProductToCart(HttpServletRequest request,HttpServletResponse response) throws SQLException {
        String date = LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        HttpSession session = request.getSession();
        int index = (int) (Math.random()*1000);
        User user = (User) session.getAttribute("acc");
        Order order = new Order(index,user.getUserID(),date);
        daoO.insert(order);
        for (int i = 0 ; i < cart.size();i++){
            daoOD.insert(new OrderDetail(cart.get(i).getId(),order.getId(),cart.get(i).getQuantity()));
        }

    }
}
