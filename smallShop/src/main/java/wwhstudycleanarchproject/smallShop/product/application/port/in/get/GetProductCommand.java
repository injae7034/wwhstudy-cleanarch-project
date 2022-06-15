package wwhstudycleanarchproject.smallShop.product.application.port.in.get;

import lombok.EqualsAndHashCode;
import lombok.Value;
import wwhstudycleanarchproject.smallShop.common.CommandValidating;

import javax.validation.constraints.NotNull;

@Value
@EqualsAndHashCode(callSuper = false)
public class GetProductCommand extends CommandValidating {

    @NotNull
    private final Long id;

    public GetProductCommand(Long id) {
        this.id = id;
        this.validateCommand();
    }
}
