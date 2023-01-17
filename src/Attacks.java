import java.util.Random;

public class Attacks {
    private String attackName;

    public Attacks(String attackName, int minDamage, int maxDamage, int apCost) {
        this.attackName = attackName;
        this.minDamage = minDamage;
        this.maxDamage = maxDamage;
        this.apCost = apCost;
    }

    public int randomDamage(){
        Random random = new Random();
        int randomDamageForOpponent = random.nextInt(this.maxDamage - this.minDamage + 1) + this.maxDamage;
        return randomDamageForOpponent;
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
    private int minDamage;
    private int maxDamage;
    private int apCost;
}
