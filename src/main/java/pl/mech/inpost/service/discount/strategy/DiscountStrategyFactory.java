package pl.mech.inpost.service.discount.strategy;

import org.springframework.stereotype.Component;
import pl.mech.inpost.domain.DiscountType;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.lang.String.format;
import static java.util.Optional.ofNullable;

@Component
public class DiscountStrategyFactory {

    private final Map<DiscountType, DiscountStrategy> discountTypeToStrategyMap = new HashMap<>();

    public DiscountStrategyFactory(List<DiscountStrategy> discountStrategies) {
        discountStrategies.forEach(discountStrategy -> discountTypeToStrategyMap.put(discountStrategy.getDiscountType(), discountStrategy));
    }

    public DiscountStrategy getDiscountStrategy(DiscountType discountType) {
        return ofNullable(discountTypeToStrategyMap.get(discountType))
                .orElseThrow(() -> new IllegalStateException(format("Discount strategy for %s not found!", discountType)));
    }
}
