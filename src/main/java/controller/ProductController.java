package controller;

import dao.DAO;
import dao.IProductDAO;
import dao.IUserDAO;
import model.Product;
import model.User;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "ProductController", urlPatterns = "/product")
public class ProductController extends HttpServlet {
    DAO dao = new IProductDAO();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "abc":
                break;
            default:
                mainAll(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }


    private void mainAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Product> listP=new ArrayList<>();
        try {
            listP = dao.showALl();
            request.setAttribute("listP", listP);
            RequestDispatcher ds = request.getRequestDispatcher("Main/index.jsp");
            ds.forward(request, response);
            System.out.println(listP);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
