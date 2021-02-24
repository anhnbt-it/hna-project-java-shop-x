package dao;

import dao.impl.ICategoryDaoImpl;
import dao.impl.ProductDaoImpl;

public class DAOFactory {

    private static DAOFactory daoFactory;

    static {
        daoFactory = new DAOFactory();
    }

    private DAOFactory() {}

    public static DAOFactory getInstance() {
        return daoFactory;
    }

    public IProductDao getProductDAO() {
        return new ProductDaoImpl();
    }

    public ICategoryDao getCategoryDao() {
        return new ICategoryDaoImpl();
    }
}
