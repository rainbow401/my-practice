package com.rainbow.practice.excel;

/**
 * @author yanzhihao
 */
public class ExcelConfig {
    /**
     * 要导出的映射类
     */
    private Class<?> clazz;
    /**
     * 导出文件名
     */
    private String fileName;
    /**
     * 所有列的宽度 width * 1000
     */
    private Integer width = 8;
    /**
     * 属性为空时，即无数据时，单元格填充的字符
     */
    private String nullFillStr = "-";

    public ExcelConfig(Class<?> clazz, String fileName, Integer width, String nullFillStr) {
        this.clazz = clazz;
        this.fileName = fileName;
        this.width = width;
        this.nullFillStr = nullFillStr;
    }

    public ExcelConfig(Class<?> clazz) {
        this.clazz = clazz;
    }

    public ExcelConfig() {
    }

    public Class<?> getClazz() {
        return clazz;
    }

    public void setClazz(Class<?> clazz) {
        this.clazz = clazz;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public String getNullFillStr() {
        return nullFillStr;
    }

    public void setNullFillStr(String nullFillStr) {
        this.nullFillStr = nullFillStr;
    }
}