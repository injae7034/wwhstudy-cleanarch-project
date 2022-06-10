package wwhstudycleanarchproject.no24shop.domain;


import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import java.time.LocalDateTime;

import static javax.persistence.FetchType.LAZY;

@Entity
@Getter @Setter
public class Delivery {

    @Id @GeneratedValue
    @Column(name = "delivery_id")
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

    @Column(name = "delivery_fee")
    private int deliveryFee;

    @Embedded
    private Address address;

    @Enumerated(EnumType.STRING)
    private DeliveryStatus status; //READY, COMP

    @OneToOne(mappedBy = "delivery", fetch = LAZY)
    private Order order;

}
