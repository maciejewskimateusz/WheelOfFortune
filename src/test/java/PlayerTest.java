import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {

    @Test
    void shouldThrowIllegalArgumentExceptionIfNameIsNull() {

        Assertions.assertThrows(IllegalArgumentException.class, () -> new Player(" "));

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