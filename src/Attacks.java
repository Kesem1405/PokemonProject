import java.util.Random;

public interface Attacks {



    static boolean performAttack(Pokemon currentPokemon, Pokemon opponentPokemon, int attackNumber,boolean isSpecialAttack) {
        boolean isAttacked = false;
        if (currentPokemon.isSpecialAttack()) {
            specialAttack(currentPokemon, opponentPokemon);
        }
        else {
            switch (currentPokemon.getPokemonName()) {
                case "Pichu" -> isAttacked = pichuAttacks(currentPokemon, opponentPokemon, attackNumber,isSpecialAttack);
                case "Pikachu" -> isAttacked = pikachuAttacks(currentPokemon, opponentPokemon, attackNumber,isSpecialAttack);
                case "Raichu" -> isAttacked = raichuAttacks(currentPokemon, opponentPokemon, attackNumber,isSpecialAttack);
                case "Moltres" -> isAttacked = moltresAttacks(currentPokemon, opponentPokemon, attackNumber,isSpecialAttack);
                case "Salandit" -> isAttacked = salanditAttacks(currentPokemon, opponentPokemon, attackNumber,isSpecialAttack);
                case "Salazzle" -> isAttacked = salazzleAttack(currentPokemon, opponentPokemon, attackNumber,isSpecialAttack);
                case "Charmander" -> isAttacked = charmanderAttacks(currentPokemon, opponentPokemon, attackNumber,isSpecialAttack);
                case "Charmeleon" -> isAttacked = charmeleonAttacks(currentPokemon, opponentPokemon, attackNumber,isSpecialAttack);
                case "Charizard" -> isAttacked = charizardAttacks(currentPokemon, opponentPokemon, attackNumber,isSpecialAttack);
                case "Electabuzz" -> isAttacked = electabuzzAttacks(currentPokemon, opponentPokemon, attackNumber,isSpecialAttack);
                case "Electivire" -> isAttacked = electivireAttacks(currentPokemon, opponentPokemon, attackNumber,isSpecialAttack);
                case "Blitzle" -> isAttacked = blitzleAttacks(currentPokemon, opponentPokemon, attackNumber,isSpecialAttack);
                case "Zebstrika " -> isAttacked = zebstrikaAttacks(currentPokemon, opponentPokemon, attackNumber,isSpecialAttack);
            }
        }
        return isAttacked;
    }

    static void specialAttack(Pokemon currentPokemon, Pokemon opponentPokemon) {
        String[] attacks = currentPokemon.getAttacks();
        Random random = new Random();
        int[] pokemonAttacks = new int[2];
        pokemonAttacks[0] = random.nextInt(attacks.length);
        pokemonAttacks[1] = random.nextInt(attacks.length);
        currentPokemon.hasSpecialAttack(false);
        currentPokemon.usedSpecialAttack(true);
        performAttack(currentPokemon, opponentPokemon, pokemonAttacks[0] + 1, true);
        performAttack(currentPokemon, opponentPokemon, pokemonAttacks[1] + 1,true);
    }
    static boolean moltresAttacks(Pokemon currentPokemon, Pokemon opponentPokemon, int attackNumber,boolean isSpecialAttack) {
        boolean isAttacked = true;
        if (attackNumber == Def.ASSISTING_HEATER) {
            if (currentPokemon.getAttackPoints() < 30 && !isSpecialAttack) {
                System.out.println("Not enough attack points.");
                isAttacked = false;
            } else {
                currentPokemon.setPokemonAttackPoints(currentPokemon.getAttackPoints() - 30);
                Random random = new Random();
                int attackRandomDamage = random.nextInt(51) + 10;
                if (currentPokemon.isTripleAttack()) {
                    int tripleAttack = attackRandomDamage * 3;
                    opponentPokemon.setPokemonHealth(opponentPokemon.getPokemonHealth() - tripleAttack);
                    System.out.println("Triple assisting heaters applied!");
                    System.out.println("Damage dealt to opponent: " + tripleAttack);
                    currentPokemon.setTripleAttack(false);
                } else {
                    opponentPokemon.setPokemonHealth(opponentPokemon.getPokemonHealth() - attackRandomDamage);
                    System.out.println("Assisting heater applied!!");
                    int selfDamage = firePokemonsSelfDamage(currentPokemon);
                    if (selfDamage == 0) {
                        System.out.println("Damage applied to opponent: " + attackRandomDamage + "||  No self damage for the attacker.");
                    } else {
                        System.out.println("Damage applied to opponent: " + attackRandomDamage + "|| Self damage for attacker:  " + selfDamage);
                    }
                }
            }
        }
        if (attackNumber == Def.FIRE_WING) {
            fireWing(currentPokemon, opponentPokemon,isSpecialAttack);
        }
        if (attackNumber == currentPokemon.getAttacks().length + 1) {
            kickAttack(currentPokemon, opponentPokemon);
        }
        return isAttacked;
    }

