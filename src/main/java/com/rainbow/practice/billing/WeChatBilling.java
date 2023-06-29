package com.rainbow.practice.billing;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author yanzhihao
 * @since 2023/6/29
 */
@Data
public class WeChatBilling {

    private Long id;

    /**
     * 交易时间
     */
    private LocalDateTime time;

    /**
     * 交易类型
     */
    private Integer category;

    /**
     * 交易对方
     */
    private String counterparty;

    /**
     * 商品
     */
    private String item;

    /**
     * 收/支
     */
    private Integer type;

    /**
     * 金额(元)
     */
    private Double amount;

    /**
     * 支付方式
     */
    private String paymentMethod;

    /**
     * 当前状态
     */
    private Integer status;

    /**
     * 交易单号
     */
    private String transactionNumber;

    /**
     * 商户单号
     */
    private String merchantNumber;

    /**
     * 备注
     */
    private String remark;

}
