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
        scanner.close();
        for (int i = 0; i < ROUNDS; i++) {
            System.out.println("Rozpoczęła się runda " + "<" + (i + 1) + ">");
            playersList.forEach((player) -> System.out.println("Tura gracza: " + player.toString()));
        }
    }
}