    static boolean salanditAttacks(Pokemon currentPokemon, Pokemon opponentPokemon, int attackNumber,boolean isSpecialAttack) {
        boolean isAttacked = false;
        if (attackNumber == Def.LIVE_COAL) {
            isAttacked = liveCoalAttack(currentPokemon, opponentPokemon,isSpecialAttack);
        } else if (attackNumber == currentPokemon.getAttacks().length + 1) {
            isAttacked = kickAttack(currentPokemon, opponentPokemon);
        }
        return isAttacked;
    }

    static boolean salazzleAttack(Pokemon currentPokemon, Pokemon opponentPokemon, int attackNumber,boolean isSpecialAttack) {
        boolean isAttacked = false;
        if (attackNumber == Def.LIVE_COAL) {
            isAttacked = liveCoalAttack(currentPokemon, opponentPokemon,isSpecialAttack);
        } else if (attackNumber == Def.FIRE_CLAWS) {
            isAttacked = fireClawsAttack(currentPokemon, opponentPokemon,isSpecialAttack);
        } else if (attackNumber == currentPokemon.getAttacks().length + 1) {
            isAttacked = kickAttack(currentPokemon, opponentPokemon);
        }
        return isAttacked;
    }

    static boolean charmanderAttacks(Pokemon currentPokemon, Pokemon opponentPokemon, int attackNumber,boolean isSpecialAttack) {
        boolean isAttacked = false;
        if (attackNumber == Def.SCRATCH) {
            isAttacked = scratchAttack(currentPokemon, opponentPokemon,isSpecialAttack);
        } else if (attackNumber == currentPokemon.getAttacks().length + 1) {
            isAttacked = kickAttack(currentPokemon, opponentPokemon);
        }
        return isAttacked;
    }

    static boolean charmeleonAttacks(Pokemon currentPokemon, Pokemon opponentPokemon, int attackNumber,boolean isSpecialAttack) {
        boolean isAttacked = false;
        if (attackNumber == Def.SCRATCH) {
            isAttacked = scratchAttack(currentPokemon, opponentPokemon,isSpecialAttack);
        }
        if (attackNumber == Def.FLAME_TAIL) {
            isAttacked = flameTailAttack(currentPokemon, opponentPokemon,isSpecialAttack);
        } else if (attackNumber == currentPokemon.getAttacks().length + 1) {
            isAttacked = kickAttack(currentPokemon, opponentPokemon);
        }
        return isAttacked;
    }

    static boolean charizardAttacks(Pokemon currentPokemon, Pokemon opponentPokemon, int attackNumber,boolean isSpecialAttack) {
        boolean isAttacked = false;
        if (attackNumber == Def.SCRATCH) {
            isAttacked = scratchAttack(currentPokemon, opponentPokemon,isSpecialAttack);
        } else if (attackNumber == Def.FLAME_TAIL) {
            isAttacked = flameTailAttack(currentPokemon, opponentPokemon,isSpecialAttack);
        } else if (attackNumber == Def.FIERY_BLAST) {
            isAttacked = fieryBlastAttack(currentPokemon, opponentPokemon,isSpecialAttack);
        } else if (attackNumber == currentPokemon.getAttacks().length + 1) {
            isAttacked = kickAttack(currentPokemon, opponentPokemon);
        }
        return isAttacked;
    }

