public class Water extends Pokemon{
  /** class constructor 
  */
  public Water(String n, int h, int m){
    super(n,h,m);
  }

  /*
  * @return menu with the special attacks
  */
  public String getAttackMenu(int atkType){
    String r = "";
    if(atkType == 1){
      r += super.getAttackMenu(atkType);
    }
    if(atkType == 2){
      r += "1. Water Gun\n2. Bubble Beamt\n3. Water Fall\n";
    }
    return r;
  }

  /**
  * @return 3 for the number of items in special attack menu
  */
  public int getNumAttackMenuItems(int atkType){
    return 3;
  }

  /** Used to return the right attack name when implementing it back into
  * attack function.
  * @return string with the special attack used
  */
  public String getAttackString(int atkType, int move){
    String aString = "";

    if(atkType == 1){
      aString += super.getAttackString(atkType, move);
    }
    else if(atkType == 2){
      if(move == 1){
        aString += "WATER GUNNED";
      }
      if(move == 2){
        aString += "BUBBLE BEAMED";
      }
      if(move == 3){
        aString += "WATER FALLED";
      }
    }
    return aString;
  }
  
  /** Used to generate random amount of damage for each move
  * @return random damage depending on the move/special attack
  */
  public int getAttackDamage(int atkType, int move){
    int d = 0;
    if(atkType == 1){
      super.getAttackDamage(atkType,move);
    }
    else if(atkType == 2){
      if(move == 1){
        d = 2 + (int)(Math.random() * 5); //gen rand dmg from 2-5
        takeDamage(d);
      }
      if(move == 2){
        d = 1 + (int)(Math.random() * 3); //gen rand dmg from 1-3
        takeDamage(d);
      }
      if(move == 3){
        d = 1 + (int)(Math.random() * 4); //gen rand dmg from 1-4
        takeDamage(d);
      }
    }
    return d;
  }

  /** Gets attack multiplier based on the
  * oponent Pokemon type.
  * @param Pokemon p
  * @param atkType is the attack type choosen.
  * Basic or Special
  * @return attack multiplier
  */
  public double getAttackMultiplier(Pokemon p, int atkType){
    double aMultiplier = 1;
    aMultiplier = battleTable[this.getType()][p.getType()];
    return aMultiplier;
  }

}