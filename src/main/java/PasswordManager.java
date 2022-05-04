import java.util.*;

public class PasswordManager {
    private List<String> passwords = new ArrayList<>();
    private Random random = new Random();
    private Map<String, Boolean> passwordsMap = new HashMap<>();
    private String currentPassword;

    PasswordManager() {
        passwords.add("Apetyt rośnie w miarę jedzenia");
        passwords.add("Co dwie głowy, to nie jedna");
        passwords.add("Cwiczenie czyni mistrza");
        passwords.add("Darowanemu koniowi w zęby się nie zagląda");
        passwords.add("Diabeł tkwi w szczegółach");
        passwords.add("Elektryka prąd nie tyka");
        passwords.add("Dodatkowe hasło");
        passwords.add("Ekstra hasło");

        for (int i = 0; i < passwords.size(); i++) {
            passwordsMap.put(passwords.get(i), false);
        }
    }

    public List<String> getPasswords() {
        return passwords;
    }

     void setCurrentPassword(String currentPassword) {
        this.currentPassword = currentPassword;
    }

    public void setPasswords(List<String> passwords) {
        this.passwords = passwords;
        passwordsMap.clear();
        for (int i = 0; i < passwords.size(); i++) {
            passwordsMap.put(passwords.get(i), false);
        }
    }

    String getRandomPassword() {
        int randomPasswordIndex = random.nextInt(passwords.size());
        String randomPassword = passwords.get(randomPasswordIndex);

        while (areUnusedPasswords()) {
            //jezeli flaga false zwroc haslo
            if (!passwordsMap.get(randomPassword)) {
                currentPassword = randomPassword;
                passwordsMap.put(randomPassword, true);
                return currentPassword;
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

    public int guessLetter(char letter) {
        char[] guessWordArrayChar = currentPassword.toLowerCase().toCharArray();
        int counter = 0;

        for (int i = 0; i < currentPassword.length(); i++) {
            if (guessWordArrayChar[i] == letter)
                counter++;
        }
        return counter;
    }

    public boolean guessPassword(String password){
        return currentPassword.equalsIgnoreCase(password);
    }

}
