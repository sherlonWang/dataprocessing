package com.sherlon.dataprocessing;

import com.sherlon.dataprocessing.chartPlot.ChartPlotUtil;
import com.sherlon.dataprocessing.fileHandler.TextFileUtil;
import com.sherlon.dataprocessing.testData.TestDataGenerateUtil;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.util.IOUtils;
import org.apache.poi.xwpf.usermodel.Document;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
class DataprocessingApplicationTests {

    @Test
    void contextLoads() {
    }
    @Test
    void testXYChart(){
        Map<String, List<Map<String, String>>> map = new HashMap<>();
        List<Map<String, String>> data1 = new ArrayList<>();
        for (int i = 1;i<100;i++) {
            Map<String,String> axis = new HashMap<>();
            axis.put("x",i+"");
            axis.put("y",Math.random()*100+"");
            data1.add(axis);
        }
        map.put("series1",data1);
        List<Map<String, String>> data2 = new ArrayList<>();
        for (int i = 1;i<100;i++) {
            Map<String,String> axis = new HashMap<>();
            axis.put("x",i+"");
            axis.put("y",Math.random()*100+"");
            data2.add(axis);
        }
        map.put("series2",data2);

        ChartPlotUtil.createXYLineChart("title","x轴","y轴",map,"/Users/sherlonwang/Desktop/test.png");
    }

    @Test
    void testCategoryChart(){
        Map<String,List<Map<String, String>>> map = new HashMap<>();
        List<Map<String, String>> data1 = new ArrayList<>();
        for (int i = 1;i<100;i++) {
            Map<String,String> axis = new HashMap<>();
            axis.put("x","分类"+(i%10));
            axis.put("y",Math.random()*100+"");
            data1.add(axis);
        }
        map.put("series1",data1);
        List<Map<String, String>> data2 = new ArrayList<>();
        for (int i = 1;i<100;i++) {
            Map<String,String> axis = new HashMap<>();
            axis.put("x","分类"+(i%10));
            axis.put("y",Math.random()*100+"");
            data2.add(axis);
        }
        map.put("series2",data2);

        ChartPlotUtil.createCategoryLineChart("title","x轴","y轴",map,"/Users/sherlonwang/Desktop/test.png");
    }

    @Test
    void testHeader(){
        int column = 10;
        String mid = ",";
        System.out.println(TestDataGenerateUtil.generateHeader(column,mid));
        System.out.println("111");
    }

    @Test
    void testGenerateFile(){
        String path = "/Users/sherlonwang/Desktop/test.txt";
        TestDataGenerateUtil.generateFile(path,100,20," ",1,20);
    }

    @Test
    void testDataHandler(){
        File file = new File("/Users/sherlonwang/Desktop/test.txt");
        List<Map<String,String>> data = TextFileUtil.getTextFileData(file,0," ");
        System.out.println(data);
    }

    @Test
    public void test2() throws IOException, InvalidFormatException {
        XWPFDocument document = new XWPFDocument();
        try {
            XWPFParagraph paragraph = document.createParagraph();
            XWPFRun run = paragraph.createRun();

            InputStream is = new FileInputStream("/Users/sherlonwang/Desktop/test.png");
            // 因为FileInputStream没有重写reset() 所有将流转为了byte数组
            byte[] bs = IOUtils.toByteArray(is);
            BufferedImage image = ImageIO.read(new ByteArrayInputStream(bs));


            int width = ChartPlotUtil.px2emu(image.getWidth());
            int height = ChartPlotUtil.px2emu(image.getHeight());

            run.addPicture(new ByteArrayInputStream(bs), Document.PICTURE_TYPE_PNG, "", width, height);

            OutputStream stream = new FileOutputStream("/Users/sherlonwang/Desktop/doc.docx");
            document.write(stream);
        } finally {
            document.close();
        }
    }

    @Test
    void test3(){
        File file = new File("/Users/sherlonwang/Desktop/test.txt");
        List<Map<String, String>> data = TextFileUtil.getTextFileData(file,0," ");
        System.out.println(data);
    }


}
