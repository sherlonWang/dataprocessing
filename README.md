## 一、生成测试文件

#### 类：TestDataGenerateUtil

#### 静态方法：

- #### generateFile

  ```java
  public static void generateFile(String outputPath,
                                  int raw,
                                  int column,
                                  String mid,
                                  double dmin,
                                  double dmax,
                                  double n,
                                  int imin,
                                  int imax,
                                  String startDate,
                                  String endDate,
                                  String format,
                                  double doubleRate,
                                  double intRate,
                                  double dateRate,
                                  double sciRate)
  ```

  生成随机矩阵文件，包含整数，小数，日期

  - **参数:**

    `outputPath` - 文件输出路径

    `raw` - 行数

    `column` - 列数

    `mid` - 分隔符

    `dmin` - 浮点型最小值

    `dmax` - 浮点型最大值

    `n` - 浮点型保留小数位

    `imin` - 整型最小值

    `imax` - 整型组大值

    `startDate` - 开始时间

    `endDate` - 结束时间

    `format` - 日期格式

    `doubleRate` - 浮点型出现概率

    `intRate` - 整型出现概率

    `dateRate` - 时期出现概率

    `sciRate` - 科学计数法出现概率



- #### generateFile

  ```java
  public static void generateFile(String outputPath,
                                  int raw,
                                  int column,
                                  String mid,
                                  double dmin,
                                  double dmax,
                                  double n,
                                  int imin,
                                  int imax,
                                  double doubleRate,
                                  double intRate,
                                  double sciRate)
  ```

  生成一个随机矩阵文件，包括整型，浮点型，科学计数法

  - **参数:**

    `outputPath` - 文件输出路径

    `raw` - 行数

    `column` - 列数

    `mid` - 分隔符

    `dmin` - 浮点型最小值

    `dmax` - 浮点型最大值

    `n` - 浮点型保留小数位

    `imin` - 整型最小值

    `imax` - 整型组大值

    `doubleRate` - 浮点型出现概率

    `intRate` - 整型出现概率

    `sciRate` - 科学计数法出现概率



- #### generateFile

  ```java
  public static void generateFile(String outputPath,
                                  int raw,
                                  int column,
                                  String mid,
                                  String startDate,
                                  String endDate,
                                  String format)
  ```

  生成一个随机日期矩阵文件

  - **参数:**

    `outputPath` - 文件输出路径

    `raw` - 行数

    `column` - 列数

    `mid` - 分隔符

    `startDate` - 开始时间

    `endDate` - 结束时间

    `format` - 日期格式



- #### generateFile

  ```java
  public static void generateFile(String outputPath,
                                  int raw,
                                  int column,
                                  String mid,
                                  int imin,
                                  int imax)
  ```

  生成随机整数矩阵文件

  - **参数:**

    `outputPath` - 文件输出路径

    `raw` - 行数

    `column` - 列数

    `mid` - 分隔符

    `imin` - 最小值

    `imax` - 最大值



- #### generateFile

  ```java
  public static void generateFile(String outputPath,
                                  int raw,
                                  int column,
                                  String mid,
                                  double min,
                                  double max,
                                  double n)
  ```

  生成随机浮点型矩阵文件

  - **参数:**

    `outputPath` - 文件输出路径

    `raw` - 行数

    `column` - 列数

    `mid` - 分隔符

    `min` - 最小值

    `max` - 最大值

    `n` - 保留小数位数



- #### generateFile

  ```java
  public static void generateFile(String outputPath,
                                  int raw,
                                  int column,
                                  String mid,
                                  double min,
                                  double max,
                                  double n,
                                  double sciRate)
  ```

  生成随机浮点型矩阵,含科学计数法

  - **参数:**

    `outputPath` - 文件输出路径

    `raw` - 行数

    `column` - 列数

    `mid` - 分隔符

    `min` - 最小值

    `max` - 最大值

    `n` - 保留小数位数

    `sciRate` - 生成科学计数法概率
    
    

## 二、数据文件解析

### 1.文本类文件

#### 类：TextFileUtil

#### 静态方法：

- #### getTextFileData

  ```java
  public static List<Map<String,String>> getTextFileData(File file,String separator)
  ```
  
解析文本类文件
  
- **参数:**
  
  `file` - 文本文件
  
  `separator` - 分隔符
  
  
  
- #### getTextFileData

  ```java
  public static List<Map<String,String>> getTextFileData(File file,Integer pos,String separator)
  ```
  
  解析文本类文件

  - **参数:**

    `file` - 文本文件

    `pos` - 开始行数 不传默认为1

    `separator` - 分隔符


### 2.Excel文件

#### 类：OrientExcelUtil

#### 静态方法：

- #### getWorkbook

  ```java
  public static Workbook getWorkbook(File file)
  ```

  获取workbook

  - **参数:**

    `file` - excel文件




- #### getWorkbook

  ```java
  public static Workbook getWorkbook(String path)
  ```

  获取workbook

  - **参数:**

    `path` - 文件路径

    
  

