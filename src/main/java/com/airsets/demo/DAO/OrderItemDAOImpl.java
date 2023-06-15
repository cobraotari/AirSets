package com.airsets.demo.DAO;

import com.airsets.demo.models.OrderItem;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderItemDAOImpl implements OrderItemDao {

    private final JdbcTemplate jdbcTemplate;

    public OrderItemDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public boolean createOrderItem(OrderItem orderItem) {
        String sql = "INSERT INTO order_items (id, order_id, product_id, quantity, price) VALUES (?, ?, ?, ?, ?)";
        int rowsAffected = jdbcTemplate.update(sql, orderItem.getId(), orderItem.getOrderId(),
                orderItem.getProductId(), orderItem.getQuantity(), orderItem.getPrice());
        return rowsAffected > 0;
    }

    @Override
    public OrderItem getOrderItemById(int id) {
        String sql = "SELECT * FROM order_items WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{id}, new OrderItemMapper());
    }

    @Override
    public boolean updateOrderItem(OrderItem orderItem) {
        String sql = "UPDATE order_items SET order_id = ?, product_id = ?, quantity = ?, price = ? WHERE id = ?";
        int rowsAffected = jdbcTemplate.update(sql, orderItem.getOrderId(), orderItem.getProductId(),
                orderItem.getQuantity(), orderItem.getPrice(), orderItem.getId());
        return rowsAffected > 0;
    }

    @Override
    public boolean removeOrderItem(OrderItem orderItem) {
        String sql = "DELETE FROM order_items WHERE id = ?";
        int rowsAffected = jdbcTemplate.update(sql, orderItem.getId());
        return rowsAffected > 0;
    }

    private static class OrderItemMapper implements RowMapper<OrderItem> {
        @Override
        public OrderItem mapRow(ResultSet rs, int rowNum) throws SQLException {
            OrderItem orderItem = new OrderItem();
            orderItem.setId(rs.getInt("id"));
            orderItem.setOrderId(rs.getInt("order_id"));
            orderItem.setProductId(rs.getInt("product_id"));
            orderItem.setQuantity(rs.getInt("quantity"));
            orderItem.setPrice(rs.getInt("price"));
            return orderItem;
        }
    }
}
