package model.order;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.management.openmbean.KeyAlreadyExistsException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.*;

class OrderRepositoryTest {

    private Order orderOne;
    private Order orderTwo;
    private Order orderThree;
    private OrderRepository orderRepository;


    @BeforeEach
    void setUp() {
        orderOne = new Order("1", Collections.emptyMap());
        orderTwo = new Order("2", Collections.emptyMap());
        orderThree = new Order("3", Collections.emptyMap());
        orderRepository = new OrderRepository(new HashMap<>(Map.of(
                orderOne.getId(), orderOne,
                orderTwo.getId(), orderTwo
        )));
    }

    @Test
    void findById() {
        assertThat(orderRepository.findById(orderOne.getId()))
                .isEqualTo(orderOne);
    }

    @Test
    void findById_thrownNoSuchElementException() {
        assertThatExceptionOfType(NoSuchElementException.class)
                .isThrownBy(() -> orderRepository.findById("ThisID does NOT exist"));
    }


    @Test
    void add() {
        orderRepository.add(orderThree);
        assertThat(orderRepository.getOrders())
                .containsExactlyInAnyOrderEntriesOf(Map.of(
                        orderOne.getId(), orderOne,
                        orderTwo.getId(), orderTwo,
                        orderThree.getId(), orderThree
                ));
    }

    @Test
    void add_throwsKeyAlreadyExistsException() {
        assertThatExceptionOfType(KeyAlreadyExistsException.class)
                .isThrownBy(() -> orderRepository.add(new Order("2", Collections.emptyMap())));
    }
}