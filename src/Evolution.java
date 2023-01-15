public class Evolution {
    public static Pokemon evolvePokemon(Pokemon currentPokemon) {
        // Level 2 pokemons.
        boolean evolved = false;
        if (currentPokemon.getPokemonHealth() >= 20 && currentPokemon.getAttackPoints() >= 25) {
            if (!currentPokemon.getPokemonName().equals("Moltres")) {
                System.out.println("Evolution cost: 20 Hp, 25 Ap.");
            }
                switch (currentPokemon.getPokemonName()) {
                    case "Pichu" -> {
                        currentPokemon = Pichu.pikachu(currentPokemon);
                        System.out.println("Pichu evolved to Pikachu!");
                        evolved = true;
                        int[] pikachuCosts = new int[]{5, 10};
                        currentPokemon.setCosts(pikachuCosts);
                        currentPokemon.setPokemonMaxHp(Def.PIKACHU_HP);
                        currentPokemon.setPokemonMaxAp(Def.PIKACHU_AP);

                    }
                    case "Moltres" -> System.out.println("This pokemon cannot be evolved.");
                    case "Salandit" -> {
                        currentPokemon = Salandit.salazzle(currentPokemon);
                        System.out.println("Salandit evolved to Salazzle!");
                        int[] salazzleCosts = new int[]{10, 25};
                        currentPokemon.setCosts(salazzleCosts);
                        currentPokemon.setPokemonMaxHp(Def.SALAZZLE_HP);
                        currentPokemon.setPokemonMaxAp(Def.SALAZZLE_AP);
                        evolved = true;
                    }
                    case "Charmander" -> {
                        currentPokemon = Charmender.charmeleon(currentPokemon);
                        System.out.println("Charmender evolved to Charmeleon!");
                        int[] charmeleonCosts = new int[]{15, 40};
                        currentPokemon.setCosts(charmeleonCosts);
                        currentPokemon.setPokemonMaxHp(Def.CHARMELEON_HP);
                        currentPokemon.setPokemonMaxAp(Def.CHARMELEON_AP);
                        evolved = true;
                    }
                    case "Blitzle" -> {
                        currentPokemon = Blitzle.zebstrika(currentPokemon);
                        System.out.println("Blitzle evolved to Zebstrika!");
                        int[] zebstrikaCosts = new int[]{20, 30};
                        currentPokemon.setCosts(zebstrikaCosts);
                        currentPokemon.setPokemonMaxHp(Def.ZEBSTRIKA_HP);
                        currentPokemon.setPokemonMaxAp(Def.ZEBSTRIKA_AP);
                        evolved = true;
                    }
                    case "Electabuzz" -> {
                        currentPokemon = Electabuzz.electivire(currentPokemon);
                        System.out.println("Electabuzz evolved to Electivire!");
                        int[] electivireCosts = new int[]{60, 80};
                        currentPokemon.setCosts(electivireCosts);
                        currentPokemon.setPokemonMaxHp(Def.ELECTRIVE_MAX_HP);
                        currentPokemon.setPokemonMaxAp(Def.ELECTRIVE_MAX_AP);
                        evolved = true;
                    }
                }
        }else {
            System.out.println("Unable to evolve right now, your Hp/Ap amount is low.");
            System.out.println("Turn is still yours, choose wisely.");
            return currentPokemon;
        }
        // Level 3 pokemons
        if (currentPokemon.getPokemonLevel() == Def.LEVEL_2 && !evolved) {
            if (currentPokemon.getPokemonHealth() >= 30 && currentPokemon.getAttackPoints() >= 40) {
                switch (currentPokemon.getPokemonName()) {
                    case "Charmeleon" -> {
                        if (currentPokemon.getPokemonHealth() >= 30 && currentPokemon.getAttackPoints() >= 40) {
                            currentPokemon = Charmender.charizard(currentPokemon);
                            System.out.println("Charmeleon evolved to Charizard!");
                            int[] charizardCosts = new int[]{15, 40, 80};
                            currentPokemon.setCosts(charizardCosts);
                            currentPokemon.setPokemonMaxHp(Def.CHARIZARD_HP);
                            currentPokemon.setPokemonMaxAp(Def.CHARIZARD_AP);
                        } else {
                            System.out.println("You need minimum of 30hp and 40ap to evolve your pokemon to third level.");
                            System.out.println("Its still your turn, please choose another option.");
                        }
                    }
                    case "Pikachu" -> {
                        if (currentPokemon.getPokemonHealth() >= 30 && currentPokemon.getAttackPoints() >= 40) {
                            currentPokemon = Pichu.raichu(currentPokemon);
                            System.out.println("Pikachu evolved to Raichu!");
                            int[] pikachuCosts = new int[]{5, 10, 60};
                            currentPokemon.setCosts(pikachuCosts);
                            currentPokemon.setPokemonMaxHp(Def.RAICHU_HP);
                            currentPokemon.setPokemonMaxAp(Def.RAICHU_AP);
                        } else {
                            System.out.println("You need minimum of 30hp and 40ap to evolve your pokemon to third level.");
                            System.out.println("Its still your turn, please choose another option.");
                        }
                    }
                }
            }
                else{
                    System.out.println("Unable to evolve right now, your Hp/Ap amount is low.");
                    System.out.println("Turn is still yours, choose wisely.");
                    return currentPokemon;
                }
                 if(currentPokemon.getPokemonLevel() == Def.LEVEL_3) {
                    System.out.println("You are at you max level.");
                }
        }
        return currentPokemon;
    }
}
