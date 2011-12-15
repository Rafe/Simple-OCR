import edu.poly.tchao.Extractor;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.*;
import java.lang.*;

public class ExtractorTest{
  @Test
  public void findEqualivalant_should_return_smallest_equal(){
    HashMap<Integer,Integer> map = new HashMap<Integer,Integer>();
    map.put(5,4);
    map.put(4,3);
    map.put(3,2);
    map.put(2,1);
    map.put(1,0);
    assertTrue(Extractor.findEqualivalant(5,map) == 0);
  }

}
