package service;

import dao.DAOFactory;
import dao.IProductDao;
import entity.Product;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class ProductServiceImpl {

    private IProductDao iProductDAO;

    public ProductServiceImpl() {
        this.iProductDAO = DAOFactory.getInstance().getProductDAO();
    }

    public int createProduct(Product product) throws SQLException {
        if (product != null) return iProductDAO.save(product);
        return -1;
    }

    public List<Product> getProducts() {
        return iProductDAO.findAll();
    }

    public boolean deleteById(Integer id) {
        return iProductDAO.deleteById(id);
    }


    public Optional<Product> findById(Integer id) {
        return iProductDAO.findById(id);
    }
}
