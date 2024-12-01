
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Data
@AllArgsConstructor
public class Product {
    private String nameProduct;
    private BigDecimal price;

}
