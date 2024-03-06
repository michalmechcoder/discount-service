package pl.mech.inpost.service.product;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.mech.inpost.domain.Product;
import pl.mech.inpost.repository.ProductRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public List<Product> getAll() {
        return productRepository.findAll();
    }
}
