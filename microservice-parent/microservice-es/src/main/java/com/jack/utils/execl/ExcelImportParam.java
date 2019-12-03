package com.jack.utils.execl;

import com.jack.utils.MessageReader;
import org.apache.commons.beanutils.MethodUtils;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;

/**
 * @author ：liyongjie
 * @ClassName ：ExcelImportParam
 * @date ： 2019-11-27 14:25
 * @modified By：
 */
public class ExcelImportParam {

    public static final int VALIDATE_TYPE_DEFAULT = 1;
    public static final int VALIDATE_TYPE_METHOD = 2;
    public static final int VALIDATE_TYPE_REGEX = 3;
    public static final int PROPERTY_VALUE_TYPE_DEFAULT = 1;
    public static final int PROPERTY_VALUE_TYPE_METHOD = 2;
    private int cellIndex;
    private String propertyName;
    private boolean nullable;
    private String nullValueInterKey;
    private String validateKey;
    private String validateFailInterKey;
    private int validateType = 1;
    private int propertyValueType = 1;
    private String propertyValueKey;
    private Object nullValue;

    public ExcelImportParam() {
    }

    public ExcelImportParam(int cellIndex, String propertyName, boolean nullable) {
        this.cellIndex = cellIndex;
        this.propertyName = propertyName;
        this.nullable = nullable;
    }

    public ExcelImportParam(int cellIndex, String propertyName, boolean nullable, String nullValueInterKey, String validateKey, String validateFailInterKey, int validateType) {
        this.cellIndex = cellIndex;
        this.propertyName = propertyName;
        this.nullable = nullable;
        this.nullValueInterKey = nullValueInterKey;
        this.validateKey = validateKey;
        this.validateFailInterKey = validateFailInterKey;
        this.validateType = validateType;
    }

    public ExcelImportParam(int cellIndex, String propertyName, boolean nullable, String nullValueInterKey, String validateKey, String validateFailInterKey, int validateType, int propertyValueType, String propertyValueKey) {
        this.cellIndex = cellIndex;
        this.propertyName = propertyName;
        this.nullable = nullable;
        this.nullValueInterKey = nullValueInterKey;
        this.validateKey = validateKey;
        this.validateFailInterKey = validateFailInterKey;
        this.validateType = validateType;
        this.propertyValueType = propertyValueType;
        this.propertyValueKey = propertyValueKey;
    }

    public ExcelImportParam(int cellIndex, String propertyName, boolean nullable, String nullValueInterKey, String validateKey, String validateFailInterKey, int validateType, int propertyValueType, String propertyValueKey, String nullValue) {
        this.cellIndex = cellIndex;
        this.propertyName = propertyName;
        this.nullable = nullable;
        this.nullValueInterKey = nullValueInterKey;
        this.validateKey = validateKey;
        this.validateFailInterKey = validateFailInterKey;
        this.validateType = validateType;
        this.propertyValueType = propertyValueType;
        this.propertyValueKey = propertyValueKey;
        this.nullValue = nullValue;
    }

    public int getCellIndex() {
        return this.cellIndex;
    }

    public void setCellIndex(int cellIndex) {
        this.cellIndex = cellIndex;
    }

