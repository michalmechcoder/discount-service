package pl.mech.inpost.service.discount;

import jakarta.annotation.Nullable;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.mech.inpost.config.DiscountProperties;
import pl.mech.inpost.domain.Product;
import pl.mech.inpost.repository.ProductRepository;
import pl.mech.inpost.service.discount.strategy.DiscountStrategyFactory;

import java.math.BigDecimal;
import java.util.UUID;

import static java.lang.String.format;
import static java.util.Optional.ofNullable;

@Service
@RequiredArgsConstructor
public class DiscountService {

    private final DiscountProperties discountProperties;

    private final DiscountStrategyFactory discountStrategyFactory;

    private final ProductRepository productRepository;

    public BigDecimal calculateProductPrice(@NotNull UUID productId, @Nullable Integer itemsCount) {
        itemsCount = ofNullable(itemsCount).orElse(1);

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new EntityNotFoundException(format("Product with id=%s not exists", productId)));

        BigDecimal priceBeforeDiscount = product.getPrice()
                .multiply(BigDecimal.valueOf(itemsCount));

        BigDecimal discount = discountStrategyFactory
                .getDiscountStrategy(discountProperties.getPolicy())
                .calculateProductDiscount(priceBeforeDiscount, itemsCount);

        return priceBeforeDiscount.subtract(discount);
    }
}
