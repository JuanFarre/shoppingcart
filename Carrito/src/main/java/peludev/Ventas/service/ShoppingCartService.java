package peludev.Ventas.service;

import org.springframework.beans.factory.annotation.Autowired;
import peludev.Ventas.client.CarritoClient;
import peludev.Ventas.dto.ProductDTO;
import peludev.Ventas.http.response.ProductByShoppingCartResponse;
import peludev.Ventas.model.ShoppingCart;
import peludev.Ventas.repository.IShoppingCartRepository;

import java.math.BigDecimal;
import java.util.List;

public class ShoppingCartService implements IShoppingCartService{

    @Autowired
    private IShoppingCartRepository shoppingCartRepository;

    @Autowired
    private CarritoClient carritoClient;

    @Autowired


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

    }

    @Override
    public ProductByShoppingCartResponse findProductByIdShoppingCart(Long id) {

        //consultar el carrito
        ShoppingCart shoppingCart = shoppingCartRepository.findById(id).orElse(new ShoppingCart());
        // obtener los productos del carrito

        List<ProductDTO> productDTOList = carritoClient.findAllByIdCarrito(id);


        return ProductByShoppingCartResponse.builder()
                .totalPrice(shoppingCart.getTotalPrice())
                .productDTOList(productDTOList)
                .build();
    }

}
