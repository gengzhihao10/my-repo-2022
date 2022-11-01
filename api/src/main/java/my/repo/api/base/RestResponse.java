package my.repo.api.base;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import my.repo.api.enums.ResponseCodeEnum;

import java.io.Serializable;

@Getter
@Setter
@Builder
@ApiModel("通用响应")
public class RestResponse<T> implements Serializable {

    private static final long serialVersionUID = -1784985132736151264L;

    @ApiModelProperty("响应码")
    private Integer code;

    @ApiModelProperty("提示信息")
    private String message;

    @ApiModelProperty("数据集")
    private T data;

    public RestResponse() {
    }

    public RestResponse(Integer code) {
        this.code = code;
    }

    public RestResponse(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public RestResponse(Integer code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static <T> RestResponse<T> ok(){
        return new RestResponse<>(ResponseCodeEnum.SUCCESS.getCode(),ResponseCodeEnum.SUCCESS.getDesc());
    }
    public static <T> RestResponse<T> ok(T data){
        return new RestResponse<>(ResponseCodeEnum.SUCCESS.getCode(),ResponseCodeEnum.SUCCESS.getDesc(),data);
    }
    public static <T> RestResponse<T> fail(ResponseCodeEnum responseCodeEnum ){
        return new RestResponse<>(responseCodeEnum.getCode(),responseCodeEnum.getDesc());
    }
}
