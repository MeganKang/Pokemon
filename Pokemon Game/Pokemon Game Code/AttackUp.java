import java.util.Random;
public class AttackUp extends PokemonDecorator{

/** class constructor 
  * @param p Pokemon
  */
public AttackUp(Pokemon p){
  super(p, " +ATK", 0);
}

/**
  * @param atktype
  * @returns attack bonus that increases attack by 1 or 2 for
    the attack type
*/
public int getAttackBonus(int atkType){
  return super.getAttackBonus(atkType) + (int) Math.random() * 2 + 1;
}

}