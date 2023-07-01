package io.mhan.springcrawling;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;
import java.util.UUID;
import java.util.concurrent.*;

@Slf4j
@Configuration
public class SeleniumTest {
    private static BlockingQueue<WebDriver> driverPool;

    @Bean
    public ApplicationRunner test() {
        return args -> {
            initializeDriverPool(20);
            ExecutorService executorService = Executors.newFixedThreadPool(20);
            // System Property 설정
            System.setProperty("webdriver.chrome.driver", "/Users/myunghan/Desktop/test/spring-Crawling/driver/chromedriver");
            Integer[] ids = {
                    167662, 167146, 166844, 166683, 164973, 164489, 165677, 167333, 167335, 167348,
                    166683, 166683, 166683, 166683, 166683, 166683, 166683, 166683 ,166683 ,166683,
                    166683, 166683, 166683, 166683, 166683, 166683, 166683, 166683 ,166683 ,166683,
                    166683, 166683, 166683, 166683, 166683, 166683, 166683, 166683 ,166683 ,166683,
                    166683, 166683, 166683, 166683, 166683, 166683, 166683, 166683 ,166683 ,166683,
                    166683, 166683, 166683, 166683, 166683, 166683, 166683, 166683 ,166683 ,166683,
                    166683, 166683, 166683, 166683, 166683, 166683, 166683, 166683 ,166683 ,166683,
                    166683, 166683, 166683, 166683, 166683, 166683, 166683, 166683 ,166683 ,166683,
                    166683, 166683, 166683, 166683, 166683, 166683, 166683, 166683 ,166683 ,166683,
                    166683, 166683, 166683, 166683, 166683, 166683, 166683, 166683 ,166683 ,166683,
                    166683, 166683, 166683, 166683, 166683, 166683, 166683, 166683 ,166683 ,166683,
                    166683, 166683, 166683, 166683, 166683, 166683, 166683, 166683 ,166683 ,166683,
                    166683, 166683, 166683, 166683, 166683, 166683, 166683, 166683 ,166683 ,166683,
                    166683, 166683, 166683, 166683, 166683, 166683, 166683, 166683 ,166683 ,166683,
                    166683, 166683, 166683, 166683, 166683, 166683, 166683, 166683 ,166683 ,166683,
                    166683, 166683, 166683, 166683, 166683, 166683, 166683, 166683 ,166683 ,166683,
                    166683, 166683, 166683, 166683, 166683, 166683, 166683, 166683 ,166683 ,166683,
                    166683, 166683, 166683, 166683, 166683, 166683, 166683, 166683 ,166683 ,166683,
                    166683, 166683, 166683, 166683, 166683, 166683, 166683, 166683 ,166683 ,166683,
                    166683, 166683, 166683, 166683, 166683, 166683, 166683, 166683 ,166683 ,166683,
                    166683, 166683, 166683, 166683, 166683, 166683, 166683, 166683 ,166683 ,166683,
                    166683, 166683, 166683, 166683, 166683, 166683, 166683, 166683 ,166683 ,166683,
                    166683, 166683, 166683, 166683, 166683, 166683, 166683, 166683 ,166683 ,166683,
                    166683, 166683, 166683, 166683, 166683, 166683, 166683, 166683 ,166683 ,166683,
                    166683, 166683, 166683, 166683, 166683, 166683, 166683, 166683 ,166683 ,166683,
                    166683, 166683, 166683, 166683, 166683, 166683, 166683, 166683 ,166683 ,166683,
                    166683, 166683, 166683, 166683, 166683, 166683, 166683, 166683 ,166683 ,166683,
                    166683, 166683, 166683, 166683, 166683, 166683, 166683, 166683 ,166683 ,166683,
                    166683, 166683, 166683, 166683, 166683, 166683, 166683, 166683 ,166683 ,166683,
                    166683, 166683, 166683, 166683, 166683, 166683, 166683, 166683 ,166683 ,166683,
                    166683, 166683, 166683, 166683, 166683, 166683, 166683, 166683 ,166683 ,166683,
                    166683, 166683, 166683, 166683, 166683, 166683, 166683, 166683 ,166683 ,166683,
                    166683, 166683, 166683, 166683, 166683, 166683, 166683, 166683 ,166683 ,166683,
                    166683, 166683, 166683, 166683, 166683, 166683, 166683, 166683 ,166683 ,166683,
                    166683, 166683, 166683, 166683, 166683, 166683, 166683, 166683 ,166683 ,166683,
                    166683, 166683, 166683, 166683, 166683, 166683, 166683, 166683 ,166683 ,166683,
                    166683, 166683, 166683, 166683, 166683, 166683, 166683, 166683 ,166683 ,166683,
                    166683, 166683, 166683, 166683, 166683, 166683, 166683, 166683 ,166683 ,166683,
                    166683, 166683, 166683, 166683, 166683, 166683, 166683, 166683 ,166683 ,166683,
                    166683, 166683, 166683, 166683, 166683, 166683, 166683, 166683 ,166683 ,166683,
                    166683, 166683, 166683, 166683, 166683, 166683, 166683, 166683 ,166683 ,166683,
            };

            for (Integer id: ids) {
                executorService.submit(() -> {
                    ChromeOptions options = new ChromeOptions();
                    options.addArguments("headless", "disable-gpu", "window-size=1920x1080",
                            "user-agent=Mozilla/5.0 (Macintosh; Intel Mac OS X 10_12_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/61.0.3163.100 Safari/537.36",
                            "blink-settings=imagesEnabled=false"
                            );
                    WebDriver driver = getDriverFromPool();
                    driver.get("https://www.wanted.co.kr/wd/" + id);

                    WebElement element = driver.findElement(By.className("JobDescription_JobDescription__VWfcb"));
                    WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(10));
                    wait1.until(ExpectedConditions.visibilityOfElementLocated(By.className("JobDescription_JobDescription__VWfcb")));

                    JavascriptExecutor js = (JavascriptExecutor) driver;
                    js.executeScript("arguments[0].scrollIntoView({block: 'end', behavior: 'auto'});", element);
                    js.executeScript("window.scrollBy(0, window.innerHeight);");

                    // 특정 웹 요소가 로드될 때까지 대기
                    WebDriverWait wait2 = new WebDriverWait(driver, Duration.ofSeconds(10));
                    WebElement deadlineElement = wait2.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"__next\"]/div[3]/div[1]/div[1]/div/div[2]/section[2]/div[1]/span[2]")));
                    WebElement workingAreaElement = wait2.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"__next\"]/div[3]/div[1]/div[1]/div/div[2]/section[2]/div[2]/span[2]")));

                    // 웹 요소로부터 텍스트 추출
                    String deadline = deadlineElement.getText();
                    String workingArea = workingAreaElement.getText();
                    log.info("{}", deadline);
                    log.info("{}", workingArea);

                    returnDriverToPool(driver);
                });
            }

            executorService.shutdown();

            new Thread(() -> {
                try {
                    if (executorService.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS)) {
                        driverPool.forEach(WebDriver::quit);
                    }
                } catch (InterruptedException e) {
                    System.err.println("Interrupted while waiting for tasks to complete.");
                }
            }).start();
        };


    }

    private static void initializeDriverPool(int size) {
        driverPool = new LinkedBlockingQueue<>();

        for (int i = 0; i < size; i++) {
            ChromeOptions options = new ChromeOptions();
            options.addArguments("headless", "disable-gpu", "window-size=1920x1080",
                    "user-agent=Mozilla/5.0 (Macintosh; Intel Mac OS X 10_12_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/61.0.3163.100 Safari/537.36",
                    "blink-settings=imagesEnabled=false"
            );
            WebDriver driver = new ChromeDriver(options);
            driverPool.add(driver);
        }
    }

    private static WebDriver getDriverFromPool() {
        try {
            return driverPool.take();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private static void returnDriverToPool(WebDriver driver) {
        driverPool.add(driver);
    }
}
