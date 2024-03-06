package pl.mech.inpost.service.discount.strategy;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import pl.mech.inpost.config.DiscountProperties;
import pl.mech.inpost.domain.DiscountType;

import java.math.BigDecimal;

@Slf4j
@Component
@RequiredArgsConstructor
public class AmountDiscountStrategy implements DiscountStrategy {

    private final DiscountProperties discountProperties;

    @Override
    public DiscountType getDiscountType() {
        return DiscountType.AMOUNT;
    }

    /**
     * The more pieces of the product are ordered, the bigger the discount is (e.g., 10 items – 2 USD off, 100 items – 5 USD off etc.).
     */
    @Override
    public BigDecimal calculateProductDiscount(BigDecimal priceBeforeDiscount, Integer itemsCount) {
        BigDecimal discount = BigDecimal.ZERO;

        for (DiscountProperties.PolicyStep policyStep : discountProperties.getAmountPolicySteps()) {
            if (itemsCount >= policyStep.getItems()) {
                discount = policyStep.getValue();
            }
        }

        log.info("Discount (based on AMOUNT) value: {}", discount);

        return discount;
    }
}
