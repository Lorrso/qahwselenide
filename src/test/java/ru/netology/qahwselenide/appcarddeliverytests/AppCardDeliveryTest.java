package ru.netology.qahwselenide.appcarddeliverytests;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class AppCardDeliveryTest {
    @Test
    void shouldSendFormPositivePath() {
        LocalDate date = LocalDate.now();
        date = date.plusDays(4);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        String formDate = date.format(formatter);

        open("http://localhost:9999");
        $("[data-test-id=city] input").setValue("Иркутск");
        $("[data-test-id=date] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.DELETE);
        $("[data-test-id=date] input").setValue(formDate);
        $("[data-test-id=name] input").setValue("Шевченко Анна");
        $("[data-test-id=phone] input").setValue("+79960000000");
        $("[data-test-id=agreement]").click();
        $(".button").click();
        $(".notification__content")
                .shouldBe(Condition.visible, Duration.ofSeconds(15))
                .shouldHave(Condition.exactText("Встреча успешно забронирована на " + formDate));
    }
}
