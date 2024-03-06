package pl.mech.inpost.service.discount.strategy;

import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import pl.mech.inpost.DiscountServiceApplication;
import pl.mech.inpost.domain.DiscountType;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = DiscountServiceApplication.class)
class DiscountStrategyFactoryIT {

    @Autowired
    private DiscountStrategyFactory discountStrategyFactory;

    @ParameterizedTest
    @MethodSource("testParameters")
    void shouldPickCorrectDiscountStrategy(DiscountType DiscountType) {
        // when
        final DiscountStrategy discountStrategy = discountStrategyFactory.getDiscountStrategy(DiscountType);

        // then
        assertEquals(discountStrategy.getDiscountType(), DiscountType);
    }

    private static Stream<Arguments> testParameters() {
        return Stream.of(
                Arguments.of(DiscountType.AMOUNT),
                Arguments.of(DiscountType.PERCENTAGE)
        );
    }
}