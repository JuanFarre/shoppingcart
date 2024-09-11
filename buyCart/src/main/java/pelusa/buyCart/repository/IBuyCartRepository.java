package pelusa.buyCart.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pelusa.buyCart.model.BuyCart;

@Repository
public interface IBuyCartRepository extends JpaRepository<BuyCart, Long> {
}
