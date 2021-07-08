package controller;

import dao.IUserDAO;
import model.User;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "UserController", urlPatterns = "/userManager")
public class UserController extends HttpServlet {

    private IUserDAO userDAO;

    public void init() {
        userDAO = new IUserDAO();
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if(action==null)
            action="";
        try {
            switch (action) {
                case "create": showCreatForm(request,response);break;
                case "edit": showEditForm(request,response); ; break;
                case "delete": deleteUser(request,response);break;
                case "Search": searchingUser(request,response); break;
                case "productManager":showProductManager(request, response);break;

                default: userList(request,response); break;
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if(action==null)
            action="";
        try {
            switch (action) {
                case "create":
                    insertUser(request,response);
                    break;
                case "Create":
                    insertAdmin(request,response);
                case "edit":
                    updateUser(request,response);
                    break;
                default:
                    userList(request,response);
                    break;
            }
        } catch (ClassNotFoundException | SQLException exception) {
            exception.printStackTrace();
        }
    }
    private void userList(HttpServletRequest request, HttpServletResponse response) throws SQLException, ClassNotFoundException, ServletException, IOException {
        List<User> userList = userDAO.showALl();
        request.setAttribute("users",userList);
        RequestDispatcher rd = request.getRequestDispatcher("user/userList.jsp");
        rd.forward(request,response);
    }
    private void showCreatForm(HttpServletRequest request, HttpServletResponse response) throws SQLException, ClassNotFoundException, ServletException, IOException {
        RequestDispatcher rd = request.getRequestDispatcher("Main/register.jsp");
        rd.forward(request,response);
    }

    private void insertUser(HttpServletRequest request, HttpServletResponse response) throws SQLException, ClassNotFoundException, ServletException, IOException {
        String userID = request.getParameter("userName");
        String userPassword = request.getParameter("userPassword");
        String address = request.getParameter("userAddress");
        String fullName = request.getParameter("userFullName");
        String phoneNumber = request.getParameter("userPhoneNum");
//        int role = Integer.parseInt(request.getParameter("userRole"));
        User newUser = new User(userID,userPassword,address,fullName,phoneNumber,0);
        userDAO.insert2(newUser);
        RequestDispatcher rd = request.getRequestDispatcher("Main/register.jsp");
        request.setAttribute("message", "New user was created");
        rd.forward(request,response);
    }
    private void insertAdmin(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException, ClassNotFoundException {
        String userID = request.getParameter("userID");
        String userPassword = request.getParameter("password");
        User newAdmin = new User(userID,userPassword,1);
        userDAO.insert(newAdmin);
        userList(request,response);
        RequestDispatcher rd = request.getRequestDispatcher("user/userList.jsp");
        request.setAttribute("message","New Admin was created");
        rd.forward(request,response);
    }
    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException, ClassNotFoundException {
        String userName = request.getParameter("userName");
        User existingUser = userDAO.select(userName);
        RequestDispatcher rd = request.getRequestDispatcher("user/edit.jsp");
        request.setAttribute("user",existingUser);
        rd.forward(request,response);
    }
    private void updateUser(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        String userID = request.getParameter("userName");
        String userPassword = request.getParameter("userPassword");
        String address = request.getParameter("address");
        String fullName = request.getParameter("fullName");
        String phoneNum = request.getParameter("userPhoneNum");
        int role = Integer.parseInt(request.getParameter("role"));
        User editedUser = new User(userID,userPassword,address,fullName,phoneNum,role);
        userDAO.update(editedUser);
        RequestDispatcher rd = request.getRequestDispatcher("user/edit.jsp");
        rd.forward(request,response);
    }
    private void deleteUser(HttpServletRequest request,HttpServletResponse response) throws SQLException, ServletException, IOException, ClassNotFoundException {
        String name = request.getParameter("userName");
        userDAO.delete(name);
        List<User> userList = userDAO.showALl();
        request.setAttribute("users",userList);
        RequestDispatcher rd = request.getRequestDispatcher("user/userList.jsp");
        rd.forward(request,response);
    }
    private void searchingUser(HttpServletRequest request,HttpServletResponse response) throws SQLException, ServletException, IOException {
        String name = request.getParameter("userName");
        List<User> listSearch = userDAO.userSearch(name);
        request.setAttribute("users",listSearch);
        RequestDispatcher rd = request.getRequestDispatcher("user/userList.jsp");
        rd.forward(request,response);
    }
    private void showProductManager(HttpServletRequest request, HttpServletResponse response) throws SQLException, ClassNotFoundException, ServletException, IOException {
        RequestDispatcher rd = request.getRequestDispatcher("/productManager");
        rd.forward(request,response);
    }

}
