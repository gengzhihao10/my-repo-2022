package my.repo.api.order.output;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderCommandOutput implements Serializable {
    private static final long serialVersionUID = 2692073018666344285L;

    /**
     * 主键
     */
    @ApiModelProperty("主键")
    private Long id;

    /**
     * 流水号
     */
    @ApiModelProperty("流水号")
    private String orderCode;

    /**
     * 订单类型：1实体销售，2网络销售
     */
    @ApiModelProperty("订单类型：1实体销售，2网络销售")
    private Short type;

    /**
     * 零售店ID
     */
    @ApiModelProperty("零售店ID")
    private Long shopId;

    /**
     * 会员ID
     */
    @ApiModelProperty("会员ID")
    private Long customerId;

    /**
     * 总金额
     */
    @ApiModelProperty("总金额")
    private BigDecimal amount;

    /**
     * 支付方式：1借记卡、2信用卡、3微信、4支付宝、5现金
     */
    @ApiModelProperty("支付方式：1借记卡、2信用卡、3微信、4支付宝、5现金")
    private Short paymentType;

    /**
     * 状态：1未付款、2已付款、3已发货、4已签收
     */
    @ApiModelProperty("状态：1未付款、2已付款、3已发货、4已签收")
    private Short orderStatus;

    /**
     * 邮费
     */
    @ApiModelProperty("邮费")
    private BigDecimal postage;

    /**
     * 重量：单位克
     */
    @ApiModelProperty("重量：单位克")
    private Integer weight;

    /**
     * 购物券ID
     */
    @ApiModelProperty("购物券ID")
    private Long voucherId;

    /**
     * 创建时间
     */
    @ApiModelProperty("创建时间")
    private Date createTime;

    /**
     * 更新时间
     */
    @ApiModelProperty("更新时间")
    private Date updateTime;
}
