package my.repo.api.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ResponseCodeEnum {

    //枚举
    SUCCESS(0,"成功"),
    FAILED(-1,"未知原因失败"),
    INPUT_IS_NULL(10000,"入参为空"),
    CREATE_SNOWFLAKE_ID_FAILED(10100,"构造雪花算法id失败"),

    ;

    /**
     * 响应码
     */
    private Integer code;

    /**
     * 描述
     */
    private String desc;

}
