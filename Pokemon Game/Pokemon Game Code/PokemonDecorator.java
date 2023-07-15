public abstract class PokemonDecorator extends Pokemon {
  private Pokemon pokemon;
  /** class constructor
  * @param p Pokemon
  * @param extraName string addition to Pokemon name 
  * @param extraHp 
  */
  public PokemonDecorator(Pokemon p, String extraName, int extraHp) {
    super(p.getName() + extraName, p.getHp() + extraHp, p.getMaxHP() + extraHp);
    pokemon = p;
  }
  /** 
  * @param atkType either Basic(1) or Special(2)
  * @return getAttackMenu
  */
  public String getAttackMenu(int atkType) {
    return pokemon.getAttackMenu(atkType);
  }
  /** 
  * @param atkType either Basic(1) or Special(2)
  * @return getNumAttackMenuItems
  */
  public int getNumAttackMenuItems(int atkType) {
    return pokemon.getNumAttackMenuItems(atkType);
  }
  /** 
  * @param atkType either Basic(1) or Special(2)
  * @return getAttackString
  */
  public String getAttackString(int atkType, int move) {
    return pokemon.getAttackString(atkType, move);
  }
  /** 
  * @param atkType either Basic(1) or Special(2)
  * @param move from attack menu slected by user
  * @return getAttackDamage
  */
  public int getAttackDamage(int atkType, int move) {
    return pokemon.getAttackDamage(atkType, move);
  }
  /** 
  * @param p Pokemon
  * @param atkType either Basic(1) or Special(2)
  */
  public double getAttackMultiplier(Pokemon p, int atkType) {
    return pokemon.getAttackMultiplier(p, atkType);
  }
  /** 
  * @param atkType either Basic(1) or Special(2)
  * @return getAttackBonus
  */
  public int getAttackBonus(int atkType) {
    return pokemon.getAttackBonus(atkType);
  }
  /** Returns Pokemon type Water,Fire or Grass
  * @return getType
  */
  public int getType() {
    return pokemon.getType();
  }

}