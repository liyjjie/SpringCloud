package com.jack.utils.execl;

import com.monitorjbl.xlsx.StreamingReader;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author ：liyongjie
 * @ClassName ：ExcelImportUtils
 * @date ： 2019-11-27 14:26
 * @modified By：
 */
//execl导出
public class ExcelImportUtils {

    public ExcelImportUtils() {
    }

    public static <T> List<T> doExcelImport(Class<T> clazz, List<ExcelImportParam> params, MultipartFile file, int sheetIdex) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException, ExcelException {
        return doExcelImport(clazz, params, getWorkbook(file), sheetIdex);
    }

    public static <T> List<T> doExcelImport(Class<T> clazz, List<ExcelImportParam> params, File file, int sheetIdex) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException, ExcelException {
        return doExcelImport(clazz, params, getWorkbook(file), sheetIdex);
    }

    private static Workbook getWorkbook(MultipartFile file) throws ExcelException {
        String fileName = file.getOriginalFilename();
        String[] fileNameArray = fileName.split("\\.");
        if (fileNameArray.length > 1) {
            try {
                String fix = fileNameArray[fileNameArray.length - 1].trim().toLowerCase();
                if ("xls".equals(fix.trim().toLowerCase())) {
                    return new HSSFWorkbook(file.getInputStream());
                }

                if ("xlsx".equals(fix.trim().toLowerCase())) {
                    Workbook wb = StreamingReader.builder().rowCacheSize(100).bufferSize(4096).open(file.getInputStream());
                    return wb;
                }
            } catch (Exception var5) {
                throw new ExcelException("excel导入失败", var5);
            }
        }

        throw new ExcelException("excel导入失败,请选择后缀为.xls或.xlsx的文件");
    }

    private static Workbook getWorkbook(File file) throws ExcelException {
        String fileName = file.getName();
        String[] fileNameArray = fileName.split("\\.");
        if (fileNameArray.length > 1) {
            try {
                String fix = fileNameArray[fileNameArray.length - 1].trim().toLowerCase();
                if ("xls".equals(fix.trim().toLowerCase())) {
                    return new HSSFWorkbook(new FileInputStream(file));
                }

                if ("xlsx".equals(fix.trim().toLowerCase())) {
                    FileInputStream fileInputStream = new FileInputStream(file);
                    Workbook wb = StreamingReader.builder().rowCacheSize(100).bufferSize(4096).open(fileInputStream);
                    return wb;
                }
            } catch (Exception var6) {
                throw new ExcelException("excel导入失败", var6);
            }
        }

        throw new ExcelException("excel导入失败,请选择后缀为.xls或.xlsx的文件");
    }

    public static <T> List<T> doExcelImport(Class<T> clazz, List<ExcelImportParam> params, Workbook data, int sheetIdex) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException, ExcelException {
        Sheet sheet = data.getSheetAt(sheetIdex);
        Iterator<Row> i = sheet.rowIterator();
        List<T> list = new ArrayList();
        if (i.hasNext()) {
            i.next();
        }

        while (i.hasNext()) {
            Row row = (Row) i.next();

            Object entity;
            try {
                entity = clazz.newInstance();
            } catch (Exception var11) {
                throw new ExcelException("excel导入失败", var11);
            }

            Iterator var9 = params.iterator();

            while (var9.hasNext()) {
                ExcelImportParam param = (ExcelImportParam) var9.next();
                param.setValue(entity, row);
            }

            list.add((T) entity);
        }

        return list;

    }

}
