package org.seleniumproject.pages;

import org.openqa.selenium.By;

public class SQLSandboxPage {
    public static By privacyPopup = By.cssSelector("#snigel-cmp-framework");
    public static By privacyPopupAcceptAllButton = By.cssSelector("#accept-choices");
    public static By inputField = By.cssSelector("pre.CodeMirror-line");
    public static By runButton = By.cssSelector("button.ws-btn");
    public static By resultSQLExpresion = By.cssSelector("#resultSQL #divResultSQL");
    public static By resultTable = By.cssSelector("div#resultSQL table");
    public static By resultTableRow = By.cssSelector("div#resultSQL table tr");
}
