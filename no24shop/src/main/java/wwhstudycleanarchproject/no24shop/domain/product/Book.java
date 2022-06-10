package wwhstudycleanarchproject.no24shop.domain.product;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter @Setter
public class Book extends Product{

    @Id @GeneratedValue
    @Column(name = "book_id")
    private Long id;

    private String author;
    private String isbn;
}
