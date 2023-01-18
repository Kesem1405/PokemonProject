import java.util.Random;
public class GameAction {
    private static final Attacks Kick = new Attacks("Kick",Def.KICK_DAMAGE,Def.KICK_DAMAGE,Def.KICK_NO_COST);


    //O(n)
    public void createPokemons() {
        String[] charmanderEvolutions = {"Charmender", "Charmeleon", "Charizard"};
        int[] charmanderMaxHealth = {Def.CHARMANDER_HP, Def.CHARMELEON_HP, Def.CHARIZARD_HP};
        int[] charmenderMaxAttackPoints = {Def.CHARMANDER_AP, Def.CHARMELEON_AP, Def.CHARIZARD_AP};
        Attacks[] charmenderAttacks = {
                Kick,
                new Attacks("Scratch", Def.SCRATCH_MINIMUM_DAMAGE, Def.SCRATCH_MAXIMUM_DAMAGE, Def.SCRATCH_COST),
                new Attacks("Flame Tale", Def.FLAME_TAIL_MINIMUM_DAMAGE, Def.FLAME_TAIL_MAXIMUM_DAMAGE, Def.FLAME_TAIL_COST),
                new Attacks("Fiery Blast", Def.FIERY_BLAST_DAMAGE, Def.FIERY_BLAST_DAMAGE, Def.FIERY_BLAST_COST),

        };
        String[] blitzleEvolutions = {"Blitzle", "Zebstrika"};
        int[] blitzleMaxHealth = {Def.BLITZLE_HP, Def.ZEBSTRIKA_HP};
        int[] blitzleMaxAttackPoints = {Def.BLITZLE_AP, Def.ZEBSTRIKA_AP};
        Attacks[] blitzleAttacks = {
                Kick,
                new Attacks("Flop", Def.FLOP_MINIMUM_DAMAGE, Def.FLOP_MAXIMUM_DAMAGE, Def.FLOP_COST),
                new Attacks("Zap Kick", Def.ZAP_KICK_MINIMUM_DAMAGE, Def.ZAP_KICK_MAXIMUM_DAMAGE, Def.ZAP_KICK_COST),
        };
        String[] salanditEvolutions = {"Salandit", "Salazzle"};
        int[] salanditMaxHealth = {Def.SALANDIT_HP, Def.SALAZZLE_HP};
        int[] salanditMaxAttackPoints = {Def.SALANDIT_AP, Def.SALAZZLE_AP};
        Attacks[] salanditAttacks = {
                Kick,
                new Attacks("Live coal", Def.LIVE_COAL_MINIMUM_DAMAGE, Def.LIVE_COAL_MAXIMUM_DAMAGE, Def.LIVE_COAL_COST),
                new Attacks("Fire claws", Def.FIRE_CLAWS_MINIMUM_DAMAGE, Def.FIRE_CLAWS_MAXIMUM_DAMAGE, Def.FIRE_CLAWS_COST),
        };

        String[] moltresEvolutions = {"Moltres"};
        int[] moltresMaxHealth = {Def.MOLTRES_HP};
        int[] moltresMaxAttackPoints = {Def.MOLTRES_AP};
        Attacks[] moltresAttacks = {
                Kick,
                new Attacks("Assisting heater", Def.ASSISTING_HEATER_MINIMUM_DAMAGE, Def.ASSISTING_HEATER_MAXIMUM_DAMAGE, Def.ASSISTING_HEATER_COST),
                new Attacks("Fire wing", Def.FIRE_WING_DAMAGE, Def.FIRE_WING_DAMAGE, Def.FIRE_WING_COST),
        };

        String[] pichuEvolutions = {"Pichu", "Pikachu", "Raichu"};
        int[] pichuMaxHealth = {Def.PICHU_HP, Def.PIKACHU_HP, Def.RAICHU_HP};
        int[] pichuMaxAttackPoints = {Def.PICHU_AP, Def.PIKACHU_AP, Def.RAICHU_AP};
        Attacks[] pichuAttacks = {
                Kick,
                new Attacks("Quick attack", Def.QUICK_ATTACK_DAMAGE, Def.QUICK_ATTACK_DAMAGE, Def.QUICK_ATTACK_COST),
                new Attacks("Electro ball", Def.ELECTRO_BALL_MINIMUM_DAMAGE, Def.ELECTRO_BALL_MAXIMUM_DAMAGE, Def.ELECTRO_BALL_COST),
                new Attacks("Electric surfer", Def.ELECTRIC_SURFER_MINIMUM_DAMAGE, Def.ELECTRIC_SURFER_MAXIMUM_DAMAGE, Def.ELECTRIC_SURFER_COST),
        };
        String[] electabuzzEvolutions = {"Electabuzz", "Electivire"};
        int[] electabuzzMaxHealth = {Def.ELECTABUZZ_MAX_HP, Def.ELECTRIVE_MAX_HP};
        int[] electabuzzMaxAttackPoints = {Def.ELECTABUZZ_AP, Def.ELECTRIVE_MAX_AP};
        Attacks[] electabuzzAttacks = {
                Kick,
                new Attacks("Thunder", Def.THUNDER_MINIMUM_DAMAGE, Def.THUNDER_MAXIMUM_DAMAGE, Def.THUNDER_COST),
                new Attacks("Thunder punch", Def.THUNDER_PUNCH_MINIMUM_DAMAGE, Def.THUNDER_PUNCH_MAXIMUM_DAMAGE, Def.THUNDER_PUNCH_COST),
        };
        ElectricPokemons pichu = new ElectricPokemons(pichuEvolutions,pichuMaxHealth,pichuMaxAttackPoints,Def.LEVEL_3,pichuAttacks);
        ElectricPokemons electabuzz = new ElectricPokemons(electabuzzEvolutions,electabuzzMaxHealth,electabuzzMaxAttackPoints,Def.LEVEL_2,electabuzzAttacks);
        FirePokemons charmander = new FirePokemons(charmanderEvolutions,charmanderMaxHealth,charmenderMaxAttackPoints,Def.LEVEL_3,charmenderAttacks);
        FirePokemons moltres = new FirePokemons(moltresEvolutions,moltresMaxHealth,moltresMaxAttackPoints,Def.LEVEL_1,moltresAttacks);
        FirePokemons salandit = new FirePokemons(salanditEvolutions,salanditMaxHealth,salanditMaxAttackPoints,Def.LEVEL_2,salanditAttacks);
        ElectricPokemons blitzle = new ElectricPokemons(blitzleEvolutions,blitzleMaxHealth,blitzleMaxAttackPoints,Def.LEVEL_2,blitzleAttacks);
        Pokemon[] pokemons = new Pokemon[6];
        pokemons[0] = pichu;
        pokemons[1] = electabuzz;
        pokemons[2] = blitzle;
        pokemons[3] = moltres;
        pokemons[4] = salandit;
        pokemons[5] = charmander;
        Pokemon[] selectedPokemons = generatePokemons(pokemons);
        Battle battle = new Battle();
        battle.startBattle(selectedPokemons[0],selectedPokemons[1]);
    }


    //O(1)
    private Pokemon[] generatePokemons(Pokemon[] pokemons) {
        Random random = new Random();
        int index1 = random.nextInt(pokemons.length);
        int index2 = random.nextInt(pokemons.length);
        while (index1 == index2) {
            index2 = random.nextInt(pokemons.length);
        }
        Pokemon pokemon1 = pokemons[index1];
        Pokemon pokemon2 = pokemons[index2];
        return new Pokemon[]{pokemon1, pokemon2};
    }
}
