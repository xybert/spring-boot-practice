package com.xybert.springbooteasyexcel.util;

import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.read.listener.ReadListener;
import org.apache.poi.ss.formula.functions.T;

import java.io.File;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

/**
 * @author xybert
 * @description EasyExcel工具类
 * @date 2022/12/22 16:13
 */

public class EasyExcelUtils {

    // 读取数据量较大的Excel时，推荐使用异步的方式

    /**
     * 同步无模板读取
     * 默认读取sheet0
     * 默认表头占一行，从第2行开始读
     *
     * @param inputStream inputStream
     * @return List<Map<Integer, String>>
     */
    public static List<Map<Integer, String>> syncRead(InputStream inputStream) {
        return EasyExcelFactory.read(inputStream).sheet().doReadSync();
    }

    /**
     * 同步无模板读取
     *
     * @param file 文件
     * @return List<Map<Integer, String>>
     */
    public static List<Map<Integer, String>> syncRead(File file) {
        return EasyExcelFactory.read(file).sheet().doReadSync();
    }

    /**
     * 同步无模板读取
     *
     * @param filePath 文件路径
     * @return List<Map<Integer, String>>
     */
    public static List<Map<Integer, String>> syncRead(String filePath) {
        return EasyExcelFactory.read(filePath).sheet().doReadSync();
    }

    /**
     * 同步无模板读取
     * 指定sheet
     * sheetNo=0表示第一个sheet
     *
     * @param inputStream inputStream
     * @param sheetNo sheet页号
     * @return List<Map<Integer, String>>
     */
    public static List<Map<Integer, String>> syncRead(InputStream inputStream, Integer sheetNo) {
        return EasyExcelFactory.read(inputStream).sheet(sheetNo).doReadSync();
    }

    /**
     * 同步无模板读取
     * 指定sheet
     *
     * @param file 文件
     * @param sheetNo sheet页号
     * @return List<Map<Integer, String>>
     */
    public static List<Map<Integer, String>> syncRead(File file, Integer sheetNo) {
        return EasyExcelFactory.read(file).sheet(sheetNo).doReadSync();
    }

    /**
     * 同步无模板读取
     * 指定sheet
     *
     * @param filePath 文件路径
     * @param sheetNo sheet页号
     * @return List<Map<Integer, String>>
     */
    public static List<Map<Integer, String>> syncRead(String filePath, Integer sheetNo) {
        return EasyExcelFactory.read(filePath).sheet(sheetNo).doReadSync();
    }

    /**
     * 同步无模板读取
     * 指定sheet和表头行数
     * headNum=0表示从第一行开始读
     *
     * @param inputStream inputStream
     * @param sheetNo sheet页号
     * @param headRowNum 表头行数
     * @return List<Map<Integer, String>>
     */
    public static List<Map<Integer, String>> syncRead(InputStream inputStream, Integer sheetNo, Integer headRowNum) {
        return EasyExcelFactory.read(inputStream).sheet(sheetNo).headRowNumber(headRowNum).doReadSync();
    }

    /**
     * 同步无模板读取
     * 指定sheet和表头行数
     *
     * @param file 文件
     * @param sheetNo sheet页号
     * @param headRowNum 表头行数
     * @return List<Map<Integer, String>>
     */
    public static List<Map<Integer, String>> syncRead(File file, Integer sheetNo, Integer headRowNum) {
        return EasyExcelFactory.read(file).sheet(sheetNo).headRowNumber(headRowNum).doReadSync();
    }

    /**
     * 同步无模板读取
     * 指定sheet和表头行数
     *
     * @param filePath 文件路径
     * @param sheetNo sheet页号
     * @param headRowNum 表头行数
     * @return List<Map<Integer, String>>
     */
    public static List<Map<Integer, String>> syncRead(String filePath, Integer sheetNo, Integer headRowNum) {
        return EasyExcelFactory.read(filePath).sheet(sheetNo).headRowNumber(headRowNum).doReadSync();
    }

