public class AttackDown extends PokemonDecorator{

/** class constructor 
  * @param p Pokemon
  */
public AttackDown(Pokemon p){
  super(p, " -ATK", 0);
}
/**
  * @param atktype
  * @returns attack bonus that decreases attack by 1 for
    the attack type
*/
public int getAttackBonus(int atkType){
  return super.getAttackBonus(atkType) - 1;
}

}