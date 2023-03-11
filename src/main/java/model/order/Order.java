package model.order;

import lombok.AllArgsConstructor;
import lombok.Getter;
import model.product.Product;

import java.util.Map;

@Getter
@AllArgsConstructor
public class Order {

    private String id;
    private Map<String, Product> products;
}
