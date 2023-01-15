import java.util.Random;
import java.util.Scanner;

public class GameAction {
    public GameAction() {
        System.out.println("[1] - Start game.");
        System.out.println("[2] - Exit.");
        Scanner scanner = new Scanner(System.in);
        int userSelection = scanner.nextInt();
        switch (userSelection) {
            case Def.START_GAME -> createPokemons();
            case Def.EXIT -> System.out.println("Exiting game..");
        }
    }

    public void createPokemons() {
        Pokemon charmender = new Charmender();
        int[] charmenderCosts = new int[]{15};
        charmender.setCosts(charmenderCosts);

        Pokemon salandit = new Salandit();
        int[] salanditCosts = new int[]{10};
        salandit.setCosts(salanditCosts);

        Pokemon moltres = new Moltres();
        int[] moltresCosts = new int[]{30,30};
        moltres.setCosts(moltresCosts);

        Pokemon pichu = new Pichu();
        int[] pichuCosts = new int[]{5};
        pichu.setCosts(pichuCosts);

        Pokemon blitzle = new Blitzle();
        int[] blitzleCosts = new int[]{20};
        blitzle.setCosts(blitzleCosts);

        Pokemon electabuzz = new Electabuzz();
        int[] electabuzzCosts = new int[]{60};
        electabuzz.setCosts(electabuzzCosts);

        Pokemon[] pokemons = {electabuzz,blitzle};
        Battle.startBattle(pokemons);
    }

    public static void battleVictory(Pokemon pokemon1, Pokemon pokemon2) {
        if (pokemon1.getPokemonHealth() <= Def.DEFEAT_HP) {
            System.out.println("There is a winner! " + pokemon2.getPokemonName());
        } else {
            System.out.println("There is a winner! " + pokemon1.getPokemonName());
        }
        pokemon1 = null;
        pokemon2 = null;
        new GameAction();
    }
}
