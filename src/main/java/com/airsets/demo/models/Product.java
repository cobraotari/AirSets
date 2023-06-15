package com.airsets.demo.models;

import lombok.Data;

@Data
public class Product {
    private int product_Id;
    private String product_Name;
    private String product_Dec;
    private double price;
    private int stock;
}
