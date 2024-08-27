package peludev.Ventas.service;


import java.util.NoSuchElementException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import peludev.Ventas.client.CarritoClient;
import peludev.Ventas.dto.ProductDTO;
import peludev.Ventas.http.response.ProductByShoppingCartResponse;
import peludev.Ventas.model.ShoppingCart;
import peludev.Ventas.repository.IShoppingCartRepository;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ShoppingCartService implements IShoppingCartService{

    @Autowired
    private IShoppingCartRepository shoppingCartRepository;

    @Autowired
    private CarritoClient carritoClient;



    @Override
    public List<ShoppingCart> getAllShoppingCart() {

        List<ShoppingCart> shoppingCartList = shoppingCartRepository.findAll();

        return shoppingCartList;
    }

    @Override
    public ShoppingCart findById(Long id) {
        return shoppingCartRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteById(Long id) {

        shoppingCartRepository.deleteById(id);

    }

    @Override
    public void save(ShoppingCart shoppingCart) {

        shoppingCartRepository.save(shoppingCart);

    }


    @Override
    public ProductByShoppingCartResponse findProductByIdShoppingCart(Long id) {
        ShoppingCart shoppingCart = shoppingCartRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Carrito de compras no encontrado"));

        // Obtener los productos asociados al carrito
        List<ProductDTO> productDTOList = carritoClient.findAllByIdCarrito(id);

        // Crear la respuesta
        ProductByShoppingCartResponse response = ProductByShoppingCartResponse.builder()
                .productDTOList(productDTOList)
                .build();

        // Calcular el precio total sumando los precios de los productos
        BigDecimal calculatedTotalPrice = response.calculateTotalPrice();

        // Actualizar el total_price del carrito y guardarlo en la base de datos
        shoppingCart.setTotalPrice(calculatedTotalPrice);
        shoppingCartRepository.save(shoppingCart);

        return response;
    }

}
