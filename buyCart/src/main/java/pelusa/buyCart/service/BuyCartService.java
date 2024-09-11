package pelusa.buyCart.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pelusa.buyCart.client.BuyCartClient;
import pelusa.buyCart.model.BuyCart;
import pelusa.buyCart.model.ItemCarrito;
import pelusa.buyCart.model.ShoppingCart;
import pelusa.buyCart.repository.IBuyCartRepository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class BuyCartService implements IBuyCartService{

    @Autowired
    private IBuyCartRepository buyCartRepository;

    @Autowired
    private BuyCartClient buyCartClient;

    @Override
    public BuyCart createBuyCart(BuyCart buyCart) {
        // Obtener el carrito de compras por ID
        ShoppingCart shoppingCart = buyCartClient.getShoppingCartById(buyCart.getCarritoId());

        // Si el carrito no existe, lanzar una excepción personalizada
        if (shoppingCart == null) {
            throw new NoSuchElementException("Carrito de compras no encontrado con ID: " + buyCart.getCarritoId());
        }

        // Asignar la fecha actual
        buyCart.setFecha(LocalDateTime.now());

        // Asignar el monto total desde el carrito
        buyCart.setMontoTotal(shoppingCart.getTotalPrice() != null ? shoppingCart.getTotalPrice() : BigDecimal.ZERO);

        // Asignar los ítems del carrito al BuyCart
        buyCart.setItems(shoppingCart.getItems() != null ? shoppingCart.getItems() : Collections.emptyList());

        // Guardar el BuyCart en la base de datos
        return buyCartRepository.save(buyCart);
    }

    @Override
    public Optional<BuyCart> findBuyCart(Long id) {
        return buyCartRepository.findById(id);
    }

    @Override
    public List<BuyCart> getAllBuyCarts() {

        return buyCartRepository.findAll();
    }

    @Override
    public void eliminarBuyCart(Long id) {
        buyCartRepository.deleteById(id);
    }

    @Override
    public BigDecimal obtenerMontoDelCarrito(Long carritoId) {
        ShoppingCart shoppingCart = buyCartClient.getShoppingCartById(carritoId);
        return shoppingCart != null ? (shoppingCart.getTotalPrice() != null ? shoppingCart.getTotalPrice() : BigDecimal.ZERO) : BigDecimal.ZERO;
    }

    @Override
    public List<ItemCarrito> obtenerListaDeProductosDelCarrito(Long carritoId) {
        ShoppingCart shoppingCart = buyCartClient.getShoppingCartById(carritoId);
        return shoppingCart != null ? (shoppingCart.getItems() != null ? shoppingCart.getItems() : Collections.emptyList()) : Collections.emptyList();
    }
}
