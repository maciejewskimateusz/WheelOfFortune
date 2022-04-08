import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class PlayerTest {

    @Test
    void shouldThrowIllegalArgumentExceptionIfNameIsEmpty() {

        Assertions.assertThrows(IllegalArgumentException.class, () -> new Player(" "));

    }

    @Test
    void shouldThrowIllegalArgumentExceptionIfNameIsNull() {

        Assertions.assertThrows(IllegalArgumentException.class, () -> new Player(null));

    }

    @Test
    void shouldPrintGoodFormatOfPlayerToString() {

        Player jan = new Player("Jan");

        Assertions.assertEquals("[<Jan>]", jan.toString());
    }

    @Test
    void shouldCreateNotNullPlayer() {

        Player jan = new Player("Jan");

        Assertions.assertNotNull(jan);
    }

}