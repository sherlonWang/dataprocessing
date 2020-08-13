package com.sherlon.dataprocessing.testData;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * @author :  sherlonWang
 * @description :  生成测试数据工具类
 * @date: 2018-01-16 23:53
 */
public class TestDataGenerateUtil {

    /**
     * 生成随机矩阵,包含整数，小数，日期，科学计数法，异常
     * @param raw 行数
     * @param column 列数
     * @param mid 分隔符
     * @param dmin 浮点型最小值
     * @param dmax 浮点型最大值
     * @param n 浮点型保留小数位
     * @param imin 整型最小值
     * @param imax 整型组大值
     * @param startDate 开始时间
     * @param endDate 结束时间
     * @param format 日期格式
     * @param doubleRate 浮点型出现概率
     * @param intRate 整型出现概率
     * @param dateRate 时期出现概率
     * @param sciRate 科学计数法出现概率
     * @return
     */
    public static String randomMatrix(int raw, int column,String mid,double dmin, double dmax, double n, int imin, int imax, String startDate, String endDate, String format, double doubleRate, double intRate, double dateRate, double sciRate){
        StringBuilder sb = new StringBuilder();
        for(int i = 0;i<raw;i++){
            for(int j=0;j<column;j++){
                double random = Math.random();
                if(random>=0 && random < doubleRate){
                    // 生成浮点型数字
                    if(randomFlag() == 1){// 不带科学计数法
                        sb.append(nextDouble(dmin,dmax,n)).append(mid);
                    }else {// 带科学计数法
                        sb.append(nextDouble(dmin, dmax, n, sciRate)).append(mid);
                    }
                }else if(random >= doubleRate && random < doubleRate+intRate){
                    // 生成整型数字
                    sb.append(nextInt(imin,imax)).append(mid);
                }else if(random >= doubleRate+intRate && random < doubleRate+intRate+dateRate){
                    // 生成日期型数字
                    sb.append(nextDate(startDate,endDate,format)).append(mid);
                }else{
                    // 生成异常符号
                    sb.append(nextException()).append(mid);
                }
            }
            sb.delete(sb.toString().length()-mid.length(),sb.toString().length()).append("\n");
        }
        return sb.toString().substring(0,sb.toString().length()-1);
    }

    /**
     * 生成随机矩阵文件，包含整数，小数，日期
     * @param outputPath 文件输出路径
     * @param raw 行数
     * @param column 列数
     * @param mid 分隔符
     * @param dmin 浮点型最小值
     * @param dmax 浮点型最大值
     * @param n 浮点型保留小数位
     * @param imin 整型最小值
     * @param imax 整型组大值
     * @param startDate 开始时间
     * @param endDate 结束时间
     * @param format 日期格式
     * @param doubleRate 浮点型出现概率
     * @param intRate 整型出现概率
     * @param dateRate 时期出现概率
     * @param sciRate 科学计数法出现概率
     */
    public static void generateFile(String outputPath, int raw, int column,String mid,double dmin, double dmax, double n, int imin, int imax, String startDate, String endDate, String format, double doubleRate, double intRate, double dateRate, double sciRate){
        String header = generateHeader(column,mid);
        String matrix = randomMatrix(raw,column,mid,dmin,dmax,n,imin,imax,startDate,endDate,format,doubleRate,intRate,dateRate,sciRate);
        saveFile(outputPath,header,matrix);
    }

    /**
     * 生成一个随机矩阵，包括整型，浮点型，科学计数法
     * @param raw 行数
     * @param column 列数
     * @param mid 分隔符
     * @param dmin 浮点型最小值
     * @param dmax 浮点型最大值
     * @param n 浮点型保留小数位
     * @param imin 整型最小值
     * @param imax 整型组大值
     * @param doubleRate 浮点型出现概率
     * @param intRate 整型出现概率
     * @param sciRate 科学计数法出现概率
     * @return
     */
    public static String randomMatrix(int raw, int column, String mid,double dmin, double dmax, double n, int imin, int imax, double doubleRate,double intRate, double sciRate){
        StringBuilder sb = new StringBuilder();
        for(int i = 0;i<raw;i++){
            for(int j=0;j<column;j++){
                double random = Math.random();
                if(random>=0 && random < doubleRate){
                    // 生成浮点型数字
                    if(randomFlag() == 1){// 不带科学计数法
                        sb.append(nextDouble(dmin,dmax,n)).append(mid);
                    }else {// 带科学计数法
                        sb.append(nextDouble(dmin, dmax, n, sciRate)).append(mid);
                    }
                }else if(random >= doubleRate && random < doubleRate+intRate){
                    // 生成整型数字
                    sb.append(nextInt(imin,imax)).append(mid);
                }
            }
            sb.delete(sb.toString().length()-mid.length(),sb.toString().length()).append("\n");
        }
        return sb.toString().substring(0,sb.toString().length()-1);
    }

