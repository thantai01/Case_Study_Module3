package controller;

import dao.DAO;
import dao.IProductDAO;
import dao.ITypeDAO;
import dao.IUserDAO;
import model.Product;
import model.Type;
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
    IProductDAO dao = new IProductDAO();
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
                    viewProduct(request,response);
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
        List<Product> listP=new ArrayList<>();
        Product product = null;
        try {
            listP = dao.showALl();
            RequestDispatcher ds = request.getRequestDispatcher("Main/index.jsp");
            product=dao.showLastProduct();
            request.setAttribute("listP", listP);
            request.setAttribute("product",product);
            ds.forward(request, response);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    private void viewProduct(HttpServletRequest request,HttpServletResponse response) throws SQLException {
        Product product =null;
        Type type = null;
        int id = Integer.parseInt(request.getParameter("id"));
        try {
            product = dao.viewProduct(id);
            type = daoT.viewType(product.getIdType());
            RequestDispatcher ds = request.getRequestDispatcher("Main/shop-details.jsp");
            request.setAttribute("p",product);
            request.setAttribute("t",type);
            System.out.println(type);
            ds.forward(request,response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
