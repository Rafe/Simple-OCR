import edu.poly.tchao.CharRange;

import org.junit.*;
import static org.junit.Assert.*;
import java.util.*;
import java.lang.*;

public class CharRangeTest{

  private CharRange range;

  @Before 
  public void createRange(){
    range = new CharRange(100,100);
  }

  @Test 
  public void Update_should_update_range_properly(){
    range.update(50,50);
    assertEquals(range.left,50);
    assertEquals(range.right,50);
    assertEquals(range.top,50);
    assertEquals(range.down,50);
    assertEquals(range.count,1);
  }

  @Test public void update_2_points_should_form_a_range(){
    range.update(20,40);
    range.update(40,60);
    assertEquals(range.left,20);
    assertEquals(range.right,40);
    assertEquals(range.top,40);
    assertEquals(range.down,60);
    
  }
}
