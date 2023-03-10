package model.product;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Map;

@Data
@AllArgsConstructor
public class ProductRepository {

    private Map<String,Product> products;


    public Product findById(String name) {
        return null;
    }
}
