package controller;

import dao.DAO;
import dao.IProductDAO;
import dao.LoginDAO;
import model.Product;
import model.User;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "Servlet", urlPatterns = "/main")
public class MainController extends HttpServlet {
    DAO<Product> productDAO = new IProductDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        showMainSite(request,response);
        String action = request.getParameter("action");
        if (action == null)
            action = "";
        switch (action) {
            case "login":
                showLoginSite(request, response);
                break;
            case "sign-up":
                showSignUpSite(request, response);
                break;
            case "logout":

                try {
                    logout(request, response);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                break;
            case "viewProduct":
                ;
                break;
            case "searchProduct":
                ;
                break;
            default:
                try {
                    showMainSite(request, response);
                } catch (SQLException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
        }
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null)
            action = "";
        switch (action) {
            case "login":
                try {
                    loginCheck(request, response);
                } catch (SQLException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
                break;

            case "create":
                ;
                break;
            case "":
                ;
                break;
//            default: showMainSite(request,response);
        }
    }

    public void showMainSite(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException, ClassNotFoundException {
        List<Product> listP;
        listP = productDAO.showALl();
        request.setAttribute("listP", listP);
        RequestDispatcher rd = request.getRequestDispatcher("Main/index.jsp");
        rd.forward(request, response);
    }

    public void showLoginSite(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher rd = request.getRequestDispatcher("Main/login.jsp");
        rd.forward(request, response);
    }

    public void loginCheck(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException, ClassNotFoundException, ServletException {
        String user = request.getParameter("userName");
        String password = request.getParameter("userPassword");
        User loginUser = LoginDAO.checkLogin(user, password);
        PrintWriter out = response.getWriter();
        RequestDispatcher rd;
        if (user == null)
//            request.setAttribute("message","A Chương ok");
//         rd = request.getRequestDispatcher("Main/login.jsp");
            showLoginSite(request, response);
//        rd.forward(request,response);
        if (loginUser != null && loginUser.getRole() == 0) {
            List<Product> listP;
            listP = productDAO.showALl();
            request.setAttribute("listP", listP);
             rd = request.getRequestDispatcher("Main/index.jsp");
            HttpSession session = request.getSession();
            session.setAttribute("acc", loginUser);
//            request.setAttribute("message",null);
            rd.forward(request, response);
        }
        if (loginUser != null && loginUser.getRole() == 1) {
            rd = request.getRequestDispatcher("/userManager");
            rd.forward(request, response);
        }
    }

    private void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException, ClassNotFoundException {
        List<Product> listP;
        listP = productDAO.showALl();
        request.setAttribute("listP", listP);
        RequestDispatcher rd = request.getRequestDispatcher("Main/index.jsp");
        HttpSession session = request.getSession();
        session.removeAttribute("acc");
        rd.forward(request, response);
    }

    private void showSignUpSite(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher rd = request.getRequestDispatcher("Main/register.jsp");
        rd.forward(request, response);
    }

    public void signUpUser(HttpServletRequest request, HttpServletResponse response) {

    }
}
