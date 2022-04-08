import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Player> playersList = new ArrayList<>();

        System.out.println("Witaj w Kole Fortuny!");
        System.out.println("Proszę podac ilość graczy");
        try {
            int numberOfPlayers = scanner.nextInt();
            scanner.nextLine();
            if (numberOfPlayers >= 2 && numberOfPlayers <= 4) {
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
    }
}
