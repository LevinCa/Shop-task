package model.product;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;
import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.*;

class ProductRepositoryTest {

    Product productOne;
    Product productTwo;
    ProductRepository productRepository;

    @BeforeEach
    void setUp() {
        productOne = new Product("1", "Keyboard");
        productTwo = new Product("2", "Mouse");
        productRepository = new ProductRepository(Map.of(productOne.getId(),productOne, productTwo.getId(), productTwo));
    }

    @Test
    void findById() {
        assertThat(productRepository.findById(productOne.getId()))
                .isEqualTo(productOne);
    }

    @Test
    void findById_throwsNoSuchElementException() {
        assertThatExceptionOfType(NoSuchElementException.class)
                .isThrownBy(() -> productRepository.findById("This ID does NOT Exist"));
    }
}