    /**
     * 生成一个随机矩阵文件，包括整型，浮点型，科学计数法
     * @param outputPath 文件输出路径
     * @param raw 行数
     * @param column 列数
     * @param mid 分隔符
     * @param dmin 浮点型最小值
     * @param dmax 浮点型最大值
     * @param n 浮点型保留小数位
     * @param imin 整型最小值
     * @param imax 整型组大值
     * @param doubleRate 浮点型出现概率
     * @param intRate 整型出现概率
     * @param sciRate 科学计数法出现概率
     * @return
     */
    public static void generateFile (String outputPath,int raw, int column, String mid,double dmin, double dmax, double n, int imin, int imax, double doubleRate,double intRate, double sciRate){
        String header = generateHeader(column,mid);
        String matrix = randomMatrix(raw,column,mid,dmin,dmax,n,imin,imax,doubleRate,intRate,sciRate);
        saveFile(outputPath,header,matrix);
    }

    /**
     * 生成一个随机日期矩阵
     * @param raw 行数
     * @param column 列数
     * @param mid 分隔符
     * @param startDate 开始时间
     * @param endDate 结束时间
     * @param format 日期格式
     * @return
     */
    public static String randomMatrix(int raw, int column,String mid,String startDate, String endDate, String format){
        StringBuilder sb = new StringBuilder();
        for(int i = 0;i<raw;i++){
            for(int j=0;j<column;j++){
                sb.append(nextDate(startDate,endDate,format)).append(mid);
            }
            sb.delete(sb.toString().length()-mid.length(),sb.toString().length()).append("\n");
        }
        return sb.toString().substring(0,sb.toString().length()-1);
    }

    /**
     * 生成一个随机日期矩阵文件
     * @param outputPath 文件输出路径
     * @param raw 行数
     * @param column 列数
     * @param mid 分隔符
     * @param startDate 开始时间
     * @param endDate 结束时间
     * @param format 日期格式
     * @return
     */
    public static void generateFile(String outputPath,int raw, int column,String mid,String startDate, String endDate, String format){
        String header = generateHeader(column,mid);
        String matrix = randomMatrix(raw,column,mid,startDate,endDate,format);
        saveFile(outputPath,header,matrix);
    }

    /**
     * 生成随机整数矩阵
     * @param raw 行数
     * @param column 列数
     * @param mid 分隔符
     * @param imin 最小值
     * @param imax 最大值
     * @return
     */
    public static String randomMatrix( int raw, int column,String mid,int imin, int imax){
        StringBuilder sb = new StringBuilder();
        for(int i = 0;i<raw;i++){
            for(int j=0;j<column;j++){
                sb.append(nextInt(imin,imax)).append(mid);
            }
            sb.delete(sb.toString().length()-mid.length(),sb.toString().length()).append("\n");
        }
        return sb.toString().substring(0,sb.toString().length()-1);
    }

    /**
     * 生成随机整数矩阵文件
     * @param outputPath 文件输出路径
     * @param raw 行数
     * @param column 列数
     * @param mid 分隔符
     * @param imin 最小值
     * @param imax 最大值
     * @return
     */
    public static void generateFile(String outputPath,int raw, int column,String mid,int imin, int imax){
        String header = generateHeader(column,mid);
        String matrix = randomMatrix(raw,column,mid,imin,imax);
        saveFile(outputPath,header,matrix);
    }

    /**
     * 生成随机浮点型矩阵
     * @param raw 行数
     * @param column 列数
     * @param mid 分隔符
     * @param min 最小值
     * @param max 最大值
     * @param n 保留小数位数
     * @return
     */
    public static String randomMatrix(int raw, int column,String mid,double min, double max, double n){
        StringBuilder sb = new StringBuilder();
        for(int i = 0;i<raw;i++){
            for(int j=0;j<column;j++){
                sb.append(nextDouble(min,max,n)).append(mid);
            }
            sb.delete(sb.toString().length()-mid.length(),sb.toString().length()).append("\n");
        }
        return sb.toString().substring(0,sb.toString().length()-1);
    }

    /**
     * 生成随机浮点型矩阵文件
     * @param outputPath 文件输出路径
     * @param raw 行数
     * @param column 列数
     * @param mid 分隔符
     * @param min 最小值
     * @param max 最大值
     * @param n 保留小数位数
     * @return
     */
    public static void generateFile(String outputPath,int raw, int column,String mid,double min, double max, double n){
        String header = generateHeader(column,mid);
        String matrix = randomMatrix(raw,column,mid,min,max,n);
        saveFile(outputPath,header,matrix);
    }

