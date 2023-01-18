import java.util.Random;

public class Attacks {
    private final String attackName;
    private final int minDamage;
    private final int maxDamage;
    private final int apCost;


    //O(1)
    public Attacks(String attackName, int minDamage, int maxDamage, int apCost) {
        this.attackName = attackName;
        this.minDamage = minDamage;
        this.maxDamage = maxDamage;
        this.apCost = apCost;
    }

    //O(1)
    public int randomDamage(){
        Random random = new Random();
        int damageForOpponent;
        if(this.minDamage != this.maxDamage) {
            damageForOpponent = random.nextInt(this.maxDamage - this.minDamage + 1) + this.minDamage;
        }
        else{
           damageForOpponent = this.minDamage;
        }
        return damageForOpponent;
    }

    //O(1)
    public String getAttackName(){
        return this.attackName;
    }

    //O(1)
    public int getCosts(){
        return this.apCost;
    }

    //O(1)
    public int getMaxDamage(){
        return this.maxDamage;
    }

    //O(1)
    public int getMinDamage(){
        return this.minDamage;
    }
}
