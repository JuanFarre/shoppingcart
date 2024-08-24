package peludev.Ventas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import peludev.Ventas.model.ShoppingCart;


@Repository
public interface IShoppingCartRepository extends JpaRepository<ShoppingCart, Long> {
}
