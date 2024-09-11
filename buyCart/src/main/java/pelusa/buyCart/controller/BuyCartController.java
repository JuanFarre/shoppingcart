package pelusa.buyCart.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pelusa.buyCart.model.BuyCart;
import pelusa.buyCart.model.ItemCarrito;
import pelusa.buyCart.service.IBuyCartService;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/buycart")
public class BuyCartController {

    @Autowired
    private IBuyCartService buyCartService;

    // Crear un nuevo buyCart
    @PostMapping("/create")
    public ResponseEntity<BuyCart> createBuyCart(@RequestBody BuyCart buyCart) {
        BuyCart nuevoBuyCart = buyCartService.createBuyCart(buyCart);
        return new ResponseEntity<>(nuevoBuyCart, HttpStatus.CREATED);
    }

    // Obtener un buyCart por ID
    @GetMapping("/{id}")
    public ResponseEntity<BuyCart> obtenerBuyCartPorId(@PathVariable Long id) {
        Optional<BuyCart> buyCart = buyCartService.findBuyCart(id);
        return buyCart.map(ResponseEntity::ok)
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Obtener todos los buyCarts
    @GetMapping("/all")
    public ResponseEntity<List<BuyCart>> obtenerTodosLosBuyCarts() {
        List<BuyCart> buyCarts = buyCartService.getAllBuyCarts();
        return new ResponseEntity<>(buyCarts, HttpStatus.OK);
    }

    // Eliminar un buyCart por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarBuyCart(@PathVariable Long id) {
        Optional<BuyCart> buyCart = buyCartService.findBuyCart(id);
        if (buyCart.isPresent()) {
            buyCartService.eliminarBuyCart(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id}/total")
    public ResponseEntity<BigDecimal> getTotalAmount(@PathVariable Long id) {
        BigDecimal totalAmount = buyCartService.obtenerMontoDelCarrito(id);
        return ResponseEntity.ok(totalAmount);
    }

    @GetMapping("/{id}/items")
    public ResponseEntity<List<ItemCarrito>> getItems(@PathVariable Long id) {
        List<ItemCarrito> items = buyCartService.obtenerListaDeProductosDelCarrito(id);
        return ResponseEntity.ok(items);
    }


}
