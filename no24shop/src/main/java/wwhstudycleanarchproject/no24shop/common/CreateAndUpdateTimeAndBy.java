package wwhstudycleanarchproject.no24shop.common;

import lombok.Getter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.time.LocalDateTime;

@Embeddable
@Getter
public class CreateAndUpdateTimeAndBy {

    @CreatedDate
    @Column(name = "create_time", updatable = false)
    private LocalDateTime createTime; // 최초 생성 시간(변경 X)

    @CreatedBy
    @Column(name = "create_by", updatable = false)
    private String createdBy; // 최초에 생성한 사람(변경 X)

    @LastModifiedDate
    @Column(name = "update_time")
    private LocalDateTime updateTime; // 마지막 수정 시간

    @LastModifiedBy
    @Column(name = "modified_by")
    private String modifiedBy; // 마지막에 수정한 사람

    protected CreateAndUpdateTimeAndBy() {
    }

    public CreateAndUpdateTimeAndBy(LocalDateTime createTime,
                                    String createdBy,
                                    LocalDateTime updateTime,
                                    String modifiedBy) {
        this.createTime = createTime;
        this.createdBy = createdBy;
        this.updateTime = updateTime;
        this.modifiedBy = modifiedBy;
    }
}
