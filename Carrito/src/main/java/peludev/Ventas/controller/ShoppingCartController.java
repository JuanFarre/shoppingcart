package peludev.Ventas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import peludev.Ventas.model.ShoppingCart;
import peludev.Ventas.service.IShoppingCartService;

import java.util.List;

@RestController
@RequestMapping("/api/shopping-carts")
public class ShoppingCartController {

    @Autowired
    private IShoppingCartService shoppingCartService;

    @GetMapping
    public ResponseEntity<List<ShoppingCart>> getAllShoppingCarts() {
        List<ShoppingCart> shoppingCarts = shoppingCartService.getAllShoppingCart();
        return ResponseEntity.ok(shoppingCarts);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ShoppingCart> getShoppingCartById(@PathVariable Long id) {
        ShoppingCart shoppingCart = shoppingCartService.findById(id);
        if (shoppingCart != null) {
            return ResponseEntity.ok(shoppingCart);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/create")
    public void saveShoppingCart(ShoppingCart shoppingCart){
        shoppingCartService.save(shoppingCart);
    }



    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteShoppingCartById(@PathVariable Long id) {
        shoppingCartService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search/carrito/{id}")
    public ResponseEntity<?> findProductByIdShoppingCart(@PathVariable Long id){
        return ResponseEntity.ok(shoppingCartService.findProductByIdShoppingCart(id));
    }

}
