package peludev.Ventas.client;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import peludev.Ventas.dto.ProductDTO;

import java.util.List;

@FeignClient(name = "msvc-product", url = "localhost:8080/api/products")
public interface CarritoClient {


    @GetMapping("/{id}")
    ProductDTO getProductById(@PathVariable Long id);
}
