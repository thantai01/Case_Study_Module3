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
    private DAO<Product> productDAO;
    private List<Product> listP;
    private ITypeDAO daoT;
    private IUserDAO daoU;
    private IOrderDAO daoO;
    private IOrderDetailDAO daoOD;
    private IProductDAO daoP;

    public void init() {
        productDAO = new IProductDAO();
        listP = new ArrayList<>();
        daoT = new ITypeDAO();
        daoU = new IUserDAO();
        daoO = new IOrderDAO();
        daoOD = new IOrderDetailDAO();
        daoP = new IProductDAO();
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
                    viewProduct(request, response);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                break;
            case "cart":
//                try {
//                    order(request,response);
//                    viewOrderDetail(request, response);
//                } catch (SQLException throwables) {
//                    throwables.printStackTrace();
//                }
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
            listP = productDAO.showALl();
            request.setAttribute("listP", listP);
            RequestDispatcher ds = request.getRequestDispatcher("product/productList.jsp");
            request.setAttribute("product", product);
            ds.forward(request, response);
            System.out.println(listP);
        } catch (SQLException | ClassNotFoundException exception) {
            exception.printStackTrace();
        }
    }

    private void viewProduct(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        Product product;
        Type type;
        int id = Integer.parseInt(request.getParameter("id"));
        try {
            product = productDAO.viewProduct(id);
            type = daoT.viewType(product.getIdType());
            RequestDispatcher ds = request.getRequestDispatcher("Main/shop-details.jsp");
            request.setAttribute("p", product);
            request.setAttribute("t", type);
            System.out.println(type);
            ds.forward(request, response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }

    private void viewProductDetail(HttpServletRequest request, HttpServletResponse response) throws SQLException, ClassNotFoundException, ServletException, IOException {
        String productID = request.getParameter("productID");
        Product product = productDAO.select(productID);
        request.setAttribute("product", product);
        RequestDispatcher rd = request.getRequestDispatcher("product/view.jsp");
        rd.forward(request, response);
    }

    private void showCreatForm(HttpServletRequest request, HttpServletResponse response) throws SQLException, ClassNotFoundException, ServletException, IOException {
        RequestDispatcher rd = request.getRequestDispatcher("product/create.jsp");
        rd.forward(request, response);
    }

    private void createProduct(HttpServletRequest request, HttpServletResponse response) throws SQLException, ClassNotFoundException, ServletException, IOException {
        String name = request.getParameter("productName");
        String price = request.getParameter("productPrice");
        String madeIn = request.getParameter("productMadeIn");
        String image = request.getParameter("productImage");
        String quantity = request.getParameter("productQuantity");
        String idType = request.getParameter("productType");
//        int role = Integer.parseInt(request.getParameter("userRole"));
        Product newProduct = new Product(name,
                Integer.parseInt(price),
                madeIn, image, Integer.parseInt(quantity), Integer.parseInt(idType));
        productDAO.insert(newProduct);
        RequestDispatcher rd = request.getRequestDispatcher("product/create.jsp");
        request.setAttribute("message", "New user was created");
        rd.forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException, ClassNotFoundException {
        String productID = request.getParameter("productID");
        Product existingProduct = productDAO.select(productID);
        RequestDispatcher rd = request.getRequestDispatcher("product/edit.jsp");
        request.setAttribute("user", existingProduct);
        rd.forward(request, response);
    }

    private void updateProduct(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        String name = request.getParameter("productName");
        String price = request.getParameter("productPrice");
        String madeIn = request.getParameter("productMadeIn");
        String image = request.getParameter("productImage");
        String quantity = request.getParameter("productQuantity");
        String idType = request.getParameter("productType");
        Product editedProduct = new Product(name,
                Integer.parseInt(price),
                madeIn, image, Integer.parseInt(quantity), Integer.parseInt(idType));
        productDAO.update(editedProduct);
        RequestDispatcher rd = request.getRequestDispatcher("user/edit.jsp");
        request.setAttribute("message", "User was edit...");
        rd.forward(request, response);
    }

    private void deleteProduct(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException, ClassNotFoundException {
        String productID = request.getParameter("ProductID");
        productDAO.delete(productID);
        List<Product> productList = productDAO.showALl();
        request.setAttribute("products", productList);
        RequestDispatcher rd = request.getRequestDispatcher("user/userList.jsp");
        rd.forward(request, response);
    }

    private void viewOrderDetail(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException, ClassNotFoundException {
        User user = daoU.select("hung");
        List<Order> listO = daoO.showListOrder();
    }
}