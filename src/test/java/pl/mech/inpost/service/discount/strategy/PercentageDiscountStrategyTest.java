package pl.mech.inpost.service.discount.strategy;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pl.mech.inpost.config.DiscountProperties;

import java.math.BigDecimal;
import java.util.List;

import static java.math.BigDecimal.valueOf;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.comparesEqualTo;

class PercentageDiscountStrategyTest {

    public static PercentageDiscountStrategy percentageDiscountStrategy;

    @BeforeAll
    public static void createDiscountProperties() {
        DiscountProperties discountProperties = new DiscountProperties();

        discountProperties.setPercentagePolicySteps(
                List.of(
                        new DiscountProperties.PolicyStep(10, valueOf(0.03)),
                        new DiscountProperties.PolicyStep(50, valueOf(0.05)),
                        new DiscountProperties.PolicyStep(100, valueOf(0.10))
                )
        );

        percentageDiscountStrategy = new PercentageDiscountStrategy(discountProperties);
    }

    @Test
    @DisplayName("Small amount of items means ZERO discount")
    void testCalculateZeroDiscount() {
        BigDecimal discount = percentageDiscountStrategy.calculateProductDiscount(valueOf(12.30), 9);

        assertThat(discount, comparesEqualTo(BigDecimal.ZERO));
    }

    @Test
    @DisplayName("Few items means first level discount")
    void testCalculateFirstLevelDiscount() {
        BigDecimal discount = percentageDiscountStrategy.calculateProductDiscount(valueOf(12.30), 10);

        assertThat(discount, comparesEqualTo(valueOf(0.36)));
    }

    @Test
    @DisplayName("A lot of items means bigger discount")
    void testCalculateBiggestDiscount() {
        BigDecimal discount = percentageDiscountStrategy.calculateProductDiscount(valueOf(12.30), 155);

        assertThat(discount, comparesEqualTo(valueOf(1.23)));
    }
}