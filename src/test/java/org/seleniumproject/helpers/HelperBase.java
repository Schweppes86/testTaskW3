package org.seleniumproject.helpers;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.seleniumproject.utils.Waiter;

import static com.codeborne.selenide.Selenide.*;

public class HelperBase {
    public static void urlShouldBe(String susbtring) {
        Waiter.getWaiter().until(ExpectedConditions.urlToBe(susbtring));
    }

    public static void titleShouldAppear(String titleText) {
        Waiter.getWaiter().until(ExpectedConditions.titleIs(titleText));
    }

    public static void elementShouldBeVisible(By locator) {
        $(locator).shouldBe(Condition.visible);
    }

    public static void elementShouldAppear(By locator) {
        Waiter.getWaiter().until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public static void elementShouldDisappear(By locator) {
        Waiter.getWaiter().until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }

    public static void elementShouldHaveText(By locator, String text) {
        Waiter.getWaiter().until(ExpectedConditions.textToBe(locator, text));
    }

    public static void elementShouldHaveText(WebElement element, String text) {
        Waiter.getWaiter().until(ExpectedConditions.textToBePresentInElement(element, text));
    }
}

