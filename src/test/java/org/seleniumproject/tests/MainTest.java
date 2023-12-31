package org.seleniumproject.tests;

import io.qameta.allure.Description;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.seleniumproject.helpers.SQLSandboxHelper;
import org.seleniumproject.testobjects.Customer;
import org.seleniumproject.utils.TestListener;

@ExtendWith(TestListener.class)
public class MainTest extends TestBase {

    @Description("Задание  1. Вывести все строки таблицы Customers и убедиться, что запись с ContactName " +
            "равной 'Giovanni Rovelli' имеет Address = 'Via Ludovico il Moro 22'")
    @Test()
    public void selectAllTest() {
        SQLSandboxHelper.selectAllRowsInCustomerTable();
        SQLSandboxHelper.checkAddressByCustomerName("Giovanni Rovelli", "Via Ludovico il Moro 22");
    }

    @Description("Задание 2. Вывести только те строки таблицы Customers, где city='London'. Проверить, что в таблице ровно 6 записей.")
    @Test()
    public void selectWithFilterTest() {
        SQLSandboxHelper.sendSQLExpresion("SELECT * FROM Customers WHERE city='London'");
        SQLSandboxHelper.checkResultRowCount(6);
    }

    @Description("Задание 3. Добавить новую запись в таблицу Customers и проверить, что эта запись добавилась.")
    @Test()
    public void insertTest() {
        Customer customer = new Customer();
        int initCount = SQLSandboxHelper.getRowCount();
        SQLSandboxHelper.addNewRowInCustomerTable(customer);
        customer.setCustomerID(initCount + 1);

        SQLSandboxHelper.selectAllRowsInCustomerTable();
        SQLSandboxHelper.checkRowIsExists(customer);
    }

    @Description("Задание 4. Обновить все поля (кроме CustomerID) в любой записи таблицы Customers и проверить, что изменения записались в базу.")
    @Test()
    public void updateTest() {
        Customer customer = new Customer();
        customer.setCustomerID(5);

        SQLSandboxHelper.updateRowInCustomerTable(customer);
        SQLSandboxHelper.selectAllRowsInCustomerTable();
        SQLSandboxHelper.checkRowIsExists(customer);
    }


    @Description("Задание 5. Создать и удалить запись. Проверить, что запись в таблице отсутствует.")
    @Test()
    public void deleteTest() {
        Customer customer = new Customer();
        int initCount = SQLSandboxHelper.getRowCount();
        SQLSandboxHelper.addNewRowInCustomerTable(customer);
        customer.setCustomerID(initCount + 1);

        SQLSandboxHelper.selectAllRowsInCustomerTable();
        SQLSandboxHelper.checkRowIsExists(customer);

        SQLSandboxHelper.deleteRowInCustomerTable(customer.getCustomerID());

        SQLSandboxHelper.selectAllRowsInCustomerTable();
        SQLSandboxHelper.checkResultRowCount(initCount);
        SQLSandboxHelper.checkRowIsDeleted(customer);
    }
}
