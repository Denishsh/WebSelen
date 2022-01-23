import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;


public class OrderTest {
    @Test
    public void correctOrderTest() {
        open("http://localhost:9999");
        $("[type=text]").setValue("Андрей");
        $("[type=tel]").setValue("+79111111111");
        $(".checkbox__box").click();
        $("button").click();
//        sleep(2000);
        $("[data-test-id=order-success]").shouldHave(text("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."));
    }

    @Test
    public void incorrectNameTest() {
        open("http://localhost:9999");
        $("[type=text]").setValue("Andrew");
        $("[type=tel]").setValue("+79111111111");
        $(".checkbox__box").click();
        $("button").click();
        $(".input_type_text .input__sub").shouldHave(text("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }

    @Test
    public void incorrectTelTest() {
        open("http://localhost:9999");
        $("[type=text]").setValue("Олег");
        $("[type=tel]").setValue("79111111111");
        $(".checkbox__box").click();
        $("button").click();
        $(".input_type_tel .input__sub").shouldHave(text("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }
}
