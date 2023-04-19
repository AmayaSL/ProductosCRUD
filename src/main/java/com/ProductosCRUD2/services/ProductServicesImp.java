package com.ProductosCRUD2.services;

import com.ProductosCRUD2.models.Producto;
import com.ProductosCRUD2.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class ProductServicesImp implements IProductServices{


    private final ProductoRepository productoRepository;

    public ProductServicesImp(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    @Override
    public List<Producto> getAllProduct() {
        return productoRepository.findAll();
    }

    @Override
    public Optional<Producto> getProductId(Long idProduct) {
        return Optional.ofNullable(productoRepository.findById(idProduct)).orElse(null);
    }

    @Override
    public Producto saveProducto(Producto producto) {
        return productoRepository.save(producto);
    }

    @Override
    public Producto updateProducto(Producto producto, Long idProducto) {
        return productoRepository.findById(idProducto).map(producto1 -> {
            producto1.setDescription((producto.getDescription() != null)? producto.getDescription():producto1.getDescription());
            producto1.setImage((producto.getImage() != null)? producto.getImage():producto1.getImage());
            producto1.setTitle((producto.getTitle() != null)? producto.getTitle():producto1.getTitle());
            producto1.setPrice((producto.getPrice() < 0 )? producto.getPrice():producto1.getPrice());
            return productoRepository.save(producto1);
        }).orElse(null);
    }

    @Override
    public Boolean deleteProducto(Long idProducto) {
        if (productoRepository.findById(idProducto).isPresent()){
            productoRepository.deleteById(idProducto);
            return true;
        }
        return false;
    }
}
