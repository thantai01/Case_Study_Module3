package controller;

import dao.*;
import model.*;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "ProductController", urlPatterns = "/product")
public class ProductController extends HttpServlet {
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
                    viewProduct(request, response);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                break;
            case "cart":
                try {
//                    order(request,response);
                    viewOrderDetail(request,response);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                break;
            default:
                mainAll(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }


    private void mainAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Product> listP = new ArrayList<>();
        Product product = null;
        try {
            listP = daoP.showALl();
            RequestDispatcher ds = request.getRequestDispatcher("Main/index.jsp");
            product = daoP.showLastProduct();
            request.setAttribute("listP", listP);
            request.setAttribute("product", product);
            ds.forward(request, response);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void viewProduct(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        Product product = null;
        Type type = null;
        int id = Integer.parseInt(request.getParameter("id"));
        try {
            product = daoP.viewProduct(id);
            type = daoT.viewType(product.getIdType());
            RequestDispatcher ds = request.getRequestDispatcher("Main/shop-details.jsp");
            request.setAttribute("p", product);
            request.setAttribute("t", type);
            ds.forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void viewOrderDetail(HttpServletRequest request,HttpServletResponse response) throws SQLException, ServletException, IOException {
//        User user = daoU.selectUser("hung");
//        List<Order> listO = daoO.showListOrder();
//        List<Product> products =  new ArrayList<>();
//        products.add(daoP.viewProduct(od.getIdProduct()));
//        Order order = daoO.findById(1);
//        List<OrderDetail> listOD = new ArrayList<>();
//        List<Product> products = new ArrayList<>();
//        int total = 0;
//        listOD = daoOD.showOrderDetailByIdOrder(order.getId());
//        for (OrderDetail od:listOD) {
//            Product product = daoP.viewProduct(od.getIdProduct());
//            products.add(product);
//            total += daoP.total(od.getQuantity(),daoP.viewProduct(od.getIdProduct()).getPrice());
//        }
//        request.setAttribute("total",total);
//        request.setAttribute("p",products);
//        request.setAttribute("Order",order);
//        request.setAttribute("listDetail",listOD);
//        RequestDispatcher ds = request.getRequestDispatcher("Main/shoping-cart.jsp");
//        ds.forward(request,response);
    }

    private void order(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
//        User user = daoU.selectUser("hung");
//        List<Order> listO = daoO.showListOrder();
//        List<OrderDetail> listOD = null;
//        for (Order o : listO) {
//            listOD = daoOD.showOrderDetailByIdOrder(o.getId());
//        }
//
//        request.setAttribute("listOrder",listO);
//        request.setAttribute("listDetail",listOD);
//        RequestDispatcher ds = request.getRequestDispatcher("abc.jsp");
//        ds.forward(request,response);
    }


}