    /**
     * 生成随机浮点型矩阵,含科学计数法
     * @param raw 行数
     * @param column 列数
     * @param mid 分隔符
     * @param min 最小值
     * @param max 最大值
     * @param n 保留小数位数
     * @param sciRate 生成科学计数法概率
     * @return
     */
    public static String randomMatrix(int raw, int column,String mid,double min, double max, double n, double sciRate){
        StringBuilder sb = new StringBuilder();
        for(int i = 0;i<raw;i++){
            for(int j=0;j<column;j++){
                sb.append(nextDouble(min,max,n,sciRate)).append(mid);
            }
            sb.delete(sb.toString().length()-mid.length(),sb.toString().length()).append("\n");
        }
        return sb.toString().substring(0,sb.toString().length()-1);
    }

    /**
     * 生成随机浮点型矩阵,含科学计数法
     * @param outputPath 文件输出路径
     * @param raw 行数
     * @param column 列数
     * @param mid 分隔符
     * @param min 最小值
     * @param max 最大值
     * @param n 保留小数位数
     * @param sciRate 生成科学计数法概率
     * @return
     */
    public static void generateFile(String outputPath,int raw, int column,String mid,double min, double max, double n, double sciRate){
        String header = generateHeader(column,mid);
        String matrix = randomMatrix(raw,column,mid,min,max,n,sciRate);
        saveFile(outputPath,header,matrix);
    }

    public static String generateHeader(int column,String mid){
        StringBuilder sb = new StringBuilder();
        for (int i = 1;i<=column;i++) {
            sb.append("A").append(i).append(mid);
        }
        return sb.toString().substring(0,sb.toString().length()-1);
    }

    /**
     * 生成随机±号1 or -1
     * @return
     */
    public static int randomFlag(){
        int random = new Random().nextInt(2);
        int flag;
        switch (random){
            case 0:
                flag = 1;
                break;
            case 1:
                flag = -1;
                break;
            default:
                flag = 1;
                break;
        }
        return flag;
    }

    /**
     * 随机生成一个异常字符
     * @return
     */
    public static String nextException(){
        String[] excs = {"#","#INT#","*","--","NAN"};
        int random = new Random().nextInt(excs.length);
        return excs[random];
    }

    /**
     * 生成一个随机的日期
     * @param startDate 开始时间
     * @param endDate 结束时间
     * @param format 日期格式
     * @return
     */
    public static String nextDate(String startDate, String endDate, String format){
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        String retVal;
        long time;
        try {
            time = nextLong(sdf.parse(startDate).getTime(),sdf.parse(endDate).getTime());
            retVal = sdf.format(new Date(time));
        } catch (ParseException e) {
            e.printStackTrace();
            retVal = "日期格式必须一致";
        }
        return retVal;

    }

    /**
     * 生成一个随机±浮点型数
     * @param min 最小值
     * @param max 最大值
     * @param n 保留小数位数
     * @return
     */
    public static double nextDouble(double min, double max,double n) {
        double num = min + ((max - min) * new Random().nextDouble());
        return (double)(Math.round(num*Math.pow(10,n)))/Math.pow(10,n)*randomFlag();
    }

    /**
     * 生成一个随机的浮点型数
     * @param min 最小值
     * @param max 最大值
     * @param n 小数位数
     * @param sciRate 出现科学计数法概率
     * @return
     */
    public static double nextDouble(double min, double max, double n, double sciRate){
        double random = Math.random();
        if(random > 0 && random < sciRate){
            return nextDouble(Math.pow(10,7),Math.pow(10,8),3);
        }else {
            return nextDouble(min,max,n);
        }
    }

    /**
     * 生成一个随机长整型数
     * @param min 最小值
     * @param max 最大值
     * @return
     */
    public static long nextLong(long min, long max){
        return (long)(min + (max - min) * new Random().nextDouble());
    }

    /**
     * 生成一个随机的±整数
     * @param min 绝对值最小值
     * @param max 绝对值最大值
     * @return
     */
    public static int nextInt(int min, int max){
        return (new Random().nextInt(max-min)+min)*randomFlag();
    }


    /**
     * 存储矩阵文件到本地
     * @param outputPath 文件输出路径
     * @param header 文件头
     * @param matrix 文件数据体
     */
    public static void saveFile(String outputPath,String header,String matrix){
        File file = new File(outputPath);
        PrintWriter pw = null;
        try {
            pw = new PrintWriter(new FileOutputStream(file));
            pw.println(header);
            pw.println(matrix);
            pw.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }finally {
            if (pw!=null) {
                pw.close();
            }
        }
        System.out.println("写出完毕！");
    }

}