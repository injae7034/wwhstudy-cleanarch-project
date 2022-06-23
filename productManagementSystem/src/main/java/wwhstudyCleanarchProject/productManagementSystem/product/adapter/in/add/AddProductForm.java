package wwhstudyCleanarchProject.productManagementSystem.product.adapter.in.add;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotEmpty;

@Getter @Setter
public class AddProductForm {

    @NotEmpty(message = "상품명은 반드시 입력하셔야 됩니다.")
    private String name;

    @NotEmpty(message = "가격은 반드시 입력하셔야 됩니다.")
    @Range(min = 1000, max = 1000000,
            message = "가격은 최소 1,000원에서 최대 1,000,000원 사이로 입력하셔야 합니다.")
    private Integer price;

    @NotEmpty(message = "수량은 반드시 입력하셔야 됩니다.")
    @Range(min = 1, max = 9999,
            message = "수량은 최소 1개에서 최대 9,999개 사이로 입력하셔야 합니다.")
    private Integer quantity;

}
