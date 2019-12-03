package com.jack.utils.execl;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.util.*;

/**
 * @author ：liyongjie
 * @ClassName ：NewExcelExportUtils
 * @date ： 2019-11-27 14:31
 * @modified By：
 */
public class NewExcelExportUtils {

    public static final String PATTEERN_DATE = "yyyy-MM-dd";
    public static final String PATTEERN_TIME = "yyyy-MM-dd mm:ss";
    public static String CUERRENCE_FORMATE = "###,##0.00";
    protected static final ThreadLocal<CellStyle> TITLE_STYLE = new ThreadLocal();
    protected static final ThreadLocal<CellStyle> CELL_STYLE = new ThreadLocal();
    protected static final ThreadLocal<CellStyle> DATE_CELL_STYLE = new ThreadLocal();

    public NewExcelExportUtils() {
    }

    public static Cell generateCell(Workbook workbook, Row row, Integer index, Object value, CellStyle style) {
        Cell cell = row.createCell(index);
        setCellValue(workbook, cell, value, style);
        return cell;
    }

    public static CellStyle getCellStyle(Workbook workbook) {
        CellStyle cellStyle = (CellStyle) CELL_STYLE.get();
        if (cellStyle == null) {
            cellStyle = generateCellStyle(workbook);
            CELL_STYLE.set(cellStyle);
        }

        return cellStyle;
    }

    public static CellStyle getTitleCellStyle(Workbook workbook) {
        return workbook.createCellStyle();
    }

    public static CellStyle getDateCellStyle(Workbook workbook) {
        CellStyle cellStyle = (CellStyle) DATE_CELL_STYLE.get();
        if (cellStyle == null) {
            cellStyle = generateDateCellStyle(workbook);
            DATE_CELL_STYLE.set(cellStyle);
        }

        return cellStyle;
    }

    private static CellStyle generateCellStyle(Workbook workbook) {
        Font font = workbook.createFont();
        font.setFontName("微软雅黑");
        CellStyle style = workbook.createCellStyle();
        style.setAlignment((short) 1);
        style.setFont(font);
        return style;
    }

    private static CellStyle generateTitleCellStyle(Workbook workbook) {
        Font font = workbook.createFont();
        font.setFontName("微软雅黑");
        font.setBoldweight((short) 700);
        font.setColor(IndexedColors.DARK_BLUE.getIndex());
        font.setFontHeight((short) 300);
        CellStyle style = workbook.createCellStyle();
        style.setAlignment((short) 1);
        style.setFont(font);
        return style;
    }

    private static CellStyle generateDateCellStyle(Workbook workbook) {
        return generateDateCellStyle(workbook, "yyyy-MM-dd");
    }

    public static CellStyle generateDateCellStyle(Workbook workbook, String patteern) {
        CellStyle style = workbook.createCellStyle();
        style.setDataFormat(workbook.createDataFormat().getFormat(patteern));
        return style;
    }

    private static void setCellValue(Workbook workbook, Cell cell, Object value, CellStyle style) {
        if (style != null) {
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
            }
        } else if (value instanceof Calendar) {
            cell.setCellValue((Calendar) value);
        } else {
            cell.setCellValue(value == null ? "" : value.toString());
        }

    }

    public static Workbook generateExcelFile(List<ExcelExportParam> params, List datas) {
        return generateExcelFile(new SXSSFWorkbook(2000), params, datas);
    }

    public static Workbook generateExcelFile(Workbook workbook, List<ExcelExportParam> params, List datas) {
        try {
            if (workbook instanceof SXSSFWorkbook) {
                ((SXSSFWorkbook) workbook).setCompressTempFiles(true);
            }

            Map<Integer, ExcelExportParam> propRefs = new TreeMap();
            Sheet sheet = workbook.createSheet();
            int[] arrColWidth = new int[params.size()];
            int minBytes = 17;
            Row row = sheet.createRow(0);
            row.setHeight((short) 500);
            Iterator<ExcelExportParam> iter = params.iterator();

            for (int i = 0; iter.hasNext(); ++i) {
                ExcelExportParam param = (ExcelExportParam) iter.next();
                int bytes = param.getTitleName().length();
                arrColWidth[i] = bytes < minBytes ? minBytes : bytes;
                generateCell(workbook, row, i, param.getTitle(), getTitleCellStyle(workbook));
                propRefs.put(new Integer(i), param);
                sheet.setColumnWidth(i, arrColWidth[i] * 256);
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
                    generateCell(workbook, row, key, exportParam.getValue(pojo), (CellStyle) null);
                }
            }
        } catch (Exception var18) {
            throw var18;
        } finally {
            resetCellStyle();
        }

        return workbook;
    }

    public static void resetCellStyle() {
        TITLE_STYLE.remove();
        CELL_STYLE.remove();
        DATE_CELL_STYLE.remove();
    }

    public static void exportExcelToClient(Workbook wb, String fileName, HttpServletResponse response) throws IOException {
        String fileNameUtf8 = URLEncoder.encode(fileName, "UTF-8");
        response.setContentType("application/msexcel;charset=utf-8");
        response.addHeader("Content-Disposition", "attachment;filename=\"" + fileNameUtf8 + "\"");
        BufferedOutputStream out = new BufferedOutputStream(response.getOutputStream());
        wb.write(out);
        out.flush();
        out.close();
    }
}