    static boolean pichuAttacks(Pokemon currentPokemon, Pokemon opponentPokemon, int attackNumber,boolean isSpecialAttack) {
        boolean isAttacked = false;
        if (attackNumber == Def.QUICK_ATTACK) {
            isAttacked = quickAttack(currentPokemon, opponentPokemon,isSpecialAttack);
        }
        if (attackNumber == currentPokemon.getAttacks().length + 1) {
            isAttacked = kickAttack(currentPokemon, opponentPokemon);
        }
        return isAttacked;
    }

    static boolean pikachuAttacks(Pokemon currentPokemon, Pokemon opponentPokemon, int attackNumber,boolean isSpecialAttack) {
        boolean isAttacked = false;
        if (attackNumber == Def.QUICK_ATTACK) {
            isAttacked = quickAttack(currentPokemon, opponentPokemon,isSpecialAttack);
        }
        if (attackNumber == Def.ELECTRO_BALL) {
            isAttacked = electroBallAttack(currentPokemon, opponentPokemon,isSpecialAttack);
        }
        if (attackNumber == currentPokemon.getAttacks().length + 1) {
            isAttacked = kickAttack(currentPokemon, opponentPokemon);
        }
        return isAttacked;
    }

    static boolean raichuAttacks(Pokemon currentPokemon, Pokemon opponentPokemon, int attackNumber,boolean isSpecialAttack) {
        boolean isAttacked = false;
        if (attackNumber == Def.QUICK_ATTACK) {
            isAttacked = quickAttack(currentPokemon, opponentPokemon,isSpecialAttack);
        }
        if (attackNumber == Def.ELECTRO_BALL) {
            isAttacked = electroBallAttack(currentPokemon, opponentPokemon,isSpecialAttack);
        }
        if (attackNumber == Def.ELECTRIC_SURFER) {
            isAttacked = electricSurferAttack(currentPokemon, opponentPokemon,isSpecialAttack);
        }
        if (attackNumber == currentPokemon.getAttacks().length + 1) {
            isAttacked = kickAttack(currentPokemon, opponentPokemon);
        }
        return isAttacked;
    }

    static boolean electabuzzAttacks(Pokemon currentPokemon, Pokemon opponentPokemon, int attackNumber,boolean isSpecialAttack) {
        boolean isAttacked = false;
        if (attackNumber == Def.THUNDER_ATTACK) {
            isAttacked = thunderAttack(currentPokemon, opponentPokemon,isSpecialAttack);
        } else if (attackNumber == currentPokemon.getAttacks().length + 1) {
            isAttacked = kickAttack(currentPokemon, opponentPokemon);
        }
        return isAttacked;
    }

    static boolean electivireAttacks(Pokemon currentPokemon, Pokemon opponentPokemon, int attackNumber,boolean isSpecialAttack) {
        boolean isAttacked = false;
        if (attackNumber == Def.THUNDER_ATTACK) {
            isAttacked = thunderAttack(currentPokemon, opponentPokemon,isSpecialAttack);
        } else if (attackNumber == Def.THUNDER_PUNCH) {
            isAttacked = thunderPunchAttack(currentPokemon, opponentPokemon,isSpecialAttack);
        } else if (attackNumber == currentPokemon.getAttacks().length + 1) {
            isAttacked = kickAttack(currentPokemon, opponentPokemon);
        }
        return isAttacked;
    }

    static boolean blitzleAttacks(Pokemon currentPokemon, Pokemon opponentPokemon, int attackNumber,boolean isSpecialAttack) {
        boolean isAttacked = false;
        if (attackNumber == Def.FLOP_ATTACK) {
            isAttacked = flopAttack(currentPokemon, opponentPokemon,isSpecialAttack);
        }
        if (attackNumber == currentPokemon.getAttacks().length + 1) {
            isAttacked = kickAttack(currentPokemon, opponentPokemon);
        }
        return isAttacked;
    }

