import java.util.Random;

public class FirePokemons extends Pokemon {
    public FirePokemons(String[] names, int[] maxHp, int[] maxAp, int maxLvl, Attacks[] attacks) {
        super(names, maxHp, maxAp, maxLvl, attacks);
        this.pokemonType = "Fire pokemon";
    }

    public boolean useAttackAbility(Pokemon opponentPokemon, int attackNumber) {
        boolean success = true;
        int selfDamage = selfDamageForAttacker();
        int damage;
        boolean enoughAp = isApEnoughToRemove(attackNumber);
        if (!enoughAp) {
            System.out.println("You dont have enough ability points for this attack");
            success = false;
        } else {
            damage = ((this.getAttacks()[attackNumber].randomDamage()));
            if (this.isTripleAttack()) {
                this.tripleAttack(opponentPokemon, damage);
            } else {
                System.out.println(this.getPokemonName() + " Attacked " + opponentPokemon.getPokemonName() + " With " + this.getAttacks()[attackNumber].getAttackName() + ", Damage:" + damage);
                System.out.println(this.getPokemonName() + " Self damage: [" + selfDamage + "]");
                opponentPokemon.isHpEnoughToRemove(damage);
            }
        }
        return success;
    }


    public boolean specialAbility(Pokemon damaged) {
        boolean success = true;
        Random random = new Random();
        int indexAttack1 = random.nextInt(2)+this.getPokemonLevel()-1;
        int indexAttack2 = random.nextInt(2)+this.getPokemonLevel()-1;
        this.setPokemonHealth(this.halfPokemonLife());
        this.setPokemonAttackPoints(Def.ZERO);
        int damage = this.getAttacks()[indexAttack1].randomDamage() + this.getAttacks()[indexAttack2].randomDamage();
        damaged.isHpEnoughToRemove(damage);
        System.out.println(this.getPokemonName() + " applied special attack!");
        if (indexAttack1 == indexAttack2) {
            System.out.println("Double " + this.getAttacks()[indexAttack1].getAttackName() + " Dealt to " + damaged.getPokemonName() + ", Total damage:[" +
                    (this.getAttacks()[indexAttack1].randomDamage() + this.getAttacks()[indexAttack2].randomDamage()) + "]");
        } else {
            System.out.println(this.getAttacks()[indexAttack1].getAttackName() + " and " + this.getAttacks()[indexAttack2].getAttackName() + " Dealt to " + damaged.getPokemonName() + "Total damage:[" +
                    (this.getAttacks()[indexAttack1].randomDamage() + this.getAttacks()[indexAttack2].randomDamage()) + "]");
        }
        return success;
    }

    private int selfDamageForAttacker() {
        Random random = new Random();
        int damage = 0;
        int changeForSelfDamage = random.nextInt(3) + 1;
        if (changeForSelfDamage == Def.CHANCE_TO_SELF_DAMAGE) {
            damage = random.nextInt(Def.MAXIMUM_SELF_DAMAGE - Def.MINIMUM_SELF_DAMAGE) + Def.MAXIMUM_SELF_DAMAGE + 1;
        }
        return damage;
    }

    public String toString() {
        return super.toString() + " , " + pokemonType;
    }
}
