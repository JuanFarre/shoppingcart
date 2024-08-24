package peludev.Ventas.http.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import peludev.Ventas.dto.ProductDTO;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductByShoppingCartResponse {

    private BigDecimal totalPrice;
    private List<ProductDTO> productDTOList;

    // Constructor, getters, and setters

    public BigDecimal calculateTotalPrice() {
       
        totalPrice = BigDecimal.ZERO;


        for (ProductDTO product : productDTOList) {
            totalPrice = totalPrice.add(product.getPrice());
        }

        return totalPrice;
    }
}