package com.practice.excel;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author yanzhihao
 * @Description:
 * @date 2021/10/29 8:47 上午
 */
public class ExcelUtil {


    public static void exportExcel(HttpServletRequest request, HttpServletResponse response, List<Object> data, Class<?> clazz) throws Exception {
        ExcelConfig config = new ExcelConfig(clazz);
        exportExcel(request, response, data, config);
    }

    /**
     * 导出数组为excel
     * 结合 @ExcelColumn 注解使用
     *
     * @param data   要导出的数据
     * @param config 相关配置
     */
    public static void exportExcel(HttpServletRequest request, HttpServletResponse response, List<Object> data, ExcelConfig config) throws Exception {
        Class<?> clazz = config.getClazz();
        String fileName = config.getFileName();
        Integer width = config.getWidth();
        String nullFillStr = config.getNullFillStr();
        XSSFWorkbook xssfWorkbook = createXSSFWorkbook(data, width, clazz, nullFillStr);
        try {
            String agent = request.getHeader("USER-AGENT").toLowerCase();

            response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=UTF-8");
            response.setCharacterEncoding("UTF-8");
            String fileNameURL;
            if (agent.contains("firefox")) {

                fileNameURL = URLEncoder.encode(fileName + ".xlsx", "ISO8859-1");
                response.setHeader("Content-Disposition", "attachment;filename=" + fileNameURL + ";" + "filename*=ISO8859-1''" + fileNameURL);
            } else {
                fileNameURL = URLEncoder.encode(fileName + ".xlsx", "UTF-8");
                response.setHeader("Content-Disposition", "attachment;filename=" + fileNameURL + ";" + "filename*=utf-8''" + fileNameURL);
            }
            response.addHeader("Cache-Control", "no-cache");
            xssfWorkbook.write(response.getOutputStream());
            xssfWorkbook.close();
        } catch (IOException e) {
            e.printStackTrace();
            try {
                xssfWorkbook.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            throw new Exception("导出失败");
        }

    }

    /**
     * 写入每个excel单元格数据和样式
     *
     * @param data  需要导出的数据
     * @param width 每列宽度
     * @param clazz 映射的类
     * @return XSSFWorkbook
     */
    public static XSSFWorkbook createXSSFWorkbook(List<Object> data, Integer width, Class<?> clazz, String nullFillStr) {

        //获取实体类对应excel列名称和java对象的属性名称
        Map<Integer, String> map = getClazzInfo(clazz);
        Map<Integer, String> excelHeaders = new HashMap<>();
        Map<Integer, String> fieldName = new HashMap<>();
        map.forEach((integer, s) -> {
            String[] strings = s.split("=");
            excelHeaders.put(integer, strings[0]);
            fieldName.put(integer, strings[1]);
        });

        XSSFWorkbook xssfWorkbook = new XSSFWorkbook();
        //首行
        //字体加粗style
        CellStyle headerCellFontStyle = xssfWorkbook.createCellStyle();
        Font font = xssfWorkbook.createFont();
        font.setBold(true);
        headerCellFontStyle.setFont(font);
        //下边框
        headerCellFontStyle.setBorderBottom(BorderStyle.THIN);
        //左边框
        headerCellFontStyle.setBorderLeft(BorderStyle.THIN);
        //上边框
        headerCellFontStyle.setBorderTop(BorderStyle.THIN);
        //右边框
        headerCellFontStyle.setBorderRight(BorderStyle.THIN);

        //数据行，边框
        CellStyle borderStyle = xssfWorkbook.createCellStyle();
        //下边框
        borderStyle.setBorderBottom(BorderStyle.THIN);
        //左边框
        borderStyle.setBorderLeft(BorderStyle.THIN);
        //上边框
        borderStyle.setBorderTop(BorderStyle.THIN);
        //右边框
        borderStyle.setBorderRight(BorderStyle.THIN);


        Sheet sheet = xssfWorkbook.createSheet();
        Row headerRow = sheet.createRow(0);

        excelHeaders.forEach((integer, s) -> {
            Cell headerCell = headerRow.createCell(integer);
            headerCell.setCellValue(s);
            headerCell.setCellStyle(headerCellFontStyle);
            sheet.setColumnWidth(integer, width * 1000);
        });

        //数据行
        for (int i = 0; i < data.size(); i++) {
            Row row = sheet.createRow(i + 1);
            Object obj = data.get(i);
            fieldName.forEach((integer, s) -> {
                Cell cell = row.createCell(integer);
                Object value = getDeclaredFieldValue(s, obj);
                // null 时填充配置中的字符串
                if (value != null) {
                    cell.setCellValue(value.toString());
                } else {
                    cell.setCellValue(nullFillStr);
                }
                cell.setCellStyle(borderStyle);
            });
        }

        return xssfWorkbook;
    }

    /**
     * 反射方式调用get方法获取属性值
     *
     * @param name 属性名
     * @param obj  需要获取的对象
     * @return 属性值
     */
    public static Object getDeclaredFieldValue(String name, Object obj) {
        try {
            Class<?> clazz = obj.getClass();
            Field field = clazz.getDeclaredField(name);
            //驼峰get方法拼接
            if ("boolean".equals(field.getType().getName())) {
                Method method = clazz.getDeclaredMethod("is" + name.substring(0, 1).toUpperCase() + name.substring(1));
                field.setAccessible(true);
                if (Boolean.TRUE.equals(method.invoke(obj))) {
                    return "是";
                } else {
                    return "否";
                }
            } else {
                Method method = clazz.getDeclaredMethod("get" + name.substring(0, 1).toUpperCase() + name.substring(1));
                field.setAccessible(true);
                return method.invoke(obj);
            }
        } catch (NoSuchFieldException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }

    /**
     * 解析实体类注解中的属性，放到Map中
     *
     * @param clazz 需要解析的类
     * @return "Map<columnIndex, "name=fieldName">"
     */
    public static Map<Integer, String> getClazzInfo(Class<?> clazz) {
        Field[] fields = clazz.getDeclaredFields();
        Map<Integer, String> map = new HashMap<>();
        try {
            for (Field field : fields) {
                boolean isExcelColumn = field.isAnnotationPresent(ExcelColumn.class);
                if (isExcelColumn) {
                    String column = field.getAnnotation(ExcelColumn.class).column();
                    int columnIndex = stringToAscii(column) - 65;
                    //示例map value："excel列头名称=java模型属性名称"
                    map.put(columnIndex, field.getAnnotation(ExcelColumn.class).headerName() + "=" + field.getName());
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("failed to convert excel header");
        }
        return map;
    }

    /**
     * 字符转ascii码
     *
     * @param value 字符
     * @return ascii码
     */
    public static int stringToAscii(String value) {
        char[] str = value.toCharArray();
        if (str.length != 1) {
            throw new RuntimeException("列属性只能输入一个字符");
        } else {
            return str[0];
        }
    }

    public static void main(String[] args) {
//        BeanUtils.findDeclaredMethod();
    }
}
