import java.util.Random;

public class Pichu extends ElectricPokemons {

    public Pichu() {
        super("Pichu", Def.PICHU_HP, Def.LEVEL_1, new String[]{"Quick attack"}, 40, "Electric pokemon", 0);
    }
    public static Pokemon pikachu(Pokemon currentPokemon) {
        currentPokemon = new ElectricPokemons("Pikachu", currentPokemon.getPokemonHealth()-20, Def.LEVEL_2, new String[]{"Quick attack", "Electro ball"}, currentPokemon.getAttackPoints()-30, "Electric pokemon", currentPokemon.getElectricPower()) {} ;
        currentPokemon.setTripleAttack(currentPokemon.isTripleAttack());
        currentPokemon.hasSpecialAttack(currentPokemon.isSpecialAttack());
        currentPokemon.usedSpecialAttack(currentPokemon.isUsedSpecialAttack());
        return currentPokemon;
    }

    public static Pokemon raichu(Pokemon currentPokemon) {
        currentPokemon = new ElectricPokemons("Raichu", currentPokemon.getPokemonHealth()-30, Def.LEVEL_3, new String[]{"Quick attack", "Electro ball","Electro surfer"}, currentPokemon.getAttackPoints()-40, "Electric pokemon", currentPokemon.getElectricPower()) {} ;
        currentPokemon.setTripleAttack(currentPokemon.isTripleAttack());
        currentPokemon.hasSpecialAttack(currentPokemon.isSpecialAttack());
        currentPokemon.usedSpecialAttack(currentPokemon.isUsedSpecialAttack());
        return currentPokemon;
    }
}
