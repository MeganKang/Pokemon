abstract class Pokemon extends Entity{

   public static final double [][] battleTable = {{1, .5, 2}, {2, 1, .5}, {.5, 2, 1}};
   
    /** class constructor 
    * @param n name of Pokemon
    * @param h Pokemon hp between 20-25
    * @param m max Pokemon health
    */
   public Pokemon(String n, int h, int m){
     //(int)(20 + (int)(Math.random() * 25))
     super(n,h,m);
   }

   /** gets Basic or Special Attack Menu
    * @return attack menu Basic Attack or Special Attack
    */
   public String getAttackTypeMenu(){
     return "1. Basic Attack\n2. Special Attack";
   }

   /** Returns number of items in attack menu
   * @return 2 items 
   */
   public int getNumAttackTypeMenuItems(){
     return 2;
   }

   /** Returns an Attack Menu based on attack type basic or special.
   * @param atkType either Basic or Special
   * @return Attack Menu based on attack type choosen.
   */
   public String getAttackMenu(int atkType){

    return "1. Slam\n2. Tackle\n3. Punch\n";
   }

   /** Returns number of items in Basic attack menu
   * @return 3 items 
   */
   public int getNumAttackMenuItems(int atkType){
     return 3;
   }

   /** Attack description
   * @returns attack string description with the attack made by the pokemon and the damage it made
   */
   public String attack(Pokemon p, int atkType, int move){
     return p.getName() + " is " + this.getAttackString(atkType, move) + " by " + this.getName() + " and takes " + this.getAttackDamage(atkType, move) + " damage!\n";
   }
   
   /** Used to get correct attack name based on move and returns it to attack function.
   * @param atkType either Basic or Special
   * @param move slected by user
   * @retun string with selected attack name
   */
   public String getAttackString(int atkType, int move){
     String aString = "";
     if (atkType == 1){
       if (move == 1){
         aString += "SLAMMED";
       }
       if (move == 2){
         aString += "TACKLED";
       }
       if (move == 3){
         aString += "PUNCHED";
       }
     } 
     return aString;
   }

   /** Based on the move, random damage points are generated 
   * @param atkType either Basic or Special
   * @param move slected by user
   * @return d damage points
   */
   public int getAttackDamage(int atkType, int move){
     int d;

     //if(atkType == 1){
       if(move == 1){
         //Slam attack. Range 0-5
         d = 0 + (int)(Math.random() * 5);
         takeDamage(d);
       }
       else if(move == 2){
         //Tackle attack. Range 2-3
         d = 2 + (int)(Math.random() * 3);
         takeDamage(d);
       }
       else{
         //Punch attack. Range 1-4
         d = 1 + (int)(Math.random() * 4);
         takeDamage(d);
       }
     //}

     return d;
   }

   /** Returns attack multiplier. atkType 1 has no multiplier.
   * @param p Pokemon
   * @param atkType 
   * @return attack multiplier 
   */
   public double getAttackMultiplier(Pokemon p, int atkType){
     double aMultiplier = 1;
     //aMultiplier = battleTable[this.getType()][p.getType()];
     return aMultiplier;
   }

   /**
    * @param atktype
    * @returns attack bonus that decreases attack by 1 for
    the attack type
    */
   public int getAttackBonus(int atkType){
     return 0;
   }

   /** returns Pokemon type
    * @return Pokemon type. Fire, Water or Grass
    */
   public int getType(){

     int type;
     //  if( this instanceof Charmander || this instanceof Ponyta)
     if(this instanceof Fire){
       type = 0; //Fire
     }
     //  else if( this instanceof Squirtle || this instanceof Staryu)
     else if(this instanceof Water){
       type = 1; // Water
     }
     else{
       type = 2; //Grass
     }
     return type;
   }



}