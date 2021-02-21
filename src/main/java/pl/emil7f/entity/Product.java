package pl.emil7f.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    @OneToMany(mappedBy = "product", cascade = {CascadeType.REMOVE, CascadeType.PERSIST})
    // zmiana mapowania!!! zamiast @JoinColumn ustawiamy parametr mappedBy = "nazwa pola po drugiej stronie"
    private Set<Review> reviews = new HashSet<>();

    @ManyToOne(fetch = FetchType.LAZY)
    private Category category;

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(joinColumns = {@JoinColumn(name = "product_id")},
            inverseJoinColumns = {@JoinColumn(name = "attribute_id")})
    private Set<Attribute> attributes = new HashSet<>();

    public void addAttributes(Attribute attribute) {
        attributes.add(attribute);           // dodajemy atrybut do listy wszystkich artrybutów
        attribute.getProducts().add(this);   // z uwagi na to ze jest to realacja ManyToMany,
        // musimy dodać też produkt z którego wywołujemy tą metode do listy produktów przekazywanego atrybutu
    }

    public void addReview(Review review) {
        reviews.add(review);
        review.setProduct(this);
    }

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

    public Set<Review> getReviews() {
        return reviews;
    }

    public void setReviews(Set<Review> reviews) {
        this.reviews = reviews;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Set<Attribute> getAttributes() {
        return attributes;
    }

    public void setAttributes(Set<Attribute> attributes) {
        this.attributes = attributes;
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
