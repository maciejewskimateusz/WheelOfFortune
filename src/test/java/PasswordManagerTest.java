import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class PasswordManagerTest {

    PasswordManager passwordManager = new PasswordManager();

    @Test
    void shouldGeneratedPasswordBeEqualPasswordFromList() {

        String firstPassword = "Apetyt rośnie w miarę jedzenia";
        List<String> testPasswordList = new ArrayList<>();
        testPasswordList.add(firstPassword);
        passwordManager.setPasswords(testPasswordList);
        String randomPassword = passwordManager.getRandomPassword();
        Assertions.assertTrue(randomPassword.equals(firstPassword));
    }

    @Test
    void shouldThrowExceptionIfListOfUniquePasswordsIsEmpty() {

        Assertions.assertThrows(IllegalStateException.class, () -> {
            PasswordManager passwordManager = new PasswordManager();
            for (int i = 0; i <= passwordManager.getPasswords().size(); i++) {
                passwordManager.getRandomPassword();
            }
        });
    }

    @Test
    void shouldReturnNumberOfGuessedLetterInHiddenPassword() {

        passwordManager.setCurrentPassword("Ala ma kota");
        int guessLetter = passwordManager.guessLetter('a');

        Assertions.assertEquals(4, guessLetter);
    }

    @Test
    void shouldReturnTrueIfGivenPasswordIsSameAsHiddenPassword() {

        passwordManager.setCurrentPassword("Tajne ukryte haslo");
        boolean password = passwordManager.guessPassword("tajnE Ukryte haSlo");

        Assertions.assertTrue(password);
    }

    @Test
    void shouldUncoverThreeLettersInHiddenPassword() {
        passwordManager.setCurrentPassword("Ala ma kota");
        passwordManager.setCorrectGuess(List.of('a', 'l', 'o'));

        Assertions.assertEquals("ala -a -o-a", passwordManager.getObscuredPassword());
    }

    @Test
    void shouldReturnFullCoveredPasswordWhenGivenLettersAreWrong() {
        passwordManager.setCurrentPassword("Ala ma kota");
        passwordManager.setCorrectGuess(List.of('z', 'w'));

        Assertions.assertEquals("--- -- ----", passwordManager.getObscuredPassword());
    }

    @Test
    void shouldReturnTrueIfAllCorrectLettersAreGiven() {
        passwordManager.setCurrentPassword("Ala ma kota");
        List<Character> characters = Arrays.asList('a', 'l', 'm', 'k', 'o', 't');
        passwordManager.setCorrectGuess(characters);

        Assertions.assertTrue(passwordManager.checkPassword());
    }

    @Test
    void shouldReturnFalseIfAllCorrectLettersAreNotGiven() {
        passwordManager.setCurrentPassword("Ala ma kota");
        /*
        nie wiem czemu jak wstawiam ponizej do listy wielkie litery to testy nie przechodza,
        a podczas normalnej gry gdy podaje wielkie litery to sa one automatycznie zamieniena na male i wszystko jest ok.
        Zerkniesz gdzie jest problem?
        Metoda checkPassword ignoruje wielkosc liter.
         */
        List<Character> characters = Arrays.asList('z', 'a', 'q', 'j', 'e', 't');
        passwordManager.setCorrectGuess(characters);

        Assertions.assertFalse(passwordManager.checkPassword());
    }

}