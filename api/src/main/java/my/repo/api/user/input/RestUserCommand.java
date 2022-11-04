package my.repo.api.user.input;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import my.repo.api.base.BaseInput;
import my.repo.common.utils.InputCheckUtil;

import java.io.Serializable;

/**
 * @author gengzhihao
 * @date 2022/11/1 14:02
 * @description User写命令
**/
@Slf4j
@ApiModel("用户写命令入参")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RestUserCommand extends BaseInput implements Serializable {

    private static final long serialVersionUID = 4502555364502286318L;

    /**
     * 主键ID，使用雪花算法生成id，非自增
     */
    @ApiModelProperty("主键ID，使用雪花算法生成id，非自增")
    private Long id;

    /**
     * 用户名
     */
    @ApiModelProperty("用户名")
    private String username;

    /**
     * 密码
     */
    @ApiModelProperty("密码")
    private String password;

    /**
     * 邮箱
     */
    @ApiModelProperty("邮箱")
    private String email;

    /**
     * 年龄
     */
    @ApiModelProperty("年龄")
    private Object age;

    /**
     * 性别
     */
    @ApiModelProperty("性别")
    private Object sex;

    /**
     * 电话
     */
    @ApiModelProperty("电话")
    private String tel;

    /**
     * 身份证号
     */
    @ApiModelProperty("身份证号")
    private String card;

    /**
     * 0代表未结婚，1代表已结婚
     */
    @ApiModelProperty("0代表未结婚，1代表已结婚")
    private Integer married;


    @Override
    public void check() {
        InputCheckUtil.checkInput(this,"用户信息入参为空");
    }
}
