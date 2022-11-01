package my.repo.api.base;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;


/**
 * @author gengzhihao
 * @date 2022/11/1 14:28
 * @description 分页入参基类
**/

@Getter
@Setter
@ApiModel("分页入参基类")
public abstract class BasePageInput extends BaseInput {

    @ApiModelProperty("分页参数")
    private PageParam pageParam;

}