    static boolean zebstrikaAttacks(Pokemon currentPokemon, Pokemon opponentPokemon, int attackNumber,boolean isSpecialAttack) {
        boolean isAttacked = false;
        if (attackNumber == Def.FLOP_ATTACK) {
            isAttacked = flopAttack(currentPokemon, opponentPokemon,isSpecialAttack);
        }
        if (attackNumber == Def.ZAP_KICK) {
            isAttacked = zapKickAttack(currentPokemon, opponentPokemon,isSpecialAttack);
        }
        if (attackNumber == currentPokemon.getAttacks().length + 1) {
            isAttacked = kickAttack(currentPokemon, opponentPokemon);
        }
        return isAttacked;
    }


    static boolean fireWing(Pokemon currentPokemon, Pokemon opponentPokemon, boolean isSpecialAttack) {
        boolean isAttacked = true;
        if (currentPokemon.getAttackPoints() < 30 &&!isSpecialAttack) {
            System.out.println("Not enough attack points.");
            isAttacked = false;
        } else {
            currentPokemon.setPokemonAttackPoints(currentPokemon.getAttackPoints() - 30);
            if (currentPokemon.isTripleAttack()) {
                opponentPokemon.setPokemonHealth(opponentPokemon.getPokemonHealth() - 30 * 3);
                System.out.println("Triple fire wings applied!");
                currentPokemon.setTripleAttack(false);
                int selfDamage = firePokemonsSelfDamage(currentPokemon);
                System.out.println("Damage applied to opponent: 90  |" + "Self damage for attacker: " + selfDamage);
            } else {
                opponentPokemon.setPokemonHealth(opponentPokemon.getPokemonHealth() - 30);
                System.out.println("Fire wing  applied!!");
                int selfDamage = firePokemonsSelfDamage(currentPokemon);
                if (selfDamage == 0) {
                    System.out.println("Damage applied to opponent: 30" + "||  No self damage for the attacker.");
                } else {
                    System.out.println("Damage applied to opponent: 30" + "|| Self damage for attacker:  " + selfDamage);
                }
            }
        }
        return isAttacked;
    }

    static boolean thunderAttack(Pokemon currentPokemon, Pokemon opponentPokemon,boolean isSpecialAttack) {
        boolean isAttacked = true;
        int damage;
        if (currentPokemon.getAttackPoints() < 60 &&!isSpecialAttack) {
            System.out.println("Not enough attack points.");
            isAttacked = false;
        } else {
            currentPokemon.setPokemonAttackPoints(currentPokemon.getAttackPoints() - 60);
            Random random = new Random();
            int thunderDamage = random.nextInt(11) + 40;
            int energy = thunderDamage * (1 + currentPokemon.getElectricPower() / 100);
            if (currentPokemon.isTripleAttack()) {
                damage = energy * 3;
                opponentPokemon.setPokemonHealth(opponentPokemon.getPokemonHealth() - (damage));
                System.out.println("Triple thunder attacks applied! ");
                System.out.println("Damage applied to opponent" + damage);
                currentPokemon.setTripleAttack(false);
            } else {
                damage = energy;
                opponentPokemon.setPokemonHealth(opponentPokemon.getPokemonHealth() - (damage));
                System.out.println("thunder attack applied!!");
                System.out.println("Damage applied to opponent:" + damage);
            }
        }
        return isAttacked;
    }

    static boolean thunderPunchAttack(Pokemon currentPokemon, Pokemon opponentPokemon,boolean isSpecialAttack) {
        boolean isAttacked = true;
        int damage;
        if (currentPokemon.getAttackPoints() < 80 &&!isSpecialAttack) {
            System.out.println("Not enough attack points.");
            isAttacked = false;
        } else {
            currentPokemon.setPokemonAttackPoints(currentPokemon.getAttackPoints() - 60);
            Random random = new Random();
            int thunderPunchDamage = random.nextInt(71) + 50;
            int energy = thunderPunchDamage * (1 + currentPokemon.getElectricPower() / 100);
            if (currentPokemon.isTripleAttack()) {
                damage = energy * 3;
                opponentPokemon.setPokemonHealth(opponentPokemon.getPokemonHealth() - (damage));
                System.out.println("Triple thunder punch attacks applied! ");
                System.out.println("Damage applied to opponent" + damage);
                currentPokemon.setTripleAttack(false);
            } else {
                damage = energy;
                opponentPokemon.setPokemonHealth(opponentPokemon.getPokemonHealth() - (damage));
                System.out.println("thunder punch attack applied!!");
                System.out.println("Damage applied to opponent: " + damage);
            }
        }
        return isAttacked;
    }

