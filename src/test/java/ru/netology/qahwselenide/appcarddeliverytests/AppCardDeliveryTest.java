package ru.netology.qahwselenide.appcarddeliverytests;

import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class AppCardDeliveryTest {
    @Test
    void shouldSendFormPositivePath() {
        LocalDate date = LocalDate.now();
        date = date.plusDays(5);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        String formDate = date.format(formatter);

        open("http://localhost:9999");
        SelenideElement form = $(".form");
        form.$("[data-test-id=city] input").setValue("Иркутск");
        form.$("[data-test-id=date] input").doubleClick().setValue(formDate);
        form.$("[data-test-id=name] input").setValue("Шевченко Анна");
        form.$("[data-test-id=phone] input").setValue("+79960000000");
        form.$("[data-test-id=agreement]").click();
        form.$(".button").click();
        $(withText("Встреча успешно забронирована на")).shouldBe(visible, Duration.ofSeconds(15));
    }
}
