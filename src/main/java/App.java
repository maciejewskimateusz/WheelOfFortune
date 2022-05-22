import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class App {
    public static final int ROUNDS = 4;
    public static final int MIN_PLAYERS = 2;
    public static final int MAX_PLAYERS = 4;
    public static final int POINTS_PER_GUESS = 10;
    public static PasswordManager passwordManager = new PasswordManager();
    public static boolean passwordNotGuessed = true;

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

        for (int i = 0; i < ROUNDS; i++) {
            passwordNotGuessed = true;
            System.out.println("Rozpoczęła się runda " + "<" + (i + 1) + ">");
            String randomPassword = passwordManager.getRandomPassword().toLowerCase();
            while (passwordNotGuessed) {
                for (Player player : playersList) {
                    System.out.println("Tura gracza: " + player);
                    System.out.println("Ukryte hasło:");
                    System.out.println(passwordManager.getObscuredPassword());
                    System.out.println("Proszę podać literę lub hasło");
                    String playerAnswer = scanner.nextLine().toLowerCase();
                    if (playerAnswer.length() == 1) {
                        guessLetter(randomPassword, playerAnswer, player);
                    } else {
                        guessPassword(playerAnswer, player);
                    }
                    if (!passwordNotGuessed) {
                        break;
                    }
                    if (passwordManager.checkPassword()) {
                        passwordNotGuessed = false;
                        System.out.println("Hasło odgadnięte");
                    }
                }
            }
        }
    }

    private static void guessPassword(String playerAnswer, Player player) {
        System.out.println("Zgaduję hasło");
        if (passwordManager.guessPassword(playerAnswer)) {
            System.out.println("Hasło odganięte");
            String passwordWithNoSpaces = passwordManager.getObscuredPassword().replaceAll("\\s", "");
            int numberOfGuessedLetters = passwordWithNoSpaces.length() - passwordManager.getCorrectGuess().size();
            int pointsForPlayer = POINTS_PER_GUESS * numberOfGuessedLetters;
            System.out.printf("Brawo zdobywasz %d punktów \n", pointsForPlayer);
            player.addPoints(pointsForPlayer);
            passwordNotGuessed = false;
        } else {
            System.out.println("Niepoprawne haslo");
        }
    }

    private static void guessLetter(String randomPassword, String playerAnswer, Player player) {
        char playerAnswerLetter = playerAnswer.charAt(0);
        System.out.println("Zgaduję literę");
        if (randomPassword.contains(playerAnswer)) {
            if (!passwordManager.getCorrectGuess().contains(playerAnswerLetter)) {
                int guessLetterNumber = passwordManager.guessLetter(playerAnswerLetter);
                int pointsForPlayer = guessLetterNumber * POINTS_PER_GUESS;
                System.out.printf("Brawo zdobywasz %d punktów \n", pointsForPlayer);
                player.addPoints(pointsForPlayer);
            } else {
                System.out.println("Taka litera została już podana");
            }
        } else
            System.out.println("Taka litera nie występuje w haśle");
    }
}
