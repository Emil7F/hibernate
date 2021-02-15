package pl.emil7f.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
// @Table(name="x_product") jeśli chcemy mieć inną nazwe tabeli w DB
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    /**
     *TABLE - dla baz które pobierają klucz główny z DB, jest najmniej wydajny, bo musimy pobrać identyfikator
     *SEQUENCE - podobny jak identity, dla baz które wspierają mechanizm sekwencji
     *IDENTITY - tworzy identyfikator na podstawie kolumny, jest oznaczona jako autoincrement
     *AUTO - default
     */
    private Long id;
    // @Column(name = "name")  jeśli nie ustawimy tej nazwy to hibernate będzie szukał kolumny po nazwie pola, można jej też na getterze
    private String name;
    private String description;
    private LocalDateTime created;
    private LocalDateTime updated;
    private BigDecimal price;
    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    // Mapowanie enuma na pole, najlepiej uzywac enuma jako stringa (EnumType.ORDINAL zrobi nam z tego wartosci 0 1 2....
    private ProductType productType;

    @OneToMany(mappedBy = "product", cascade = CascadeType.REMOVE)
    // zmiana mapowania!!! zamiast @JoinColumn ustawiamy parametr mappedBy = "nazwa pola po drugiej stronie"
    private List<Review> reviews;

    @OneToOne(fetch = FetchType.LAZY)
    private Category category;


    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public LocalDateTime getUpdated() {
        return updated;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public ProductType getProductType() {
        return productType;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCreated(LocalDateTime creationTime) {
        this.created = creationTime;
    }

    public void setUpdated(LocalDateTime updateTime) {
        this.updated = updateTime;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public void setProductType(ProductType productType) {
        this.productType = productType;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", created=" + created +
                ", updated=" + updated +
                ", price=" + price +
                ", productType=" + productType +
                //    ", reviews=" + reviews + // usuwamy to pole ponieważ z jego powodu wykonujemy dodatkowe zapytania do DB
                '}';
    }
}
