package com.taikang.exceldemo.utils;

import org.apache.poi.hssf.usermodel.*;

import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @Auther :朱树广
 * @Date :2020/7/25
 * @Description :com.taikang.exceldemo.utils
 * @Version :1.0
 */
public class ExportExcelUtils {
    public static <T> void export(String excelName, List<T> list, LinkedHashMap<String, String> fieldMap, HttpServletResponse response) {

        // 设置默认文件名为当前时间：年月日时分秒
        if (excelName == null || excelName == "") {
            excelName = new SimpleDateFormat("yyyyMMddhhmmss").format(
                    new Date()).toString();
        }
        // 设置response头信息
        response.reset();
        response.setContentType("application/vnd.ms-excel"); // 改成输出excel文件
        try {
            response.setHeader("Content-disposition", "attachment; filename="
                    + new String(excelName.getBytes("gb2312"), "ISO-8859-1") + ".xls");
        } catch (UnsupportedEncodingException e1) {
        }
        try {
            HSSFWorkbook wb = new HSSFWorkbook();
            HSSFSheet sheet = wb.createSheet(excelName);
            HSSFCellStyle style = wb.createCellStyle();
            //style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
            fillSheet(sheet, list, fieldMap, style);
            OutputStream ouputStream = response.getOutputStream();
            wb.write(ouputStream);
            ouputStream.flush();
            ouputStream.close();
        } catch (Exception e) {
        }
    }

    public static Field getFieldByName(String fieldName, Class<?> clazz) {
        Field[] selfFields = clazz.getDeclaredFields();
        for (Field field : selfFields) {
            if (field.getName().equals(fieldName)) {
                return field;
            }
        }
        Class<?> superClazz = clazz.getSuperclass();
        if (superClazz != null && superClazz != Object.class) {
            return getFieldByName(fieldName, superClazz);
        }
        return null;
    }

    public static Object getFieldValueByName(String fieldName, Object o) throws Exception {
        Object value = null;
        Field field = getFieldByName(fieldName, o.getClass());
        if (field != null) {
            field.setAccessible(true);
            value = field.get(o);
        } else {
            throw new Exception(o.getClass().getSimpleName() + "类不存在字段名 " + fieldName);
        }
        return value;
    }

    public static Object getFieldValueByNameSequence(String fieldNameSequence, Object o) throws Exception {
        Object value = null;
        String[] attributes = fieldNameSequence.split("\\.");
        if (attributes.length == 1) {
            value = getFieldValueByName(fieldNameSequence, o);
        } else {
            Object fieldObj = getFieldValueByName(attributes[0], o);
            String subFieldNameSequence = fieldNameSequence.substring(fieldNameSequence.indexOf(".") + 1);
            value = getFieldValueByNameSequence(subFieldNameSequence, fieldObj);
        }
        return value;
    }

    public static <T> void fillSheet(HSSFSheet sheet, List<T> list, LinkedHashMap<String, String> fieldMap, HSSFCellStyle style) throws Exception {
        String[] enFields = new String[fieldMap.size()];
        String[] cnFields = new String[fieldMap.size()];
        int count = 0;
        for (Map.Entry<String, String> entry : fieldMap.entrySet()) {
            enFields[count] = entry.getKey();
            cnFields[count] = entry.getValue();
            count++;
        }
         HSSFRow row = sheet.createRow((int) 0);
        for (int i = 0; i < cnFields.length; i++) {
            HSSFCell cell = row.createCell(i);
            cell.setCellValue(cnFields[i]);
            cell.setCellStyle(style);
            sheet.autoSizeColumn(i);
        }
        for (int index = 0; index < list.size(); index++) {
            row = sheet.createRow(index + 1);
            T item = list.get(index);
            for (int i = 0; i < enFields.length; i++) {
                Object objValue = getFieldValueByNameSequence(enFields[i], item);
                String fieldValue = objValue == null ? "" : objValue.toString();
                row.createCell(i).setCellValue(fieldValue);
            }
        }
    }
}
