package controller;

import dao.DAO;
import dao.IUserDAO;
import model.User;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "UserController", urlPatterns = "/main")
public class UserController extends HttpServlet {
    DAO dao = new IUserDAO();

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
        try {
            List<User> list = dao.showALl();
            request.setAttribute("listUser", list);
            RequestDispatcher ds = request.getRequestDispatcher("Main/index.jsp");
            ds.forward(request, response);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
