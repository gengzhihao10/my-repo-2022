//package my.repo.infrastructure.VO;
//
//import java.io.Serializable;
//
//
///**
// * @author gengzhihao
// * @date 2022/11/1 14:48
// * @description 用户表 数据库写命令
//**/
//
//public class UserCommand implements Serializable {
//
//    private static final long serialVersionUID = -2908843314085657485L;
//    /**
//     * 主键ID，使用雪花算法生成id，非自增
//     */
//    private Long id;
//    /**
//     * 用户名
//     */
//    private String username;
//    /**
//     * 密码
//     */
//    private String password;
//    /**
//     * 邮箱
//     */
//    private String email;
//    /**
//     * 年龄
//     */
//    private Object age;
//    /**
//     * 性别
//     */
//    private Object sex;
//    /**
//     * 电话
//     */
//    private String tel;
//    /**
//     * 身份证号
//     */
//    private String card;
//    /**
//     * 0代表未结婚，1代表已结婚
//     */
//    private Integer married;
//    /**
//     * 逻辑删除，0未删除 1已删除
//     */
//    private Integer deleteYn;
//}