    public String getPropertyName() {
        return this.propertyName;
    }

    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }

    public boolean isNullable() {
        return this.nullable;
    }

    public void setNullable(boolean nullable) {
        this.nullable = nullable;
    }

    public String getNullValueInterKey() {
        return this.nullValueInterKey;
    }

    public void setNullValueInterKey(String nullNullInterKey) {
        this.nullValueInterKey = nullNullInterKey;
    }

    public String getValidateKey() {
        return this.validateKey;
    }

    public void setValidateKey(String validateKey) {
        this.validateKey = validateKey;
    }

    public String getValidateFailInterKey() {
        return this.validateFailInterKey;
    }

    public void setValidateFailInterKey(String validateFailInterKey) {
        this.validateFailInterKey = validateFailInterKey;
    }

    public int getValidateType() {
        return this.validateType;
    }

    public void setValidateType(int validateType) {
        this.validateType = validateType;
    }

    public int getPropertyValueType() {
        return this.propertyValueType;
    }

    public void setPropertyValueType(int propertyValueType) {
        this.propertyValueType = propertyValueType;
    }

    public String getPropertyValueKey() {
        return this.propertyValueKey;
    }

    public void setPropertyValueKey(String propertyValueKey) {
        this.propertyValueKey = propertyValueKey;
    }

    public Object getNullValue() {
        return this.nullValue;
    }

    public void setNullValue(Object nullValue) {
        this.nullValue = nullValue;
    }

    public void setValue(Object entity, Row row) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException, ExcelException {
        Class<?> propertyType = PropertyUtils.getPropertyType(entity, this.getPropertyName());
        Cell cell = row.getCell(this.getCellIndex());
        Object cellValue = null;
        if (cell != null) {
            cellValue = this.getCellValue(cell, propertyType);
        }

        if (cellValue == null && !this.isNullable()) {
            throw new ExcelException(MessageReader.getMessage(this.getNullValueInterKey(), new String[]{String.valueOf(row.getRowNum() + 1)}));
        } else {
            this.doValidate(entity, cellValue, row.getRowNum() + 1);
            cellValue = this.getValue(entity, cellValue);
            PropertyUtils.setProperty(entity, this.getPropertyName(), cellValue);
        }
    }

    private Object getCellValue(Cell cell, Class<?> propertyType) throws ExcelException {
        Object cellValue = this.getCellValue(cell);
        if (propertyType.isAssignableFrom(Boolean.class)) {
            cellValue = cellValue == null ? null : Boolean.valueOf(cellValue.toString());
        } else if (propertyType.isAssignableFrom(Short.class)) {
            cellValue = cellValue == null ? null : Short.valueOf(cellValue.toString());
        } else if (propertyType.isAssignableFrom(Integer.class)) {
            cellValue = cellValue == null ? null : Integer.valueOf(cellValue.toString());
        } else if (propertyType.isAssignableFrom(Long.class)) {
            cellValue = cellValue == null ? null : Long.valueOf(cellValue.toString());
        } else if (propertyType.isAssignableFrom(Float.class)) {
            cellValue = cellValue == null ? null : Float.valueOf(cellValue.toString());
        } else if (propertyType.isAssignableFrom(Double.class)) {
            cellValue = cellValue == null ? null : Double.valueOf(cellValue.toString());
        } else if (propertyType.isAssignableFrom(Byte.class)) {
            cellValue = cellValue == null ? null : Byte.valueOf(cellValue.toString());
        } else if (propertyType.isAssignableFrom(Character.class)) {
            cellValue = cellValue == null ? null : (Character) cellValue;
        } else if (propertyType.isAssignableFrom(String.class)) {
            cellValue = cellValue == null ? null : String.valueOf(cellValue.toString());
        } else {
            if (!propertyType.isAssignableFrom(BigDecimal.class)) {
                if (this.propertyValueType == 2 && this.propertyValueKey != null) {
                    return cellValue;
                }

                throw new ExcelException("excel导入失败,未知类型:" + propertyType.getClass());
            }

            cellValue = cellValue == null ? null : new BigDecimal(cellValue.toString());
        }

        return cellValue;
    }

    private Object getCellValue(Cell cell) {
        return cell.getStringCellValue();
    }

    private Object getValue(Object entity, Object cellValue) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        Object value = null;
        switch (this.getPropertyValueType()) {
            case 1:
                value = cellValue;
                break;
            case 2:
                value = cellValue == null ? null : MethodUtils.invokeMethod(entity, this.getPropertyValueKey(), cellValue);
        }

        return value == null ? this.getNullValue() : value;
    }

    private void doValidate(Object entity, Object cellValue, int rowNum) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, ExcelException {
        switch (this.getValidateType()) {
            case 1:
            default:
                break;
            case 2:
                Object reuslt = MethodUtils.invokeMethod(entity, this.getValidateKey(), cellValue);
                if (reuslt != null && reuslt instanceof String) {
                    throw new ExcelException(MessageReader.getMessage(reuslt.toString(), new String[]{String.valueOf(rowNum)}));
                }
                break;
            case 3:
                if (cellValue != null && !cellValue.toString().matches(this.getValidateKey())) {
                    throw new ExcelException(MessageReader.getMessage(this.getValidateFailInterKey(), new String[]{String.valueOf(rowNum)}));
                }
        }

    }
}
