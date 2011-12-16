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
  public void getRatio_should_return_ratio(){
    int[][] labeledImage = {{0,1,0,0},{0,1,1,1},{0,1,1,0}};
    int label = 1;
    CharImage image = new CharImage(label,labeledImage);
    
    assertEquals(image.getRatio(),0.5,0.1);
  }
}
