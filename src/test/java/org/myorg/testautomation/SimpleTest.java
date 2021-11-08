package org.myorg.testautomation;

import org.graphwalker.core.condition.EdgeCoverage;
import org.graphwalker.core.condition.ReachedVertex;
import org.graphwalker.core.condition.TimeDuration;
import org.graphwalker.core.generator.AStarPath;
import org.graphwalker.core.generator.RandomPath;
import org.graphwalker.core.machine.ExecutionContext;
import org.graphwalker.core.model.Edge;
import org.graphwalker.java.test.TestBuilder;
import org.graphwalker.java.test.TestExecutor;
import org.junit.Test;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;

import org.openqa.selenium.WebElement;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.TimeUnit;
import java.io.File;
import java.net.URL;
import java.util.regex.Pattern;
import java.net.MalformedURLException;

import org.openqa.selenium.Platform;
import org.graphwalker.java.annotation.GraphWalker;
import org.graphwalker.java.annotation.AfterExecution;
import org.graphwalker.java.annotation.BeforeExecution;

import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.android.AndroidElement;

import org.openqa.selenium.remote.DesiredCapabilities;

@GraphWalker(value = "random(edge_coverage(100))", start = "v_AppNotRunning")
public class SimpleTest extends ExecutionContext implements Login {

    private AndroidDriver<AndroidElement> driver;


    @Override
    public void v_Login() {
        System.out.println("v_Login: Insert test code here!");
    }


    @Override
    public void v_AppNotRunning() {
        System.out.println("v_AppNotRunning: Insert test code here!");
    }


    @Override
    public void e_RememberMe() {
        System.out.println("e_RememberMe: Insert test code here!");
    }

    @Override
    public void e_InvalidCredentials() {
        System.out.println("e_InvalidCredentials: Insert test code here!");
    }

    @Override
    public void e_ValidCredentials() {
        System.out.println("e_ValidCredentials: Insert test code here!");
    }

    @Override
    public void e_Exit() {
        System.out.println("e_Exit: Insert test code here!");
    }


    @Override
    public void e_Start() {
        System.out.println("e_Start: Insert test code here!");
    }

    @Override
    public void e_Close() {
        System.out.println("e_Close: Insert test code here!");
    }

    @Override
    public void e_Logout() {
        System.out.println("e_Logout: Insert test code here!");
    }


    @Override
    public void v_Browse() {
        System.out.println("v_Browse: Insert test code here!");
        //查找id为edit_input的控件
        AndroidElement searchBoxEl = (AndroidElement) driver.findElementById("edit_input");
        //将此控件的内容变为其他值（尝试Button与TextView都无法使用此api）
        searchBoxEl.replaceValue("点击");
        System.out.println("v_Browse: get btn_check!!!!!!!");
        //触发Button点击
        driver.findElementById("com.jetpack.demo:id/btn_check").click();
        System.out.println("v_Browse: click!!!!!!!");
        //获取TextView的值
        String value = driver.findElementById("com.jetpack.demo:id/tv_top").getText();
        System.out.println("v_Browse: text!!!!!====  " + value);
    }

    @BeforeExecution
    public void setup() {
        //这里是android的配置，如果是ios与web则配置不同
        //这里的driver初始化功能是由appium.java_client提供
        File classpathRoot = new File(System.getProperty("user.dir"));
        //设置待测试apk文件夹路径
        File appDir = new File(classpathRoot, "src/main/resources");
        //设置待测试apk名称，此demo项目的apk中，包含3个控件，分别是TextView(id=tv_top)、EditText(id=edit_input)、Button(id=btn_check)
        File app = new File(appDir, "app-release.apk");
        DesiredCapabilities capabilities = new DesiredCapabilities();
        //自动化名称：固定值，必须设置为UiAutomator2；
        capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UiAutomator2");
        //平台类型
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, Platform.ANDROID);
        //todo 系统版本号：指手机系统版本，需要根据测试机不同继续宁修改
        capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "11");
        //todo 设备名称：每个型号手机不同，需要通过adb连接获取，需要根据测试机不同继续宁修改
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "samsung SM-G9750");
        //待测试apk路径
        capabilities.setCapability(MobileCapabilityType.APP, app.getAbsolutePath());
        //待测试apk包名，可不填，appium会尝试自动获取
        capabilities.setCapability("appPackage", "com.jetpack.demo");
        ////待测试apk页面名，可不填，appium会尝试自动获取
        capabilities.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, ".ui.OldMainActivity");

        try {
            System.out.println("Try to find driver");
            //查找设备
            driver = new AndroidDriver<>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
            System.out.println("find driver: " + driver);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @AfterExecution
    public void tearDown() {
        //释放driver
        driver.quit();
    }

}