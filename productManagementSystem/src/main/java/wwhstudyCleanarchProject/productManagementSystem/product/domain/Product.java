package wwhstudyCleanarchProject.productManagementSystem.product.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
@NoArgsConstructor
public class Product {

    @Id @GeneratedValue
    @Column(name = "product_id")
    private Long id;

    private String name;
    private int price;
    private int quantity;

    public Product(Long id, String name,
                   int price, int quantity) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public Product(String name, int price, int quantity) {
        this(null, name, price, quantity);
    }

    public void changeProductInfo(Product product) {
        String name = product.getName();
        if (this.name.compareTo(name) != 0) {
            this.name = name;
        }

        int price = product.getPrice();
        if (this.price != price) {
            this.price = price;
        }

        int stockQuantity = product.getQuantity();
        if (this.quantity != stockQuantity) {
            this.quantity = stockQuantity;
        }
    }
}
