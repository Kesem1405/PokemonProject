public class ElectricPokemons extends Pokemon {


    public ElectricPokemons(String[] names, int[] maxHp, int[] maxAp, int maxLvl, Attacks[] attacks) {
        super(names, maxHp, maxAp, maxLvl, attacks);
        this.electricPower=0;
        this.pokemonType = "Electric pokemon";
    }

    public boolean useAttackAbility(Pokemon opponentPokemon,int attackNumber) {
        boolean success = true;
        boolean enoughAp=isApEnoughToRemove(attackNumber);
        if (attackNumber == this.pokemonCurrentLevel() + 1) {
            success = this.kickAttack(opponentPokemon);
            return success;
        }
        int damage;
        if (!enoughAp){
            System.out.println(this.getPokemonName() + " You dont have enough ability points for this attack");
            success= false;
        }else {
            damage = (int) ((this.getAttacks()[attackNumber].randomDamage())*bonusElectricToAttack());
            if (this.isTripleAttack()){
                tripleAttack(opponentPokemon,damage);}

            else {
                System.out.println(this.getPokemonName()+" Attacked " +opponentPokemon.getPokemonName()+ " With " +this.getAttacks()[pokemonCurrentLevel()].getAttackName()+", Damage:[" +(damage*bonusElectricToAttack())+"]");
                opponentPokemon.isHpEnoughToRemove(damage);
            }
        }
        return success;
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
