package com.airsets.demo.DAO;

import com.airsets.demo.models.Order;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderDAOImpl implements OrderDao {

    private final JdbcTemplate jdbcTemplate;

    public OrderDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public boolean createOrder(Order order) {
        String sql = "INSERT INTO orders (id, email, total_amount, order_date) VALUES (?, ?, ?, ?)";
        int rowsAffected = jdbcTemplate.update(sql, order.getId(), order.getEmail(), order.getTotalAmount(),
                order.getOrderDate());
        return rowsAffected > 0;
    }

    @Override
    public Order getOrderById(int id) {
        String sql = "SELECT * FROM orders WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{id}, new OrderMapper());
    }

    @Override
    public boolean removeOrder(Order order) {
        String sql = "DELETE FROM orders WHERE id = ?";
        int rowsAffected = jdbcTemplate.update(sql, order.getId());
        return rowsAffected > 0;
    }

    private static class OrderMapper implements RowMapper<Order> {
        @Override
        public Order mapRow(ResultSet rs, int rowNum) throws SQLException {
            Order order = new Order();
            order.setId(rs.getInt("id"));
            order.setEmail(rs.getString("email"));
            order.setTotalAmount(rs.getInt("total_amount"));
            order.setOrderDate(rs.getTimestamp("order_date").toLocalDateTime());
            return order;
        }
    }
}
