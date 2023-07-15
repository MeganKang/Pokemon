import java.util.Random;

public class HpUp extends PokemonDecorator{
  /** class constructor 
  * @param p Pokemon
  */
  public HpUp(Pokemon p){
    super(p, " +HP", 1 + (int)(Math.random() * 2)); //random 1-2
  }
}