package com.airsets.demo.DAO;

import com.airsets.demo.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Component
public class ProductDAOImpl implements ProductDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ProductDAOImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public List<Product> getAllProducts() {
        String sql = "SELECT * FROM Product";

        return jdbcTemplate.query(sql, new RowMapper<Product>() {
            @Override
            public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
                Product product = new Product();
                product.setProduct_Id(rs.getInt("id"));
                product.setProduct_Name(rs.getString("name"));
                product.setProduct_Dec(rs.getString("product_dec"));
                product.setPrice(rs.getDouble("price"));
                product.setStock(rs.getInt("stock"));
                return product;
            }
        });
    }

    @Override
    public boolean createProduct(Product product) {
        String sql = "INSERT INTO product (name, product_dec, price, stock) VALUES (?, ?, ?, ?)";
        int rowsAffected = jdbcTemplate.update(sql, product.getProduct_Name(), product.getProduct_Dec(),
                product.getPrice(), product.getStock());
        return rowsAffected > 0;
    }

    @Override
    public Product getProductById(int id) {
        String sql = "SELECT * FROM Product WHERE product_id = ?";

        return jdbcTemplate.queryForObject(sql, new Object[]{id}, new RowMapper<Product>() {
            @Override
            public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
                Product product = new Product();
                product.setProduct_Id(rs.getInt("id"));
                product.setProduct_Name(rs.getString("name"));
                product.setProduct_Dec(rs.getString("product_dec"));
                product.setPrice(rs.getDouble("price"));
                product.setStock(rs.getInt("stock"));
                return product;
            }
        });
    }

    @Override
    public boolean updateProduct(Product product) {
        String sql = "UPDATE product SET name = ?, product_dec = ?, price = ?, stock = ? WHERE id = ?";
        int rowsAffected = jdbcTemplate.update(sql, product.getProduct_Name(), product.getProduct_Dec(),
                product.getPrice(), product.getStock(), product.getProduct_Id());
        return rowsAffected > 0;
    }

    @Override
    public boolean removeProduct(Product product) {
        String sql = "DELETE FROM product WHERE id = ?";
        int rowsAffected = jdbcTemplate.update(sql, product.getProduct_Id());
        return rowsAffected > 0;
    }
}
