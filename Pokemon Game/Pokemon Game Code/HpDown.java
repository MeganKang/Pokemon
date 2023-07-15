public class HpDown extends PokemonDecorator{
  /** class constructor 
  * @param p Pokemon
  */
  public HpDown(Pokemon p){
    super(p, " -HP", -1); 
  }
}