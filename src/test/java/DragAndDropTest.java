import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.DragAndDropOptions.to;
import static com.codeborne.selenide.Selenide.*;

public class DragAndDropTest {
    @BeforeAll
    static void beforeAll() {
        Configuration.browserSize = "1920x1080";
        Configuration.baseUrl = "https://the-internet.herokuapp.com";
        Configuration.pageLoadStrategy = "eager";
    }

    @Test
    void swapAB () {
        open("/drag_and_drop");
        // Проверка предусловия
        $("#column-a").shouldHave(exactTextCaseSensitive("A"));
        $("#column-b").shouldHave(exactTextCaseSensitive("B"));

        // Перемещение
        actions().moveToElement($("#column-a")).clickAndHold().moveToElement($("#column-b")).release().perform();

        // Проверка результата
        $("#column-a").shouldHave(exactTextCaseSensitive("B"));
        $("#column-b").shouldHave(exactTextCaseSensitive("A"));

        // dragAndDrop
        $("#column-a").dragAndDrop(to("#column-b"));

        // Проверка результата
        $("#column-a").shouldHave(exactTextCaseSensitive("A"));
        $("#column-b").shouldHave(exactTextCaseSensitive("B"));
    }
}
