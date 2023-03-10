import java.util.Random;
import java.util.Scanner;

public class Battle {
    private boolean successRound;

    // O(n)
    public void startBattle(Pokemon pokemon1, Pokemon pokemon2){
        System.out.println("Instruction: \n" +
                "Fire pokemons: Every pokemon can evolve instead of moltres, fire pokemons special attack \n" +
                "can be used how many times the player is choosing, the attack points will reset to 0 and his hp will \n" +
                "decrease by 50% every time. \n" +
                "////////////////////////////////////////////////////////////////////////////////////////////////\n"+
                "Electric pokemons : same as fire pokemons, they can evolve, their special attack can be used once \n" +
                "and their HP and AP will recharge to 100%. \n" +
                "electric pokemons attacks always multiply with their electric power! \n" +
                "every turn electric pokemon playing, his electric power got more 5% power. \n"+
                "Waiting a turn stats is generated between: [5-30] Hp // [0-35] Ap // Triple attack next turn.\n"+
                "--------------------------------------------------------------------------------------");
        System.out.println("Let the battle begin!");
        int turnCounter = 0;
        System.out.println(pokemon1);
        System.out.println("------ [VS] ------");
        System.out.println(pokemon2);
        System.out.println(" ");
        this.successRound = false;
        battleProgress(pokemon1, pokemon2, turnCounter);
    }

    // O(n^2)
    private void battleProgress(Pokemon pokemon1, Pokemon pokemon2, int turnCounter) {
        do {
            if(this.successRound){
                turnCounter++;
            }
            Pokemon currentPokemon = null;
            Pokemon opponentPokemon = null;
            int playerID = getPlayerTurn(turnCounter);
            if (playerID == Def.PLAYER_1) {
                if (turnCounter >= Def.ROUND_ONE  && this.successRound) {
                    addElectricPokemonStats(pokemon2);
                    generateStatsPerRound(pokemon2);
                    Pokemon.getPokemonsStats(pokemon2, pokemon1);
                }
                currentPokemon = pokemon1;
                opponentPokemon = pokemon2;

            } if(playerID == Def.PLAYER_2){
                if (turnCounter >= Def.ROUND_ONE && this.successRound) {
                    addElectricPokemonStats(pokemon1);
                    generateStatsPerRound(pokemon1);
                    Pokemon.getPokemonsStats(pokemon1, pokemon2);
                }
                currentPokemon = pokemon2;
                opponentPokemon = pokemon1;
            }
            if(currentPokemon != null) {
                battleMenu(currentPokemon, opponentPokemon);
            }
        } while (!pokemon1.isDefeated() && !pokemon2.isDefeated());
        Pokemon.getPokemonsStats(pokemon1,pokemon2);
        battleVictory(pokemon1, pokemon2);
        System.out.println("Do you want to do another fight?" + '\n' + "[1] - Start match " + '\n' + "[2] - Finish game.");
        Scanner scanner = new Scanner(System.in);
        int userInput = scanner.nextInt();
        while(userInput < 1 || userInput > 2){
            System.out.println("Invalid input, try again.");
            userInput = scanner.nextInt();
        }
        switch (userInput){
            case Def.START_GAME -> {
                GameAction gameAction = new GameAction();
                gameAction.createPokemons();
            }
            case Def.EXIT -> System.out.println("Exiting game...");
        }
    }

