package peludev.Ventas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import peludev.Ventas.model.ItemCarrito;
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


    @DeleteMapping("/{carritoId}/productos/{itemId}")
    public ResponseEntity<?> quitarProducto(@PathVariable Long carritoId, @PathVariable Long itemId) {
        ShoppingCart carrito = shoppingCartService.quitarProducto(carritoId, itemId);
        return ResponseEntity.ok(carrito);
    }


    // Endpoint para agregar un producto al carrito
    @PostMapping("/{carritoId}/productos")
    public ResponseEntity<ShoppingCart> agregarProducto(
            @PathVariable Long carritoId,
            @RequestBody ItemCarrito itemCarrito) {
        try {
            ShoppingCart carritoActualizado = shoppingCartService.agregarProducto(carritoId, itemCarrito);
            if (carritoActualizado != null) {
                return new ResponseEntity<>(carritoActualizado, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            // Manejo de errores
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



}
