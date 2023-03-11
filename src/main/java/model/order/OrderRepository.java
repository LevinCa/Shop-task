package model.order;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.management.openmbean.KeyAlreadyExistsException;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.stream.Stream;

@Data
@AllArgsConstructor
public class OrderRepository {

    private Map<String,Order> orders;


    public Order findById(String id) throws NoSuchElementException {
        Stream.of(this.orders.keySet())
                .filter(o -> o.contains(id))
                .findFirst()
                .orElseThrow(NoSuchElementException::new);
        return this.orders.get(id);
    }

    public void add(Order order) throws KeyAlreadyExistsException {
        if (this.orders.containsKey(order.getId())) {
            throw new KeyAlreadyExistsException();
        }
        this.orders.put(order.getId(), order);
    }
}
