package wwhstudycleanarchproject.smallShop.product.application.port.in.create;

import lombok.EqualsAndHashCode;
import lombok.Value;
import wwhstudycleanarchproject.smallShop.common.CommandValidating;

import javax.validation.constraints.NotNull;

@Value
@EqualsAndHashCode(callSuper = false)
public class CreateProductCommand
        extends CommandValidating<CreateProductCommand> {

    @NotNull
    private String name;
    @NotNull
    private String maker;

    private Integer price;
    private Integer stockQuantity;

    public CreateProductCommand(String name, String maker,
                                Integer price, Integer stockQuantity) {
        this.name = name;
        this.maker = maker;
        if(price == null) {
            this.price = 0;
        } else {
            this.price = price;
        }
        if(stockQuantity == null) {
            this.stockQuantity = 0;
        } else {
            this.stockQuantity = stockQuantity;
        }

        this.validateCommand();
    }
}
