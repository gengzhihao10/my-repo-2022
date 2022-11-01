package my.repo.api.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ResponseCodeEnum {

    //枚举
    SUCCESS(0,"成功"),
    FAILED(-1,"未知原因失败"),
    INPUTISNULL(10000,"入参为空"),

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
