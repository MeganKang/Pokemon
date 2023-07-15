import java.util.ArrayList;
import java.awt.Point;
import java.util.Random;

/*
* instance variables for Trainer
*/
public class Trainer extends Entity{
  private int money;
  private int potions;
  private int pokeballs;
  private Point loc;
  
  private ArrayList <Pokemon> pokemon;

  /** class constructor 
    * @param n name of trainer
    * @param p name of pokemon
    * @param map
    */
  public Trainer (String n, Pokemon p){
    super(n, 25, 25);
    pokemon = new ArrayList <Pokemon> ();
    pokemon.add(p);
    money = 25;
    potions = 1;
    pokeballs = 5;
    Map map = Map.getInstance();
    loc = map.findStart();
  }

  /*
  * retrieves value of money
  * @return money amount of money the trainer has
  */
  public int getMoney(){
    return money;
  }

  /*
  * subtracts amount of money spent from total money if the player has enough money to spend
  * @param amt amount spending
  * @return true if trainer has enough money
  * @return false if trainer doesn't have enough money
  */
  public boolean spendMoney(int amt){
    if (amt > money){
      money -= amt;
      return true;
    }
    return false;
  }
  
  /*
  * amount received is added to total money
  * @param amt amount received
  */
  public void receiveMoney(int amt){
    money += amt;
  }

  /*
  * checks to see if trainer has potions
  * @return false if player doesn't have potions
  * @return true if player does have potions
  */
  public boolean hasPotion(){
    if (potions == 0){
      return false;
    }
    return true;
  }
  
  /*
  * gives trainer a potion, adds to total potions
  */
  public void receivePotion(){
    potions += 1;
  } 

  /*
  * grabs a pokemon from the list by using an index and heals that pokemon using a potion unless they don't have any potions
  * decreases total amount of potions if used
  * @param pokeIndex the trainer's selection of pokemon
  */
  public void usePotion(int pokeIndex){
    pokemon.get(pokeIndex - 1).heal();
    potions -= 1;

  }

  /*
  * checks to see if the trainer has pokeballs
  * @return false if trainer doesn't have pokeballs
  * @return true if trainer has pokeballs
  */
  public boolean hasPokeBall(){
    if (pokeballs == 0){
      return false;
    }
    return true;
  }

  /*
  * adds a pokeball to the total amount of pokeballs
  */
  public void receivePokeBall(){
    pokeballs += 1;
  }

  /*
  * catches a pokemon and adds it to the pokemon list if pokemon is less than 50% hp; cannot catch if pokemon hp is greater than 50%, or 0% (dead)
  * @param pokemon the trainer wants to catch
  * @return true if conditions are met, pokemon is caught
  * @return false if conditions are not met, pokemon cannot be caught
  */
  public boolean catchPokemon(Pokemon p){
    double pokehp = (double) (p.getHp());
    if (pokehp > 0.0 && (pokehp/p.getMaxHP()) < 0.5 && pokeballs > 0)
    {
      pokemon.add(p);
      pokeballs -= 1;
      return true;
    }
    return false;
    }

  /*
  * grabs the location of trainer
  * @return loc location of trainer
  */
  public Point getLocation(){
    return loc;
  }

  /*
  * if desired location is not out of bounds, trainer's location is updated y+1
  * if desired location is out of bounds, trainer's location is not updated
  * @return 'x' out of bounds location
  * @return map.getCharAtLoc(loc) new location of trainer
  */
  public char goNorth(){
    Map map = Map.getInstance();
    // Point point = new Point((int)loc.getX(), (int)loc.getY());
    // point.translate(-1, 0);
    if((int)loc.getX() == 0){
      System.out.println("You cannot go that way.");
      return 'B';
    }
    else{
      loc.translate(-1, 0);
      return map.getCharAtLoc(loc);
    }
  }

