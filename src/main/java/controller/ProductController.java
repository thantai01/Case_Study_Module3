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
//            case "view":
//                try {
//                    showView(request, response);
//                } catch (SQLException | ClassNotFoundException e) {
//                    e.printStackTrace();
//                }
//                break;
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

//    public void showEditForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException, ClassNotFoundException {
//        RequestDispatcher requestDispatcher = request.getRequestDispatcher("product/edit.jsp");
//        requestDispatcher.forward(request, response);
//    }

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

    public void showEditForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException, ClassNotFoundException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("product/update.jsp");
        String id = request.getParameter("id");
        try {
            Product product = productDAO.select(id);
            RequestDispatcher dispatcher = request.getRequestDispatcher("product/edit.jsp");
            request.setAttribute("product", product);
            dispatcher.forward(request,response);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

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


//
//    private void mainAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        Product product = null;
//        try {
//            listP = productDAO.showALl();
//            request.setAttribute("listP", listP);
//            RequestDispatcher ds = request.getRequestDispatcher("product/productList.jsp");
//            request.setAttribute("product",product);
//            ds.forward(request, response);
//            System.out.println(listP);
//        } catch (SQLException | ClassNotFoundException exception) {
//            exception.printStackTrace();
//        }
//    }
//    private void viewProduct(HttpServletRequest request,HttpServletResponse response) throws SQLException {
//        Product product;
//        Type type;
//        int id = Integer.parseInt(request.getParameter("id"));
//        try {
//            product = productDAO.select();
//            type = daoT.viewType(product.getIdType());
//            RequestDispatcher ds = request.getRequestDispatcher("Main/shop-details.jsp");
//            request.setAttribute("p",product);
//            request.setAttribute("t",type);
//            System.out.println(type);
//            ds.forward(request,response);
//        } catch (ServletException | IOException e) {
//            e.printStackTrace();
//        }
//    }
//    private void viewProductDetail(HttpServletRequest request, HttpServletResponse response) throws SQLException, ClassNotFoundException, ServletException, IOException {
//        String productID = request.getParameter("productID");
//        Product product = productDAO.select(productID);
//        request.setAttribute("product", product);
//        RequestDispatcher rd = request.getRequestDispatcher("product/view.jsp");
//        rd.forward(request, response);
//    }
//
//    private void showCreatForm(HttpServletRequest request, HttpServletResponse response) throws SQLException, ClassNotFoundException, ServletException, IOException {
//        RequestDispatcher rd = request.getRequestDispatcher("product/create.jsp");
//        rd.forward(request, response);
//    }
//
//    private void createProduct(HttpServletRequest request, HttpServletResponse response) throws SQLException, ClassNotFoundException, ServletException, IOException {
//        String name = request.getParameter("name");
//        String price = request.getParameter("price");
//        String madeIn = request.getParameter("madeIn");
//        String image = request.getParameter("image");
//        String quantity = request.getParameter("quantity");
//        String idType = request.getParameter("idType");
////        int role = Integer.parseInt(request.getParameter("userRole"));
//        Product newProduct = new Product(name,
//                Integer.parseInt(price),
//                madeIn, image, Integer.parseInt(quantity), Integer.parseInt(idType));
//        productDAO.insert(newProduct);
//        RequestDispatcher rd = request.getRequestDispatcher("product/create.jsp");
//        request.setAttribute("message", "New user was created");
//        rd.forward(request, response);
//    }
//
//    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException, ClassNotFoundException {
//        String productID = request.getParameter("productID");
//        Product existingProduct = productDAO.select(productID);
//        RequestDispatcher rd = request.getRequestDispatcher("product/edit.jsp");
//        request.setAttribute("user", existingProduct);
//        rd.forward(request, response);
//    }
//    private void updateProduct(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
//        String name = request.getParameter("productName");
//        String price = request.getParameter("productPrice");
//        String madeIn = request.getParameter("productMadeIn");
//        String image = request.getParameter("productImage");
//        String quantity = request.getParameter("productQuantity");
//        String idType = request.getParameter("productType");
//        Product editedProduct = new Product(name,
//                Integer.parseInt(price),
//                madeIn, image, Integer.parseInt(quantity), Integer.parseInt(idType));
//        productDAO.update(editedProduct);
//        RequestDispatcher rd = request.getRequestDispatcher("user/edit.jsp");
//        request.setAttribute("message", "User was edit...");
//        rd.forward(request,response);
//    }
//    private void deleteProduct(HttpServletRequest request,HttpServletResponse response) throws SQLException, ServletException, IOException, ClassNotFoundException {
//        String productID = request.getParameter("ProductID");
//        productDAO.delete(productID);
//        List<Product> productList = productDAO.showALl();
//        request.setAttribute("products", productList);
//        RequestDispatcher rd = request.getRequestDispatcher("user/userList.jsp");
//        rd.forward(request, response);
//    }
}