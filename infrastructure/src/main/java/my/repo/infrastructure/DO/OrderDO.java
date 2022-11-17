package my.repo.infrastructure.DO;

import java.math.BigDecimal;
import java.util.Date;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * 订单表(MyOrder)实体类
 *
 * @author makejava
 * @since 2022-11-17 10:04:25
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@TableName("my_order")
public class OrderDO implements Serializable {
    private static final long serialVersionUID = -26471413419241364L;
    /**
     * 主键
     */
    private Long id;
    /**
     * 流水号
     */
    private String orderCode;
    /**
     * 订单类型：1实体销售，2网络销售
     */
    private Short type;
    /**
     * 零售店ID
     */
    private Long shopId;
    /**
     * 会员ID
     */
    private Long customerId;
    /**
     * 总金额
     */
    private BigDecimal amount;
    /**
     * 支付方式：1借记卡、2信用卡、3微信、4支付宝、5现金
     */
    private Short paymentType;
    /**
     * 状态：1未付款、2已付款、3已发货、4已签收
     */
    private Short orderStatus;
    /**
     * 邮费
     */
    private BigDecimal postage;
    /**
     * 重量：单位克
     */
    private Integer weight;
    /**
     * 购物券ID
     */
    private Long voucherId;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 逻辑删除，0未删除，1已删除
     */
    private Short deleteYn;


}


