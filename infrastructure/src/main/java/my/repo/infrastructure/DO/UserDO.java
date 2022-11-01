package my.repo.infrastructure.DO;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 用户表(User)实体类
 *
 * @author 庚志浩
 * @since 2022-11-01 10:59:07
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@TableName("user")
public class UserDO implements Serializable {
    private static final long serialVersionUID = 509386025156832110L;
    /**
     * 主键ID，使用雪花算法生成id，非自增
     */
    private Long id;
    /**
     * 用户名
     */
    private String username;
    /**
     * 密码
     */
    private String password;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 年龄
     */
    private Object age;
    /**
     * 性别
     */
    private Object sex;
    /**
     * 电话
     */
    private String tel;
    /**
     * 身份证号
     */
    private String card;
    /**
     * 0代表未结婚，1代表已结婚
     */
    private Integer married;
    /**
     * 逻辑删除，0未删除 1已删除
     */
    private Integer deleteYn;


}


