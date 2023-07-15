import java.io.File;
import java.io.FileNotFoundException;
import java.lang.ArrayIndexOutOfBoundsException;
import java.util.Arrays;
import java.util.Scanner;
import java.awt.Point;

public class Map{
  private char [][] map;
  private boolean [][] revealed;
  private static Map instance = null;
  /** class constructor  
  */
  private Map(){
    int i = 5;
    int j = 5;
    map = new char[i][j];
    revealed = new boolean[i][j];
    for(i=0; i<5; i++) {
      for(j=0; j<5; j++) {
        revealed[i][j] = false;
      }
    }
  }
  public static Map getInstance(){
    if(instance == null){
      instance = new Map();
    }
    return instance;
  }
  /**
  * Reads file and load into a 2D array
  *@param mapNum number of which Area
  *@throws FileNotFoundException
  */
  public void loadMap(int mapNum) throws FileNotFoundException{
    try{
    String filename = "Area" + mapNum + ".txt";
    File fileIn = new File(filename);
    Scanner fileReader = new Scanner(fileIn);
      for (int i=0; i<5; i++){
        for (int j =0; j<5; j++){
          String currentChar = fileReader.next();
          
          map[i][j] = currentChar.charAt(0);
        }
        
        
      }
    fileReader.close();
    } catch(FileNotFoundException e){
      throw new FileNotFoundException("Error.");
    }
    
  }
  /**
  *Get character at the location in map
  *@param p 
  *@return x and y of point where character is
  */
  public char getCharAtLoc(Point p){
    if((p.x < 0 || p.x >= 5) || (p.y < 0 || p.y >= 5)){
      System.out.println("Out of Bounds.");
    }
    return map[p.x][p.y];
  }
  /**
  *Display the map
  *@param p
  *@return string of the map
  */
  public String mapToString(Point p){
    String mapString = "";
    for(int i = 0; i<5; i++){
      for(int j =0; j<5; j++){
        if(p.x == i && p.y == j){
          char ch = '*';
          mapString += ch;

        }
        else if (revealed[i][j] == true){
          char ch = map[i][j];
          mapString += ch;

        }
        else{
          char ch = 'x';
          mapString += ch;

        }
      }
      mapString += "\n";
    }
    return mapString;
  }
  /**
  *Find the start of the map
  *@return point in which the start of the map is
  */
  public Point findStart(){
    Point p = new Point();
    for(int i = 0; i < 5; i++){
      for(int j = 0; j < 5; j++){
        
        if (map[i][j] == 's' ){
          
          p.setLocation(i, j);
        }
      }
    }
    return p;
  }
  /**
  *Reveals a point on the map
  *@param p the point being revealed
  */
  public void reveal(Point p){
    revealed[p.x][p.y] = true;
  }
  /**
  *Remove the character in the location and replace with n
  *@param p point that is removed
  */
  public void removeCharAtLoc(Point p){
    map[p.x][p.y] = 'n';
  }

}