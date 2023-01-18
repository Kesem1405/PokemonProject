import java.util.Random;
import java.util.Scanner;

public class Battle {
    private boolean successRound;

    public void startBattle(Pokemon pokemon1, Pokemon pokemon2){
        System.out.println("Let the battle begin!");
        int turnCounter = 0;
        System.out.println(pokemon1);
        System.out.println("------ [VS] ------");
        System.out.println(pokemon2);
        this.successRound = false;
        battleProgress(pokemon1, pokemon2, turnCounter);
    }

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
    private boolean pokemonAttack(Pokemon currentPokemon, Pokemon opponentPokemon) {
        boolean isAttacked;
        int numberOfAttack = 0;
        if(currentPokemon.isTripleAttack()){
            System.out.println("TRIPLE ATTACK IS ON! All damage is x3!");
        }
        for (int i = 0; i < currentPokemon.getPokemonLevel(); i++) {
            if (!currentPokemon.isTripleAttack()) {
                numberOfAttack++;
                System.out.println("[" + numberOfAttack + "] - " + currentPokemon.getAttacks()[i].getAttackName() +
                        " | Damage: [" + currentPokemon.getAttacks()[i].getMinDamage() + "-" + currentPokemon.getAttacks()[i].getMaxDamage() +
                        "] | Ability costs: [" + currentPokemon.getAttacks()[i].getCosts() + "]");
            } else {
                numberOfAttack++;
                System.out.println("[" + numberOfAttack + "] - " + currentPokemon.getAttacks()[i].getAttackName() +
                        " | Damage: [" + currentPokemon.getAttacks()[i].getMinDamage()*3 + "-" + currentPokemon.getAttacks()[i].getMaxDamage()*3 +
                        "] | Ability costs: [" + currentPokemon.getAttacks()[i].getCosts() + "]");
            }
        }
        int kick = numberOfAttack + 1;
        if(!currentPokemon.isTripleAttack()) {
            System.out.println("[" + kick + "] - Kick | Damage: [2] | Ability cost: [FREE]");
        }
        else{
            System.out.println("[" + kick + "] - Kick | Damage: [6] | Ability cost: [FREE]");
        }
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

    private int getPlayerTurn(int turnCounter) {
        int playerID;
        if (turnCounter % 2 == 0) {
            playerID = Def.PLAYER_1;
        } else {
            playerID = Def.PLAYER_2;
        }
        return playerID;
    }

    private boolean checkIfPokemonCanEvolve(Pokemon currentPokemon){
        boolean canEvolve = false;
        if(currentPokemon.getPokemonLevel() < currentPokemon.getMaxLevel()){
            if(currentPokemon.getPokemonLevel() == Def.LEVEL_1){
                if(currentPokemon.getPokemonHealth() >= Def.HP_COST_FOR_EVOLVE_LV2 &&currentPokemon.getAttackPoints() >= Def.AP_COST_FOR_EVOLVE_LV2){
                    canEvolve = true;
                }
                else{
                    System.out.println(currentPokemon.getPokemonName()+" dont have enough Hp/Ap to evolve.");
                }
            }
            if(currentPokemon.getPokemonLevel() == Def.LEVEL_2){
                if(currentPokemon.getPokemonHealth() >= Def.HP_COST_FOR_EVOLVE_LV3 && currentPokemon.getAttackPoints() >= Def.AP_COST_FOR_EVOLVE_LV3){
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

    public void battleVictory(Pokemon pokemon1, Pokemon pokemon2) {
        if (pokemon1.isPokemonDead()) {
            System.out.println("There is a winner! " + pokemon2.getPokemonName());
        }
        if(pokemon2.isPokemonDead()){
            System.out.println("There is a winner! " + pokemon1.getPokemonName());
        }
    }
}
