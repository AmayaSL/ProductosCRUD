package com.ProductosCRUD2.services;

import com.ProductosCRUD2.models.Producto;
import com.ProductosCRUD2.models.ProductoRequest;
import com.ProductosCRUD2.models.ProductoResponse;

import java.util.List;
import java.util.Optional;

public interface IProductServices {
    ProductoResponse create(ProductoRequest productoRequest);

    List<Producto> getAllProduct();
    Optional<Producto> getProductId(Long idProduct);
    Producto saveProducto(Producto producto);
    Producto updateProducto(Producto producto, Long idProducto);
    Boolean deleteProducto(Long idProducto);
}
