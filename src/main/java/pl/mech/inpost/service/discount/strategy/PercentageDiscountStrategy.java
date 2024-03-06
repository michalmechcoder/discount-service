package pl.mech.inpost.service.discount.strategy;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.mech.inpost.config.DiscountProperties;
import pl.mech.inpost.domain.DiscountType;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Component
@RequiredArgsConstructor
public class PercentageDiscountStrategy implements DiscountStrategy {

    private final DiscountProperties discountProperties;

    @Override
    public DiscountType getDiscountType() {
        return DiscountType.PERCENTAGE;
    }

    /**
     * User gets a percentage discount – the more items ordered;
     * the bigger the percentage discount is. (e.g., 10 items, 3% off, 50 items, 5% off etc.)
     */
    @Override
    public BigDecimal calculateProductDiscount(BigDecimal priceBeforeDiscount, Integer itemsCount) {
        BigDecimal discount = BigDecimal.ZERO;

        for (DiscountProperties.PolicyStep policyStep : discountProperties.getPercentagePolicySteps()) {
            if (itemsCount >= policyStep.getItems()) {
                discount = policyStep.getValue();
            }
        }

        return priceBeforeDiscount
                .multiply(discount)
                .setScale(2, RoundingMode.FLOOR);
    }
}
