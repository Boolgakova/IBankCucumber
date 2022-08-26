package ru.netology.ibank.steps;

import io.cucumber.java.ru.*;
import ru.netology.ibank.data.DataHelper;
import ru.netology.ibank.page.DashboardPage;
import ru.netology.ibank.page.LoginPage;
import ru.netology.ibank.page.TransferPage;

import static com.codeborne.selenide.Selenide.open;

public class TemplateSteps {

    @Пусть("пользователь залогинен;")
    public void пользователь_залогинен() {
        open("http://localhost:9999");
        LoginPage loginPage = new LoginPage();
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        verificationPage.validVerify(verificationCode);
    }

    @Когда("пользователь переводит {string} рублей на свою {string} карту с главной страницы;")
    public void пользователь_переводит_рублей_на_свою_карту_с_главной_страницы(String string, String string2) {
        if(string2.equals("1")){
            DashboardPage dashboardPage = new DashboardPage();
            dashboardPage.depositToFirst();
            TransferPage transferPage = new TransferPage();
            transferPage.moneyTransfer(DataHelper.CardInfo.getCardTwo(), string);
        }
        if(string2.equals("2")) {
            DashboardPage dashboardPage = new DashboardPage();
            dashboardPage.depositToSecond();
            TransferPage transferPage = new TransferPage();
            transferPage.moneyTransfer(DataHelper.CardInfo.getCardOne(), string);
        }
    }
    @Тогда("баланс его {string} карты из списка на главной странице должен стать {string} рублей.")
    public void баланс_его_карты_из_списка_на_главной_странице_должен_стать_рублей(String string, String string2) {
        DashboardPage dashboardPage = new DashboardPage();
        string2 = String.valueOf(dashboardPage.getCardBalance(string));
    }
}