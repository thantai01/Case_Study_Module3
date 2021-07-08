package controller;

import dao.*;
import model.Order;
import model.OrderDetail;
import model.Product;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
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
            default:
                try {
                    showListOrder(request, response);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
    public void showListOrder(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("listOrder.jsp");
        List<Order> orders = daoO.showListOrder();
        request.setAttribute("orders", orders);
        requestDispatcher.forward(request, response);
    }
    public void showDetailOrder(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("Main/shoping-cart.jsp");
        int id = Integer.parseInt(request.getParameter("id"));
        List<OrderDetail> orderDs = daoOD.showOrderDetailByIdOrder(id);
        Order order = daoO.findById(id);
        List<Product> products = new ArrayList<>();
        for (int i=0 ;i<orderDs.size();i++) {
            products.add(daoP.viewProduct(orderDs.get(i).getIdProduct()));
        }
        request.setAttribute("orderDs", orderDs);
        request.setAttribute("order", order);
        request.setAttribute("products", products);
        requestDispatcher.forward(request, response);
    }
}
