package pl.mech.inpost.web.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class DiscountResponse {

    private BigDecimal price;
}
