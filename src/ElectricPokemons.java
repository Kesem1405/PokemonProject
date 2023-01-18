public class ElectricPokemons extends Pokemon {


    public ElectricPokemons(String[] names, int[] maxHp, int[] maxAp, int maxLvl, Attacks[] attacks) {
        super(names, maxHp, maxAp, maxLvl, attacks);
        this.electricPower=0;
        this.pokemonType = "Electric pokemon";
    }

    public boolean useAttackAbility(Pokemon opponentPokemon,int attackNumber) {
        boolean success = true;
        boolean enoughAp=isApEnoughToRemove(attackNumber);
        if (!enoughAp){
            System.out.println(this.getPokemonName() + " You dont have enough ability points for this attack");
            success= false;
        }else {
            int damage = getDamage(this, attackNumber);
            if (this.isTripleAttack()){
                tripleAttack(opponentPokemon,damage);}
            else {
                if (this.getElectricPower() != 0) {
                    System.out.println(this.getPokemonName() + " Attacked " + opponentPokemon.getPokemonName() + " With "
                            + this.getAttacks()[attackNumber].getAttackName() + ", Damage:[" + (damage * bonusElectricToAttack()) + "]");
                    opponentPokemon.isHpEnoughToRemove(damage);
                }
                else{
                    System.out.println(this.getPokemonName() + " Attacked " + opponentPokemon.getPokemonName() + " With "
                            + this.getAttacks()[attackNumber].getAttackName() + ", Damage:[" + damage+"]");
                    opponentPokemon.isHpEnoughToRemove(damage);
                }
            }
        }
        return success;
    }

    private int getDamage(Pokemon currentPokemon, int attackNumber){
        int damage;
        if (currentPokemon.getElectricPower() != 0) {
            damage = (int) ((currentPokemon.getAttacks()[attackNumber].randomDamage()) * bonusElectricToAttack());
        }
        else{
            damage = currentPokemon.getAttacks()[attackNumber].randomDamage();
        }
        return damage;
    }

    public boolean specialAbility(Pokemon opponentPokemon) {
        boolean success = true;
        if(this.isUsedSpecialAttack()){
            System.out.println("You already done a special attack, please choose something else.");
            success = false;
        }
        else{
            System.out.println("You are using special attack, your regained your health and ability points");
            this.setPokemonHealth(this.getPokemonMaxHp()[this.pokemonCurrentLevel()]);
            this.setPokemonAttackPoints(this.getPokemonMaxAp()[this.pokemonCurrentLevel()]);
            this.usedSpecialAttack(true);
        }
        return success;
    }

    private  int electricPower;

    private double bonusElectricToAttack() {
        double bonus =this.electricPower+Def.HUNDRED_PERCENT;
        bonus/=Def.HUNDRED_PERCENT;
        return bonus;
    }

    public String toString() {
        return super.toString() + " ," + pokemonType;
    }


}
