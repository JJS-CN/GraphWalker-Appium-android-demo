
## 描述
基于GraphWalker和 Appium的模型（MBT）的测试demo，从零搭建运行环境说明
一切顺利时，你只需要以下几个步骤即可运行此demo;
1. 安装好所需软件，下载此demo
2. 修改`SimpleTest.java`类中的`PLATFORM_VERSION`和`DEVICE_NAME`配置
3. 开启一个终端启动appium服务，执行命令`appium`
4. 再开启一个终端cd到项目根目录
5. 执行命令`mvn graphwalker:generate-sources`
6. 执行命令`mvn test`


## 本demo运行环境
* System: windows11
* Java:  11 or 8
* Maven: 3.8.3
* GraphWalker: 4.3.1
* Nodejs : 14.15.3
* Appium: 1.22.0


## java运行环境安装
* 下载java并安装
* 系统环境变量：新增变量名`JAVA_HOME`，变量值为java根目录(示例)`C:\Program Files\Java\jdk1.8.0_311`
* 系统环境变量：Path变量中，新增配置`%JAVA_HOME%\bin`
* 验证：终端中输入`java -version`

下载地址：https://www.java.com/zh-CN/download/

## Maven运行环境安装
* 下载Maven压缩包，安装时解压即可；存放到合适位置，以防误删
* 系统环境变量：新增变量名`MAVEN_HOME`，变量值为maven根目录(示例)`E:\worker\maven\apache-maven-3.8.3`
* 系统环境变量：Path变量中，新增配置`%MAVEN_HOME%\bin`
* 验证：终端中输入`mvn -v`

下载地址：https://maven.apache.org


## GraphWalker安装
* 下载官网提供的`graphwalker-studio-4.3.1.jar`文件，并存放到合适位置（因为以后创建模型都需要运行这个jar）

* 启动并运行服务：终端执行命令`java -jar graphwalker-studio-4.3.1.jar`

* 从浏览器通过url地址打开操作界面：http://localhost:9090/studio.html

* 操作方式：https://github.com/GraphWalker/graphwalker-project/wiki/GraphWalker-Studio
* 在操作页面新建模型并验证通过后，保存下来；（demo中为Login.json）

官网下载地址（也可通过git下载）：http://graphwalker.github.io/

git地址：https://github.com/GraphWalker/graphwalker-project

详细文档：https://github.com/GraphWalker/graphwalker-project/wiki

## node.js安装
* 从官网下载安装包安装
* 验证：终端输入`npm -v`

下载地址：https://nodejs.org/

## appium安装
* 通过npm下载：npm install -g appium
* 验证：终端输入`appium -v`
* 启动并运行服务：终端执行命令`appium`

## demo项目说明
参照项目创建文档：https://github.com/GraphWalker/graphwalker-project/wiki/Test-execution
* 创建文件夹结构：
 ```sh
    %> mkdir -p project/src/main/java/org/myorg/testautomation
    %> mkdir -p project/src/main/resources/org/myorg/testautomation
    %> mkdir -p project/src/test/java/org/myorg/testautomation
 ```
* 将GraphWalker生成的模型文件放到`project/src/main/resources/org/myorg/testautomation`目录下
* 在项目根目录新建pom.xml文件，你可能需要更改xml文件中的`graphwalker.version`或`com.github.appium:java-client`版本号
* 终端cd 到项目根目录project下
* 终端执行命令`mvn graphwalker:generate-sources`，完成后会在项目根目录下新建`target/generated-sources/graphwalker/`文件夹，其中会生成一个同模型名字的java接口文件，demo中是`Login.java`
* 在`project/src/test/java/org/myorg/testautomation`目录下新建接口实现类，demo中是`SimpleText.java`

#### demo项目最终结构
  ```sh
  .
  ├── pom.xml
  ├── src
  │   ├── main
  │   │   ├── java
  │   │   │   └── org
  │   │   │       └── myorg
  │   │   │           └── testautomation
  │   │   └── resources
  │   │       └── app-release.apk
  │   │       └── org
  │   │           └── myorg
  │   │               └── testautomation
  │   │                   └── Login.json
  │   └── test
  │       └── java
  │           └── org
  │               └── myorg
  │                   └── testautomation
  │                       └── SimpleTest.java
  └── target
    └── generated-sources
        └── graphwalker
            └── org
                └── myorg
                    └── testautomation
                        └── Login.java
  ```

## SimpleTest.java说明
* extends ExecutionContext和@GraphWalker为固定写法
* @GraphWalker注解：value表示结束条件，start表示开始顶点
* implements Login 是我们由模型自动生成的接口类，实现所有接口方法并通过appium编码测试代码
* @BeforeExecution注解，在测试用例执行前触发，这里连接到设备driver; 初始化配置请参照demo注释与`appium-uiautomator2配置文档`
* @AfterExecution注解，在测试用例执行后触发，这里释放掉设备driver

appium-api官方文档：http://appium.io/docs/en/about-appium/api/

appium-api javadoc文档：https://www.javadoc.io/static/io.appium/java-client/7.6.0/overview-summary.html

appium-uiautomator2配置文档：https://github.com/appium/appium-uiautomator2-driver

## 运行
* 终端执行命令`mvn test`
* 执行完成后会在终端提示`BUILD SUCCESS`