    /**
     * 同步模板读取
     *
     * @param inputStream inputStream
     * @param clazz 模板类
     * @return List<T>
     */
    public static <T> List<T> syncReadModel(InputStream inputStream, Class<T> clazz) {
        return EasyExcelFactory.read(inputStream).head(clazz).sheet().doReadSync();
    }

    /**
     * 同步模板读取
     *
     * @param file 文件
     * @param clazz 模板类
     * @return List<T>
     */
    public static <T> List<T> syncReadModel(File file, Class<T> clazz) {
        return EasyExcelFactory.read(file).head(clazz).sheet().doReadSync();
    }

    /**
     * 同步模板读取
     *
     * @param filePath 文件路径
     * @param clazz 模板类
     * @return List<T>
     */
    public static <T> List<T> syncReadModel(String filePath, Class<T> clazz) {
        return EasyExcelFactory.read(filePath).head(clazz).sheet().doReadSync();
    }

    /**
     * 同步模板读取
     * 指定sheet
     *
     * @param inputStream inputStream
     * @param clazz 模板类
     * @param sheetNo sheet页号
     * @return List<T>
     */
    public static <T> List<T> syncReadModel(InputStream inputStream, Class<T> clazz, Integer sheetNo) {
        return EasyExcelFactory.read(inputStream).head(clazz).sheet(sheetNo).doReadSync();
    }

    /**
     * 同步模板读取
     * 指定sheet
     *
     * @param file 文件
     * @param clazz 模板类
     * @param sheetNo sheet页号
     * @return List<T>
     */
    public static <T> List<T> syncReadModel(File file, Class<T> clazz, Integer sheetNo) {
        return EasyExcelFactory.read(file).head(clazz).sheet(sheetNo).doReadSync();
    }

    /**
     * 同步模板读取
     * 指定sheet
     *
     * @param filePath 文件路径
     * @param clazz 模板类
     * @param sheetNo sheet页号
     * @return List<T>
     */
    public static <T> List<T> syncReadModel(String filePath, Class<T> clazz, Integer sheetNo) {
        return EasyExcelFactory.read(filePath).head(clazz).sheet(sheetNo).doReadSync();
    }

    /**
     * 同步模板读取
     * 指定sheet和表头行数
     *
     * @param inputStream inputStream
     * @param clazz 模板类
     * @param sheetNo sheet页号
     * @return List<T>
     */
    public static <T> List<T> syncReadModel(InputStream inputStream, Class<T> clazz, Integer sheetNo, Integer headRowNum) {
        return EasyExcelFactory.read(inputStream).head(clazz).sheet(sheetNo).headRowNumber(headRowNum).doReadSync();
    }

    /**
     * 同步模板读取
     * 指定sheet和表头行数
     *
     * @param file 文件
     * @param clazz 模板类
     * @param sheetNo sheet页号
     * @return List<T>
     */
    public static <T> List<T> syncReadModel(File file, Class<T> clazz, Integer sheetNo, Integer headRowNum) {
        return EasyExcelFactory.read(file).head(clazz).sheet(sheetNo).headRowNumber(headRowNum).doReadSync();
    }

    /**
     * 同步模板读取
     * 指定sheet和表头行数
     *
     * @param filePath 文件路径
     * @param clazz 模板类
     * @param sheetNo sheet页号
     */
    public static void syncReadModel(String filePath, Class<T> clazz, Integer sheetNo, Integer headRowNum) {
        EasyExcelFactory.read(filePath).head(clazz).sheet(sheetNo).headRowNumber(headRowNum).doReadSync();
    }

    /**
     * 异步模板读取
     *
     * @param inputStream inputStream
     * @param clazz 模板类
     * @param readListener 监听器
     */
    public static <T> void asyncReadModel(InputStream inputStream, Class<T> clazz, ReadListener<T> readListener) {
        EasyExcelFactory.read(inputStream).head(clazz).registerReadListener(readListener).sheet().doRead();
    }