    static boolean scratchAttack(Pokemon currentPokemon, Pokemon opponentPokemon, boolean isSpecialAttack) {
        boolean isAttacked = true;
        if (currentPokemon.getAttackPoints() < 15 &&!isSpecialAttack) {
            System.out.println("Not enough attack points.");
            isAttacked = false;
        } else {
            currentPokemon.setPokemonAttackPoints(currentPokemon.getAttackPoints() - 15);
            Random random = new Random();
            int attackRandomDamage = random.nextInt((35 - 25) + 1) + 25;
            if (currentPokemon.isTripleAttack()) {
                int tripleBonus = attackRandomDamage * 3;
                opponentPokemon.setPokemonHealth(opponentPokemon.getPokemonHealth() - tripleBonus);
                System.out.println("Triple scratch applied!!");
                System.out.println("Damage applied to opponent: " + tripleBonus);
                currentPokemon.setTripleAttack(false);
            } else {
                System.out.println("Scratch applied!!");
                opponentPokemon.setPokemonHealth(opponentPokemon.getPokemonHealth() - attackRandomDamage);
                int selfDamage = firePokemonsSelfDamage(currentPokemon);
                if (selfDamage == 0) {
                    System.out.println("Damage applied to opponent: 30" + "||  No self damage for the attacker.");
                } else {
                    System.out.println("Damage applied to opponent: " + attackRandomDamage + " |Self damage for attacker: " + selfDamage);
                }
            }
        }
        return isAttacked;
    }

    static boolean flameTailAttack(Pokemon currentPokemon, Pokemon opponentPokemon, boolean isSpecialAttack) {
        boolean isAttacked = true;
        if (currentPokemon.getAttackPoints() < 40 &&!isSpecialAttack) {
            System.out.println("Not enough attack points.");
            isAttacked = false;
        } else {
            currentPokemon.setPokemonAttackPoints(currentPokemon.getAttackPoints() - 40);
            Random random = new Random();
            int selfDamage = firePokemonsSelfDamage(currentPokemon);
            int attackRandomDamage = random.nextInt(21) + 30;
            if (currentPokemon.isTripleAttack()) {
                int tripleBonus = attackRandomDamage * 3;
                opponentPokemon.setPokemonHealth(opponentPokemon.getPokemonHealth() - tripleBonus);
                System.out.println("Triple flame tails applied!!");
                if (selfDamage == 0) {
                    System.out.println("Damage applied to opponent: " + tripleBonus + "||  No self damage for the attacker.");
                } else {
                    System.out.println("Damage applied to opponent: " + tripleBonus + " || Self damage for attacker: " + selfDamage);
                }
                currentPokemon.setTripleAttack(false);
            } else {
                System.out.println("Flame tail applied!!");
                opponentPokemon.setPokemonHealth(opponentPokemon.getPokemonHealth() - attackRandomDamage);
                if (selfDamage == 0) {
                    System.out.println("Damage applied to opponent: 30" + "||  No self damage for the attacker.");
                } else {
                    System.out.println("Damage applied to opponent: " + attackRandomDamage + " || Self damage for attacker: " + selfDamage);
                }
            }
        }
        return isAttacked;
    }

