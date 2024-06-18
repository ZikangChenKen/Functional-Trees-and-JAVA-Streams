package edu.rice.comp322.provided.streams.models;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.With;
import org.springframework.stereotype.Component;

@Builder
@Data
@Component
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String category;

    @With
    private Double fullPrice;

    @With
    private Double salePrice;

    @With
    private Double tierOnePrice;

    @With
    private Double tierTwoPrice;

    @ManyToMany(mappedBy = "products")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<Order> orders;

    public Long getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getCategory() {
        return this.category;
    }

    public Double getFullPrice() {
        return this.fullPrice;
    }

    public Double getSalePrice() {
        return this.salePrice;
    }

    public Double getTierOnePrice() {
        return this.tierOnePrice;
    }

    public Double getTierTwoPrice() {
        return this.tierTwoPrice;
    }

    public Set<Order> getOrders() {
        return this.orders;
    }

}

