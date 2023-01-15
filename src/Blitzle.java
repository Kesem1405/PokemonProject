public class Blitzle extends ElectricPokemons{
    public Blitzle() {
        super("Blitzle", Def.BLITZLE_HP, Def.LEVEL_1, new String[]{"Flop"}, 35, "Electric pokemon",0);
    }

    public static Pokemon zebstrika(Pokemon currentPokemon) {
        currentPokemon = new ElectricPokemons("Zebstrika", currentPokemon.getPokemonHealth()-20, Def.LEVEL_2, new String[]{"Flop", "Zap kick"}, currentPokemon.getAttackPoints()-30, "Electric pokemon", currentPokemon.getElectricPower());
        currentPokemon.setTripleAttack(currentPokemon.isTripleAttack());
        currentPokemon.hasSpecialAttack(currentPokemon.isSpecialAttack());
        currentPokemon.usedSpecialAttack(currentPokemon.isUsedSpecialAttack());
        return currentPokemon;
    }

}
