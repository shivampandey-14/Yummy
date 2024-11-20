package com.shivam.esd_assignment.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "products")
public class Product
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long pid;

    @Column(name = "p_name")
    private String productName;

    @Column(name = "p_desc")
    private String productDesc;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "brand")
    private String brand;

    @Column(name = "c_id")
    private Long categoryId;

    @Column(name = "price")
    private double price;
}
