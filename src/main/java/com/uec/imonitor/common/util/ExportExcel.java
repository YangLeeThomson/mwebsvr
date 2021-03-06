package com.uec.imonitor.common.util;


import java.io.*;
import java.lang.reflect.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.text.SimpleDateFormat;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;

/** 
 * <p>Copyright: All Rights Reserved</p>  
 * <p>Company: 北京荣之联科技股份有限公司   http://www.ronglian.com</p> 
 * <p>Description: 利用开源组件POI动态导出EXCEL文档 </p> 
 * <p>Author:xpguo/郭晓鹏</p>
 * @param <T>
 */
public class ExportExcel<T> {

    public void exportExcel(Collection<T> dataset, OutputStream out) {
        exportExcel("导入数据", null, dataset, out, "yyyy-MM-dd");
    }

    public void exportExcel(String[] headers, Collection<T> dataset,
            OutputStream out) {
        exportExcel("导入数据", headers, dataset, out, "yyyy-MM-dd");
    }

    public void exportExcel(String[] headers, Collection<T> dataset,
            OutputStream out, String pattern) {
        exportExcel("导入数据", headers, dataset, out, pattern);
    }

    /**
     * 这是一个通用的方法，利用了JAVA的反射机制，可以将放置在JAVA集合中并且符号一定条件的数据以EXCEL 的形式输出到指定IO设备上
     * 
     * @param title
     *            表格标题名
     * @param headers
     *            表格属性列名数组
     * @param dataset
     *            需要显示的数据集合,集合中一定要放置符合javabean风格的类的对象。此方法支持的
     *            javabean属性的数据类型有基本数据类型及String,Date,byte[](图片数据)
     * @param out
     *            与输出设备关联的流对象，可以将EXCEL文档导出到本地文件或者网络中
     * @param pattern
     *            如果有时间数据，设定输出格式。默认为"yyy-MM-dd"
     */
    public void exportExcel(String title, String[] headers,
            Collection<T> dataset, OutputStream out, String pattern) {
        // 声明一个工作薄
        HSSFWorkbook workbook = new HSSFWorkbook();
     // 遍历集合数据，产生数据行
        Iterator<T> it = dataset.iterator();
        int sheetSize = 1000;
        int dataNo = 0;
        for (int j = 0; j*sheetSize <= dataset.size(); j++) {
        	// 生成一个表格
            HSSFSheet sheet = workbook.createSheet((j*sheetSize+1)+"-"+((j+1)*sheetSize));
            // 设置表格默认列宽度为15个字节
            sheet.setDefaultColumnWidth(15);
            // 生成一个样式
            HSSFCellStyle style = workbook.createCellStyle();
            // 设置这些样式
            style.setFillForegroundColor(HSSFColor.SKY_BLUE.index);
            style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            style.setBorderBottom(BorderStyle.THIN);
            style.setBorderLeft(BorderStyle.THIN);
            style.setBorderRight(BorderStyle.THIN);
            style.setBorderTop(BorderStyle.THIN);
            style.setAlignment(HorizontalAlignment.CENTER);
            // 生成一个字体
            HSSFFont font = workbook.createFont();
            font.setColor(HSSFColor.VIOLET.index);
            font.setFontHeightInPoints((short) 12);
//            font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
            font.setBold(true);
            // 把字体应用到当前的样式
            style.setFont(font);
            // 生成并设置另一个样式
            HSSFCellStyle style2 = workbook.createCellStyle();
            style2.setFillForegroundColor(HSSFColor.LIGHT_YELLOW.index);
            style2.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            style2.setBorderBottom(BorderStyle.THIN);
            style2.setBorderLeft(BorderStyle.THIN);
            style2.setBorderRight(BorderStyle.THIN);
            style2.setBorderTop(BorderStyle.THIN);
            style2.setAlignment(HorizontalAlignment.CENTER);
			style2.setVerticalAlignment(VerticalAlignment.CENTER);
            // 生成另一个字体
            HSSFFont font2 = workbook.createFont();
            font2.setBold(false);
            // 把字体应用到当前的样式
            style2.setFont(font2);

            // 声明一个画图的顶级管理器
            HSSFPatriarch patriarch = sheet.createDrawingPatriarch();
            // 定义注释的大小和位置,详见文档
            HSSFComment comment = patriarch.createComment(new HSSFClientAnchor(0,
                    0, 0, 0, (short) 4, 2, (short) 6, 5));
            // 设置注释内容
            comment.setString(new HSSFRichTextString("可以在POI中添加注释！"));
            // 设置注释作者，当鼠标移动到单元格上是可以在状态栏中看到该内容.
            comment.setAuthor("leno");

            // 产生表格标题行
            HSSFRow row = sheet.createRow(0);
            for (int i = 0; i < headers.length; i++) {
                HSSFCell cell = row.createCell(i);
                cell.setCellStyle(style);
                HSSFRichTextString text = new HSSFRichTextString(headers[i]);
                cell.setCellValue(text);
            }
            int index = 0;
            while (dataNo>=(sheetSize*j)&&dataNo<(sheetSize*(j+1))&&it.hasNext()) {
                index++;
                dataNo++;
                row = sheet.createRow(index);
                T t = (T) it.next();
                // 利用反射，根据javabean属性的先后顺序，动态调用getXxx()方法得到属性值
                Field[] fields = t.getClass().getDeclaredFields();
                for (int i = 0; i < fields.length; i++) {
                    HSSFCell cell = row.createCell(i);
                    cell.setCellStyle(style2);
                    Field field = fields[i];
                    String fieldName = field.getName();
                    String getMethodName = "get"
                            + fieldName.substring(0, 1).toUpperCase()
                            + fieldName.substring(1);
                    try {
//    					Class tCls = t.getClass();
                        Method getMethod = t.getClass().getMethod(getMethodName,
                                new Class[] {});
                        Object value = getMethod.invoke(t, new Object[] {});
                        // 判断值的类型后进行强制类型转换
                        String textValue = null;
                        // if (value instanceof Integer) {
                        // int intValue = (Integer) value;
                        // cell.setCellValue(intValue);
                        // } else if (value instanceof Float) {
                        // float fValue = (Float) value;
                        // textValue = new HSSFRichTextString(
                        // String.valueOf(fValue));
                        // cell.setCellValue(textValue);
                        // } else if (value instanceof Double) {
                        // double dValue = (Double) value;
                        // textValue = new HSSFRichTextString(
                        // String.valueOf(dValue));
                        // cell.setCellValue(textValue);
                        // } else if (value instanceof Long) {
                        // long longValue = (Long) value;
                        // cell.setCellValue(longValue);
                        // }
                        if (value instanceof Boolean) {
                            boolean bValue = (Boolean) value;
                            textValue = "男";
                            if (!bValue) {
                                textValue = "女";
                            }
                        } else if (value instanceof Date) {
                            Date date = (Date) value;
                            SimpleDateFormat sdf = new SimpleDateFormat(pattern);
                            textValue = sdf.format(date);
                        } else if (value instanceof byte[]) {
                            // 有图片时，设置行高为60px;
                            row.setHeightInPoints(60);
                            // 设置图片所在列宽度为80px,注意这里单位的一个换算
                            sheet.setColumnWidth(i, (short) (35.7 * 80));
                            // sheet.autoSizeColumn(i);
                            byte[] bsValue = (byte[]) value;
                            HSSFClientAnchor anchor = new HSSFClientAnchor(0, 0,
                                    1023, 255, (short) 6, index, (short) 6, index);
                            anchor.setAnchorType(2);
                            patriarch.createPicture(anchor, workbook.addPicture(
                                    bsValue, HSSFWorkbook.PICTURE_TYPE_JPEG));
                        } else {
                            // 其它数据类型都当作字符串简单处理
                        	if(value != null) {
                        		textValue = value.toString();
                        	} else {
                        		textValue = "";
                        	}
                            
                        }
                        // 如果不是图片数据，就利用正则表达式判断textValue是否全部由数字组成
                        if (textValue != null) {
                            Pattern p = Pattern.compile("^//d+(//.//d+)?$");
                            Matcher matcher = p.matcher(textValue);
                            if (matcher.matches()) {
                                // 是数字当作double处理
                                cell.setCellValue(Double.parseDouble(textValue));
                            } else {
                                HSSFRichTextString richString = new HSSFRichTextString(
                                        textValue);
                                HSSFFont font3 = workbook.createFont();
                                font3.setColor(HSSFColor.BLUE.index);
                                richString.applyFont(font3);
                                cell.setCellValue(richString);
                            }
                        }
                    } catch (SecurityException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    } catch (NoSuchMethodException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    } catch (IllegalArgumentException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    } finally {
                        // 清理资源
                    }
                }

            }
		}
        
        try {
            workbook.write(out);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

}