    static boolean fieryBlastAttack(Pokemon currentPokemon, Pokemon opponentPokemon, boolean isSpecialAttack) {
        boolean isAttacked = true;
        if (currentPokemon.getAttackPoints() < 50 &&!isSpecialAttack) {
            System.out.println("Not enough attack points.");
            isAttacked = false;
        } else {
            int selfDamage = firePokemonsSelfDamage(currentPokemon);
            currentPokemon.setPokemonAttackPoints(currentPokemon.getAttackPoints() - 50);
            if (currentPokemon.isTripleAttack()) {
                int tripleBonus = Def.FIERY_BLAST_DAMAGE * 3;
                opponentPokemon.setPokemonHealth(opponentPokemon.getPokemonHealth() - tripleBonus);
                System.out.println("Triple fiery blasts applied!!");
                if (selfDamage == 0) {
                    System.out.println("Damage applied to opponent: " + tripleBonus + "||  No self damage for the attacker.");
                } else {
                    System.out.println("Damage applied to opponent: " + tripleBonus + " || Self damage for attacker: " + selfDamage);
                }
                currentPokemon.setTripleAttack(false);
            } else {
                System.out.println("Fiery blast applied!!");
                opponentPokemon.setPokemonHealth(opponentPokemon.getPokemonHealth() - Def.FIERY_BLAST_DAMAGE);
                if (selfDamage == 0) {
                    System.out.println("Damage applied to opponent: " + Def.FIERY_BLAST + "||  No self damage for the attacker.");
                } else {
                    System.out.println("Damage applied to opponent: " + Def.FIERY_BLAST_DAMAGE + " || Self damage for attacker: " + selfDamage);
                }
            }
        }
        return isAttacked;
    }

    static boolean flopAttack(Pokemon currentPokemon, Pokemon opponentPokemon,boolean isSpecialAttack) {
        boolean isAttacked = true;
        int damage;
        if (currentPokemon.getAttackPoints() < 20 &&!isSpecialAttack) {
            System.out.println("Not enough attack points.");
            isAttacked = false;
        } else {
            currentPokemon.setPokemonAttackPoints(currentPokemon.getAttackPoints() - 20);
            Random random = new Random();
            int flopDamage = random.nextInt(6) + 20;
            int energy = flopDamage * (1 + currentPokemon.getElectricPower() / 100);
            if (currentPokemon.isTripleAttack()) {
                damage = energy * 3;
                opponentPokemon.setPokemonHealth(opponentPokemon.getPokemonHealth() - (damage));
                System.out.println("Triple flop attacks applied! ");
                System.out.println("Damage applied to opponent" + damage);
                currentPokemon.setTripleAttack(false);
            } else {
                damage = energy;
                opponentPokemon.setPokemonHealth(opponentPokemon.getPokemonHealth() - (damage));
                System.out.println("Flop attack applied!!");
                System.out.println("Damage applied to opponent:" + damage);
            }
        }
        return isAttacked;
    }

    static boolean zapKickAttack(Pokemon currentPokemon, Pokemon opponentPokemon,boolean isSpecialAttack) {
        boolean isAttacked = true;
        int damage;
        if (currentPokemon.getAttackPoints() < 30 &&!isSpecialAttack) {
            System.out.println("Not enough attack points.");
            isAttacked = false;
        } else {
            currentPokemon.setPokemonAttackPoints(currentPokemon.getAttackPoints() - 30);
            Random random = new Random();
            int flopDamage = random.nextInt(6) + 30;
            int energy = flopDamage * (1 + currentPokemon.getElectricPower() / 100);
            if (currentPokemon.isTripleAttack()) {
                damage = energy * 3;
                opponentPokemon.setPokemonHealth(opponentPokemon.getPokemonHealth() - (damage));
                System.out.println("Triple zap kicks applied! ");
                System.out.println("Damage applied to opponent" + damage);
                currentPokemon.setTripleAttack(false);
            } else {
                damage = energy;
                opponentPokemon.setPokemonHealth(opponentPokemon.getPokemonHealth() - (damage));
                System.out.println("Zap kicks applied!!");
                System.out.println("Damage applied to opponent:" + damage);
            }
        }
        return isAttacked;
    }

