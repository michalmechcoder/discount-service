package pl.mech.inpost.web;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;
import pl.mech.inpost.domain.Product;
import pl.mech.inpost.service.discount.DiscountService;
import pl.mech.inpost.service.product.ProductService;
import pl.mech.inpost.web.response.DiscountResponse;
import pl.mech.inpost.web.response.ProductResponse;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    private final DiscountService discountService;

    private final ModelMapper modelMapper;

    @Operation(summary = "Get all products from database")
    @GetMapping
    public ProductResponse getAllItems() {
        List<Product> products = productService.getAll();

        List<ProductResponse.Product> responseProducts = mapList(products, ProductResponse.Product.class);

        return new ProductResponse(responseProducts);
    }

    @Operation(summary = "Calculating a given product's price based on the number of products ordered")
    @GetMapping("{productId}/price")
    public DiscountResponse getProductPrice(
            @PathVariable UUID productId,
            @RequestParam(required = false) @Min(1) Integer itemsCount
    ) {
        BigDecimal priceWithDiscount = discountService.calculateProductPrice(productId, itemsCount);

        return new DiscountResponse(priceWithDiscount);
    }

    /**
     * In real life this method shouldn't be here. I used it only once... ;)
     */
    private <S, T> List<T> mapList(List<S> source, Class<T> targetClass) {
        return source.stream()
                .map(element -> modelMapper.map(element, targetClass))
                .collect(Collectors.toList());
    }
}
