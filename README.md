## 解析mdb和excel多种方法和测试

解析10、50、100、500和1000个文件耗时测试记录，在/data文件夹
；
其中用到的自导入jar包，也在/data目录中，以及mvn导入命令；

<hr>

下面是请求地址：

**参数：**

num：文件数

fileName：文件名

threadNum：线程数

1、复制文件1000个：

http://localhost:8080/copyFile?fileName=0315-file.mdb&num=1000

2、解析mdb文件：

http://localhost:8080/startInsertGzbThread?num=1&fileName=0315-file&threadNum=3

3、JXL解析xls文件

3.1、使用多线程 解析xls

http://localhost:8080/jxl/startInsertThreadAll?num=10&fileName=0312-file&threadNum=3

3.2、一个文件，一个线程

http://localhost:8080/jxl/startInsertThreadAll?num=1&fileName=0312-file&threadNum=1

4、POI解析xls文件

http://localhost:8080/poi/startInsertThreadAll?num=10&fileName=0312-file&threadNum=3

5、EasyExcel解析xls文件

5.1、不使用实体映射

http://localhost:8080/easy/startInsertThreadAll?num=10&fileName=0312-file&threadNum=3

5.2、使用实体映射

http://localhost:8080/easy/startInsertThreadAll_Entity?num=10&fileName=0312-file&threadNum=3

6、FastExcel解析xls文件

http://localhost:8080/fast/startInsertThreadAll?num=10&fileName=0312-file&threadNum=3
