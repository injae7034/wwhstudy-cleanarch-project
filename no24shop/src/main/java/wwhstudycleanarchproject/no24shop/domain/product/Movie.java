package wwhstudycleanarchproject.no24shop.domain.product;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter @Setter
public class Movie extends Product{

    @Id @GeneratedValue
    @Column(name = "movie_id")
    private Long id;

    private String director;
    private String actor;
}
