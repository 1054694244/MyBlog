package com.shenzc.utils;

import com.shenzc.anno.excel.ExcelAnno;
import com.shenzc.entity.front.Blog;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @shenzc
 * @2020/12/8 14:53
 * 文件说明： excel导入导出
 */
public class ExcelUtils<T> {

    //导出数据(整个类所有字段全导入字段为null的时候转换为""导出)
    public void export(List<T> list, List<String> nameList,String fileName,String addr) throws Exception {
        Map<String,List<String>> map = new HashMap<>();
        int i = 0;
        for (T t : list){
            //将map转换为list集合
            List<String> list1 = beanToList(t);
            map.put("data"+i,list1);
            i++;
        }
        map.put("data"+i,nameList);
        createSheet(map,fileName,addr);
    }

    //添加注解的字段导入
    //导出数据(整个类所有字段全导入字段为null的时候转换为""导出)
    public void exportAnno(List<T> list,String fileName,String addr) throws Exception {
        Map<String,List<String>> map = new HashMap<>();
        List<String> nameList = null;
        int i = 0;
        for (T t : list){
            //将map转换为list集合
            Map<String, List<String>> map1 = beanToListAnno(t,i);
            List<String> dataList = map1.get("data");
            if (i==0){
                nameList = map1.get("name");
            }
            map.put("data"+i,dataList);
            i++;
        }
        map.put("data"+i,nameList);
        createSheet(map,fileName,addr);
    }

    //导入数据(只导入不为null的数据)
    public static MultiValueMap<Integer,String> parseExcelService(String filePath) throws IOException {
        FileInputStream is = new FileInputStream(filePath);
        XSSFWorkbook workbook = new XSSFWorkbook(is);
        //获取第一页内容
        Sheet sheet = workbook.getSheetAt(0);
        //解析数据
        MultiValueMap<Integer,String> multiValueMap = new LinkedMultiValueMap();
        Row row = sheet.getRow(0);
        //获取最大行数
        int rowNum = sheet.getPhysicalNumberOfRows();
        //获取最大列数
        int colNum = row.getPhysicalNumberOfCells();
        //从第二行开始遍历，开始获取数据
        for (int i = 1; i < rowNum; i++) {
            //获取第i行数据
            row = sheet.getRow(i);
            for (int j = 0; j < colNum; j++) {
                Cell cell = row.getCell(j);
                //cell.setCellType(Cell.CELL_TYPE_STRING);
                String cellText = cell.getStringCellValue();
                if (!"".equals(cellText)){
                    multiValueMap.add(i+1,cellText);
                }
            }
        }
        return multiValueMap;
    }

    //将map转换为list集合
    private List<String> beanToList(T t) throws IllegalAccessException {
        List<String> list = new ArrayList<>();
        Field[] fields = t.getClass().getDeclaredFields();
        for (int i=0;i<fields.length;i++){
            fields[i].setAccessible(true);
            Object o = fields[i].get(t);
            if (o == null){
                o = "";
            }else if (o instanceof Integer){
                o = String.valueOf((Integer)o);
            }
            list.add((String) o);
        }
        return list;
    }

    //将map转换为list集合(注解字段导入，并且按照注解顺序导入)
    private Map<String, List<String>> beanToListAnno(T t,Integer j) throws IllegalAccessException {
        Map<String,List<String>> mapList = new HashMap<>();
        List<String> dataList = new ArrayList<>();
        List<Field> fieldList = new ArrayList<>();
        Field[] fields = t.getClass().getDeclaredFields();
        for (int i=0;i<fields.length;i++){
            fields[i].setAccessible(true);
            //获取ExcelAnno注解
            if (fields[i].isAnnotationPresent(ExcelAnno.class)){
                fieldList.add(fields[i]);
            }
        }
        //对注解字段排序
        List<Field> collect = fieldList.stream().sorted(new Comparator<Field>() {
            @Override
            public int compare(Field o1, Field o2) {
                return o1.getAnnotation(ExcelAnno.class).column() - o2.getAnnotation(ExcelAnno.class).column();
            }
        }).collect(Collectors.toList());
        //获取dataList
        collect.forEach(field -> {
            Object o = null;
            try {
                o = field.get(t);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            if (o == null){
                o = "";
            }else if (o instanceof Integer){
                o = String.valueOf((Integer)o);
            }
            dataList.add((String) o);
        });
        //获取titleList,只获取一次
        if (j == 0){
            List<String> nameList = new ArrayList<>();
            collect.forEach(field -> {
                nameList.add(field.getAnnotation(ExcelAnno.class).title());
            });
            mapList.put("name",nameList);
        }
        mapList.put("data",dataList);
        return mapList;
    }

    //导出数据
    public void createSheet(Map<String,List<String>> map,String fileName,String addr) throws Exception {
        // 【SXSSFWorkbook----------->sheet----------->row------------>cell】

        // SXSSFWorkbook支持excel2010
        SXSSFWorkbook sw = new SXSSFWorkbook();
        // 表名
        Sheet sheet = sw.createSheet("成绩表");
        // 创建行 【第一行  从零开始】
        Row row = sheet.createRow(0);
        // 创建列 【第一列  从零开始】
        Cell cell = row.createCell(0);

        //设置单元格内容
        //cell.setCellValue("学员考试成绩一览表");
        //合并单元格CellRangeAddress构造参数依次表示起始行，截至行，起始列， 截至列
        //sheet.addMergedRegion(new CellRangeAddress(0,0,0,3));

        int i = 0;
        Row row1 = null;
        for (String key : map.keySet()){
            List<String> list = map.get(key);
            row1 = sheet.createRow(i);
            int j = 0;
            for (String value : list){
                row1.createCell(j).setCellValue(value);
                j++;
            }
            j = 0;
            i++;
        }

        // 新建导出流
        FileOutputStream fileOutputStream = new FileOutputStream(addr+"/"+fileName);
        // 将文件写出
        sw.write(fileOutputStream);

        fileOutputStream.flush();

        fileOutputStream.close();

    }

    public static void main(String[] args) throws Exception {
        List<Blog> blogs = new ArrayList<>(5);
        for (int i=0;i<5;i++){
            Blog blog = new Blog();
            blog.setContent("456");
            blog.setBlogId("123");
            blog.setIsDelete(1);
            blogs.add(blog);
        }
        ExcelUtils excelUtils = new ExcelUtils();
        List<String> list = new ArrayList<>();
        list.add("ID");
        list.add("blogId");
        list.add("标题");
        list.add("内容");
        list.add("分类");
        list.add("类型");
        list.add("状态");
        list.add("公共的");
        list.add("删除");
        list.add("创建人");
        list.add("创建时间");
        list.add("跟新人");
        list.add("跟新时间");
        excelUtils.exportAnno(blogs,"dddd.xlsx","D:");
        //parseExcelService("D:/dddd.xlsx");
    }

}
