package com.api.arithmeticcalculator.models;


import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "operation")
public class OperationModel implements Serializable {

    private static final Long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private UUID id;

    @Column(name = "type", unique = true)
    private String type;

    @Column(name = "cost")
    private Integer cost;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getCost() {
        return cost;
    }

    public void setCost(Integer cost) {
        this.cost = cost;
    }
}
