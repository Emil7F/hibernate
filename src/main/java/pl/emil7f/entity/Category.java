package pl.emil7f.entity;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "category")// mappedBy="category" nazwa pola które mamy w produkcie
    private Set<Product> product;

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

    public Set<Product> getProduct() {
        return product;
    }

    public void setProduct(Set<Product> products) {
        this.product = products;
    }

    @Override
    public String  toString() {
        return "Category{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}

