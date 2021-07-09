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

@WebServlet(name = "ProductController", urlPatterns = "/productManager")
public class ProductController extends HttpServlet {
    private DAO<Product> productDAO;
    private List<Product> listP;
    private ITypeDAO daoT;

    public void init() {
        productDAO = new IProductDAO();
        listP = new ArrayList<>();
        daoT = new ITypeDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "view":
                try {
                    showView(request, response);
                } catch (SQLException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
                break;
            case "create":
                try {
                    showCreatForm(request, response);
                } catch (SQLException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
                break;
            case "edit":
                try {
                    showEditForm(request, response);
                } catch (SQLException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
                break;
            case "delete":
                try {
                    deleteProduct(request, response);
                } catch (SQLException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
                break;

            default:
                showListAllProduct(request, response);
        }
    }

    public void showCreatForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException, ClassNotFoundException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("product/create.jsp");
        requestDispatcher.forward(request, response);
    }

    public void showEditForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException, ClassNotFoundException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("product/edit.jsp");
        requestDispatcher.forward(request, response);
    }

    public void showListAllProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("product/productList.jsp");
        try {
            List<Product> products = productDAO.showALl();
            request.setAttribute("products", products);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        requestDispatcher.forward(request, response);
    }

    public void showView(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException, ClassNotFoundException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("product/view.jsp");
        String id = request.getParameter("id");
        try {
            Product products = productDAO.select(id);
            request.setAttribute("products", products);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        requestDispatcher.forward(request, response);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "create":
                try {
                    addProduct(request, response);
                } catch (SQLException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
                break;
            case "edit":
                try {
                    updateProduct(request, response);
                } catch (SQLException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
                break;

        }
    }


    public void addProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException, ClassNotFoundException {
        String name = request.getParameter("name");
        String price = request.getParameter("price");
        String madeIn = request.getParameter("madeIn");
        String image = request.getParameter("image");
        String quantity = request.getParameter("quantity");
        String idType = request.getParameter("idType");
        try {
            Product newProduct = new Product(name,
                    Integer.parseInt(price),
                    madeIn, image, Integer.parseInt(quantity), Integer.parseInt(idType));
            productDAO.insert(newProduct);
            showListAllProduct(request, response);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void updateProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException, ClassNotFoundException {
        String name = request.getParameter("name");
        String id = request.getParameter("id");
        String price = request.getParameter("price");
        String madeIn = request.getParameter("madeIn");
        String image = request.getParameter("image");
        String quantity = request.getParameter("quantity");
        String idType = request.getParameter("idType");

        try {
            Product newProduct = new Product(Integer.parseInt(id), name,
                    Integer.parseInt(price),
                    madeIn, image, Integer.parseInt(quantity), Integer.parseInt(idType));
            productDAO.update(newProduct);
            showListAllProduct(request, response);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void deleteProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException, ClassNotFoundException {
        String id = request.getParameter("id");
        try {
            productDAO.delete(id);
            showListAllProduct(request, response);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}