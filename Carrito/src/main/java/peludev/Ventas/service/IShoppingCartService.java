package peludev.Ventas.service;

import peludev.Ventas.http.response.ProductByShoppingCartResponse;
import peludev.Ventas.model.ShoppingCart;

import java.math.BigDecimal;
import java.util.List;

public interface IShoppingCartService {

    public List<ShoppingCart> getAllShoppingCart();

    public ShoppingCart findById(Long id);

    public void deleteById(Long id);

    public void save(ShoppingCart shoppingCart);

    public ProductByShoppingCartResponse findProductByIdShoppingCart(Long id);


}
