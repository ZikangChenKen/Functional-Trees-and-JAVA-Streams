package edu.rice.comp322.provided.streams.models;

import lombok.*;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Builder
@Data
@Component
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "product_order")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "order_date")
    private LocalDate orderDate;

    @Column(name = "delivery_date")
    private LocalDate deliveryDate;

    private String status;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToMany
    @JoinTable(
        name = "order_product_relationship",
        joinColumns = { @JoinColumn(name = "order_id") },
        inverseJoinColumns = { @JoinColumn(name = "product_id") })

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    Set<Product> products;

    public Long getId() {
        return this.id;
    }

    public LocalDate getOrderDate() {
        return this.orderDate;
    }

    public LocalDate getDeliveryDate() {
        return this.deliveryDate;
    }

    public String getStatus() {
        return this.status;
    }

    public Customer getCustomer() {
        return this.customer;
    }

    public Set<Product> getProducts() {
        return this.products;
    }

}

