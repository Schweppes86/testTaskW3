package org.seleniumproject.helpers;


import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import dev.failsafe.internal.util.Assert;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.seleniumproject.testObject.Customer;


import static com.codeborne.selenide.Selenide.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.seleniumproject.helpers.HelperBase.*;
import static org.seleniumproject.pages.SQLSandboxPage.*;

public class SQLSandboxHelper {
    @Step
    public static void checkAndSubmitPrivacyPopup() {
        elementShouldAppear(privacyPopup);
        $(privacyPopupAcceptAllButton).click();
        elementShouldDisappear(privacyPopup);
    }

    @Step
    public static void sendSQLExpresion(String value) {
        executeJavaScript(String.format("window.editor.setValue(\"%s\")", value));
        elementShouldHaveText(inputField, value);

        $(runButton).click();
    }

    @Step
    public static void selectAllRowsInCustomerTable() {
        SQLSandboxHelper.sendSQLExpresion("SELECT * FROM Customers;");
    }

    @Step
    public static void addNewRowInCustomerTable(Customer customer) {
        SQLSandboxHelper.sendSQLExpresion("INSERT INTO Customers VALUES (NULL, '" + customer.getFullName() + "', '" + customer.getContactName()
                + "', '" + customer.getAddress() + "', '" + customer.getCity() + "', '"
                + customer.getZipCode() + "', '" + customer.getCountryCode() + "')");

        requestCompletedSuccessfully();
    }

    @Step
    public static void updateRowInCustomerTable(Customer customer) {
        SQLSandboxHelper.sendSQLExpresion("UPDATE Customers SET " +
                "CustomerName = '" + customer.getFullName() + "', " +
                "ContactName = '" + customer.getContactName() + "', " +
                "Address = '" + customer.getAddress() + "', " +
                "City = '" + customer.getCity() + "', "  +
                "PostalCode = '" + customer.getZipCode() + "', " +
                "Country = '" + customer.getCountryCode() + "'" +
                " WHERE CustomerID = " + customer.getCustomerID());

        elementShouldHaveText(resultSQLExpresion, "You have made changes to the database. Rows affected: 1");
    }

    @Step
    public static void deleteRowInCustomerTable(int customerID) {
        SQLSandboxHelper.sendSQLExpresion("DELETE FROM Customers WHERE CustomerID = " + customerID);

        requestCompletedSuccessfully();
    }

    @Step
    public static void requestCompletedSuccessfully() {
        elementShouldHaveText(resultSQLExpresion, "You have made changes to the database. Rows affected: 1");
    }


    @Step
    public static void checkResultRowCount(int expectedCount) {
        elementShouldAppear(resultTableRow);
        int actualCount = $$(resultTableRow).size() - 1;
        assertEquals(actualCount, expectedCount, "Expected count " + expectedCount + ", but found " + actualCount);
    }

    @Step
    public static int getRowCount() {
        elementShouldAppear(resultSQLExpresion);
        SQLSandboxHelper.sendSQLExpresion("SELECT COUNT(*) FROM Customers;");
        String s = $$(resultTableRow).get(1).getText();
        return Integer.parseInt(s);
    }

    @Step
    public static void checkAddressByCustomerName(String customerNameValue, String expectedAddressValue) {
        elementShouldAppear(resultTable);
        By rowValueByCustomerName = By.xpath(String.format("//*/table//td[3][contains(text(),'%s')]/..", customerNameValue));
        Assert.notNull(rowValueByCustomerName, "Can't find elements with Customer Name " + customerNameValue);
        elementShouldBeVisible(rowValueByCustomerName);
        elementShouldHaveText($(rowValueByCustomerName).$$("td").get(3), expectedAddressValue);
    }

    @Step
    public static void checkRowIsExists(Customer customer) {
        elementShouldAppear(resultTable);
        String expectedRow = customer.getCustomerID() + " " + customer.getFullName() +" " + customer.getContactName()
                + " " + customer.getAddress() + " " + customer.getCity() + " "
                + customer.getZipCode() + " " + customer.getCountryCode();
        String actualRow = $$(resultTableRow).get(customer.getCustomerID()).getText();

        assertEquals(expectedRow, actualRow, "Rows don't match! Expected: " + expectedRow + " , but found " + actualRow);
    }

    @Step
    public static void checkRowIsDeleted(Customer customer) {
        elementShouldAppear(resultTable);
        String expectedRow = customer.getCustomerID() + " " + customer.getFullName() +" " + customer.getContactName()
                + " " + customer.getAddress() + " " + customer.getCity() + " "
                + customer.getZipCode() + " " + customer.getCountryCode();

        for (SelenideElement element : $$(resultTableRow)) {
            element.shouldNotHave(Condition.text(expectedRow));
        }
    }
}
