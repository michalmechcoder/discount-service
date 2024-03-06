package pl.mech.inpost.web.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
public class ProductResponse {

    @Data
    public static class Product {
        private UUID id;

        private String name;

        private BigDecimal price;
    }

    private List<Product> products;
}
