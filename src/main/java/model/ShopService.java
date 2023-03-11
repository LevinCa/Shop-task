package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import model.order.Order;
import model.order.OrderRepository;
import model.product.Product;
import model.product.ProductRepository;

import javax.management.openmbean.KeyAlreadyExistsException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
public class ShopService {

    private ProductRepository productRepository;
    private OrderRepository orderRepository;


    public Product findProductById(String id) throws NoSuchElementException {
        return this.productRepository.findById(id);
    }

    public List<Product> listProducts() {
        return this.productRepository.getProducts().values()
                .stream().filter(Objects::nonNull).collect(Collectors.toList());
    }


    public void addOrder(Order order) throws KeyAlreadyExistsException {
        this.orderRepository.add(order);
    }

    public Order findOrderById(String id) throws NoSuchElementException {
        return this.orderRepository.findById(id);
    }

    public List<Order> listOrders() {
        return this.orderRepository.getOrders().values()
                .stream().filter(Objects::nonNull).collect(Collectors.toList());
    }
}
