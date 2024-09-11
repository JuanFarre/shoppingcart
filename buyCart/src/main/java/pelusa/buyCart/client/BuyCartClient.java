package pelusa.buyCart.client;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import pelusa.buyCart.model.ShoppingCart;

@FeignClient(name = "msvc-carrito", url = "localhost:8080/api/shopping-carts")
public interface BuyCartClient {

    @GetMapping("/{id}")
    ShoppingCart getShoppingCartById(@PathVariable Long id);




}
