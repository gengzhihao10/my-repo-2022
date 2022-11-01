package my.repo.api.base;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * @author gengzhihao
 * @date 2022/11/1 14:27
 * @description 分页参数
**/
@Getter
@Setter
@ApiModel("分页参数")
public class PageParam {

    @ApiModelProperty("页容量")
    private int size;

    @ApiModelProperty("当前页码")
    private int pageNo;
}
