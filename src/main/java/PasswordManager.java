import java.util.*;

public class PasswordManager {
    private List<String> passwords = new ArrayList<>();
    private Random random = new Random();
    private Map<String, Boolean> passwordsMap = new HashMap<>();

    PasswordManager() {
        passwords.add("Apetyt rośnie w miarę jedzenia");
        passwords.add("Co dwie głowy, to nie jedna");
        passwords.add("Cwiczenie czyni mistrza");
        passwords.add("Darowanemu koniowi w zęby się nie zagląda");
        passwords.add("Diabeł tkwi w szczegółach");
        passwords.add("Elektryka prąd nie tyka");

        for (int i = 0; i < passwords.size(); i++) {
            passwordsMap.put(passwords.get(i), false);
        }
    }

    String getRandomPassword() {
        int randomPasswordIndex = random.nextInt(passwords.size());
        String password;
        String randomPassword = passwords.get(randomPasswordIndex);

        while (areUnusedPasswords()) {
            //jezeli flaga false zwroc haslo
            if (!passwordsMap.get(randomPassword)){
                password = randomPassword;
                passwordsMap.put(randomPassword, true);
                return password;
            } else {
                // jezeli wylosowane haslo ma juz flage true, losuj nowe haslo
                randomPassword = passwords.get(random.nextInt(passwords.size()));
            }
        }
        throw new IllegalStateException("brak unikalnego hasła");
    }

    private boolean areUnusedPasswords() {
        for (Map.Entry<String, Boolean> entry : passwordsMap.entrySet()) {
            if (!entry.getValue())
                return true;
        }
        return false;
    }

    public void setPasswords(List<String> passwords) {
        this.passwords = passwords;
        passwordsMap.clear();
        for (int i = 0; i < passwords.size(); i++) {
            passwordsMap.put(passwords.get(i), false);
        }
    }

    public List<String> getPasswords() {
        return passwords;
    }
}
