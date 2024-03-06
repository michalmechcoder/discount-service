package pl.mech.inpost.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.mech.inpost.domain.Product;

import java.util.UUID;

public interface ProductRepository extends JpaRepository<Product, UUID> {
}
