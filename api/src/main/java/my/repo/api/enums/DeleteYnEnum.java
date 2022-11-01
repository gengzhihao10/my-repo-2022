package my.repo.api.enums;


import lombok.AllArgsConstructor;
import lombok.Getter;


/**
 * @author gengzhihao
 * @date 2022/10/31 14:07
 * @description 逻辑删除枚举
**/

@Getter
@AllArgsConstructor
public enum DeleteYnEnum {

    //枚举
    NORMAL(0,"未删除"),
    DELETE(1,"已删除"),
    ;

    /**
     * 是否逻辑删除
     */
    private final int deleteYn;

    /**
     * 描述
     */
    private final String desc;
}
