//package controller;
//
//import dao.IOrderDAO;
//import model.Order;
//
//import javax.servlet.*;
//import javax.servlet.http.*;
//import javax.servlet.annotation.*;
//import java.io.IOException;
//import java.sql.SQLException;
//import java.util.List;
//
//@WebServlet(name = "OrderController", urlPatterns = "/order")
//public class OrderController extends HttpServlet {
//    IOrderDAO dao = new IOrderDAO();
//}
//
//    @Override
//    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        String action = request.getParameter("action");
//        if (action == null) {
//            action = "";
//        }
//        switch (action) {
//            default:
//                listOrder(request, response);
//                break;
//        }
//    }
//
//    private void listOrder(HttpServletRequest request, HttpServletResponse response) {
////        List<Order> orders = dao.;
//        request.setAttribute("orders", dao.showAllOrderDetail());
//        RequestDispatcher dispatcher = request.getRequestDispatcher("/order/order.jsp");
//        try {
//            dispatcher.forward(request, response);
//        } catch (ServletException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//    }
//
//    @Override
//    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//
//    }
