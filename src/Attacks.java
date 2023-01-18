import java.util.Random;

public class Attacks {
    private final String attackName;
    private int minDamage;
    private final int maxDamage;
    private final int apCost;

    public Attacks(String attackName, int minDamage, int maxDamage, int apCost) {
        this.attackName = attackName;
        this.minDamage = minDamage;
        this.maxDamage = maxDamage;
        this.apCost = apCost;
    }

    public int randomDamage(){
        Random random = new Random();
        int damageForOpponent = 0;
        if(this.minDamage != this.maxDamage) {
            damageForOpponent = random.nextInt(this.maxDamage - this.minDamage + 1) + this.minDamage;
        }
        else{
            this.minDamage = damageForOpponent;
        }
        return damageForOpponent;
    }

    public String getAttackName(){
        return this.attackName;
    }
    public int getCosts(){
        return this.apCost;
    }
    public int getMaxDamage(){
        return this.maxDamage;
    }
    public int getMinDamage(){
        return this.minDamage;
    }
}
