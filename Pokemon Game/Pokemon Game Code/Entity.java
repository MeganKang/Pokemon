abstract class Entity{
  private String name;
  private int hp;
  private int maxHp;
  
  /**class constructor
  *@param n name of entity
  *@param h health of pokemon
  *@param m max health
  */
  public Entity(String n, int h, int m){
    this.name = n;
    this.hp = h;
    this.maxHp = m;

  }
  
  /** Returns entity hp
  * @return hp
  */
  public int getHp(){
    return hp;
  }
  
  /*
  * returns max health
  * @return maxHp the max health of the pokemon
  */
  public int getMaxHP(){
    return maxHp;
  }
  
  /** entity takes damage and subtracts it from their
  * total hp
  * @param d is amount of damage entity is taking
  */
  public void takeDamage(int d){
    if (hp > 0){
      hp -= d;
      if ((hp -= d) < 0){
        hp = 0;
      }
    }
    
  }

  /*
  * heals a pokemon to max health
  */
  public void heal(){
    hp = maxHp;
  }

  /*
  * returns name of pokemon or trainer
  * @return name of pokemon or trainer
  */
  public String getName(){
    return name;
  }

  /*
  * String representation of trainer or pokemon and their hp
  * @return toString() String representation
  */
  public String toString(){
    return "Name: " + name + " HP: " + hp +"/" + maxHp; 
  }
  

}