package wwhstudycleanarchproject.smallShop.product.domain;

import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
public class Product {

    @Id @GeneratedValue
    @Column(name = "product_id")
    private Long id;

    private String name;
    private String maker;
    private int price;
    private int stockQuantity;

    public Product(Long id, String name, String maker,
                   int price, int stockQuantity) {
        this.id = id;
        this.name = name;
        this.maker = maker;
        this.price = price;
        this.stockQuantity = stockQuantity;
    }

    public Product(String name, String maker, int price, int stockQuantity) {
        this(null, name, maker, price, stockQuantity);
    }

    public void changeProductInfo(Product product) {
        String name = product.getName();
        if (this.name.compareTo(name) != 0) {
            this.name = name;
        }

        String maker = product.getMaker();
        if (this.maker.compareTo(maker) != 0) {
            this.maker = maker;
        }

        int price = product.getPrice();
        if (this.price != price) {
            this.price = price;
        }

        int stockQuantity = product.getStockQuantity();
        if (this.stockQuantity != stockQuantity) {
            this.stockQuantity = stockQuantity;
        }
    }
}
