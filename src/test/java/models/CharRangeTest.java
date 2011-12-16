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
  public void CharRange_should_create_correctly(){
    assertEquals(range.count,1);
    assertEquals(range.left,100);
    assertEquals(range.right,100);
  }

  @Test 
  public void Update_should_update_range_properly(){
    range = new CharRange(50,50);
    assertEquals(range.left,50);
    assertEquals(range.right,50);
    assertEquals(range.top,50);
    assertEquals(range.down,50);
    range.update(60,60);
    assertEquals(range.count,2);
  }

  @Test public void update_2_points_should_form_a_range(){
    range = new CharRange(20,40);
    range.update(40,60);
    assertEquals(range.left,20);
    assertEquals(range.right,40);
    assertEquals(range.top,40);
    assertEquals(range.down,60);
  }

  @Test public void getWidth_should_return_width(){
    range = new CharRange(20,40);
    range.update(40,60);
    assertEquals(range.getWidth(),21);
    assertEquals(range.getHeight(),21);
  }

  @Test public void getWidth_should_return_one_for_one_point(){
    assertEquals(range.getWidth(),1);
    assertEquals(range.getHeight(),1);
  }
}
