import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;


public class OrderTest {

    @BeforeEach
    public void setup() {
        open("http://localhost:9999");
    }

    @Test
    public void correctOrderTest() {
        $("[type=text]").setValue("Иванов Андрей");
        $("[type=tel]").setValue("+79111111111");
        $(".checkbox__box").click();
        $("button").click();
//        sleep(2000);
        $("[data-test-id=order-success]").shouldHave(text("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."));
    }

    @Test
    public void incorrectNameTest() {
        $("[type=text]").setValue("Andrew");
        $("[type=tel]").setValue("+79111111111");
        $(".checkbox__box").click();
        $("button").click();
        $(".input_invalid .input__sub").shouldHave(text("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }

    @Test
    public void emptyNameTest() {
        $("[type=text]").setValue("");
        $("[type=tel]").setValue("+79111111111");
        $(".checkbox__box").click();
        $("button").click();
        $(".input_invalid .input__sub").shouldHave(text("Поле обязательно для заполнения"));
    }

    @Test
    public void incorrectTelTest() {
        $("[type=text]").setValue("Петров Олег");
        $("[type=tel]").setValue("79111111111");
        $(".checkbox__box").click();
        $("button").click();
        $(".input_invalid .input__sub").shouldHave(text("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }

    @Test
    public void emptyTelTest() {
        $("[type=text]").setValue("Петров Олег");
        $("[type=tel]").setValue("");
        $(".checkbox__box").click();
        $("button").click();
        $(".input_invalid .input__sub").shouldHave(text("Поле обязательно для заполнения"));
    }

    @Test
    public void withoutCheckboxTest() {
        $("[type=text]").setValue("Петров Олег");
        $("[type=tel]").setValue("79111111111");
        $("button").click();
        $(".input_invalid");
    }
}
