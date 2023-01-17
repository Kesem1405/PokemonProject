public abstract class Pokemon {

    private int electricPower;
    public String pokemonType;
    private final String[] pokemonEvolveNames;
    private String pokemonName;
    private final int[] maxHp;
    private final int[] maxAp;
    private int currentHp;
    private int currentAp;
    private int pokemonLevel;
    private final int maxLevel;
    private final Attacks[] attacks;

    private boolean tripleAttack;

    private boolean isPokemonSpecialAttack;

    private boolean usedSpecialAttack;


    public Pokemon(String[] pokemonNames, int[] maxHp, int[] maxAp, int maxLevel, Attacks[] attacks) {
        this.pokemonEvolveNames = pokemonNames;
        this.pokemonName = pokemonNames[pokemonLevel];
        this.maxHp = maxHp;
        this.maxAp = maxAp;
        this.currentHp = maxHp[pokemonLevel];
        this.maxLevel = maxLevel;
        this.pokemonLevel = 1;
        this.currentAp = (int) (Def.START_AP_PERCENT * (this.maxAp[this.pokemonLevel-1]));
        this.attacks = attacks;
        tripleAttack = false;
        usedSpecialAttack = false;
    }

    public String toString() {
        return "Pokemon: " + this.pokemonName + "\n" +
                "Health: [ " + this.currentHp + "/" + this.maxHp[this.pokemonCurrentLevel()] + "]" +
                " || Level: " + this.pokemonLevel +
                " || attack points: [" + this.currentAp + "/" + this.maxAp[this.pokemonCurrentLevel()] + "]";
    }

    public static void getPokemonsStats(Pokemon pokemon1, Pokemon pokemon2) {
        String pokemonStats = pokemon1.getPokemonName();
        if (pokemon1.isPokemonDead()) {
            pokemonStats += "Health: 0";
        } else {
            pokemonStats += " || Health:[ " + pokemon1.currentHp + "/" + pokemon1.maxHp[pokemon1.pokemonCurrentLevel()] + "] || ";
        }
        if (pokemon1.getAttackPoints() <= Def.DEFEAT_HP) {
            pokemonStats += "Attack points: [ 0 / " + pokemon1.getPokemonMaxAp()[pokemon1.pokemonCurrentLevel()] + "]";
        } else {
            pokemonStats += "Attack points: [" + pokemon1.currentAp + "/" + pokemon1.maxAp[pokemon1.pokemonCurrentLevel()] + "]";
        }
        if (pokemon1.getPokemonType().equals("Electric pokemon")) {
            pokemonStats += " || Electric power: [" + pokemon1.getElectricPower() + "%/100%]" + '\n';
        } else {
            pokemonStats += '\n';
        }
        pokemonStats += pokemon2.getPokemonName();
        if (pokemon2.isPokemonDead()) {
            pokemonStats += " || Health: 0";
        } else {
            pokemonStats += " || Health:[ " + pokemon2.currentHp + "/" + pokemon2.getPokemonMaxHp()[pokemon2.pokemonCurrentLevel()] + "] || ";
        }
        if (pokemon2.getAttackPoints() <= Def.DEFEAT_HP) {
            pokemonStats += "  " + "Attack points: [ 0 /" + pokemon2.getPokemonMaxAp()[pokemon2.pokemonCurrentLevel()] + "]";
        } else {
            pokemonStats += "  " + "Attack points: [" + pokemon2.currentAp + "/" + pokemon2.getPokemonMaxAp()[pokemon2.pokemonCurrentLevel()] + "]";
        }
        if (pokemon2.getPokemonType().equals("Electric pokemon")) {
            pokemonStats += " || Electric power: [" + pokemon2.getElectricPower() + "%/100%]" + '\n';
        } else {
            pokemonStats += '\n';
        }
        System.out.println(pokemonStats);
    }

    public Attacks[] getAttacks() {
        return attacks;
    }


    public String getPokemonName() {
        return pokemonName;
    }

    public int getPokemonHealth() {
        return this.currentHp;
    }

    public boolean isPokemonDead() {
        return this.currentHp <= 0;
    }

    public boolean isPokemonHpUnder20Percent() {
        int twentyPercent = (int) (Def.TWENTY_PERCENT * this.maxHp[this.pokemonCurrentLevel()]);
        return this.currentHp < twentyPercent;
    }

    public boolean isHpBiggerThanMaxHp() {
        return this.currentHp > this.maxHp[pokemonCurrentLevel()];
    }

    public boolean isApBiggerThanMaxAp() {
        return this.currentAp > this.maxAp[pokemonCurrentLevel()];
    }

    public int differenceHp() {
        int differenceHp = 0;
        if (this.currentHp > this.maxHp[pokemonCurrentLevel()]) {
            differenceHp = this.currentHp - this.maxHp[pokemonCurrentLevel()];
        }
        return differenceHp;
    }

    public int removeDifferenceHp(int difference) {
        this.currentHp = this.currentHp - difference;
        return this.currentHp;
    }

    public int differenceAp() {
        int differenceAp = 0;
        if (this.currentAp > this.maxAp[pokemonCurrentLevel()]) {
            differenceAp = currentAp - maxAp[pokemonCurrentLevel()];
        }
        return differenceAp;
    }

    public void removeDifferenceAp(int difference) {
        this.currentAp = this.currentAp - difference;
    }

    public void addBonusStats(int bonusHp, int bonusAp) {
        this.currentHp += bonusHp;
        this.currentAp += bonusAp;
    }

    public void setPokemonHealth(int pokemonHealth) {
        this.currentHp = pokemonHealth;
    }

    public int pokemonCurrentLevel() {
        return pokemonLevel - 1;
    }

    public int getElectricPower() {
        return this.electricPower;
    }

    public void setElectricPower(int electricPower) {
        this.electricPower += electricPower;
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
        return this.currentAp;
    }

    public abstract boolean useAttackAbility(Pokemon opponentPokemon, int attackNumber);

    public abstract boolean specialAbility(Pokemon opponentPokemon);

    public boolean fullyCharged() {
        boolean isFullyCharged = false;
        if (this.currentHp == this.maxHp[pokemonCurrentLevel()] && this.currentAp == this.maxAp[pokemonCurrentLevel()] && isTripleAttack()) {
            isFullyCharged = true;
        }
        return isFullyCharged;
    }

    public int isHpEnoughToRemove(int damage) {
        if (this.currentHp - damage > Def.DEFEAT_HP) {
            this.setPokemonHealth(this.currentHp - damage);
        } else {
            this.currentHp = Def.DEFEAT_HP;
        }
        return damage;
    }

    public int halfPokemonLife(){
        return (int) (Def.FIFTY_PERCENT * this.getPokemonHealth());
    }

    public boolean isApEnoughToRemove(int attackNumber) {
        boolean isEnough = false;
        if (this.currentAp >= this.getAttacks()[attackNumber].getCosts()) {
            isEnough = true;
        }
        return isEnough;
    }

    public void setPokemonAttackPoints(int pokemonAttackPoints) {
        this.currentAp = pokemonAttackPoints;
    }

    public int[] getPokemonMaxHp() {
        return this.maxHp;
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

    public int[] getPokemonMaxAp() {
        return this.maxAp;
    }

    public boolean pokemonEvolution() {
        boolean success = true;
        if(this.pokemonLevel < this.maxLevel){
            switch(this.pokemonLevel){
                case Def.LEVEL_1 -> {
                    if(this.currentHp >= Def.HP_COST_FOR_EVOLVE_LV2 && this.currentAp >=Def.AP_COST_FOR_EVOLVE_LV2){
                        this.currentHp-=Def.HP_COST_FOR_EVOLVE_LV2;
                        this.currentAp-=Def.AP_COST_FOR_EVOLVE_LV2;
                        String beforeEvolvedName = this.pokemonName;
                        this.pokemonName = this.setEvolvedName();
                        this.pokemonLevel = this.pokemonLevel+Def.LEVEL_UPGRADE;
                        System.out.println(beforeEvolvedName+" Evolved to "+this.pokemonName);
                    }
                    else{
                        System.out.println(this.getPokemonName()+" Don't have enough Hp/Ap to evolve.");
                        success = false;
                    }
                }
                case Def.LEVEL_2 ->{
                    if(this.currentHp >= Def.HP_COST_FOR_EVOLVE_LV3 && this.currentAp >=Def.AP_COST_FOR_EVOLVE_LV3) {
                        this.currentHp -= Def.HP_COST_FOR_EVOLVE_LV3;
                        this.currentAp -= Def.AP_COST_FOR_EVOLVE_LV3;
                        String beforeEvolvedName = this.pokemonName;
                        this.pokemonName = this.setEvolvedName();
                        this.pokemonLevel = this.pokemonLevel + Def.LEVEL_UPGRADE;
                        System.out.println(beforeEvolvedName + " Evolved to " + this.pokemonName);
                    }
                    else{
                        System.out.println(this.getPokemonName()+" Don't have enough Hp/Ap to evolve.");
                        success = false;
                    }
                }
            }
        }
        else{
            System.out.println("You are at max level, you can't evolve anymore.");
            success = false;
        }
        return success;
    }


    private String setEvolvedName(){
        this.pokemonName = this.pokemonEvolveNames[pokemonCurrentLevel()+Def.LEVEL_UPGRADE];
        return this.pokemonName;
    }

    public void hasSpecialAttack(boolean isSpecialAttack) {
        this.isPokemonSpecialAttack = isSpecialAttack;
    }

    public void tripleAttack(Pokemon opponentPokemon, int damage){
        System.out.println("Triple damage !!" + '\n' + "Damage:" + damage*Def.TRIPLE_ATTACK_BONUS);
        opponentPokemon.isHpEnoughToRemove(damage*Def.TRIPLE_ATTACK_BONUS);
    }

    public boolean kickAttack(Pokemon opponentPokemon){
        if(this.isTripleAttack()){
            System.out.println(this.getPokemonName() + " Triple kicked " + opponentPokemon.getPokemonName() + ", Damage: "+Def.TRIPLE_KICK_DAMAGE);
            opponentPokemon.isHpEnoughToRemove(Def.KICK_DAMAGE*Def.TRIPLE_ATTACK_BONUS);
        }
        else {
            System.out.println(this.getPokemonName() + " Kicked " + opponentPokemon.getPokemonName() + ", Damage: "+Def.KICK_DAMAGE);
            opponentPokemon.isHpEnoughToRemove(Def.KICK_DAMAGE);
        }
        return true;
    }

    public boolean isDefeated() {
        boolean defeated = false;
        if (this.currentHp <= Def.DEFEAT_HP) {
            this.currentHp = Def.DEFEAT_HP;
            defeated = true;
        }
        return defeated;
    }
}