    /**
     * 异步模板读取
     *
     * @param file 文件
     * @param clazz 模板类
     * @param readListener 监听器
     */
    public static <T> void asyncReadModel(File file, Class<T> clazz, ReadListener<T> readListener) {
        EasyExcelFactory.read(file).head(clazz).registerReadListener(readListener).sheet().doRead();
    }

    /**
     * 异步模板读取
     *
     * @param filePath 文件路径
     * @param clazz 模板类
     * @param readListener 监听器
     */
    public static <T> void asyncReadModel(String filePath, Class<T> clazz, ReadListener<T> readListener) {
        EasyExcelFactory.read(filePath).head(clazz).registerReadListener(readListener).sheet().doRead();
    }

    /**
     * 异步模板读取
     * 指定sheet
     *
     * @param inputStream inputStream
     * @param clazz 模板类
     * @param readListener 监听器
     * @param sheetNo sheet页号
     */
    public static <T> void asyncReadModel(InputStream inputStream, Class<T> clazz, ReadListener<T> readListener, Integer sheetNo) {
        EasyExcelFactory.read(inputStream).head(clazz).registerReadListener(readListener).sheet(sheetNo).doRead();
    }

    /**
     * 异步模板读取
     * 指定sheet
     *
     * @param file 文件
     * @param clazz 模板类
     * @param readListener 监听器
     * @param sheetNo sheet页号
     */
    public static <T> void asyncReadModel(File file, Class<T> clazz, ReadListener<T> readListener, Integer sheetNo) {
        EasyExcelFactory.read(file).head(clazz).registerReadListener(readListener).sheet(sheetNo).doRead();
    }

    /**
     * 异步模板读取
     * 指定sheet
     *
     * @param filePath 文件路径
     * @param clazz 模板类
     * @param readListener 监听器
     * @param sheetNo sheet页号
     */
    public static <T> void asyncReadModel(String filePath, Class<T> clazz, ReadListener<T> readListener, Integer sheetNo) {
        EasyExcelFactory.read(filePath).head(clazz).registerReadListener(readListener).sheet(sheetNo).doRead();
    }

    /**
     * 异步模板读取
     * 指定sheet和表头行数
     *
     * @param inputStream inputStream
     * @param clazz 模板类
     * @param readListener 监听器
     * @param sheetNo sheet页号
     * @param headRowNum 表头行数
     */
    public static <T> void asyncReadModel(InputStream inputStream, Class<T> clazz, ReadListener<T> readListener,
                                          Integer sheetNo, Integer headRowNum) {
        EasyExcelFactory.read(inputStream).head(clazz).registerReadListener(readListener).sheet(sheetNo)
                .headRowNumber(headRowNum).doRead();
    }

    /**
     * 异步模板读取
     * 指定sheet和表头行数
     *
     * @param file 文件
     * @param clazz 模板类
     * @param readListener 监听器
     * @param sheetNo sheet页号
     * @param headRowNum 表头行数
     */
    public static <T> void asyncReadModel(File file, Class<T> clazz, ReadListener<T> readListener,
                                          Integer sheetNo, Integer headRowNum) {
        EasyExcelFactory.read(file).head(clazz).registerReadListener(readListener).sheet(sheetNo)
                .headRowNumber(headRowNum).doRead();
    }

    /**
     * 异步模板读取
     * 指定sheet和表头行数
     *
     * @param filePath 文件路径
     * @param clazz 模板类
     * @param readListener 监听器
     * @param sheetNo sheet页号
     * @param headRowNum 表头行数
     */
    public static <T> void asyncReadModel(String filePath, Class<T> clazz, ReadListener<T> readListener,
                                          Integer sheetNo, Integer headRowNum) {
        EasyExcelFactory.read(filePath).head(clazz).registerReadListener(readListener).sheet(sheetNo)
                .headRowNumber(headRowNum).doRead();
    }
}
