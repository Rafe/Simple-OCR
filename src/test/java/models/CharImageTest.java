import edu.poly.tchao.CharRange;
import edu.poly.tchao.CharImage;

import org.junit.*;
import static org.junit.Assert.*;
import java.util.*;
import java.lang.*;

public class CharImageTest{
  
  @Test 
  public void CharImage_should_create_sucessfully(){

    int[][] labeledImage = {{0,1,1,0},{0,1,1,1},{0,1,1,0}};
    int label = 1;
    CharRange range = new CharRange(1,3,0,2,7);
    CharImage image = new CharImage(label,labeledImage,range);

    assertEquals(image.imageMap[0][0],true);
    
  }

  @Test 
  public void getRatio_should_return_width_height_ratio(){
    int[][] labeledImage = {{0,1,0,0},{0,1,1,1},{0,1,1,0}};
    int label = 1;
    CharImage image = new CharImage(label,labeledImage);
    
    assertEquals(image.getRatio(),0.75,0.1);
  }

  @Test 
  public void getBlackRatio_should_return_ratio(){
    int[][] labeledImage = {{0,1,0,0},{0,1,1,1},{0,1,1,0}};
    int label = 1;
    CharImage image = new CharImage(label,labeledImage);
    
    assertEquals(image.getBlackRatio(),0.5,0.1);
  }

  @Test
  public void getHole_should_return_hole_value(){
    int[][] labeledImage = {{0,0,0,0,0},
                            {0,1,1,1,0},
                            {0,1,0,1,0},
                            {0,1,1,1,0},
                            {0,0,0,0,0}};
    int label = 1;
    CharImage image = new CharImage(label,labeledImage);
    assertEquals(image.getHole(),1);
  }

  @Test
  public void getHole_should_count_2_holes_correctly(){
    int[][] labeledImage = {{1,1,1,1,1},
                            {1,0,1,1,1},
                            {1,0,1,0,1},
                            {1,0,1,1,1},
                            {1,1,1,1,1}};
    int label = 1;
    CharImage image = new CharImage(label,labeledImage);
    assertEquals(image.getHole(),2);
  }

  @Test 
  public void getMaxHeight_should_return_max_height_ratio(){
    int[][] labeledImage = {{0,1,0,0,0},
                            {1,1,1,1,1},
                            {0,1,0,1,0},
                            {0,1,1,1,1},
                            {0,0,1,0,0}};
    int label = 1;
    CharImage image = new CharImage(label,labeledImage);
    assertEquals(image.getMaxHeight(),0.8,0.1);
  }

  @Test 
  public void getMaxWidth_should_return_max_width_ratio(){
    int[][] labeledImage = {{0,1,1,1,0},
                            {1,1,0,1,1},
                            {1,1,0,1,1},
                            {1,0,1,1,0},
                            {0,1,1,1,1}};
    int label = 1;
    CharImage image = new CharImage(label,labeledImage);
    assertEquals(image.getMaxWidth(),0.8,0.1);
  }

  @Test 
  public void paintBlack_should_paint_image_successfully(){
    boolean[][] map = {{false,false,false},
                       {true,true,false},
                       {false,false,false}};
    CharImage.paintBlack(map,0,0);
    assertTrue(map[0][0]);
    assertTrue(map[1][0]);
    assertTrue(map[0][2]);
    assertTrue(map[2][2]);
  }
}
