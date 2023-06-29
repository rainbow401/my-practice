package com.rainbow.practice.billing;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author yanzhihao
 * @since 2023/6/29
 */
@Data
public class AliPayBillingData {

    /**
     * 交易时间
     */
    private String time;

    /**
     * 交易分类
     */
    private String category;

    /**
     * 交易对方
     */
    private String counterparty;

    /**
     * 对方账号
     */
    private String counterpartyAccount;

    /**
     * 商品说明
     */
    private String item;

    /**
     * 收/支
     */
    private String type;

    /**
     * 金额
     */
    private Double amount;

    /**
     * 收/付款方式
     */
    private String paymentMethod;

    /**
     * 交易状态
     */
    private String status;

    /**
     * 交易订单号
     */
    private String transactionOrderNumber;

    /**
     * 商家订单号
     */
    private String merchantOrderNumber;

    /**
     * 备注
     */
    private String remark;

}