    // O(n)
    private void battleMenu(Pokemon currentPokemon,Pokemon opponentPokemon){
        System.out.println(" ");
        System.out.println(currentPokemon.getPokemonName() + " " + "Turn.");
        System.out.println(" ");
        System.out.println("[1] - Attack. || [2] - Waiting ");
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
                    this.successRound = false;
                }
                else{
                    this.successRound = true;
                }
            }
            case Def.CHARGE -> this.successRound = charge(currentPokemon);
            case Def.EVOLUTION -> {
                boolean isPokemonCanEvolve = checkIfPokemonCanEvolve(currentPokemon);
                if(isPokemonCanEvolve) {
                    currentPokemon.pokemonEvolution();
                    this.successRound = true;
                }
                if(!isPokemonCanEvolve){
                    System.out.println("Turn is still with "+currentPokemon.getPokemonName());
                    this.successRound = false;
                }
            }
            case Def.SPECIAL -> {
                this.successRound= specialPokemonAction(currentPokemon, opponentPokemon);
                if (!this.successRound) {
                    System.out.println("Turn is still with " + currentPokemon.getPokemonName());
                }
            }
        }
    }
    // O(n)
    private boolean pokemonAttack(Pokemon currentPokemon, Pokemon opponentPokemon) {
        boolean isAttacked;
        if(currentPokemon.isTripleAttack()){
            System.out.println("TRIPLE ATTACK IS ON! All damage is x3!");
        }
        printAttacks(currentPokemon);
        if(currentPokemon.getPokemonType().equals("Electric pokemon")){
            System.out.println("All attacks is multiply with your energy percent!");
        }
        Scanner scanner = new Scanner(System.in);
        int userAttack = scanner.nextInt();
        while(userAttack < 1 || userAttack > currentPokemon.getPokemonLevel()+1){
            System.out.println("Invalid input, try again");
            userAttack = scanner.nextInt();
        }
        isAttacked = currentPokemon.useAttackAbility(opponentPokemon,userAttack-1);
        return isAttacked;
    }

    //O(n)
    private void printAttacks(Pokemon currentPokemon){
        int numberOfAttack = 0;
        for (int i = 0; i < currentPokemon.getPokemonLevel()+1; i++) {
            if (currentPokemon.isTripleAttack()) {
                if (currentPokemon.getAttacks()[i].getMinDamage() == currentPokemon.getAttacks()[i].getMaxDamage()){
                    numberOfAttack++;
                    System.out.println("[" + numberOfAttack + "] - " + currentPokemon.getAttacks()[i].getAttackName() +
                            " | Damage: [" + currentPokemon.getAttacks()[i].getMinDamage()*3+
                            "] | Ability costs: [" + currentPokemon.getAttacks()[i].getCosts() + "]");
                } else {
                    numberOfAttack++;
                    System.out.println("[" + numberOfAttack + "] - " + currentPokemon.getAttacks()[i].getAttackName() +
                            " | Damage: [" + currentPokemon.getAttacks()[i].getMinDamage() * 3 + "-" + currentPokemon.getAttacks()[i].getMaxDamage() * 3 +
                            "] | Ability costs: [" + currentPokemon.getAttacks()[i].getCosts() + "]");
                }
            }else{
                if (currentPokemon.getAttacks()[i].getMinDamage() == currentPokemon.getAttacks()[i].getMaxDamage()){
                    numberOfAttack++;
                    System.out.println("[" + numberOfAttack + "] - " + currentPokemon.getAttacks()[i].getAttackName() +
                            " | Damage: [" + currentPokemon.getAttacks()[i].getMinDamage() +
                            "] | Ability costs: [" + currentPokemon.getAttacks()[i].getCosts() + "]");
                }
                else{
                    numberOfAttack++;
                    System.out.println("[" + numberOfAttack + "] - " + currentPokemon.getAttacks()[i].getAttackName() +
                            " | Damage: [" + currentPokemon.getAttacks()[i].getMinDamage() + "-" + currentPokemon.getAttacks()[i].getMaxDamage() +
                            "] | Ability costs: [" + currentPokemon.getAttacks()[i].getCosts() + "]");

                }
            }
        }
    }

    //O(1)
    private boolean charge(Pokemon currentPokemon) {
        this.successRound = true;
        int chargeHp;
        int chargeAp;
        Random random = new Random();
        int randomNumber = random.nextInt(Def.MAXIMUM_RANDOM_CHANGE_CHARGE) + Def.MINIMUM_RANDOM_CHANGE_CHARGE;
        if (currentPokemon.isTripleAttack()) {
            randomNumber = random.nextInt(Def.MIDDLE_RANDOM_CHANGE_CHARGE) + Def.MINIMUM_RANDOM_CHANGE_CHARGE;
        }
        if(currentPokemon.fullyCharged()){
            System.out.println("You are fully charger and got triple attack, choose another option.");
            System.out.println("Turn is still yours "+currentPokemon.getPokemonName());
            this.successRound = false;
        }
        else {
            if (randomNumber == Def.HP_BONUS) {
                chargeHp = random.nextInt(Def.HP_BONUS_MAXIMUM-Def.HP_BONUS_MINIMUM) + Def.HP_BONUS_MINIMUM;
                currentPokemon.setPokemonHealth(currentPokemon.getPokemonHealth() + chargeHp);
                checkPokemonHpAndAp(currentPokemon);
                System.out.println("You have gained: " + chargeHp + " Health points.");
            }
            if (randomNumber == Def.AP_BONUS) {
                chargeAp = random.nextInt(Def.AP_BONUS_MAXIMUM - Def.AP_BONUS_MINIMUM) + Def.AP_BONUS_MINIMUM;
                currentPokemon.setPokemonAttackPoints(currentPokemon.getAttackPoints() + chargeAp);
                checkPokemonHpAndAp(currentPokemon);
                System.out.println("You have gained " + chargeAp + " Ap points");
            }
            if (randomNumber == Def.TRIPLE_ATTACK_BONUS) {
                currentPokemon.setTripleAttack(true);
                System.out.println("You got a triple attack.");
            }
        }
        return successRound;
    }

    //O(1)
    private boolean specialPokemonAction(Pokemon currentPokemon, Pokemon opponentPokemon) {
        boolean success = false;
        if (!currentPokemon.isUsedSpecialAttack()){
                switch (currentPokemon.getPokemonType()) {
                    case "Fire pokemon", "Electric pokemon" -> success = currentPokemon.specialAbility(opponentPokemon);
                }
            } else {
                System.out.println("You already did a special attack, choose another option in the battle.");
        }
        return success;
    }
        //O(1)
        private void generateStatsPerRound(Pokemon currentPokemon) {
        Random randomHp = new Random();
        Random randomAp = new Random();
        int bonusHp = randomHp.nextInt(4) + 1;
        int bonusAp = randomAp.nextInt(5)+ 1;
        currentPokemon.addBonusStats(bonusHp,bonusAp);
        if(currentPokemon.isHpBiggerThanMaxHp()) {
            int newHp = currentPokemon.differenceHp();
            currentPokemon.removeDifferenceHp(newHp);
        }
        if(currentPokemon.isApBiggerThanMaxAp()) {
            int newAp = currentPokemon.differenceAp();
            currentPokemon.removeDifferenceAp(newAp);
        }
        System.out.println(currentPokemon.getPokemonName()+" Bonus stats! Hp:[" +bonusHp + "]  " + " Ap:[" +bonusAp+"]");

    }

    //O(1)
    private void addElectricPokemonStats(Pokemon pokemon1) {
        if (pokemon1.getPokemonType().equals("Electric pokemon")) {
            if (pokemon1.isPokemonHpUnder20Percent()) {
                pokemon1.resetElectricPower();
                System.out.println(pokemon1.getPokemonName() + "Have less than 20% hp, The electric power reset.");
            } else {
                pokemon1.setElectricPower(Def.FIVE_PERCENT);
            }
        }
    }

    //O(1)
    private void checkPokemonHpAndAp(Pokemon currentPokemon) {
        if (currentPokemon.isHpBiggerThanMaxHp()) {
            int removalHp = currentPokemon.differenceHp();
            currentPokemon.removeDifferenceHp(removalHp);
        }
        if (currentPokemon.isApBiggerThanMaxAp()) {
            int differenceAp = currentPokemon.differenceAp();
            currentPokemon.removeDifferenceAp(differenceAp);
        }
    }
    //O(1)
    private int getPlayerTurn(int turnCounter) {
        int playerID;
        if (turnCounter % 2 == 0) {
            playerID = Def.PLAYER_1;
        } else {
            playerID = Def.PLAYER_2;
        }
        return playerID;
    }
    //O(1)
    private boolean checkIfPokemonCanEvolve(Pokemon currentPokemon){
        boolean canEvolve = false;
        if(currentPokemon.checkIfPokemonAtMaxLevel()){
            if(currentPokemon.getPokemonLevel() == Def.LEVEL_1){
                if(currentPokemon.checkIfPokemonCanEvolveLv2()){
                    canEvolve = true;
                }
                else{
                    System.out.println(currentPokemon.getPokemonName()+" dont have enough Hp/Ap to evolve.");
                }
            }
            if(currentPokemon.getPokemonLevel() == Def.LEVEL_2){
                if(currentPokemon.checkIfPokemonCanEvolveLv3()){
                    canEvolve = true;
                }
                else{
                    System.out.println(currentPokemon.getPokemonName()+" dont have enough Hp/Ap to evolve.");
                }
            }
        }
        else{
            System.out.println(currentPokemon.getPokemonName()+" is at max level, can't evolve anymore");}
        return canEvolve;
  }
    //O(1)
    private void battleVictory(Pokemon pokemon1, Pokemon pokemon2) {
        if (pokemon1.isPokemonDead()) {
            System.out.println("There is a winner! " + pokemon2.getPokemonName());
        }
        if(pokemon2.isPokemonDead()){
            System.out.println("There is a winner! " + pokemon1.getPokemonName());
        }
    }
}
