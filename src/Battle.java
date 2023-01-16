import java.util.Random;
import java.util.Scanner;

public class Battle implements Attacks {
    private static final Random random = new Random();


    public static void startBattle(Pokemon[] pokemons) {
        int index1 = random.nextInt(pokemons.length);
        int index2 = random.nextInt(pokemons.length);
        while (index1 == index2) {
            index2 = random.nextInt(pokemons.length);
        }
        Pokemon pokemon1 = pokemons[index1];
        Pokemon pokemon2 = pokemons[index2];
        System.out.println("Let the battle begin!");
        setPokemonMaxStats(pokemon1, pokemon2);
        int turnCounter = 0;
        pokemon1.setPokemonAttackPoints((pokemon1.getPokemonMaxAp() * 75) / 100);
        pokemon2.setPokemonAttackPoints((pokemon2.getPokemonMaxAp() * 75) / 100);
        System.out.println(pokemon1);
        System.out.println("------ [VS] ------");
        System.out.println(pokemon2);
        battleProgress(pokemon1, pokemon2, turnCounter,true);
    }

    private static void battleProgress(Pokemon pokemon1, Pokemon pokemon2, int turnCounter,boolean successRound) {
        if (pokemon1.isDefeated() || pokemon2.isDefeated()) {
            Pokemon.getPokemonsStats(pokemon1,pokemon2);
            GameAction.battleVictory(pokemon1, pokemon2);
        } else {
            Pokemon currentPokemon, opponentPokemon;
            int playerID = getPlayerTurn(turnCounter);
            System.out.println(playerID+" PLAYER ID");
            if (playerID == Def.PLAYER_1) {
                currentPokemon = pokemon1;
                opponentPokemon = pokemon2;
            } else {
                currentPokemon = pokemon2;
                opponentPokemon = pokemon1;
            }
            if (turnCounter >= 1 && successRound) {
                addElectricPokemonStats(currentPokemon, opponentPokemon);
                generateStatsPerRound(currentPokemon, opponentPokemon);
                Pokemon.getPokemonsStats(currentPokemon, opponentPokemon);
            }
            battleMenu(currentPokemon,opponentPokemon,turnCounter);
        }
    }

    private static void battleMenu(Pokemon currentPokemon,Pokemon opponentPokemon, int turnCounter){
        System.out.println(" ");
        System.out.println(currentPokemon.getPokemonName() + " " + "Turn.");
        System.out.println(" ");
        System.out.println("[1] - Attack. || [2] - Charge.");
        System.out.println("[3] - Pokemon evolution. || [4] - Special attack");
        Scanner scanner = new Scanner(System.in);
        int userInput = scanner.nextInt();
        while(userInput < 1 || userInput > 4){
            System.out.println("Invalid input, try again.");
            userInput = scanner.nextInt();
        }
        switch (userInput) {
            case Def.ATTACK -> {
                boolean isAttacked = pokemonAttack(currentPokemon, opponentPokemon);
                if(!isAttacked){
                    System.out.println("Turn is still yours, the attack didn't apply.");
                    battleProgress(currentPokemon,opponentPokemon,turnCounter,false);
                }
                else{
                    turnCounter++;
                    battleProgress(currentPokemon,opponentPokemon,turnCounter,true);
                }
            }
            case Def.CHARGE -> charge(currentPokemon, opponentPokemon, turnCounter);
            case Def.EVOLUTION -> {
                String preEvolvedName = currentPokemon.getPokemonName();
                Pokemon evolvedPokemon = setPokemonLevel(currentPokemon);
                if (evolvedPokemon.getPokemonName().equals(preEvolvedName)) {
                    battleProgress(currentPokemon, opponentPokemon, turnCounter,false);
                } else {
                    turnCounter++;
                    battleProgress(evolvedPokemon, opponentPokemon,turnCounter,true);
                }
            }
            case Def.SPECIAL -> {
                boolean success = specialPokemonAction(currentPokemon, opponentPokemon);
                if (!success) {
                    System.out.println("Turn is still with " + currentPokemon.getPokemonName());
                    battleProgress(currentPokemon, opponentPokemon, turnCounter,false);
                }
                else {
                    turnCounter++;
                    battleProgress(currentPokemon, opponentPokemon, turnCounter,true);
                }
            }
        }
    }
    private static boolean pokemonAttack(Pokemon currentPokemon, Pokemon opponentPokemon) {
        boolean isAttacked;
        int numberOfAttack = 0;
        for (int i = 0; i < currentPokemon.getAttacks().length; i++) {
            numberOfAttack++;
            System.out.println("[" + numberOfAttack + "] - " + currentPokemon.getAttacks()[i] + "|  Ability costs: [" + currentPokemon.getCosts()[i] + "]");
        }
        int kick = numberOfAttack + 1;
        System.out.println("[" + kick + "] - Kick | [No cost.]");
        Scanner scanner = new Scanner(System.in);
        int userAttack = scanner.nextInt();
        while(userAttack < 1 || userAttack > currentPokemon.getAttacks().length+1){
            System.out.println("Invalid input, try again");
            userAttack = scanner.nextInt();
        }
        isAttacked = Attacks.performAttack(currentPokemon, opponentPokemon, userAttack, false);
        return isAttacked;
    }

