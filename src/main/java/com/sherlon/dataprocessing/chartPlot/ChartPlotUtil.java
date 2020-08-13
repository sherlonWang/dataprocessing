package com.sherlon.dataprocessing.chartPlot;

import cn.hutool.core.util.StrUtil;
import com.sherlon.dataprocessing.common.CommonTools;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.StandardChartTheme;
import org.jfree.chart.block.BlockBorder;
import org.jfree.chart.plot.DefaultDrawingSupplier;
import org.jfree.chart.plot.PieLabelLinkStyle;
import org.jfree.chart.renderer.category.StandardBarPainter;
import org.jfree.chart.renderer.xy.StandardXYBarPainter;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.RectangleInsets;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.*;
import java.util.List;

/**
 * @author :  sherlonWang
 * @description :  jFreeChart图表绘制工具类
 * @date: 2020-08-12
 */
public class ChartPlotUtil implements Serializable {
    // px和emu单位之间的转换比
    public static final int EMU_PER_PX = 9525;
    // 默认除法运算精度
    private static final Integer DEF_DIV_SCALE = 2;
    // 默认生成图片宽度
    private static int WIDTH = 600;
    // 默认生成图片高度
    private static int HEIGHT = 400;
    // 字体
    private static Font FONT = new Font("宋体", Font.PLAIN, 12);
    // 颜色
    public static Color[] CHART_COLORS = {
            new Color(31, 129, 188), new Color(92, 92, 97), new Color(144, 237, 125), new Color(255, 188, 117),
            new Color(153, 158, 255), new Color(255, 117, 153), new Color(253, 236, 109), new Color(128, 133, 232),
            new Color(158, 90, 102), new Color(255, 204, 102)};//颜色


    static {
        // 初始化主题配置
        initChartTheme();
    }

    /**
     * 初始化主题样式
     */
    public static void initChartTheme() {
        System.out.println("初始化图表配置...");
        // 设置中文主题样式 解决乱码
        StandardChartTheme chartTheme = new StandardChartTheme("CN");
        // 设置标题字体
        chartTheme.setExtraLargeFont(FONT);
        // 设置图例的字体
        chartTheme.setRegularFont(FONT);
        // 设置轴向的字体
        chartTheme.setLargeFont(FONT);
        chartTheme.setSmallFont(FONT);
        chartTheme.setTitlePaint(new Color(51, 51, 51));
        chartTheme.setSubtitlePaint(new Color(85, 85, 85));

        chartTheme.setLegendBackgroundPaint(Color.WHITE);// 设置标注
        chartTheme.setLegendItemPaint(Color.BLACK);//
        chartTheme.setChartBackgroundPaint(Color.WHITE);
        // 绘制颜色绘制颜色.轮廓供应商
        // paintSequence,outlinePaintSequence,strokeSequence,outlineStrokeSequence,shapeSequence

        Paint[] OUTLINE_PAINT_SEQUENCE = new Paint[]{Color.WHITE};
        // 绘制器颜色源
        DefaultDrawingSupplier drawingSupplier = new DefaultDrawingSupplier(CHART_COLORS, CHART_COLORS, OUTLINE_PAINT_SEQUENCE,
                DefaultDrawingSupplier.DEFAULT_STROKE_SEQUENCE, DefaultDrawingSupplier.DEFAULT_OUTLINE_STROKE_SEQUENCE,
                DefaultDrawingSupplier.DEFAULT_SHAPE_SEQUENCE);
        chartTheme.setDrawingSupplier(drawingSupplier);

        chartTheme.setPlotBackgroundPaint(Color.WHITE);// 绘制区域
        chartTheme.setPlotOutlinePaint(Color.WHITE);// 绘制区域外边框
        chartTheme.setLabelLinkPaint(new Color(8, 55, 114));// 链接标签颜色
        chartTheme.setLabelLinkStyle(PieLabelLinkStyle.CUBIC_CURVE);

        chartTheme.setAxisOffset(new RectangleInsets(5, 12, 5, 12));
        chartTheme.setDomainGridlinePaint(new Color(192, 208, 224));// X坐标轴垂直网格颜色
        chartTheme.setRangeGridlinePaint(new Color(192, 192, 192));// Y坐标轴水平网格颜色

        chartTheme.setBaselinePaint(Color.WHITE);
        chartTheme.setCrosshairPaint(Color.BLUE);// 不确定含义
        chartTheme.setAxisLabelPaint(new Color(51, 51, 51));// 坐标轴标题文字颜色
        chartTheme.setTickLabelPaint(new Color(67, 67, 72));// 刻度数字
        chartTheme.setBarPainter(new StandardBarPainter());// 设置柱状图渲染
        chartTheme.setXYBarPainter(new StandardXYBarPainter());// XYBar 渲染

        chartTheme.setItemLabelPaint(Color.black);
        chartTheme.setThermometerPaint(Color.white);// 温度计

        ChartFactory.setChartTheme(chartTheme);
    }

