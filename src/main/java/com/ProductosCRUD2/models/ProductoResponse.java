package com.ProductosCRUD2.models;

import com.fasterxml.jackson.annotation.JsonProperty;


import java.util.List;

public class ProductoResponse {

    @JsonProperty("")
    private List<Producto> producto;

    public List<Producto> getProducto(){
        return producto;
    }
}