    static boolean quickAttack(Pokemon currentPokemon, Pokemon opponentPokemon, boolean isSpecialAttack) {
        boolean isAttacked = true;
        if (currentPokemon.getAttackPoints() < 5 &&!isSpecialAttack) {
            System.out.println("Not enough attack points.");
            isAttacked = false;
        } else {
            currentPokemon.setPokemonAttackPoints(currentPokemon.getAttackPoints() - 5);
            int energy = 10 * (1 + currentPokemon.getElectricPower() / 100);
            if (currentPokemon.isTripleAttack()) {
                opponentPokemon.setPokemonHealth(opponentPokemon.getPokemonHealth() - (3 * energy));
                System.out.println("Triple quick attacks applied! ");
                System.out.println("Damage applied to opponent" + energy * 3);
                currentPokemon.setTripleAttack(false);
            } else {
                opponentPokemon.setPokemonHealth(opponentPokemon.getPokemonHealth() - (energy));
                System.out.println("Quick attack applied!!");
                System.out.println("Damage applied to opponent:" + energy);
            }
        }
        return isAttacked;
    }

    static boolean electroBallAttack(Pokemon currentPokemon, Pokemon opponentPokemon, boolean isSpecialAttack) {
        boolean isAttacked = true;
        if (currentPokemon.getAttackPoints() < 10 &&!isSpecialAttack) {
            System.out.println("Not enough attack points.");
            isAttacked = false;
        } else {
            currentPokemon.setPokemonAttackPoints(currentPokemon.getAttackPoints() - 10);
            Random random = new Random();
            int electroBallDamage = random.nextInt(11) + 30;
            int energy = electroBallDamage * (1 + currentPokemon.getElectricPower() / 100);
            int tripleEnergy = energy * 3;
            if (currentPokemon.isTripleAttack()) {
                opponentPokemon.setPokemonHealth(opponentPokemon.getPokemonHealth() - tripleEnergy);
                System.out.println("Triple electro balls applied! ");
                System.out.println("Damage applied to opponent" + tripleEnergy);
                currentPokemon.setTripleAttack(false);
            } else {
                opponentPokemon.setPokemonHealth(opponentPokemon.getPokemonHealth() - (energy));
                System.out.println("Electro balls attack applied!!");
                System.out.println("Damage applied to opponent:" + energy);
            }
        }
        return isAttacked;
    }

    static boolean electricSurferAttack(Pokemon currentPokemon, Pokemon opponentPokemon, boolean isSpecialAttack) {
        boolean isAttacked = true;
        if (currentPokemon.getAttackPoints() < 60 &&!isSpecialAttack) {
            System.out.println("Not enough attack points.");
            isAttacked = false;
        } else {
            currentPokemon.setPokemonAttackPoints(currentPokemon.getAttackPoints() - 60);
            Random random = new Random();
            int electricSurferDamage = random.nextInt(101) + 20;
            int energy = electricSurferDamage * (1 + currentPokemon.getElectricPower() / 100);
            int tripleEnergy = energy * 3;
            if (currentPokemon.isTripleAttack()) {
                opponentPokemon.setPokemonHealth(opponentPokemon.getPokemonHealth() - tripleEnergy);
                System.out.println("Triple electric surfers attacks applied! ");
                System.out.println("Damage applied to opponent" + tripleEnergy);
                currentPokemon.setTripleAttack(false);
            } else {
                opponentPokemon.setPokemonHealth(opponentPokemon.getPokemonHealth() - (energy));
                System.out.println("Electric surfers attack applied!!");
                System.out.println("Damage applied to opponent:" + energy);
            }
        }
        return isAttacked;
    }

