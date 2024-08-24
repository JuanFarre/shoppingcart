package peludev.Ventas.dto;

import jakarta.persistence.Column;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductDTO {

    private String name;
    private String brand;
    private BigDecimal price;
    private Long carritoId;
}
