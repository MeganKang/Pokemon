import java.util.Scanner;
import java.util.Random;
import java.io.FileNotFoundException;
import java.awt.Point;

/*
* @author Stephanie Park
* @author Megan Kang
* @author Lizeth Valdovinos
* Trainer chooses a starter pokemon
* With that pokemon, trainer travels throughout the map
* Trainer can fight or catch pokemon, go to the store, and retrieve items, exploring as they go
*/
class Main {
  public static void main(String[] args) throws FileNotFoundException {
    // prompt user to enter name, select starter pokemon
    Scanner in = new Scanner(System.in);

    System.out.println("Prof. Oak: Hello there new trainer, what is your name?");
    String name = CheckInput.getString();
    System.out.println("Nice to meet you, " + name);
    System.out.println("Choose your first Pokemon:");
    System.out.println("1. Charmander\n2. Bulbasaur\n3. Squirtle");
    Pokemon p;
    String firstPokemon;
    int level = 1;
    int inputPokemon = CheckInput.getIntRange(1, 3);

    PokemonGenerator PokeGen = PokemonGenerator.getInstance();

    if (inputPokemon == 1) {
      p = PokeGen.getPokemon("Charmander");
    } 
    else if (inputPokemon == 2) {
      p = PokeGen.getPokemon("Bulbasaur");
    } 
    else{
      p = PokeGen.getPokemon("Squirtle");
    }
  
    int mapCount = 1;

    Map m = Map.getInstance();
    m.loadMap(mapCount);

    Trainer t = new Trainer(name, p);

    int mapInput = 0;
    do {
      Point loc = t.getLocation();
      m.reveal(loc);
      System.out.println(t);

      mainMenu();
      mapInput = CheckInput.getInt();
      if (mapInput == 1) {
        t.goNorth();
      } else if (mapInput == 2) {
        t.goSouth();
      } else if (mapInput == 3) {
        t.goEast();
      } else if (mapInput == 4) {
        t.goWest();
      } else if (mapInput == 5) {
        System.out.println("Game Over");
      } else {
        System.out.println("Invalid input.");
      }
      if (m.getCharAtLoc(t.getLocation()) == 'n') {
        System.out.println("Nothing is found.\n");
      }

      else if (m.getCharAtLoc(t.getLocation()) == 'i') {
        Random rand = new Random();
        int randItem = rand.nextInt(3) + 1;
        switch (randItem) {
          case 1:
            t.receiveMoney(10);
            System.out.println("You found 10 dollars!");
            m.removeCharAtLoc(loc);
            break;
          case 2:
            t.receivePotion();
            System.out.println("You found a potion!");
            m.removeCharAtLoc(loc);
            break;
          case 3:
            t.receivePokeBall();
            System.out.println("You found a pokeball!");
            m.removeCharAtLoc(loc);
            break;
        }
      } else if (m.getCharAtLoc(t.getLocation()) == 'w') {
        int toDo;
        Pokemon wild = PokeGen.generateRandomPokemon(level);
        System.out.print("A wild " + wild.getName() + " has appeared! What do you want to do?");
        do {
          System.out.println("\n1. Fight\n2. Use Potion\n3. Throw Poke Ball\n4. Run Away");
          toDo = CheckInput.getIntRange(1, 4);
          if (toDo == 1) {
            wild = trainerAttack(t, wild);
          } 
          else if (toDo == 2) {
            t.hasPotion();
            if (t.hasPotion() == true) {
              System.out.println("Choose a Pokemon:\n" + t.getPokemonList());
              int c1 = CheckInput.getIntRange(1, t.getNumPokemon());
              t.usePotion(c1);
            } 
            else {
              System.out.println("You do not have enough potions.");
            }
          } 
          else if (toDo == 3) {
            t.hasPokeBall();
            if (t.hasPokeBall() == true) {
              System.out.println("Shake...Shake...Shake...");
              t.catchPokemon(wild);
              if (t.catchPokemon(wild) == true) {
                System.out.println("You caught the pokemon!");

                if (t.getNumPokemon() == 7) {
                  System.out.println("You can't carry anymore Pokemon!");
                  System.out.println("Please select a Pokemon to release:");
                  t.getPokemonList();
                  int pokeRemove = CheckInput.getIntRange(1, 6);
                  t.removePokemon(pokeRemove);
                }

              } 
              else {
                System.out.println("You cannot catch this Pokemon!");
              }
            } 
            else {
              System.out.println("You do not have enough pokeballs.");
            }
          } 
          else if (toDo == 4) {
          System.out.print("You ran away...");
          Random rand = new Random();
          int randLoc = rand.nextInt(4) + 1;
          switch (randLoc) {
            case 1:
              m.reveal(loc);
              t.goNorth();
              break;
            case 2:
              m.reveal(loc);
              t.goSouth();
              break;
            case 3:
              m.reveal(loc);
              t.goWest();
              break;
            case 4:
              m.reveal(loc);
              t.goEast();
              break;
            }
            toDo = 4;
            break;
          }

        } while (wild.getHp() != 0 || (t.catchPokemon(wild) != true) || toDo != 4);
        if (wild.getHp() == 0) {
          System.out.println("You beat the pokemon!");
        }

      }

      else if (m.getCharAtLoc(t.getLocation()) == 'p') {
        Random rand = new Random();
        int randEncounter = rand.nextInt(4) + 1;
        switch (randEncounter) {
          case 1:
            System.out.println("Misty says hi and gives you a pokeball to help you out.");
            t.receivePokeBall();
            m.removeCharAtLoc(loc);
            break;
          case 2:
            System.out.println("Team Rocket spots you and steals 10 dollars from you!");
            t.receiveMoney(-10);
            m.removeCharAtLoc(loc);
            break;
          case 3:
            System.out.println("You encounter an old friend, Brock. He gives you 10 dollars.");
            t.receiveMoney(10);
            m.removeCharAtLoc(loc);
            break;
          case 4:
            System.out.println("Professor Oak sees your pokemon and decides to help out. He gives you a potion.");
            t.receivePotion();
            m.removeCharAtLoc(loc);
            break;
        }

      } else if (m.getCharAtLoc(t.getLocation()) == 'c') {
        System.out.println("You've entered the city.");
        System.out.println("Where would you like to go?");
        System.out.println("1. Store\n2. Pokemon hospital");
        store(t);
      } else if (m.getCharAtLoc(t.getLocation()) == 'f') {
        int contPlay;
        int toDo = 0;
        System.out.println("You reached a gym! Defeat the gym leader!");
        Pokemon gymPoke = PokeGen.generateRandomPokemon((level + 2));
        do {
          System.out.println("\n1. Fight\n2. Use Potion");
          toDo = CheckInput.getIntRange(1, 2);
          if (toDo == 1) {
            trainerAttack(t, gymPoke);
          } else if (toDo == 2) {
            if (t.hasPotion() == true) {
              System.out.println("Choose a Pokemon:\n" + t.getPokemonList());
              int c1 = CheckInput.getIntRange(1, t.getNumPokemon());
              t.usePotion(c1);
            } else {
              System.out.println("You do not have enough potions.");
            }
          }
        } while (gymPoke.getHp() != 0 || p.getHp() != 0);

        if (gymPoke.getHp() <= 0){
          System.out.println("You defeated the gym leader!");
          switch (mapCount) {
          case 1:
            m.loadMap(mapCount + 1);
            break;
          case 2:
            m.loadMap(mapCount + 1);
            break;
          case 3:
            m.loadMap(mapCount - 1);
            break;
        }
        }
        if (p.getHp() == 0){
          System.out.println("You lost to the gym leader! Come back when you're ready.");
        }
        m.loadMap(mapCount);
      } else {
        System.out.print("Goodbye!");
      }

    } while (mapInput != 5);
  }

