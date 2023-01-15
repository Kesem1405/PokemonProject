public class Charmender extends FirePokemons {
    public Charmender() {
        super("Charmander", Def.CHARMENDER_HP, Def.LEVEL_1, new String[]{"Scratch"}, 40, "Fire pokemon");
    }

    public static Pokemon charmeleon(Pokemon currentPokemon){
        currentPokemon = new FirePokemons("Charmeleon", currentPokemon.getPokemonHealth()-20, Def.LEVEL_2, new String[]{"Scratch", "Flame tail"}, currentPokemon.getAttackPoints()-25, "Fire pokemon") {} ;
        currentPokemon.setTripleAttack(currentPokemon.isTripleAttack());
        currentPokemon.hasSpecialAttack(currentPokemon.isSpecialAttack());
        currentPokemon.usedSpecialAttack(currentPokemon.isUsedSpecialAttack());
        return currentPokemon;
    }

    public static Pokemon charizard(Pokemon currentPokemon){
        currentPokemon = new FirePokemons("Charizard", currentPokemon.getPokemonHealth()-30, Def.LEVEL_3, new String[]{"Scratch", "Flame tail","Fiery blast"}, currentPokemon.getAttackPoints()-40, "Fire pokemon") {} ;
        currentPokemon.setTripleAttack(currentPokemon.isTripleAttack());
        currentPokemon.hasSpecialAttack(currentPokemon.isSpecialAttack());
        currentPokemon.usedSpecialAttack(currentPokemon.isUsedSpecialAttack());
        return currentPokemon;
    }
}
