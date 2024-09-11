package peludev.Ventas.service;


import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import peludev.Ventas.http.client.CarritoClient;
import peludev.Ventas.dto.ProductDTO;
import peludev.Ventas.model.ItemCarrito;
import peludev.Ventas.model.ShoppingCart;
import peludev.Ventas.repository.IShoppingCartRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class ShoppingCartService implements IShoppingCartService{

    @Autowired
    private IShoppingCartRepository shoppingCartRepository;

    @Autowired
    private CarritoClient carritoClient;


    public ShoppingCart agregarProducto(Long carritoId, ItemCarrito item) {
        // Buscar el carrito de compras por su ID
        Optional<ShoppingCart> shoppingCartOptional = shoppingCartRepository.findById(carritoId);

        if (!shoppingCartOptional.isPresent()) {
            throw new RuntimeException("Carrito no encontrado con el ID: " + carritoId);
        }

        ShoppingCart shoppingCart = shoppingCartOptional.get();

        // Validar si el producto existe en el microservicio de productos
        try {
            ProductDTO product = carritoClient.getProductById(item.getProductoId());

            // Si el producto es válido, agregarlo al carrito
            item.setPrecio(product.getPrice());
            shoppingCart.getItems().add(item);

            // Actualizar el total del carrito
            actualizarTotal(shoppingCart);

            // Guardar y devolver el carrito actualizado
            return shoppingCartRepository.save(shoppingCart);
        } catch (FeignException.NotFound e) {
            throw new RuntimeException("Producto no encontrado con el ID: " + item.getProductoId());
        } catch (Exception e) {
            throw new RuntimeException("Error al agregar el producto al carrito", e);
        }
    }

    private void actualizarTotal(ShoppingCart shoppingCart) {
        BigDecimal total = shoppingCart.getItems().stream()
                .map(item -> item.getPrecio().multiply(BigDecimal.valueOf(item.getCantidad())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        shoppingCart.setTotalPrice(total);
    }

    public ShoppingCart quitarProducto(Long carritoId, Long itemId) {
        Optional<ShoppingCart> carritoOpt = shoppingCartRepository.findById(carritoId);

        if (carritoOpt.isPresent()) {
            ShoppingCart carrito = carritoOpt.get();
            carrito.getItems().removeIf(item -> item.getId().equals(itemId));
            actualizarTotal(carrito);
            return shoppingCartRepository.save(carrito);
        }

        return null; // o lanza una excepción
    }


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





}
