package dao.impl;

import dao.DBUtil;
import dao.ProductDao;
import entities.Product;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProductDaoImpl implements ProductDao {

    private final String SQL_INSERT = "INSERT INTO `products` (`name`, `price`, `created_at`)" +
            " VALUES (?, ?, ?)";
    private static final String SQL_UPDATE
            = "UPDATE EMP SET ENAME = '%s', JOB = '%s', HIREDATE = "
            + "TO_DATE('%s', 'yyyy/mm/dd'), SAL = %f WHERE EMPNO = %d";
    private static final String SQL_DELETE = "DELETE FROM EMP WHERE EMPNO = %d";
    private static final String SQL_SELECT_ALL = "SELECT * FROM EMP";
    private static final String SQL_SELECT_ONE = "SELECT * FROM EMP WHERE EMPNO = %d";

    private Connection conn;

    public ProductDaoImpl() {
        this.conn = DBUtil.getConnection();
    }

    @Override
    public boolean save(Product entity) throws SQLException {
        int rowCount = 0;
        int autoIncKeyFromApi = -1;
        try (PreparedStatement pstmt = conn.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS)) {
            conn.setAutoCommit(false);

            pstmt.setString(1, entity.getName());
            pstmt.setDouble(2, entity.getPrice());
            pstmt.setObject(3, entity.getCreatedAt());
            rowCount = pstmt.executeUpdate();
            ResultSet rs = pstmt.getGeneratedKeys();

            if (rs.next()) {
                autoIncKeyFromApi = rs.getInt(1);
            } else {
                System.out.println("NO KEYS WERE GENERATED.");
            }
            System.out.println("Key returned from getGeneratedKeys():"
                    + autoIncKeyFromApi);

            conn.commit();
            rs.close();

            System.out.println("ADDED ROW"); // UPDATED ROW
        } catch (SQLException ex) {
            try {
                conn.rollback();
            } catch (Throwable throwable) {
                System.out.println("Could not rollback transaction");
            }
        } finally {
            conn.setAutoCommit(true);
        }
        return rowCount > 0;
    }

    @Override
    public Optional<Product> findById(Integer integer) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Integer integer) {
        return false;
    }

    @Override
    public List<Product> findAll() {
        List<Product> products = new ArrayList<>();
        String query = "SELECT * FROM products";
        try (Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                Product product = new Product();
                product.setId(rs.getInt("id"));
                product.setName(rs.getString("name"));
                product.setPrice(rs.getDouble("price"));
                product.setCreatedAt(rs.getObject("created_at", LocalDate.class));
                products.add(product);
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }

    @Override
    public long count() {
        int rowCount = -1;
        try (Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery("SELECT COUNT(*) FROM products");
            rs.next();
            rowCount = rs.getInt(1);
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rowCount;
    }

    @Override
    public boolean deleteById(Integer id) {
        int noOfRecordsDeleted = 0;
        String query = "DELETE FROM products WHERE id = ?";
        try {
            PreparedStatement pstmt = conn.prepareStatement(query);
            noOfRecordsDeleted = pstmt.executeUpdate();
            System.out.println("Number of records deleted : " + noOfRecordsDeleted);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return noOfRecordsDeleted > 0;
    }

    @Override
    public void delete(Product entity) {

    }

    private void displayRow(String title, ResultSet rs) {
        System.out.println(title);
        System.out.println();
    }
}

