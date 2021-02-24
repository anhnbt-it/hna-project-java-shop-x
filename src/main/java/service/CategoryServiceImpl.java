package service;

import dao.DAOFactory;
import dao.ICategoryDao;
import entity.Category;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class CategoryServiceImpl {

    private final ICategoryDao iCategoryDao;

    public CategoryServiceImpl() {
        this.iCategoryDao = DAOFactory.getInstance().getCategoryDao();
    }

    public int save(Category category) throws SQLException {
        if (category != null) return iCategoryDao.save(category);
        return -1;
    }

    public List<Category> findAll() throws SQLException {
        return iCategoryDao.findAll();
    }

    public boolean deleteById(Integer id) {
        return iCategoryDao.deleteById(id);
    }


    public Optional<Category> findById(Integer id) throws SQLException {
        return iCategoryDao.findById(id);
    }

}
