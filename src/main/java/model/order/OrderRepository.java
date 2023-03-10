package model.order;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Map;

@Data
@AllArgsConstructor
public class OrderRepository {

    private Map<String,Order> orders;


    public Order findById(String id) {
        return null;
    }

    public void add(Order order) {

    }
}
