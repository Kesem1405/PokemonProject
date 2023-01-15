public abstract class FirePokemons extends  Pokemon{
    private final String pokemonType;
    public FirePokemons(String pokemonName, int pokemonHp , int pokemonLevel,String[] attacks, int maxAttackPoint, String pokemonType) {
        super(pokemonName,pokemonHp ,pokemonLevel,attacks,maxAttackPoint, pokemonType);
        this.pokemonType = pokemonType;
    }

    public String toString() {
        return  super.toString() + "," + pokemonType;
    }
}
