package edu.poly.tchao;

public class CharRange{
  
  public int count;
  public int left,right,top,down;

  public CharRange(int x,int y){
    left = 0;
    right = x;
    top = y;
    down = 0;
  }

  public void update(int x,int y){
    if(x < left){
      left = x;
    }else if(x > right){
      right = x;
    }

    if(y < top){
      top = y;
    }else if(y > down){
      down = y;
    }

    count += 1;
  }
}
