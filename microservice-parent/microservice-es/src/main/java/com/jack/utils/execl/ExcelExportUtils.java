package com.jack.utils.execl;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

/**
 * @author ：liyongjie
 * @ClassName ：ExcelExportUtils
 * @date ： 2019-11-27 14:23
 * @modified By：
 */
public class ExcelExportUtils {

    public static final String PATTEERN_DATE = "yyyy-MM-dd";
    public static final String PATTEERN_TIME = "yyyy-MM-dd mm:ss";
    public static String CUERRENCE_FORMATE = "###,##0.00";
    protected static final ThreadLocal<HSSFCellStyle> TITLE_STYLE = new ThreadLocal();
    protected static final ThreadLocal<HSSFCellStyle> CELL_STYLE = new ThreadLocal();
    protected static final ThreadLocal<HSSFCellStyle> DATE_CELL_STYLE = new ThreadLocal();

    public ExcelExportUtils() {
    }

    public static HSSFCell generateCell(HSSFWorkbook workbook, HSSFRow row, Integer index, Object value, HSSFCellStyle style) {
        HSSFCell cell = row.createCell(index);
        setCellValue(workbook, cell, value, style);
        return cell;
    }

    public static HSSFCellStyle getCellStyle(HSSFWorkbook workbook) {
        HSSFCellStyle cellStyle = (HSSFCellStyle) CELL_STYLE.get();
        if (cellStyle == null) {
            cellStyle = generateCellStyle(workbook);
            CELL_STYLE.set(cellStyle);
        }

        return cellStyle;
    }

    public static HSSFCellStyle getTitleCellStyle(HSSFWorkbook workbook) {
        HSSFCellStyle cellStyle = (HSSFCellStyle) TITLE_STYLE.get();
        if (cellStyle == null) {
            cellStyle = generateTitleCellStyle(workbook);
            TITLE_STYLE.set(cellStyle);
        }

        return cellStyle;
    }

    public static HSSFCellStyle getDateCellStyle(HSSFWorkbook workbook) {
        HSSFCellStyle cellStyle = (HSSFCellStyle) DATE_CELL_STYLE.get();
        if (cellStyle == null) {
            cellStyle = generateDateCellStyle(workbook);
            DATE_CELL_STYLE.set(cellStyle);
        }

        return cellStyle;
    }

    private static HSSFCellStyle generateCellStyle(HSSFWorkbook workbook) {
        HSSFFont font = workbook.createFont();
        font.setFontName("微软雅黑");
        HSSFCellStyle style = workbook.createCellStyle();
        style.setAlignment((short) 1);
        style.setFont(font);
        return style;
    }

    private static HSSFCellStyle generateTitleCellStyle(HSSFWorkbook workbook) {
        HSSFFont font = workbook.createFont();
        font.setFontName("微软雅黑");
        font.setBoldweight((short) 700);
        font.setColor((short) 18);
        font.setFontHeight((short) 300);
        HSSFCellStyle style = workbook.createCellStyle();
        style.setAlignment((short) 1);
        style.setFont(font);
        return style;
    }

    private static HSSFCellStyle generateDateCellStyle(HSSFWorkbook workbook) {
        return generateDateCellStyle(workbook, "yyyy-MM-dd");
    }

    public static HSSFCellStyle generateDateCellStyle(HSSFWorkbook workbook, String patteern) {
        HSSFCellStyle style = workbook.createCellStyle();
        style.setDataFormat(workbook.createDataFormat().getFormat(patteern));
        return style;
    }

    private static void setCellValue(HSSFWorkbook workbook, HSSFCell cell, Object value, HSSFCellStyle style) {
        cell.setCellStyle(getCellStyle(workbook));
        if (style != null) {
            cell.setCellStyle(style);
        }

        if (value instanceof Boolean) {
            cell.setCellValue((Boolean) value);
        } else if (value instanceof Double) {
            cell.setCellValue((Double) value);
        } else if (value instanceof Float) {
            cell.setCellValue(((Float) value).doubleValue());
        } else if (value instanceof BigDecimal) {
            cell.setCellValue(((BigDecimal) value).doubleValue());
        } else if (value instanceof Integer) {
            cell.setCellValue((double) (Integer) value);
        } else if (value instanceof Long) {
            cell.setCellValue((double) (Long) value);
        } else if (value instanceof Date) {
            cell.setCellValue((Date) value);
            if (style == null) {
                cell.setCellStyle(getDateCellStyle(workbook));
            }
        } else if (value instanceof Calendar) {
            cell.setCellValue((Calendar) value);
        } else {
            cell.setCellValue(value == null ? "" : value.toString());
        }

    }

    public static HSSFWorkbook generateExcelFile(List<ExcelExportParam> params, List datas) {
        return generateExcelFile(new HSSFWorkbook(), params, datas);
    }

    public static HSSFWorkbook generateExcelFile(HSSFWorkbook workbook, List<ExcelExportParam> params, List datas) {
        Map<Integer, ExcelExportParam> propRefs = new TreeMap();
        HSSFSheet sheet = workbook.createSheet();
        HSSFRow row = sheet.createRow(0);
        row.setHeight((short) 500);
        Iterator<ExcelExportParam> iter = params.iterator();

        for (int i = 0; iter.hasNext(); ++i) {
            ExcelExportParam param = (ExcelExportParam) iter.next();
            generateCell(workbook, row, i, param.getTitle(), getTitleCellStyle(workbook));
            propRefs.put(new Integer(i), param);
            sheet.autoSizeColumn(i, true);
        }

        Iterator dataIter = datas.iterator();

        for (int i = 0; dataIter.hasNext(); ++i) {
            Object pojo = dataIter.next();
            row = sheet.createRow(i + 1);
            row.setHeight((short) 500);
            Iterator ki = propRefs.keySet().iterator();

            while (ki.hasNext()) {
                Integer key = (Integer) ki.next();
                ExcelExportParam exportParam = (ExcelExportParam) propRefs.get(key);
                generateCell(workbook, row, key, exportParam.getValue(pojo), (HSSFCellStyle) null);
            }
        }

        resetCellStyle();
        return workbook;
    }

    public static void resetCellStyle() {
        TITLE_STYLE.remove();
        CELL_STYLE.remove();
        DATE_CELL_STYLE.remove();
    }

    public static void exportExcelToClient(HSSFWorkbook wb, String fileName, HttpServletResponse response) throws IOException {
        String fileNameUtf8 = URLEncoder.encode(fileName, "UTF-8");
        response.setContentType("application/msexcel;charset=utf-8");
        response.addHeader("Content-Disposition", "attachment;filename=\"" + fileNameUtf8 + "\"");
        BufferedOutputStream out = new BufferedOutputStream(response.getOutputStream());
        wb.write(out);
        out.flush();
        out.close();
    }
}