  /*
   * options to go north, south, east, or west, or quit the game
   */
  public static int mainMenu() {
    System.out.println("Main Menu:");
    System.out.println("1. Go North");
    System.out.println("2. Go South");
    System.out.println("3. Go East");
    System.out.println("4. Go West");
    System.out.println("5. Quit");
    return 0;
  }

  /*
   * brings up store for the trainer to buy either buy potions, buy pokeballs, or exit
   * @param t trainer
   */
  public static void store(Trainer t) {
    Scanner input = new Scanner(System.in);
    int storeOption = CheckInput.getIntRange(1, 3);

    do {
      System.out.println("Hello! How can I help you?");
      System.out.println("1. Buy Potions - $5\n2. Buy Pokeballs - $3\n3. Exit");

      storeOption = input.nextInt();
      switch (storeOption) {
        case 1:
          if (t.getMoney() >= 5) {
            t.spendMoney(5);
            t.receivePotion();
            System.out.println("Here's your potion!");
          } else {
            System.out.println("You do not have enough money to spend.");
          }
          break;

        case 2:
          if (t.getMoney() >= 3) {
            t.spendMoney(3);
            t.receivePokeBall();
            System.out.println("Here's your PokeBall!");
          } else {
            System.out.println("You do not have enough money to spend.");
          }
          break;

        case 3:
          System.out.println("Thank You, Come Again!");
          break;
        default:
          System.out.print("Invalid Selection. Please Try Again.");
          break;
      }
    } while (storeOption != 3);

  }

  /**
   * Trainer is choosing fight option in an unexpected battle with wild pokemon
   * 
   * @param t trainer
   * @param wild pokemon being encountered
   * @return wild debuffed pokemon
   */
  public static Pokemon trainerAttack(Trainer t, Pokemon wild) {
    PokemonGenerator PokeGen = PokemonGenerator.getInstance();
    // when player chooses 'fight' option
    Scanner in = new Scanner(System.in);
    int rand = 1 + (int) (Math.random() * 3);
    int move;
    System.out.println("Choose a Pokemon:\n" + t.getPokemonList());
    // prints list

    Pokemon pokeChosen = t.getPokemon(CheckInput.getIntRange(1, t.getNumPokemon()));

    System.out.println(pokeChosen.getName() + ", I choose you!");

    // Random Debuffs
    int randBuffw = 1 + (int) (Math.random() * 4);
    int randBuffT = 1 + (int) (Math.random() * 10);
    if (randBuffw == 1) {
      wild = PokeGen.addRandomDebuff(wild);
    }
    if (randBuffT == 1) {
      pokeChosen = PokeGen.addRandomDebuff(pokeChosen);
    }

    System.out.println(pokeChosen.getAttackTypeMenu());
    int c = CheckInput.getIntRange(1, 2);
    if (c == 1) {

      System.out.print(pokeChosen.getAttackMenu(c));
      move = CheckInput.getIntRange(1, 3);

      System.out.println(pokeChosen.attack(wild, c, move));

      System.out.println(wild.attack(pokeChosen, c, rand));
      System.out.println(wild.toString());
    } else {

      System.out.print(pokeChosen.getAttackMenu(c));

      move = CheckInput.getIntRange(1, 3);

      System.out.println(pokeChosen.attack(wild, c, move));

      System.out.println(wild.attack(pokeChosen, c, rand));
      System.out.print(wild.toString());
    }
    return wild;
  }
}