- #### getSheetByIndex

  ```java
  public static Sheet getSheetByIndex(Workbook workbook,int sheetIndex)
  ```
  

  根据sheet下标获取sheet


  - **参数:**

    `workbook` - 工作簿

    `sheetIndex` - sheet下标

    

- #### getSheetByName

  ```java
  public static Sheet getSheetByName(Workbook workbook,String sheetName)
  ```
  
根据sheet名称获取sheet
  
- **参数:**
  
  `workbook` - 工作簿
  
  `sheetName` - sheet名称
  
  
  
- #### getSimpleSheetData

  ```java
  public static List<Map<String,String>> getSimpleSheetData(Sheet sheet,Integer pos)
  ```

  根据header或取sheet数据 key:col,value:cellValue

  - **参数:**

    `sheet` - sheet页

    `pos` - 从第pos行开始解析，pos默认为1



- #### getSimpleSheetData

  ```java
  public static List<Map<String,String>> getSimpleSheetData(Sheet sheet)
  ```

  根据header或取sheet数据 key:col,value:cellValue

  - **参数:**

    `sheet` - sheet页

    

- #### getSimpleSheetDataByRow

  ```java
  public static List<List<String>> getSimpleSheetDataByRow(Sheet sheet,Integer pos)
  ```

  按行获取sheet数据

  - **参数:**

    `sheet` - sheet页

    `pos` - 数据开始行,默认为1

    


- #### getSimpleSheetDataByRow

  ```java
  public static List<List<String>> getSimpleSheetDataByRow(Sheet sheet)
  ```
  
  按行获取sheet数据
  
  - **参数:**
  
    `sheet` - sheet页
  
    
  
- #### getSimpleSheetDataByColumn

  ```java
  public static List<List<String>> getSimpleSheetDataByColumn(Sheet sheet,Integer pos)
  ```

  按列获取sheet数据

  - **参数:**

    `sheet` - sheet页

    `pos` - 数据开始行,默认为1

    

- #### getSimpleSheetDataByColumn

  ```java
  public static List<List<String>> getSimpleSheetDataByColumn(Sheet sheet)
  ```

  按列获取sheet数据

  - **参数:**

    `sheet` - sheet页

    

## 三、绘图

#### 类：ChartPlotUtil

#### 静态方法：

- #### createXYLineChart

  ```java
  public static void createXYLineChart(String title,
                                       String xAxisLabel,
                                       String yAxisLabel,
                                       Map<String,List<Map<String,String>>> data,
                                       String outputPath)
  ```

  绘制xy数值型曲线图

  - **参数:**

    `title` - 图表标题

    `xAxisLabel` - x轴标题

    `yAxisLabel` - y轴标题

    `data` - 数据 {"series1":[{"x":1.0,"y":2.0},{"x":2.0,"y":3.0},...],"series1":[{"x":1.0,"y":2.0},{"x":2.0,"y":3.0},...]}

    `outputPath` - 图片输出路径



- #### createXYLineChart

  ```java
  public static void createXYLineChart(String title,
                                       String xAxisLabel,
                                       String yAxisLabel,
                                       Map<String,List<Map<String,String>>> data,
                                       String outputPath,
                                       Boolean withLegend)
  ```

  绘制xy数值型曲线图

  - **参数:**

    `title` - 图表标题

    `xAxisLabel` - x轴标题

    `yAxisLabel` - y轴标题

    `data` - 数据 {"series1":[{"x":1.0,"y":2.0},{"x":2.0,"y":3.0},...],"series1":[{"x":1.0,"y":2.0},{"x":2.0,"y":3.0},...]}

    `outputPath` - 图片输出路径

    `withLegend` - 是否显示图例



- #### createCategoryLineChart

  ```java
  public static void createCategoryLineChart(String title,
                                             String xAxisLabel,
                                             String yAxisLabel,
                                             Map<String,List<Map<String,String>>> data,
                                             String outputPath,
                                             Boolean withLegend)
  ```

  绘制分类型曲线图

  - **参数:**

    `title` - 图表标题

    `xAxisLabel` - x轴标题

    `yAxisLabel` - y轴标题

    `data` - 数据 {"row1":[{"x":"一月","y":2.0},{"x":"二月","y":3.0},...],"row2":[{"x":"一月","y":2.0},{"x":"二月","y":3.0},...]}

    `outputPath` - 图片输出路径

    `withLegend` - 是否显示图例



- #### createCategoryLineChart

  ```java
  public static void createCategoryLineChart(String title,
                                             String xAxisLabel,
                                             String yAxisLabel,
                                             Map<String,List<Map<String,String>>> data,
                                             String outputPath)
  ```

  绘制分类型曲线图

  - **参数:**

    `title` - 图表标题

    `xAxisLabel` - x轴标题

    `yAxisLabel` - y轴标题

    `data` - 数据 {"row1":[{"x":"一月","y":2.0},{"x":"二月","y":3.0},...],"row2":[{"x":"一月","y":2.0},{"x":"二月","y":3.0},...]}

    `outputPath` - 图片输出路径