  /*
  * if desired location is not out of bounds, trainer's location is updated y-1
  * if desired location is out of bounds, trainer's location is not updated
  * @return 'x' out of bounds location
  * @return map.getCharAtLoc(loc) new location of trainer
  */
  public char goSouth(){
    Map map = Map.getInstance();
    if((int)loc.getX() > 3){
      System.out.println("You cannot go that way.");
      return 'B';
    }
    else{
      loc.translate(1, 0);
      return map.getCharAtLoc(loc);
    }
  }

  /*
  * if desired location is not out of bounds, trainer's location is updated x+1
  * if desired location is out of bounds, trainer's location is not updated
  * @return 'x' out of bounds location
  * @return map.getCharAtLoc(loc) new location of trainer
  */
  public char goEast(){
    Map map = Map.getInstance();
    if((int)loc.getY() == 4){
      System.out.println("You cannot go that way.");
      return 'B';
    }
    else{
      loc.translate(0, 1);
      return map.getCharAtLoc(loc);
    }
  }

  /*
  * if desired location is not out of bounds, trainer's location is updated x-1
  * if desired location is out of bounds, trainer's location is not updated
  * @return 'x' out of bounds location
  * @return map.getCharAtLoc(loc) new location of trainer
  */
  public char goWest(){

    Map map = Map.getInstance();
    if((int)loc.getY() == 0){
      System.out.println("You cannot go that way.");
      return 'B';
    }
    else{
      loc.translate(0, -1);
      return map.getCharAtLoc(loc);
    }
  }

  /*
  * grabs the number of pokemon that the trainer has
  * @return pokemon.size() number of pokemon
  */
  public int getNumPokemon(){
    return pokemon.size();
  }

  /*
  * heals all of the trainer's pokemon to full health
  */
  public void healAllPokemon(){
    for(Pokemon p : pokemon){
      p.heal();
    }
  }
  //Project 2 Update
  /** This functions generates a random buff, either AttackUp or HpHp and applies it to all Pokemon trainer carries
  */
  public void buffAllPokemon(){
    Random rand =  new Random();
    int randBuff = rand.nextInt(2) + 1; //random num 1-2
    for(int i = 0; i < pokemon.size(); i++){
      if(randBuff == 1){
        //Attack Up
        pokemon.set(i, new AttackUp(this.getPokemon(i)));
      }
      else{
        //HP up
        pokemon.set(i, new HpUp(this.getPokemon(i)));
      }
    }
    
    
     
  }
  //Project 2 Update
  /** This function generates a random buff, and then debuffs it
  @param index
  */
  public void debuffPokemon(int index){
    Random rand =  new Random();
    int randBuff = rand.nextInt(2) + 1; //random num 1-2
      if(randBuff == 1){
        //Attack Up
        pokemon.set(index, new AttackDown(this.getPokemon(index)));
      }
      else{
        //HP up
        pokemon.set(index, new HpDown(this.getPokemon(index)));
      }
  }

  /*
  * checks if we have the pokemon in the list based on the index
  * @param index the pokemon's number
  * @return null if index cannot grab pokemon
  * @return pokemon.get(index-1) if pokemon in the list exists for that index
  */
  public Pokemon getPokemon(int index){
    if (index >= pokemon.size() && index < 0){
      System.out.println("Invalid index.");
      return null;
    }
    else{
      return pokemon.get(index - 1);
    }
  }

  /*
  * gets the list of pokemon that the trainer has
  * @return result the list of pokemon
  */
  public String getPokemonList(){
    String result = "";
    for(int i = 0; i < pokemon.size(); i++){
      result += (i + 1) + ". " + (pokemon.get(i)).toString() + "\n";
    }
    return result;
  }
  //Project 2 Update
  public Pokemon removePokemon(int index){
    return pokemon.remove(index);
  }

  /*
  * String representation of trainer's stats, pokemon and its hp
  * @return output String representation of the trainer's stats, pokemon and its hp
  */
  public String toString(){
    Map map = Map.getInstance();
    String output = String.format("\n%s: %d/25\nMoney: %d\nPotions: %d\nPoke Balls: %d\nPokemon:\n-------\n%s \nMap:\n%s", super.getName(), super.getHp(), getMoney(), potions, pokeballs, getPokemonList(),map.mapToString(loc));

    return output;
  }
}