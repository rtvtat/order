package ru.netology;


import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class TestOrder {

    @Test
    public void testOrderCorrectNameAndPhone() {
        open("http://localhost:9999");
        $("span[data-test-id='name'] input[name='name']").setValue("Ивановаа Татьяна");
        $("span[data-test-id='phone'] input[name='phone']").setValue("+79278886677");
        $("label[data-test-id='agreement']").click();
        $("button").click();
        $("p[data-test-id='order-success']").shouldHave(
                Condition.exactText("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время.")
        );
    }

    @Test
    public void testOrderNameWithHyphen() {
        open("http://localhost:9999");
        $("span[data-test-id='name'] input[name='name']").setValue("Ивановаа-Сидорова Галина");
        $("span[data-test-id='phone'] input[name='phone']").setValue("+79278886677");
        $("label[data-test-id='agreement']").click();
        $("button").click();
        $("p[data-test-id='order-success']").shouldHave(
                Condition.exactText("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время.")
        );
    }

    @Test
    public void testOrderithEmptyName() {
        open("http://localhost:9999");
        $("span[data-test-id='name'] input[name='name']").setValue("Ивановаа-Сидорова Галина");
        $("span[data-test-id='phone'] input[name='phone']").setValue("");
        $("label[data-test-id='agreement']").click();
        $("button").click();
        $(".input_invalid[data-test-id='phone'] .input__sub").shouldHave(
                Condition.exactText("Поле обязательно для заполнения")
        );
    }

    @Test
    public void testOrderithEmptyPhone() {
        open("http://localhost:9999");
        $("span[data-test-id='name'] input[name='name']").setValue("");
        $("span[data-test-id='phone'] input[name='phone']").setValue("+79278886677");
        $("label[data-test-id='agreement']").click();
        $("button").click();
        $(".input_invalid[data-test-id='name'] .input__sub").shouldHave(
                Condition.exactText("Поле обязательно для заполнения")
        );
    }

    @Test
    public void testOrderithIncorrectPhone() {
        open("http://localhost:9999");
        $("span[data-test-id='name'] input[name='name']").setValue("Ивановаа-Сидорова Галина");
        $("span[data-test-id='phone'] input[name='phone']").setValue("79278886677");
        $("label[data-test-id='agreement']").click();
        $("button").click();
        $(".input_invalid[data-test-id='phone'] .input__sub").shouldHave(
                Condition.exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }

    @Test
    public void testOrderithIncorrectName() {
        open("http://localhost:9999");
        $("span[data-test-id='name'] input[name='name']").setValue("Ivanova");
        $("span[data-test-id='phone'] input[name='phone']").setValue("+79278886677");
        $("label[data-test-id='agreement']").click();
        $("button").click();
        $(".input_invalid[data-test-id='name'] .input__sub").shouldHave(
                Condition.exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }

    @Test
    public void testOrderWithEmptyAgreement() {
        open("http://localhost:9999");
        $("span[data-test-id='name'] input[name='name']").setValue("Ивановаа-Сидорова Галина");
        $("span[data-test-id='phone'] input[name='phone']").setValue("+79278886677");
        $("button").click();
        $(".input_invalid[data-test-id='agreement']").shouldHave(Condition.exist);
    }
}