    /**
     * 绘制xy数值型曲线图
     * @param title      图表标题
     * @param xAxisLabel x轴标题
     * @param yAxisLabel y轴标题
     * @param data       数据 {"series1":[{"x":1.0,"y":2.0},{"x":2.0,"y":3.0},...],"series1":[{"x":1.0,"y":2.0},{"x":2.0,"y":3.0},...]}
     * @param outputPath 图片输出路径
     */
    public static void createXYLineChart(String title, String xAxisLabel, String yAxisLabel, Map<String, List<Map<String, String>>> data, String outputPath) {
        createXYLineChart(title,xAxisLabel,yAxisLabel,data,outputPath,true);
    }

    /**
     * 绘制xy数值型曲线图
     * @param title      图表标题
     * @param xAxisLabel x轴标题
     * @param yAxisLabel y轴标题
     * @param data       数据 {"series1":[{"x":1.0,"y":2.0},{"x":2.0,"y":3.0},...],"series1":[{"x":1.0,"y":2.0},{"x":2.0,"y":3.0},...]}
     * @param outputPath 图片输出路径
     * @param withLegend 是否显示图例
     */
    public static void createXYLineChart(String title, String xAxisLabel, String yAxisLabel, Map<String, List<Map<String, String>>> data, String outputPath,Boolean withLegend) {

        if (data == null || data.size() == 0) {
            throw new RuntimeException("数据不能为空");
        }
        if (outputPath == null || StrUtil.isBlank(outputPath)) {
            throw new RuntimeException("图片输出路径不能为空");
        }
        // 多条曲线数据集
        XYSeriesCollection xyDataset = new XYSeriesCollection();
        // 遍历数据，生成数据集
        for (Map.Entry<String, List<Map<String, String>>> entry : data.entrySet()) {
            // 曲线名称 每条曲线对应一个series
            String seriesName = entry.getKey();
            List<Map<String, String>> xyData = entry.getValue();
            XYSeries series = new XYSeries(seriesName);
            for (Map<String, String> map : xyData) {
                Number xValue = Double.valueOf(map.get("x"));
                Number yValue = Double.valueOf(map.get("y"));
                series.add(xValue, yValue);
            }
            xyDataset.addSeries(series);
        }

        JFreeChart chart = ChartFactory.createXYLineChart(title, xAxisLabel, yAxisLabel, xyDataset);
        if (withLegend == null || withLegend) {
            // 设置标注无边框
            chart.getLegend().setFrame(new BlockBorder(Color.WHITE));
        }else {
            // 移除legend
            chart.removeLegend();
        }
        // 保存图片到本地
        saveChartForJPEG(outputPath,chart);
    }


    /**
     * 绘制分类型曲线图
     * @param title      图表标题
     * @param xAxisLabel x轴标题
     * @param yAxisLabel y轴标题
     * @param data       数据 {"row1":[{"x":"一月","y":2.0},{"x":"二月","y":3.0},...],"row2":[{"x":"一月","y":2.0},{"x":"二月","y":3.0},...]}
     * @param outputPath 图片输出路径
     * @param withLegend 是否显示图例
     */
    public static void createCategoryLineChart(String title, String xAxisLabel, String yAxisLabel, Map<String, List<Map<String, String>>> data, String outputPath,Boolean withLegend) {
        if (data == null || data.size() == 0) {
            throw new RuntimeException("数据不能为空");
        }
        if (outputPath == null || StrUtil.isBlank(outputPath)) {
            throw new RuntimeException("图片输出路径不能为空");
        }
        // 数据集
        DefaultCategoryDataset mDataset = new DefaultCategoryDataset();
        // 遍历数据，生成数据集
        for (Map.Entry<String, List<Map<String, String>>> entry : data.entrySet()) {
            // 曲线名称 每条曲线对应一个series
            String rowKey = entry.getKey();
            List<Map<String, String>> xyData = entry.getValue();
            for (Map<String, String> map : xyData) {
                Number value = Double.valueOf(map.get("y"));
                String columnKey = map.get("x");
                mDataset.addValue(value,rowKey,columnKey);
            }
        }
        JFreeChart chart = ChartFactory.createLineChart(title,xAxisLabel,yAxisLabel,mDataset);
        if (withLegend == null || withLegend) {
            // 设置标注无边框
            chart.getLegend().setFrame(new BlockBorder(Color.WHITE));
        }else {
            // 移除legend
            chart.removeLegend();
        }
        // 保存图片到本地
        saveChartForJPEG(outputPath,chart);

    }

