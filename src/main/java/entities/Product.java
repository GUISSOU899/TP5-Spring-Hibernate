package entities;

import javax.persistence.*;

@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private double price;

    // Relation ManyToOne vers Category
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    // Constructeur vide (obligatoire pour Hibernate)
    public Product() {}

    // Constructeur pratique pour créer rapidement un produit
    public Product(String name, double price) {
        this.name = name;
        this.price = price;
    }

    // --- Getters et Setters ---
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "Product [id=" + id + ", name=" + name + ", price=" + price +
                ", category=" + (category != null ? category.getName() : "Aucune") + "]";
    }
}
