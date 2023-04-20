package com.ProductosCRUD2.controller;

import com.ProductosCRUD2.models.ProductoRequest;
import com.ProductosCRUD2.models.ProductoResponse;
import com.ProductosCRUD2.services.IProductServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RequestMapping("producto")
@RestController
public class ProductoController {

    private final IProductServices iProductServices;

    public ProductoController(IProductServices iProduct) {
        this.iProductServices = iProduct;
    }

    @PostMapping
    public ProductoResponse updateUser(@RequestBody ProductoRequest productoRequest) {
        return this.iProductServices.create(productoRequest);
    }
}