    public static void charge(Pokemon currentPokemon, Pokemon opponentPokemon, int turnCounter) {
        int chargeHp;
        int chargeAp;
        Random random = new Random();
        int randomNumber = random.nextInt(3) + 1;
        if (currentPokemon.isTripleAttack()) {
            randomNumber = random.nextInt(2) + 1;
        }
        if(currentPokemon.getPokemonHealth() == currentPokemon.getPokemonMaxHp() && currentPokemon.getAttackPoints() == currentPokemon.getPokemonMaxAp() && currentPokemon.isTripleAttack()){
            System.out.println("You are fully charger and got triple attack, choose another option.");
            System.out.println("Turn is still yours "+currentPokemon.getPokemonName());
            battleProgress(currentPokemon,opponentPokemon,turnCounter,false);
        }
        else {
            if (randomNumber == Def.HP_BONUS) {
                chargeHp = random.nextInt(30 - 4) + 5;
                currentPokemon.setPokemonHealth(currentPokemon.getPokemonHealth() + chargeHp);
                checkPokemonHpAndAp(currentPokemon);
                System.out.println("You have gained: " + chargeHp + " Health points.");
            }
            if (randomNumber == Def.AP_BONUS) {
                chargeAp = random.nextInt(40) + 1;
                currentPokemon.setPokemonAttackPoints(currentPokemon.getAttackPoints() + chargeAp);
                checkPokemonHpAndAp(currentPokemon);
                System.out.println("You have gained " + chargeAp + " Ap points");
            }
            if (randomNumber == Def.TRIPLE_ATTACK_BONUS) {
                currentPokemon.setTripleAttack(true);
                System.out.println("You got a triple attack.");
            }
            turnCounter++;
            battleProgress(currentPokemon, opponentPokemon, turnCounter, true);
        }
    }

    public static Pokemon setPokemonLevel(Pokemon currentPokemon) {
        Pokemon evolvedPokemon = Evolution.evolvePokemon(currentPokemon);
        if (evolvedPokemon.getPokemonName().equals(currentPokemon.getPokemonName())) {
            return currentPokemon;
        } else {
            return evolvedPokemon;
        }
    }


    public static boolean specialPokemonAction(Pokemon currentPokemon, Pokemon oponnentPokemon) {
        boolean success = false;
        boolean isSpecialAttack = true;
        if (!currentPokemon.isSpecialAttack()) {
            if (!currentPokemon.isUsedSpecialAttack()) {
                switch (currentPokemon.getPokemonType()) {
                    case "Fire pokemon" -> {
                        currentPokemon.setPokemonHealth((currentPokemon.getPokemonHealth() * 50) / 100);
                        currentPokemon.setPokemonAttackPoints(0);
                        currentPokemon.hasSpecialAttack(true);
                        System.out.println("Special attack from " + currentPokemon.getPokemonName());
                        Attacks.performAttack(currentPokemon, oponnentPokemon, 0,isSpecialAttack);
                        success = true;
                    }
                    case "Electric pokemon" -> {
                        currentPokemon.setPokemonHealth(currentPokemon.getPokemonMaxHp());
                        currentPokemon.setPokemonAttackPoints(currentPokemon.getPokemonMaxAp());
                        currentPokemon.hasSpecialAttack(false);
                        currentPokemon.usedSpecialAttack(true);
                        System.out.println("You filled your power and health to the maximum!");
                        success = true;
                    }
                }
            } else {
                System.out.println("You already did a special attack, choose another option in the battle.");
            }
        }
        return success;
    }

