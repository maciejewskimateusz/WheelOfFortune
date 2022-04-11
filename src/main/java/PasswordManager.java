import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PasswordManager {
    private List<String> passwords = new ArrayList<>();
    private Random random = new Random();

    public PasswordManager() {
        passwords.add("Apetyt rośnie w miarę jedzenia");
        passwords.add("Co dwie głowy, to nie jedna");
        passwords.add("Cwiczenie czyni mistrza");
        passwords.add("Darowanemu koniowi w zęby się nie zagląda");
        passwords.add("Diabeł tkwi w szczegółach");
        passwords.add("Elektryka prąd nie tyka");
    }

    String getRandomPassword() {
        int randomPasswordIndex = random.nextInt(passwords.size());
        return passwords.get(randomPasswordIndex);
    }
}
