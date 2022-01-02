/**
 * The simulation takes place here.
 * 
 * @author Emiliano 
 * @version 18/06/2019
 */
import java.awt.Color;
import java.awt.Font;
public class Simulation
{

  public Simulation()
  {

  }
  int xSize = 600;
  int ySize = 600;
  double xSize1 = 20;
  double ySize1 = 20;
  int rows = 50;
  int columns = 50;
  int grid[][] = new int[rows][columns];
  int neighbours[][] = new int[rows][columns];
  double cellSize = xSize1 / (double)rows;
  public void simulate()
  {  
    StdDraw.setCanvasSize(xSize,ySize);
    StdDraw.setXscale(-10,10);
    StdDraw.setYscale(-10,10);
    StdDraw.enableDoubleBuffering();
    Font font2 = new Font("Arial", Font.BOLD, 20);
    StdDraw.setFont(font2);
    for(int i= 0; i < columns; i++){
        for(int e = 0; e < rows; e++){
            if(Math.random()<.3){
                grid[i][e] = 1;
            }
        } 
    }
    

    
    //Count neighbours

    
    
    final long timeInterval = 100;
    Runnable runnable = new Runnable() {   
      public void run() {
        while (true) {
          changeGrid();
          
          try {
           Thread.sleep(timeInterval);
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
        }
      }
    };
    
    Thread thread = new Thread(runnable);
    thread.start();
  }
  public void changeGrid(){
      countNeighbours();
      fillNewValues();
      paint();
  }
  public void fillNewValues(){
      /*for(int i = 0; i < columns; i++){
          for(int e = 0; e < rows; e++){
              grid[i][e] = 0;
          }
      }*/
      for(int i = 0; i < columns; i++){
          for(int e = 0; e < rows; e++){
              if(neighbours[i][e] <= 1){
                  grid[i][e] = 0;
              }
              if(neighbours[i][e] > 3){
                  grid[i][e] = 0;
              }
              if(neighbours[i][e] == 3){
                  grid[i][e] = 1;
              } 
              
          }
      }
  }
  public void countNeighbours(){
      boolean up,down,right,left,uright,uleft,dright,dleft;
      for(int i = 0; i < columns; i++){
          for(int e = 0; e < rows; e++){
              neighbours[i][e] = 0;
          }
      }
      for(int i = 0; i < columns; i++){
          for(int e = 0; e < rows; e++){
              up = down = right = left = uright = uleft = dright = dleft = true;
              if(i == 0){
                  left = false;
                  uleft = false;
                  dleft = false;
              }
              if(i == columns-1){
                  right = false;
                  uright = false;
                  dright = false;
              }
              if(e == 0){
                  up = false;
                  uleft = false;
                  uright = false;
              }
              if(e == columns-1){
                  down = false;
                  dright = false;
                  dleft = false;
              }
              if(up){
                  if(grid[i][e-1] == 1){
                      neighbours[i][e]++;
                  }
              }
              if(down){
                  if(grid[i][e+1] == 1){
                      neighbours[i][e]++;
                  }
              }
              if(right){
                  if(grid[i+1][e] == 1){
                      neighbours[i][e]++;
                  }
              }
              if(left){
                  if(grid[i-1][e] == 1){
                      neighbours[i][e]++;
                  }
              }
              if(uright){
                  if(grid[i+1][e-1] == 1){
                      neighbours[i][e]++;
                  }
              }
              if(uleft){
                  if(grid[i-1][e-1] == 1){
                      neighbours[i][e]++;
                  }
              }
              if(dright){
                  if(grid[i+1][e+1] == 1){
                      neighbours[i][e]++;
                  }
              }
              if(dleft){
                 if(grid[i-1][e+1] == 1){
                      neighbours[i][e]++;
                  } 
              }
          }
      }
  }
  public void paint(){
      //Draw lines
      double xx = columns;
      double yy = rows;
      double increaseX = xSize1 / xx;
      double increaseY = ySize1 / yy;
      double initialX = -(xSize1/2);
      double initialY = -(ySize1/2);
      StdDraw.clear();
      for(int i = 1; i <= columns; i++){
          StdDraw.line(initialX+increaseX*i,(ySize1/2),initialX+increaseX*i,-(ySize1/2));
      }
      for(int i = 1; i <= rows; i++){
          StdDraw.line((xSize1/2),initialY+increaseY*i,-(xSize1/2),initialY+increaseY*i);
      }
      initialY *= -1;
      for(int i = 0; i < columns; i++){
          for(int e = 0; e < rows; e++){
              if(grid[i][e] == 1){
                  StdDraw.filledSquare((initialX+increaseX*i)+(cellSize/2),(initialY-increaseY*e)-(cellSize/2),cellSize/2);
              }
          }
      }
      StdDraw.show();
  }
}