    public static void generateStatsPerRound(Pokemon currentPokemon, Pokemon opponentPokemon) {
        Random randomHp = new Random();
        Random randomAp = new Random();
        int hpCurrentPokemon = randomHp.nextInt(4) + 1;
        int apCurrentPokemon = randomAp.nextInt(5) + 1;
        int hpOpponentPokemon = randomHp.nextInt(4) + 1;
        int apOpponentPokemon = randomAp.nextInt(5) + 1;
        currentPokemon.setPokemonHealth(currentPokemon.getPokemonHealth() + hpCurrentPokemon);
        currentPokemon.setPokemonAttackPoints(currentPokemon.getAttackPoints() + apCurrentPokemon);
        opponentPokemon.setPokemonHealth(opponentPokemon.getPokemonHealth() + hpOpponentPokemon);
        opponentPokemon.setPokemonAttackPoints(opponentPokemon.getAttackPoints() + apOpponentPokemon);
        checkPokemonHpAndAp(currentPokemon);
        checkPokemonHpAndAp(opponentPokemon);
        System.out.println(currentPokemon.getPokemonName()+" Bonus stats! Hp:[" +hpCurrentPokemon + "]  " + " Ap:[" +apCurrentPokemon+"]");
        System.out.println(opponentPokemon.getPokemonName()+" Bonus stats! Hp:[" +hpOpponentPokemon + "]  " + " Ap:[" +apOpponentPokemon+"]");
    }

    public static void addElectricPokemonStats(Pokemon pokemon1, Pokemon pokemon2) {
        if (pokemon1.getPokemonType().equals("Electric pokemon")) {
            if (pokemon1.getPokemonHealth() <= pokemon1.getPokemonHealth() / 100 * 20) {
                pokemon1.setElectricPower(0);
                System.out.println(pokemon1.getPokemonName() + "Have less than 20% hp, The electric power reset.");
            } else {
                pokemon1.setElectricPower(pokemon1.getElectricPower() + Def.FIVE_PERCENT);
            }
        } else if (pokemon2.getPokemonType().equals("Electric pokemon")) {
            if (pokemon2.getPokemonHealth() <= pokemon2.getPokemonHealth() / 100 * 20) {
                pokemon2.setElectricPower(0);
                System.out.println(pokemon1.getPokemonName() + "Have less than 20% hp, The electric power reset.");
            } else {
                pokemon2.setElectricPower(pokemon2.getElectricPower() + Def.FIVE_PERCENT);
            }
        }
    }

    private static void setPokemonMaxStats(Pokemon pokemon1, Pokemon pokemon2) {
        pokemon1.setPokemonMaxHp(pokemon1.getPokemonHealth());
        pokemon2.setPokemonMaxHp(pokemon2.getPokemonHealth());
        pokemon1.setPokemonMaxAp(pokemon1.getAttackPoints());
        pokemon2.setPokemonMaxAp(pokemon2.getAttackPoints());
    }

    public static void checkPokemonHpAndAp(Pokemon currentPokemon) {
        if (currentPokemon.getPokemonHealth() > currentPokemon.getPokemonMaxHp()) {
            int differenceHp = currentPokemon.getPokemonHealth() -currentPokemon.getPokemonMaxHp();
            currentPokemon.setPokemonHealth(currentPokemon.getPokemonHealth() - differenceHp);
        }
        if ( currentPokemon.getAttackPoints() > currentPokemon.getPokemonMaxAp()) {
            int differenceAp =currentPokemon.getAttackPoints() - currentPokemon.getPokemonMaxAp();
            currentPokemon.setPokemonAttackPoints(currentPokemon.getAttackPoints() - differenceAp);
        }
    }

    public static int getPlayerTurn(int turnCounter) {
        int playerID;
        System.out.println(turnCounter);
        if (turnCounter % 2 == 0) {
            playerID = Def.PLAYER_2;
            System.out.println("TRUE");
        } else {
            playerID = Def.PLAYER_1;
            System.out.println("FALSE");

        }
        return playerID;
    }
}
