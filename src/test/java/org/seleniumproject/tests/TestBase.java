package org.seleniumproject.tests;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.open;
import static org.seleniumproject.helpers.SQLSandboxHelper.checkAndSubmitPrivacyPopup;

public abstract class TestBase {
    public static boolean privacyPopupAccepted = false;

    @BeforeAll
    static void init(){
        Configuration.timeout = 10000;
        Configuration.pageLoadTimeout = 60000;
        Configuration.browser = "chrome";
        Configuration.browserSize = "1920x1080";
        Configuration.headless = false;
        Configuration.screenshots = false;
        Configuration.downloadsFolder = "\\artifacts\\downloads";
        Configuration.reportsFolder = "\\artifacts\\screenshots";
        Configuration.baseUrl = "https://www.w3schools.com/sql/trysql.asp?filename=trysql_select_all";
    }

    @BeforeEach
    void setup() {
        open(Configuration.baseUrl);
        if (!privacyPopupAccepted) {
            checkAndSubmitPrivacyPopup();
            privacyPopupAccepted = true;
        }
    }


    @AfterAll
    static void close(){
        closeWebDriver();
    }
}
