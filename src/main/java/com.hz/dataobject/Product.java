package com.hz.dataobject;


import br.com.caelum.vraptor.ioc.Component;
import br.com.caelum.vraptor.ioc.PrototypeScoped;

import javax.persistence.*;

@Entity
@Table(name="t_product")
@Component
@PrototypeScoped
public class Product {
    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "`name`")
    private String name;
    @Column(name="`desc`")
    private String description;
    @Column(name="price")
    private Double price;

    public Product() {
    }

    public Product(String description, String name, Double price) {
        this.description = description;
        this.name = name;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Product{" +
                "description='" + description + '\'' +
                ", id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}