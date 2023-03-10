package model.order;

import lombok.AllArgsConstructor;
import lombok.Data;
import model.product.Product;

import java.util.Map;

@Data
@AllArgsConstructor
public class Order {

    private String id;
    private Map<String, Product> products;
}
