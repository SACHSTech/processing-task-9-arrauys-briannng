import processing.core.PApplet;

public class Sketch extends PApplet {
  // declaring variables

  // snow variables
	float[] circleY = new float[15];
  float[] circleX = new float[15];
  float circleWidth = 50;
  float circleHeight = 50;
  int snowSpeed = 3;
  boolean[] ballHideStatus = new boolean[70];
  boolean clickSnow = false;
    
  // player variables
  boolean PlayerUp;
  boolean PlayerLeft;
  boolean PlayerDown;
  boolean PlayerRight;
  float PlayerX = 0;
  float PlayerY = 380;
  float PlayerWidth = circleWidth - 10;
  float PlayerHeight = circleHeight - 10;
  int intPlayerLives = 3;

  // game variable
  int intGameOver = 0;
	
  /**
   * Called once at the beginning of execution, put your size all in this method
   */
  public void settings() {
	// put your size call here
    size(400, 400);
  }

  /** 
   * Called once at the beginning of execution.  Add initial set up
   * values here i.e background, stroke, fill etc.
   */
  public void setup() {
    for(int i = 0; i < circleY.length; i++){
      circleY[i] = random(height);
      circleX[i] = random(40, 400);
      ballHideStatus[i] = true;
      }
  }

  /**
   * Called repeatedly, anything drawn to the screen goes here
   */
  public void draw() {
    // if out of lives
    if(intGameOver != 0){
      background(255);
      fill(0);
      text("GAME OVER", 200, 200);
    }
    // when game starts  
    else if(intGameOver == 0){
      background(50);
      Player();
      snowFall();
      }
  }
  
  // define other methods down here.
  /**
  * drawing the snowfall and adding level 3 and level 4 requirements
  */
  public void snowFall(){
    // snow fall
    for (int i = 0; i < circleY.length; i++) {
      if(ballHideStatus[i]){
        fill(255);
        ellipse(circleX[i], circleY[i], circleWidth, circleHeight);
        }
      
      circleY[i] += snowSpeed;
      
      if (circleY[i] > height) {
        circleY[i] = 0;
        }
    
    // Collision between blue circle and snowballs
    if(dist(PlayerX, PlayerY, circleX[i], circleY[i]) <= 30 && (ballHideStatus[i])){
      intPlayerLives -= 1;
      ballHideStatus[i] = false;
      }
    // Mouse interplay with snowballs
    if(clickSnow && dist(mouseX, mouseY, circleX[i], circleY[i]) <= 15){
      ballHideStatus[i] = false;
      }
    }
    
  }
  
  /**
  * draw lives and blue player
  */
  public void Player(){
    // drawing blue circle and lives
    for (int i = 1; i <= intPlayerLives; i++){
      fill(255, 0, 0);
      // lives
      rect ((i + 8) * 30, 15, 30, 30);
      }
    fill(0, 0, 255);
    ellipse(PlayerX, PlayerY, PlayerWidth, PlayerHeight);
  
    if(intPlayerLives == 0){
      intGameOver = 3;
    }
    
    // moving blue circle
    if (PlayerUp){
      PlayerY -= 3;
    }
    if (PlayerDown){
      PlayerY += 3;
    }
    if (PlayerLeft){   
      PlayerX -= 3;
    }
    if (PlayerRight){
      PlayerX += 3;
    }
  }
  
  /**
  * pressing snow
  */
  public void mousePressed(){
    clickSnow = true;
  }
  
  /**
  * releasing snow
  */
  public void mouseReleased(){
    clickSnow = false;
  }
   /**
  * slowing or accelarating snowfall
  */
  public void keyPressed() {
    // changing speed of snow
    if (keyCode == DOWN) {
      snowSpeed = 1;
    }
    if (keyCode == UP) {
      snowSpeed = 4;
    }
    // moving blue circle
    if(key == 'w'){
      PlayerUp = true;
    if(key == 'a'){
      PlayerLeft = true;
      }
    if(key == 's'){
      PlayerDown = true;
      }
    if(key == 'd'){
      PlayerRight = true;
      }
      }
  }
  /*
  * movement of player
  */
  public void keyReleased() {
    snowSpeed = 3;
      if (key == 'w'){
      PlayerUp = false;
    if (key == 'a'){
      PlayerLeft = false;
      }
    if (key == 's'){
      PlayerDown = false;
      }
    if (key == 'd'){
      PlayerRight = false;
      }
    }
  }
}