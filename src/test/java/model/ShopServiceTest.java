package model;

import model.order.Order;
import model.order.OrderRepository;
import model.product.Product;
import model.product.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.management.openmbean.KeyAlreadyExistsException;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.*;

class ShopServiceTest {

    Product productOne;
    Product productTwo;
    Product productThree;
    Order orderOne;
    Order orderTwo;
    Order orderThree;
    ProductRepository productRepository;
    OrderRepository orderRepository;
    ShopService shopService;

    @BeforeEach
    void setUp() {
        productOne = new Product("1", "Keyboard");
        productTwo = new Product("2", "Mouse");
        productThree = new Product("3", "Monitor");
        orderOne = new Order("1", new HashMap<>(Map.of(
                productOne.getId(), productOne
        )));
        orderTwo = new Order("2", new HashMap<>(Map.of(
                productTwo.getId(), productTwo,
                productThree.getId(), productThree
        )));
        orderThree = new Order("3", new HashMap<>(Map.of(
                productOne.getId(), productOne,
                productTwo.getId(), productTwo
        )));
        productRepository = new ProductRepository(new HashMap<>(Map.of(
                productOne.getId(), productOne,
                productTwo.getId(), productTwo
        )));
        orderRepository = new OrderRepository(new HashMap<>(Map.of(
                orderOne.getId(), orderOne,
                orderTwo.getId(), orderTwo
        )));
        shopService = new ShopService(productRepository, orderRepository);
    }

    @Test
    void findProductById() {
        assertThat(shopService.findProductById(productOne.getId()))
                .isEqualTo(productOne);
        assertThat(shopService.findProductById(productTwo.getId()))
                .isEqualTo(productTwo);
    }

    @Test
    void findProductById_throwsNoSuchElementException() {
        assertThatExceptionOfType(NoSuchElementException.class)
                .isThrownBy(() -> shopService.findProductById(orderThree.getId()));
    }

    @Test
    void listProducts() {
        assertThat(shopService.listProducts())
                .contains(productOne, productTwo)
                .doesNotContain(productThree)
                .doesNotContainNull();
    }

    @Test
    void addOrder() {
        shopService.addOrder(orderThree);
        assertThat(shopService.listOrders()).contains(orderThree);
    }

    @Test
    void addOrder_throwsKeyAlreadyExistsException() {
        assertThatExceptionOfType(KeyAlreadyExistsException.class)
                .isThrownBy(() -> shopService.addOrder(orderOne));
    }

    @Test
    void findOrderById() {
        assertThat(shopService.findOrderById(orderOne.getId()))
                .isEqualTo(orderOne);
        assertThat(shopService.findOrderById(orderTwo.getId()))
                .isEqualTo(orderTwo);
    }

    @Test
    void findOrderById_throwsNoSuchElementException() {
        assertThatExceptionOfType(NoSuchElementException.class)
                .isThrownBy(() -> shopService.findOrderById(orderThree.getId()));
    }

    @Test
    void listOrders() {
        assertThat(shopService.listOrders())
                .contains(orderOne, orderTwo)
                .doesNotContain(orderThree)
                .doesNotContainNull();
    }
}