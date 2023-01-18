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


    private boolean usedSpecialAttack;


    //O(1)
    public Pokemon(String[] pokemonNames, int[] maxHp, int[] maxAp, int maxLevel, Attacks[] attacks) {
        this.pokemonEvolveNames = pokemonNames;
        this.pokemonName = pokemonNames[pokemonLevel];
        this.maxHp = maxHp;
        this.maxAp = maxAp;
        this.currentHp = maxHp[pokemonLevel];
        this.maxLevel = maxLevel;
        this.pokemonLevel = 1;
        this.currentAp = (int) (Def.START_AP_PERCENT * (this.maxAp[this.pokemonLevel - 1]));
        this.attacks = attacks;
        tripleAttack = false;
        usedSpecialAttack = false;
    }

    // O(1)
    public String toString() {
        return "Pokemon: " + this.pokemonName + "\n" +
                "Health: [ " + this.currentHp + "/" + this.maxHp[this.pokemonCurrentLevel()] + "]" +
                " || Level: " + this.pokemonLevel +
                " || attack points: [" + this.currentAp + "/" + this.maxAp[this.pokemonCurrentLevel()] + "]";
    }

    //O(2)
    public static void getPokemonsStats(Pokemon pokemon1, Pokemon pokemon2) {
        String pokemonStats = pokemon1.getPokemonName();
        if (pokemon1.isPokemonDead()) {
            pokemonStats += "Health: [0/" + pokemon1.maxHp[pokemon1.pokemonCurrentLevel()] + "]";
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
            pokemonStats += " || Health: [0/" + pokemon2.maxHp[pokemon2.pokemonCurrentLevel()] + "]";
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

    //O(1)
    public Attacks[] getAttacks() {
        return attacks;
    }

    //O(1)
    public String getPokemonName() {
        return pokemonName;
    }
    //O(1)
    public int getPokemonHealth() {
        return this.currentHp;
    }
    //O(1)
    public boolean isPokemonDead() {
        return this.currentHp <= 0;
    }
    //O(1)
    public boolean isPokemonHpUnder20Percent() {
        int twentyPercent = (int) (Def.TWENTY_PERCENT * this.maxHp[this.pokemonCurrentLevel()]);
        return this.currentHp < twentyPercent;
    }
    //O(1)

    public boolean isHpBiggerThanMaxHp() {
        return this.currentHp > this.maxHp[pokemonCurrentLevel()];
    }

    //O(1)
    public boolean isApBiggerThanMaxAp() {
        return this.currentAp > this.maxAp[pokemonCurrentLevel()];
    }

    //O(1)
    public int differenceHp() {
        int differenceHp = 0;
        if (this.currentHp > this.maxHp[pokemonCurrentLevel()]) {
            differenceHp = this.currentHp - this.maxHp[pokemonCurrentLevel()];
        }
        return differenceHp;
    }

    //O(1)
    public int removeDifferenceHp(int difference) {
        this.currentHp = this.currentHp - difference;
        return this.currentHp;
    }

    //O(1)
    public int differenceAp() {
        int differenceAp = 0;
        if (this.currentAp > this.maxAp[pokemonCurrentLevel()]) {
            differenceAp = currentAp - maxAp[pokemonCurrentLevel()];
        }
        return differenceAp;
    }

    //O(1)
    public void removeDifferenceAp(int difference) {
        this.currentAp = this.currentAp - difference;
    }

    //O(1)
    public void addBonusStats(int bonusHp, int bonusAp) {
        this.currentHp += bonusHp;
        this.currentAp += bonusAp;
    }

    //O(1)
    public void setPokemonHealth(int pokemonHealth) {
        this.currentHp = pokemonHealth;
    }

    //O(1)
    public int pokemonCurrentLevel() {
        return pokemonLevel - 1;
    }

    //O(1)
    public int getElectricPower() {
        return this.electricPower;
    }

    //O(1)
    public void setElectricPower(int electricPower) {
        this.electricPower = this.electricPower + electricPower;
    }

    //O(1)
    public boolean checkIfPokemonAtMaxLevel(){
        return this.pokemonLevel < this.maxLevel;
    }
    //O(1)
    public boolean checkIfPokemonCanEvolveLv2(){
        return this.currentHp >= Def.HP_COST_FOR_EVOLVE_LV2 && this.currentAp >= Def.AP_COST_FOR_EVOLVE_LV2;
    }
    //O(1)
    public boolean checkIfPokemonCanEvolveLv3(){
        return this.currentHp >= Def.HP_COST_FOR_EVOLVE_LV3 && this.currentAp >= Def.AP_COST_FOR_EVOLVE_LV3;
    }

    //O(1)
    public void resetElectricPower() {
        this.electricPower = 0;
    }
    //O(1)
    public boolean isTripleAttack() {
        return tripleAttack;
    }
    //O(1)
    public void setTripleAttack(boolean tripleAttack) {
        this.tripleAttack = tripleAttack;
    }

    //O(1)
    public int getPokemonLevel() {
        return pokemonLevel;
    }

    //O(1)
    public int getAttackPoints() {
        return this.currentAp;
    }

    public abstract boolean useAttackAbility(Pokemon opponentPokemon, int attackNumber);

    public abstract boolean specialAbility(Pokemon opponentPokemon);

    //O(1)
    public boolean fullyCharged() {
        boolean isFullyCharged = false;
        if (this.currentHp == this.maxHp[pokemonCurrentLevel()] && this.currentAp == this.maxAp[pokemonCurrentLevel()] && isTripleAttack()) {
            isFullyCharged = true;
        }
        return isFullyCharged;
    }

    //O(1)
    public int isHpEnoughToRemove(int damage) {
        if (this.currentHp - damage > Def.DEFEAT_HP) {
            this.setPokemonHealth(this.currentHp - damage);
        } else {
            this.currentHp = Def.DEFEAT_HP;
        }
        return damage;
    }

    //O(1)
    public int halfPokemonLife() {
        return (int) (Def.FIFTY_PERCENT * this.getPokemonHealth());
    }

    //O(1)
    public boolean isApEnoughToRemove(int attackNumber) {
        boolean isEnough = false;
        if (this.currentAp >= this.getAttacks()[attackNumber].getCosts()) {
            isEnough = true;
        }
        return isEnough;
    }

    //O(1)
    public void setPokemonAttackPoints(int pokemonAttackPoints) {
        this.currentAp = pokemonAttackPoints;
    }

    //O(1)
    public int[] getPokemonMaxHp() {
        return this.maxHp;
    }

    //O(1)
    public String getPokemonType() {
        return pokemonType;
    }

    //O(1)
    public boolean isUsedSpecialAttack() {
        return usedSpecialAttack;
    }

    //O(1)
    public void usedSpecialAttack(boolean usedSpecialAttack) {
        this.usedSpecialAttack = usedSpecialAttack;
    }

    //O(1)
    public int[] getPokemonMaxAp() {
        return this.maxAp;
    }


    //O(1)
    public Pokemon pokemonEvolution() {
        switch (this.pokemonLevel) {
            case Def.LEVEL_1 -> {
                this.currentHp -= Def.HP_COST_FOR_EVOLVE_LV2;
                this.currentAp -= Def.AP_COST_FOR_EVOLVE_LV2;
                this.maxHp[this.pokemonLevel-1] = this.maxHp[this.pokemonLevel];
                this.maxAp[this.pokemonLevel-1] = this.maxAp[this.pokemonLevel];
                String beforeEvolvedName = this.pokemonName;
                this.pokemonName = this.setEvolvedName();
                this.pokemonLevel = this.pokemonLevel + Def.LEVEL_UPGRADE;

                System.out.println(beforeEvolvedName + " Evolved to " + this.pokemonName);
                return this;

            }
            case Def.LEVEL_2 -> {
                this.currentHp -= Def.HP_COST_FOR_EVOLVE_LV3;
                this.currentAp -= Def.AP_COST_FOR_EVOLVE_LV3;
                this.maxHp[this.pokemonLevel-1] = this.maxHp[this.pokemonLevel];
                this.maxAp[this.pokemonLevel-1] = this.maxAp[this.pokemonLevel];
                String beforeEvolvedName = this.pokemonName;
                this.pokemonName = this.setEvolvedName();
                this.pokemonLevel = this.pokemonLevel + Def.LEVEL_UPGRADE;
                System.out.println(beforeEvolvedName + " Evolved to " + this.pokemonName);
            }
        }
        return this;
    }


    //O(1)
    private String setEvolvedName() {
        this.pokemonName = this.pokemonEvolveNames[pokemonCurrentLevel() + Def.LEVEL_UPGRADE];
        return this.pokemonName;
    }


    //O(1)
    public void tripleAttack(Pokemon opponentPokemon, int damage) {
        System.out.println("Triple damage !!" + '\n' + "Damage:" + damage * Def.TRIPLE_ATTACK_BONUS);
        opponentPokemon.isHpEnoughToRemove(damage * Def.TRIPLE_ATTACK_BONUS);
    }

    public boolean kickAttack(Pokemon opponentPokemon) {
        if (this.isTripleAttack()) {
            System.out.println(this.getPokemonName() + " Triple kicked " + opponentPokemon.getPokemonName() + ", Damage: " + Def.TRIPLE_KICK_DAMAGE);
            opponentPokemon.isHpEnoughToRemove(Def.KICK_DAMAGE * Def.TRIPLE_ATTACK_BONUS);
        } else {
            System.out.println(this.getPokemonName() + " Kicked " + opponentPokemon.getPokemonName() + ", Damage: " + Def.KICK_DAMAGE);
            opponentPokemon.isHpEnoughToRemove(Def.KICK_DAMAGE);
        }
        return true;
    }

    //O(1)
    public boolean isDefeated() {
        boolean defeated = false;
        if (this.currentHp <= Def.DEFEAT_HP) {
            this.currentHp = Def.DEFEAT_HP;
            defeated = true;
        }
        return defeated;
    }
}
