package org.seleniumproject.utils;

import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Allure;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestWatcher;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.util.Optional;


public class TestListener implements TestWatcher {
    @Override
    public void testFailed(ExtensionContext context, Throwable cause) {
        Allure.getLifecycle().addAttachment(
                "screenshot", "image/png", "png",
                ((TakesScreenshot) WebDriverRunner.getWebDriver()).getScreenshotAs(OutputType.BYTES));

        System.out.println("Test Failed for " + context.getDisplayName()
                + " + with reason : " + cause);
    }

    @Override
    public void testDisabled(ExtensionContext context, Optional<String> reason) {
        System.out.println("Test Disabled for " + context.getDisplayName()
                + " + with reason : " + reason.orElse("No reason"));
    }

    @Override
    public void testSuccessful(ExtensionContext context) {
        System.out.println("Test Successful for " + context.getDisplayName());
    }

    @Override
    public void testAborted(ExtensionContext context, Throwable cause) {
        System.out.println("Test Disabled for " + context.getDisplayName()
                + " + with reason : " + cause);
    }

    @AfterEach
    public void closeWebDriver() {
        closeWebDriver();
    }
}
