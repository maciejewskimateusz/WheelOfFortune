import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class App {
    public static final int ROUNDS = 4;
    public static final int MIN_PLAYERS = 2;
    public static final int MAX_PLAYERS = 4;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Player> playersList = new ArrayList<>();
        PasswordManager passwordManager = new PasswordManager();

        System.out.println("Witaj w Kole Fortuny!");
        System.out.println("Proszę podac ilość graczy");
        try {
            int numberOfPlayers = scanner.nextInt();
            scanner.nextLine();
            if (numberOfPlayers >= MIN_PLAYERS && numberOfPlayers <= MAX_PLAYERS) {
                for (int i = 0; i < numberOfPlayers; i++) {
                    System.out.println("Proszę podać imie gracza nr " + (i + 1));
                    String playerName = scanner.nextLine();
                    playersList.add(new Player(playerName));
                }
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Imie gracza nie moze byc puste");
        } catch (InputMismatchException e) {
            System.out.println("Nie podales liczby calkowitej");
        }

        for (int i = 0; i < ROUNDS; i++) {
            System.out.println("Rozpoczęła się runda " + "<" + (i + 1) + ">");
            for (Player player : playersList) {
                String randomPassword = passwordManager.getRandomPassword().toLowerCase();
                System.out.println("Tura gracza: " + player);
                System.out.println("Proszę podać literę lub hasło");
                String playerAnswer = scanner.nextLine().toLowerCase();
                if (playerAnswer.length() == 1) {
                    System.out.println("Zgaduję literę");
                    if (randomPassword.contains(playerAnswer))
                        System.out.println("Zgadnięta");
                    else
                        System.out.println("Taka litera nie występuje w haśle");
                } else {
                    System.out.println("Zgaduję hasło");
                    if (randomPassword.equals(playerAnswer))
                        System.out.println("Hasło odganięte");
                    else
                        System.out.println("Niepoprawne haslo");
                }
            }
        }
    }
}
