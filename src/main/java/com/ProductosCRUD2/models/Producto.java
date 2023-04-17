package com.ProductosCRUD2.models;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "productos")
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idProducto")
    private Long id;
    @Column(name = "titulo", nullable = false,length = 100)
    private String title;
    @Column(name = "precio",nullable = false)
    private double price;
    @Column(name = "descripcion")
    private String description;
    @Column(name = "imagen")
    private String image;
}