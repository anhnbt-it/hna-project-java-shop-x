package dao.impl;

import dao.DBUtil;
import dao.ICategoryDao;
import entity.Category;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ICategoryDaoImpl implements ICategoryDao {

    private Connection conn;

    public ICategoryDaoImpl() {
        this.conn = DBUtil.getConnection();
    }

    @Override
    public int save(Category entity) throws SQLException {
        int rowCount = -1;
        String sql = "INSERT INTO tbl_categories (name) VALUES (?)";
        if (entity.getId() != null) {
            sql = "UPDATE tbl_categories SET name = ? WHERE id = ?";
        }
        try (PreparedStatement pstmt = conn.prepareStatement(sql, Statement.NO_GENERATED_KEYS)) {
            conn.setAutoCommit(false);
            pstmt.setString(1, entity.getName());
            if (entity.getId() != null) {
                pstmt.setInt(2, entity.getId());
            }
            rowCount = pstmt.executeUpdate();
            pstmt.close();
            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            conn.rollback();
        } finally {
            conn.setAutoCommit(true);
        }
        return rowCount;
    }

    @Override
    public Optional<Category> findById(Integer id) throws SQLException {
        Category category = new Category();
        String sql = "SELECT * FROM tbl_categories WHERE id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                category.setId(rs.getInt("id"));
                category.setName(rs.getString("name"));
            }
            pstmt.close();
            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            conn.rollback();
        } finally {
            conn.setAutoCommit(true);
        }
        return Optional.of(category);
    }

    @Override
    public boolean existsById(Integer integer) {
        return false;
    }

    @Override
    public List<Category> findAll() throws SQLException {
        List<Category> categories = new ArrayList<>();
        String sql = "SELECT * FROM tbl_categories ORDER BY id DESC";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            conn.setAutoCommit(false);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Category category = new Category();
                category.setId(rs.getInt("id"));
                category.setName(rs.getString("name"));
                categories.add(category);
            }
            pstmt.close();
            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            conn.rollback();
        } finally {
            conn.setAutoCommit(true);
        }
        return categories;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public boolean deleteById(Integer integer) {
        return false;
    }

    @Override
    public void delete(Category entity) {

    }
}
