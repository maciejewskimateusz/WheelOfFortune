import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class PasswordManagerTest {

    @Test
    void shouldGeneratedPasswordBeEqualPasswordFromList() {

        PasswordManager passwordManager = new PasswordManager();
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
}