public class Electabuzz extends ElectricPokemons{
    public Electabuzz() {
        super("Electabuzz", Def.ELECTABUZZ_MAX_HP, 1, new String[]{"Thunder"}, 100, "Electric pokemon", 0);
    }
        public static Pokemon electivire(Pokemon currentPokemon){
            currentPokemon = new ElectricPokemons("Electrive", currentPokemon.getPokemonHealth()-20, Def.LEVEL_2, new String[]{"Thunder", "Thunder punch"}, currentPokemon.getAttackPoints()-30, "Electric pokemon", currentPokemon.getElectricPower()) {} ;
            currentPokemon.setTripleAttack(currentPokemon.isTripleAttack());
            currentPokemon.hasSpecialAttack(currentPokemon.isSpecialAttack());
            currentPokemon.usedSpecialAttack(currentPokemon.isUsedSpecialAttack());
            return currentPokemon;
        }
}
