package edu.rice.comp322.provided.streams.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Builder
@Data
@Component
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Integer tier;

    public Long getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public Integer getTier() {
        return this.tier;
    }

}