    /**
     * 绘制分类型曲线图
     * @param title      图表标题
     * @param xAxisLabel x轴标题
     * @param yAxisLabel y轴标题
     * @param data       数据 {"row1":[{"x":"一月","y":2.0},{"x":"二月","y":3.0},...],"row2":[{"x":"一月","y":2.0},{"x":"二月","y":3.0},...]}
     * @param outputPath 图片输出路径
     */
    public static void createCategoryLineChart(String title, String xAxisLabel, String yAxisLabel, Map<String, List<Map<String, String>>> data, String outputPath) {
        createCategoryLineChart(title,xAxisLabel,yAxisLabel,data,outputPath,true);
    }

    /**
     * 保存图片至本地
     * @param outputPath 图片输出路径
     * @param chart chart
     */
    public static void saveChartForJPEG(String outputPath,JFreeChart chart){
        if (outputPath == null || StrUtil.isBlank(outputPath)) {
            throw new RuntimeException("图片输出路径不能为空");
        }
        File outFile = new File(outputPath);
        try {
            ChartUtilities.saveChartAsJPEG(outFile, chart, WIDTH, HEIGHT);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 转换mongo服务中的数据为绘图需要的数据结构
     * @param mongoData mongo服务返回的数据
     * @return
     */
    public static Map<String,List<Map<String,String>>> mongoDataTransform(List<Map<String,String>> mongoData){
        if (mongoData == null || mongoData.size() == 0) {
            return new HashMap<>();
        }
        Map<String,List<Map<String,String>>> result = new HashMap<>();
        List<String> keys = new ArrayList<>();
        // 如果x轴或y轴为空，则默认取第一个字段为x轴，后面的字段为y轴
        for (String key: mongoData.get(0).keySet()) {
            // 除去_id字段
            if (!"_id".equals(key)) {
                keys.add(key);
            }
        }
        String xAxis = keys.get(0);
        String[] yAxises = new String[keys.size()-1];
        for (int i = 0;i<keys.size()-1;i++) {
            yAxises[i] = keys.get(i+1);
        }
        for (String y : yAxises) {
            List<Map<String,String>> list = new ArrayList<>(mongoData.size());
            for (Map<String,String> map : mongoData) {
                Map<String,String> xyData = new HashMap<>();
                String xValue = map.get(xAxis);
                String yValue = map.get(y);
                xyData.put("x",xValue);
                xyData.put("y",yValue);
                list.add(xyData);
            }
            result.put(y,list);
        }
        return result;
    }

    /**
     * 转换mongo服务中的数据为绘图需要的数据结构
     * @param mongoData mongo服务返回的数据
     * @param xAxis x轴名称
     * @param yAxises y轴名称 可能有多个y轴，用数组存储
     * @return
     */
    public static Map<String,List<Map<String,String>>> mongoDataTransform(List<Map<String,String>> mongoData,String xAxis,String[] yAxises){
        if (mongoData == null || mongoData.size() == 0) {
            return new HashMap<>();
        }
        if (CommonTools.isEmptyStr(xAxis) || yAxises == null || yAxises.length == 0) {
            List<String> keys = new ArrayList<>();
            // 如果传入的x轴或y轴为空，则默认取第一个字段为x轴，后面的字段为y轴
            for (String key: mongoData.get(0).keySet()) {
                // 除去_id字段
                if (!"_id".equals(key)) {
                    keys.add(key);
                }
            }
            xAxis = keys.get(0);
            yAxises = new String[keys.size()-1];
            for (int i = 0;i<keys.size()-1;i++) {
                yAxises[i] = keys.get(i+1);
            }
        }
        Map<String,List<Map<String,String>>> result = new HashMap<>();
        for (String y : yAxises) {
            List<Map<String,String>> list = new ArrayList<>(mongoData.size());
            for (Map<String,String> map : mongoData) {
                Map<String,String> xyData = new HashMap<>();
                String xValue = map.get(xAxis);
                String yValue = map.get(y);
                xyData.put("x",xValue);
                xyData.put("y",yValue);
                list.add(xyData);
            }
            result.put(y,list);
        }
        return result;
    }

    /**
     * 将图片像素单位px转化为word中的图片单位emu
     * @param px 图片像素单位
     * @return 返回emu单位
     */
    public static int px2emu(double px){
        BigDecimal b1 = new BigDecimal(Double.toString(px));
        BigDecimal b2 = new BigDecimal(Double.toString(EMU_PER_PX));
        BigDecimal multiply = b1.multiply(b2);
        BigDecimal setScale = multiply.setScale(DEF_DIV_SCALE, BigDecimal.ROUND_HALF_UP);
        return setScale.intValue();
    }


}
