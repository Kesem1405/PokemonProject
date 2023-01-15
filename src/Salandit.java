public class Salandit extends FirePokemons {
    public Salandit() {
        super("Salandit", Def.SALANDIT_HP, Def.LEVEL_1, new String[]{"Live coal"}, 45, "Fire pokemon");
    }

    public static Pokemon salazzle(Pokemon currentPokemon) {
        currentPokemon = new FirePokemons("Salazzle", currentPokemon.getPokemonHealth() - 20, Def.LEVEL_2, new String[]{"Live coal", "Fire claws"}, currentPokemon.getAttackPoints()-30, "Fire pokemon"){};
        currentPokemon.setTripleAttack(currentPokemon.isTripleAttack());
        currentPokemon.hasSpecialAttack(currentPokemon.isSpecialAttack());
        currentPokemon.usedSpecialAttack(currentPokemon.isUsedSpecialAttack());
        return currentPokemon;
    }
}