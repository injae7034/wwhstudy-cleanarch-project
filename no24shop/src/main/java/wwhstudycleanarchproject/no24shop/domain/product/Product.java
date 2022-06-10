package wwhstudycleanarchproject.no24shop.domain.product;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import wwhstudycleanarchproject.no24shop.domain.Category;
import wwhstudycleanarchproject.no24shop.domain.SellStatus;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToMany;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "DTYPE")
@Getter @Setter
public abstract class Product {

    @Id @GeneratedValue
    @Column(name = "product_id")
    private Long id;

    @CreatedDate
    @Column(name = "create_time", updatable = false)
    private LocalDateTime createTime; // 최초 생성 시간(변경 X)

    @LastModifiedDate
    @Column(name = "update_time")
    private LocalDateTime updateTime; // 마지막 수정 시간

    @CreatedBy
    @Column(name = "create_by", updatable = false)
    private String createdBy; // 최초에 생성한 사람(변경 X)

    @LastModifiedBy
    @Column(name = "modified_by")
    private String modifiedBy; // 마지막에 수정한 사람

    @Column(name = "item_name")
    private String name;

    @Column(name = "item_sell_status")
    private SellStatus status;

    private int price;
    private int stockQuantity;
    private String detail;

    @ManyToMany(mappedBy = "products")
    private List<Category> categories = new ArrayList<>();

}
