import java.util.Scanner;

public class Main {
    //O(1)
    public static void main(String[] args) {
        System.out.println("Welcome to my pokemon game! \n" + "\n" +
        "[Introduction]:This programs simulates a battle between 2 Pokemon.\n" +
                "Electric Pokemon, and Fire Pokemon.\n" +
                "Each opponent plays in turn, and each turn has a choice between several options.\n" +
                "Whether to attack the opponent, wait a turn and load up with certain stats,\n" +
                "Evolve to the next level of his Pokemon, or perform a special attack.\n" +
                "Each player plays his turn and chooses one of the options,\n" +
                "Until one of the Pokemon was defeated.\n" +
                "A fun game!" );
        System.out.println("--------------------------------------------------------------");
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
