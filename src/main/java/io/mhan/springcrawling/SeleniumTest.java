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
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
@Configuration
public class SeleniumTest {

    @Bean
    public ApplicationRunner test() {
        return args -> {
            ExecutorService executorService = Executors.newFixedThreadPool(4);
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
            };

            for (Integer id: ids) {
                executorService.submit(() -> {
                    ChromeOptions options = new ChromeOptions();
                    options.addArguments("headless", "disable-gpu", "window-size=1920x1080",
                            "user-agent=Mozilla/5.0 (Macintosh; Intel Mac OS X 10_12_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/61.0.3163.100 Safari/537.36"
                            );
                    WebDriver driver = new ChromeDriver(options);
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

                    // 브라우저 닫기
                    driver.quit();
                });
            }

            executorService.shutdown();
        };
    }
}
