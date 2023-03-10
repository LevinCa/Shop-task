package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import model.order.Order;
import model.order.OrderRepository;
import model.product.Product;
import model.product.ProductRepository;

@Data
@AllArgsConstructor
public class ShopService {

    private ProductRepository productRepository;
    private OrderRepository orderRepository;


    public Product findProductById(String id){
        return null;
    }

    public void listProducts() {

    }


    public void addOrder(Order order) {

    }

    public Order findOrderById(String id) {
        return null;
    }

    public void listOrders() {

    }
}
