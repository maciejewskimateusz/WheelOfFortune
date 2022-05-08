import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
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
    void shouldReturnNumberOfGuessedLetterInHiddenWord() {

        passwordManager.setCurrentPassword("Ala ma kota");
        int guessLetter = passwordManager.guessLetter('a');

        Assertions.assertEquals(4, guessLetter);
    }

    @Test
    void shouldReturnTrueIfGivenWordIsSameAsHiddenWord() {

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
    void shouldReturnFullCoveredPasswordWithGivenWrongLetters() {
        passwordManager.setCurrentPassword("Ala ma kota");
        passwordManager.setCorrectGuess(List.of('z', 'w'));

        Assertions.assertEquals("--- -- ----", passwordManager.getObscuredPassword());
    }
}