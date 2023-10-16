package org.seleniumproject.utils;

import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Step;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.function.Function;

public class Waiter {
    private static final int DEFAULT_TIMEOUT = 20;
    public static WebDriverWait getWaiter() {
        return new WebDriverWait(WebDriverRunner.getWebDriver(), Duration.ofSeconds(DEFAULT_TIMEOUT));
    }
}
