package pl.mech.inpost.service.discount;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import pl.mech.inpost.DiscountServiceApplication;
import pl.mech.inpost.config.DiscountProperties;
import pl.mech.inpost.domain.DiscountType;
import pl.mech.inpost.domain.Product;
import pl.mech.inpost.repository.ProductRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static java.math.BigDecimal.valueOf;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.comparesEqualTo;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = DiscountServiceApplication.class)
class DiscountServiceTest {

    @MockBean
    private DiscountProperties discountProperties;

    @MockBean
    private ProductRepository productRepository;

    @Autowired
    private DiscountService discountService;

    @Test
    @DisplayName("Discount should be calculated using amount policy")
    void calculateProductPriceWithAmount() {
        // given
        UUID productId = UUID.randomUUID();

        when(discountProperties.getPolicy()).thenReturn(DiscountType.AMOUNT);
        when(discountProperties.getAmountPolicySteps()).thenReturn(
                List.of(
                        new DiscountProperties.PolicyStep(10, valueOf(2)),
                        new DiscountProperties.PolicyStep(50, valueOf(20)),
                        new DiscountProperties.PolicyStep(100, valueOf(30))
                )
        );
        when(productRepository.findById(any())).thenReturn(Optional.of(new Product(productId, "Some product", valueOf(12.30))));

        // when
        BigDecimal productPrice = discountService.calculateProductPrice(productId, 10);

        // then
        assertThat(productPrice, comparesEqualTo(valueOf(121)));
    }

    @Test
    @DisplayName("Discount should be calculated using percentage policy")
    void calculateProductPriceWithPercentage() {
        // given
        UUID productId = UUID.randomUUID();

        when(discountProperties.getPolicy()).thenReturn(DiscountType.PERCENTAGE);
        when(discountProperties.getPercentagePolicySteps()).thenReturn(
                List.of(
                        new DiscountProperties.PolicyStep(10, valueOf(0.03)),
                        new DiscountProperties.PolicyStep(50, valueOf(0.05)),
                        new DiscountProperties.PolicyStep(100, valueOf(0.10))
                )
        );
        when(productRepository.findById(any())).thenReturn(Optional.of(new Product(productId, "Some product", valueOf(12.30))));

        // when
        BigDecimal productPrice = discountService.calculateProductPrice(productId, 10);

        // then
        assertThat(productPrice, comparesEqualTo(valueOf(119.31)));
    }
}