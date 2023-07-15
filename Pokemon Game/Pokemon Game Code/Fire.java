public class Fire extends Pokemon{
  
  /** class constructor 
  */
  public Fire(String n, int h, int m){
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
      r += "1. Ember\n2. Fire Blast\n3. Fire Punch\n";
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
        aString += "EMBERED";
      }
      if(move == 2){
        aString += "FIRE BLASTED";
      }
      if(move == 3){
        aString += "FIRE PUNCHED";
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
        d = 0 + (int)(Math.random() * 4); //gen rand dmg from 0-4
        takeDamage(d);
      }
      if(move == 2){
        d = 2 + (int)(Math.random() * 5); //gen rand dmg from 2-5
        takeDamage(d);
      }
      if(move == 3){
        d = 0 + (int)(Math.random() * 4); //gen rand dmg from 0-4
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