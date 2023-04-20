package com.ProductosCRUD2.services;

import com.ProductosCRUD2.models.Producto;
import com.ProductosCRUD2.models.ProductoRequest;
import com.ProductosCRUD2.models.ProductoResponse;
import com.ProductosCRUD2.repository.ProductoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Slf4j
@Service
public class ProductServicesImp extends AbstractClient implements IProductServices{

    @Autowired
    private ProductoRepository productoRepository;

    public ProductServicesImp(RestTemplate productoRepository) {
        super(productoRepository);

    }

    @Override
    public ProductoResponse create(ProductoRequest productoRequest) {
        String uri = baseUrl + "/products";
        System.out.println(productoRequest);
        try {
            ResponseEntity<ProductoResponse> response = restTemplate.getForEntity(uri, ProductoResponse.class);
            if (response.getStatusCode().is2xxSuccessful()) {
                System.out.println(Objects.requireNonNull(response.getBody()).getProducto());
                List<Producto> products = Objects.requireNonNull(response.getBody()).getProducto();
                productoRepository.saveAll(products);
                return response.getBody();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        //log.error("Error in user creation - httpStatus was: {}", response.getStatusCode());
        throw new RuntimeException("Error");
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
