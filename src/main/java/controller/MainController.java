package controller;

import dao.LoginDAO;
import model.User;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "Servlet", urlPatterns = "/main")
public class MainController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        showMainSite(request,response);
        String action = request.getParameter("action");
        if(action==null)
            action="";
        switch (action) {
            case "login": showLoginSite(request,response);break;
            case "viewProduct":  ; break;
            case "searchProduct": ; break;
            default: showMainSite(request,response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if(action==null)
            action="";
        switch (action) {
            case "login":
                try {
                    loginCheck(request,response);
                } catch (SQLException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
                break;
            case "create":  ; break;
            case "": ; break;
            default: showMainSite(request,response);
        }
    }
    public void showMainSite(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher rd = request.getRequestDispatcher("Main/index.jsp");
        rd.forward(request,response);
    }
    public void showLoginSite(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher rd = request.getRequestDispatcher("Main/login.jsp");
        rd.forward(request,response);
    }
    public void loginCheck(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException, ClassNotFoundException, ServletException {
        String user = request.getParameter("userName");
        String password = request.getParameter("userPassword");
        User loginUser = LoginDAO.checkLogin(user,password,role);
        if(user == null) {
            response.sendRedirect("Main/login.jsp");
        } else {
            assert loginUser != null;
            if (loginUser.getRole()== 0){
                RequestDispatcher rd = request.getRequestDispatcher("Main/index.jsp");
                rd.forward(request,response);
            } else if(loginUser.getRole() ==1) {
                RequestDispatcher rd = request.getRequestDispatcher("/userManager");
                rd.forward(request,response);
            }
        }

    }
}
