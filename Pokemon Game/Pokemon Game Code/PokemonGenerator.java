import java.util.*;
import java.util.HashMap;
import java.util.Scanner;
import java.io.FileReader;
import java.io.FileNotFoundException;

public class PokemonGenerator {
  private HashMap<String, String> pokemon;
  private static PokemonGenerator instance = null;

  /** class constructor 
    * reads the PokemonList.txt (if it exists) and places it in a Hashmap
    * key is pokemon name, value is pokemon type
  */
  private PokemonGenerator(){
    try{
      Scanner in = new Scanner(new FileReader("PokemonList.txt"));
      pokemon = new HashMap<String, String>();
      while (in.hasNextLine()){
        String[] div = in.nextLine().split(",");
        pokemon.put(div[0], div[1]);
        }
    }catch(FileNotFoundException e){
      System.out.println("File Not Found");
    }
  } 

  /**
    * creates an instance of the Singleton class
  */
  public static PokemonGenerator getInstance(){
    if(instance == null){
      instance = new PokemonGenerator();
    }
    return instance;
  }

  /**
    * @param level which starts at level 1, increases by 1 after defeating gyms
    * generates a random pokemon for the player to fight
    * gives that random pokemon random buffs, depending on the level
    * @return random pokemon generated
  */
  public Pokemon generateRandomPokemon(int level){
    Random rand = new Random();
    List<String> keys = new ArrayList<String>(pokemon.keySet());
    int randIndex = rand.nextInt(keys.size());
    String randPoke = keys.get(randIndex);
    String pokeType = pokemon.get(randPoke);

    Pokemon randP = getPokemon(randPoke);
    
    int levelBuff = (int) Math.random() * 2 + 1;
    for (int x = 1; x < level; x++){
      if(levelBuff == 1){
        randP = new AttackUp(randP);
      }
      else{
        randP = new HpUp(randP);
      }
    }
    addRandomBuff(randP);
    return randP;
    }

  /**
    * @param name of pokemon
    * creates a new pokemon object based on the element type of the pokemon, using the name given
    * gives the pokemon random hp from 20-25
    * @return the pokemon object
  */
  public Pokemon getPokemon(String name){
    Random rand = new Random();
    Pokemon poke;
    int hp = rand.nextInt(25 - 20) + 20;
    //int hp = (int)(20 + (int)(Math.random() * 25));
    int maxHp = hp;
    if (pokemon.get(name).equals("Fire")){
      poke = new Fire(name, hp, maxHp);
    }
    else if (pokemon.get(name).equals("Water")){
      poke = new Water(name, hp, maxHp);
    }
    else {
      poke = new Grass(name, hp, maxHp);
    }
    return poke;
  }

  /**
    * @param p pokemon
    * gives a random buff (increased attack or hp) to the pokemon
    * @return overrided pokemon object
  */
  public Pokemon addRandomBuff(Pokemon p){
    Random rand =  new Random();
    int randBuff = rand.nextInt(2) + 1; //random num 1-2
    AttackUp attackUp;
    HpUp hpup;
    if(randBuff == 1){
      //Attack Up
      attackUp = new AttackUp(p);
    }
    else{
      //HpDown
      hpup = new HpUp(p);
    }
    return p;
  }

  /**
    * @param p pokemon
    * gives a random debuff (decreased attack or hp) to       pokemon
    * @return overrided pokemon object

  */
  public Pokemon addRandomDebuff(Pokemon p){
    Random rand =  new Random();
    int randBuff = rand.nextInt(2) + 1; //random num 1-2
    if(randBuff == 1){
      //Attack Up
      p = new AttackDown(p);
    }
    else{
      //HpDown
      p = new HpDown(p);
    }
    return p;
  }
}

