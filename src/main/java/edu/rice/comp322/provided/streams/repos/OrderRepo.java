package edu.rice.comp322.provided.streams.repos;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import edu.rice.comp322.provided.streams.models.Order;

@Repository
public interface OrderRepo extends CrudRepository<Order, Long> {

    List<Order> findAll();
}
