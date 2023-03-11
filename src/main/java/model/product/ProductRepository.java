package model.product;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Map;
import java.util.NoSuchElementException;
import java.util.stream.Stream;


@Getter
@AllArgsConstructor
public class ProductRepository {

    private Map<String,Product> products;


    public Product findById(String id) throws NoSuchElementException {
        Stream.of(this.products.keySet())
                .filter(p -> p.contains(id))
                .findFirst()
                .orElseThrow(NoSuchElementException::new);
        return this.products.get(id);
    }
}
