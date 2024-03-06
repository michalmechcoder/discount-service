package pl.mech.inpost.service.discount.strategy;

import pl.mech.inpost.domain.DiscountType;

import java.math.BigDecimal;

public interface DiscountStrategy {

    DiscountType getDiscountType();

    BigDecimal calculateProductDiscount(BigDecimal priceBeforeDiscount, Integer itemsCount);
}