    static boolean liveCoalAttack(Pokemon currentPokemon, Pokemon opponentPokemon, boolean isSpecialAttack) {
        boolean isAttacked = true;
        if (currentPokemon.getAttackPoints() < 10 &&!isSpecialAttack) {
            System.out.println("Not enough attack points.");
            isAttacked = false;
        } else {
            currentPokemon.setPokemonAttackPoints(currentPokemon.getAttackPoints() - 10);
            Random random = new Random();
            int attackRandomDamage = random.nextInt(26);
            if (currentPokemon.isTripleAttack()) {
                int tripleBonus = attackRandomDamage * 3;
                System.out.println("Triple live coal applied!!");
                System.out.println("Damage dealt to opponent: " + tripleBonus);
                opponentPokemon.setPokemonHealth(opponentPokemon.getPokemonHealth() - tripleBonus);
                currentPokemon.setTripleAttack(false);
            } else {
                opponentPokemon.setPokemonHealth(opponentPokemon.getPokemonHealth() - attackRandomDamage);
                System.out.println("Live coal applied!!");
                int selfDamage = firePokemonsSelfDamage(currentPokemon);
                if (selfDamage == 0) {
                    System.out.println("Damage applied to opponent: " + attackRandomDamage + "||  No self damage for the attacker.");
                } else {
                    System.out.println("Damage applied to opponent: " + attackRandomDamage + "| Self damage for attacker:  " + selfDamage);
                }
            }
        }
        return isAttacked;
    }

    static boolean fireClawsAttack(Pokemon currentPokemon, Pokemon opponentPokemon, boolean isSpecialAttack) {
        boolean isAttacked = true;
        if (currentPokemon.getAttackPoints() < 25 &&!isSpecialAttack) {
            System.out.println("Not enough attack points.");
            isAttacked = false;
        } else {
            int selfDamage = firePokemonsSelfDamage(currentPokemon);
            currentPokemon.setPokemonAttackPoints(currentPokemon.getAttackPoints() - 25);
            Random random = new Random();
            int attackRandomDamage = random.nextInt(50) + 1;
            if (currentPokemon.isTripleAttack()) {
                int tripleBonus = attackRandomDamage * 3;
                System.out.println("Triple fire claws applied!!");
                if (selfDamage == 0) {
                    System.out.println("Damage applied to opponent: " + tripleBonus + "||  No self damage for the attacker.");
                } else {
                    System.out.println("Damage applied to opponent: " + tripleBonus + "| Self damage for attacker:  " + selfDamage);
                }
                opponentPokemon.setPokemonHealth(opponentPokemon.getPokemonHealth() - tripleBonus);
                currentPokemon.setTripleAttack(false);
            } else {
                opponentPokemon.setPokemonHealth(opponentPokemon.getPokemonHealth() - attackRandomDamage);
                System.out.println("Fire claws applied!!");
                if (selfDamage == 0) {
                    System.out.println("Damage applied to opponent: " + attackRandomDamage + "||  No self damage for the attacker.");
                } else {
                    System.out.println("Damage applied to opponent: " + attackRandomDamage + "| Self damage for attacker:  " + selfDamage);
                }
            }
        }
        return isAttacked;
    }

    static boolean kickAttack(Pokemon currentPokemon, Pokemon opponentPokemon) {
        boolean isAttacked = true;
        if (currentPokemon.isTripleAttack()) {
            opponentPokemon.setPokemonHealth(opponentPokemon.getPokemonHealth() - 6);
            System.out.println("3 Kicks applied!");
            System.out.println("Damage dealt to opponent: 6");
            currentPokemon.setTripleAttack(false);
        }
        opponentPokemon.setPokemonHealth(opponentPokemon.getPokemonHealth() - 2);
        System.out.println("Kick attack from: " + currentPokemon.getPokemonName());
        System.out.println("Damage dealt to opponent: 2");
        return isAttacked;
    }


    static int firePokemonsSelfDamage(Pokemon currentPokemon) {
        Random attackerSelfDamage = new Random();
        Random changeForSelfDamage = new Random();
        int chance = changeForSelfDamage.nextInt(3) + 1;
        int attackerRandomTakesDamage = 0;
        if (chance == Def.CHANCE_TO_SELF_DAMAGE) {
            attackerRandomTakesDamage = attackerSelfDamage.nextInt(10) + 3;
            currentPokemon.setPokemonHealth(currentPokemon.getPokemonHealth() - attackerRandomTakesDamage);
        }
        return attackerRandomTakesDamage;
    }


}
