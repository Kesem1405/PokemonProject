import java.util.Arrays;

public abstract class Pokemon {

    private int electricPower;
    public String pokemonType;
    private final String pokemonName;
    private int pokemonHp;
    private int maxHp;
    private int maxAp;
    private final int pokemonLevel;
    private final String[] attacks;
    private int attackPoints;

    private boolean tripleAttack;

    private boolean isPokemonSpecialAttack;

    private boolean usedSpecialAttack;

    public Pokemon(String pokemonName, int pokemonHp , int pokemonLevel, String[] attacks, int attackPoints, String pokemonType) {
        this.pokemonName = pokemonName;
        this.pokemonHp  = pokemonHp ;
        this.pokemonLevel = pokemonLevel;
        this.attacks = attacks;
        this.attackPoints = attackPoints;
        this.pokemonType = pokemonType;
    }

    public String toString() {
        return "Pokemon: " + this.pokemonName + "\n" +
                "Health: [ " + this.pokemonHp  + "/"+getPokemonMaxHp() +"]"+
                " || Level: " + this.pokemonLevel +
                " || attack points: [" + attackPoints + "/" +getPokemonMaxAp() + "]" +
                ", Attacks: " + Arrays.toString(getAttacks()) + '\'';
    }

    public static void getPokemonsStats(Pokemon pokemon1, Pokemon pokemon2) {
        String pokemonStats = pokemon1.getPokemonName();
        if (pokemon1.getPokemonHealth() <= 0) {
            pokemonStats += "Health: 0";
        } else {
            pokemonStats += " || Health:[ " + pokemon1.getPokemonHealth() + "/" + pokemon1.getPokemonMaxHp() + "] || ";
        }
        if (pokemon1.getAttackPoints() <= 0) {
            pokemonStats += "Attack points: [ 0 / "+ pokemon1.getPokemonMaxAp() + "]";
        }
        else{
            pokemonStats += "Attack points: [" + pokemon1.getAttackPoints() + "/" + pokemon1.getPokemonMaxAp() + "]";
        }
        if (pokemon1.getPokemonType().equals("Electric pokemon")) {
            pokemonStats += " || Electric power: [" + pokemon1.getElectricPower() + "%/100%]" + '\n';
        } else {
            pokemonStats += '\n';
        }
        pokemonStats += pokemon2.getPokemonName();
        if (pokemon2.getPokemonHealth() <= 0) {
            pokemonStats += " || Health: 0";
        } else {
            pokemonStats += " || Health:[ " + pokemon2.getPokemonHealth() + "/" + pokemon2.getPokemonMaxHp() + "] || ";
        }
        if(pokemon2.getAttackPoints() <= 0){
            pokemonStats += "  " + "Attack points: [ 0 /" + pokemon2.getPokemonMaxAp() + "]";
        }
        else {
            pokemonStats += "  " + "Attack points: [" + pokemon2.getAttackPoints() + "/" + pokemon2.getPokemonMaxAp() + "]";
        }
        if (pokemon2.getPokemonType().equals("Electric pokemon")) {
            pokemonStats += " || Electric power: [" + pokemon2.getElectricPower() + "%/100%]" + '\n';
        } else {
            pokemonStats += '\n';
        }
        System.out.println(pokemonStats);
    }

    public String[] getAttacks() {
        return attacks;
    }

    private int[] costs;

    public int[] getCosts() {
        return costs;
    }
    public void setCosts(int[] costs) {
        this.costs = costs;
    }

    public String getPokemonName() {
        return pokemonName;
    }

    public int getPokemonHealth() {
        return pokemonHp ;
    }

    public void setPokemonHealth(int pokemonHealth) {
        this.pokemonHp  = pokemonHealth;
    }

    public int getElectricPower() {
        return electricPower;
    }

    public void setElectricPower(int electricPower) {
        this.electricPower = electricPower;
    }

    public boolean isTripleAttack() {
        return tripleAttack;
    }

    public void setTripleAttack(boolean tripleAttack) {
        this.tripleAttack = tripleAttack;
    }

    public int getPokemonLevel() {
        return pokemonLevel;
    }

    public boolean isSpecialAttack() {
        return isPokemonSpecialAttack;
    }

    public int getAttackPoints() {
        return attackPoints;
    }

    public void setPokemonAttackPoints(int pokemonAttackPoints) {
        this.attackPoints = pokemonAttackPoints;
    }


    public void setPokemonMaxHp(int maxHp) {
        this.maxHp = maxHp;
    }

    public int getPokemonMaxHp() {
        return maxHp;
    }

    public String getPokemonType() {
        return pokemonType;
    }

    public boolean isUsedSpecialAttack() {
        return usedSpecialAttack;
    }

    public void usedSpecialAttack(boolean usedSpecialAttack) {
        this.usedSpecialAttack = usedSpecialAttack;
    }

    public void setPokemonMaxAp(int maxAp) {
        this.maxAp = maxAp;
    }

    public int getPokemonMaxAp() {
        return maxAp;
    }

    public void hasSpecialAttack(boolean isSpecialAttack) {
        this.isPokemonSpecialAttack = isSpecialAttack;
    }


    public boolean isDefeated() {
        boolean defeated = false;
        if (getPokemonHealth() <= Def.DEFEAT_HP) {
            setPokemonHealth(0);
            defeated = true;
        }
        return defeated;
    }
}
