import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Welcome to my pokemon game!");
        System.out.println("[1] - Start game.");
        System.out.println("[2] - Exit.");
        Scanner scanner = new Scanner(System.in);
        int userSelection = scanner.nextInt();
        while ( userSelection < Def.START_GAME || userSelection > Def.EXIT ){
            System.out.println("Invalid input, try again");
            userSelection = scanner.nextInt();
        }
        switch (userSelection) {
            case Def.START_GAME -> {
                GameAction gameAction = new GameAction();
                gameAction.createPokemons();
            }
            case Def.EXIT -> System.out.println("Exiting game..");
        }
    }
}
