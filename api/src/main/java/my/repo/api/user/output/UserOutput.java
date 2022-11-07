package my.repo.api.user.output;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserOutput implements Serializable {
    private static final long serialVersionUID = 5529238285692559406L;

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

    /**
     * 创建时间
     */
    @ApiModelProperty("创建时间")
    private Date createTime;

    /**
     * 修改时间
     */
    @ApiModelProperty("修改时间")
    private Date updateTime;

}
