package pelusa.buyCart.service;

import pelusa.buyCart.model.BuyCart;
import pelusa.buyCart.model.ItemCarrito;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface IBuyCartService {

    BuyCart createBuyCart(BuyCart buyCart);

    Optional<BuyCart> findBuyCart(Long id);

    List<BuyCart> getAllBuyCarts();

    void eliminarBuyCart(Long id);

    public BigDecimal obtenerMontoDelCarrito(Long carritoId);

    public List<ItemCarrito> obtenerListaDeProductosDelCarrito(Long carritoId);

}
