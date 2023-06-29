package com.rainbow.practice.billing;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.support.ExcelTypeEnum;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * @author yanzhihao
 * @since 2023/6/29
 */
public class Test {

    public static void main(String[] args) {
        EasyExcel.read("C:\\Users\\lenovo\\Desktop\\alipay_record_20230629_085151.csv", AliPayBillingData.class, new DemoListener()).headRowNumber(25).excelType(ExcelTypeEnum.CSV).charset(Charset.forName("GBK")).sheet().doRead();
    }
}
