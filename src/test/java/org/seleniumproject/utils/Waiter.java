package org.seleniumproject.utils;

import com.codeborne.selenide.WebDriverRunner;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Waiter {
    private static final int DEFAULT_TIMEOUT = 20;
    public static WebDriverWait getWaiter() {
        return new WebDriverWait(WebDriverRunner.getWebDriver(), Duration.ofSeconds(DEFAULT_TIMEOUT));
    }
}
