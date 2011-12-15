package edu.poly.tchao;

public class CharRange{
  
  public int count;
  public int left,right,top,down;

  public CharRange(int x,int y){
    left = x;
    right = 0;
    top = y;
    down = 0;
  }

  public void update(int x,int y){
    if(x < left){
      left = x;
    }

    if(x > right){
      right = x;
    }

    if(y < top){
      top = y;
    }

    if(y > down){
      down = y;
    }

    count += 1;
  }
}
