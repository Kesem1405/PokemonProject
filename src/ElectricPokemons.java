public class ElectricPokemons extends Pokemon {
    public int getElectricPower() {
        return electricPower;
    }

    public void setElectricPower(int electricPower) {
        this.electricPower = electricPower;
    }

    private  int electricPower;

    public String toString() {
        return super.toString() + "," + pokemonType;
    }

    public ElectricPokemons(String pokemonName, int pokemonHp , int pokemonLevel, String[] attacks, int maxAttackPoint, String pokemonType, int electricPower) {
        super(pokemonName, pokemonHp , pokemonLevel, attacks, maxAttackPoint, pokemonType);
        this.electricPower = electricPower;
    }
}
