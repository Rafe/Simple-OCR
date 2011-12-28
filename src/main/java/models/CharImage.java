package edu.poly.tchao;

import edu.poly.tchao.CharRange;

import java.util.*;

//Extracted character image for classify
public class CharImage{
  public boolean[][] imageMap;
  public float ratio;
  public int x;
  public int y;
  public int hole;
  public int maxHeight;
  public int maxWidth;
  private char charactor;

  public CharImage(int label,int[][] labeledImage){
    this(label,labeledImage,
        new CharRange(0,labeledImage[0].length-1,0,labeledImage.length-1,1));
  }

  public CharImage(int label,int[][] labeledImage,CharRange range){
    int width = range.getWidth();
    int height = range.getHeight();
    ratio = 0;
    hole = -1;
    x = range.left;
    y = range.top;
    charactor = '*';
    maxHeight = 0;
    maxWidth = 0;
    this.imageMap = new boolean[height][width];

    //copy the labeledImage to bit imageMap
    for(int h = range.top;h<=range.down;h++){
      for(int w = range.left;w<=range.right;w++){
        if(labeledImage[h][w] == label){
          imageMap[h-range.top][w-range.left] = true;
        }
      }
    }
  }

  public void setCharactor(char w){
    charactor = w;
  }

  public char getCharactor(){
    return charactor;
  }

  public int getWidth(){
    return imageMap[0].length;
  }

  public int getHeight(){
    return imageMap.length;
  }

  //Transform to BufferedImage for output
  public int[][] toImage(){
    int[][] image = new int[getHeight()][getWidth()];
    for(int h =0;h<getHeight();h++){
      for(int w=0;w<getWidth();w++){
        image[h][w] = imageMap[h][w] ? 1 : 0;
      }
    }
    return image;
  }

  //hole counting
  public int getHole(){
    if(hole > -1){
      return hole;
    }

    boolean[][] map = copyArray(imageMap);

    //first, paint the background to black
    paintBlack(map,0,0);
    paintBlack(map,0,getWidth()-1);
    paintBlack(map,getHeight()-1,0);
    paintBlack(map,getHeight()-1,getWidth()-1);

    //then, start counting
    int e = 0;
    int i = 0;

    for (int h = 0; h < getHeight() - 1 ; h++) {
      for (int w = 0; w < getWidth() - 1 ; w++) {
        int black = 0;
        if(map[h][w]) black += 1;
        if(map[h+1][w]) black += 1;
        if(map[h][w+1]) black += 1;
        if(map[h+1][w+1]) black += 1;
        if(black == 3){
          e += 1;
        }else if(black == 1){
          i += 1;
        }
      }
    }
    hole = (e-i)/4;
    return hole;
  }

  //black / white ratio of image
  public float getBlackRatio(){
    if(ratio > 0){
      return ratio;
    }
    float blackCount = 0;
    for(int h=0; h<getHeight();h++){
      for(int w=0; w<getWidth();w++){
        if(imageMap[h][w]){
          blackCount += 1;
        } 
      }
    }
    return blackCount / (getWidth() * getHeight());
  }

  //width/height ratio
  public float getRatio(){
    return (float) getHeight() / (float) getWidth();
  }

  // maximun height line / height ratio
  public float getMaxHeight(){
    for(int w = 0; w < getWidth();w++){
      int maxInLine = 0;
      int line = 0;
      for (int h = 0; h < getHeight(); h++) {
        if(imageMap[h][w] == true){
          line += 1;
        }else{
          maxInLine = Math.max(line,maxInLine);
          line = 0;
        }
      }
      maxInLine = Math.max(line,maxInLine);
      maxHeight = Math.max(maxHeight,maxInLine);
    }
    //return maxHeight;
    return (float) maxHeight / (float) getHeight();
  }

  // maximun width line / width ratio
  public float getMaxWidth(){
    for (int h = 0; h < getHeight(); h++) {
      int maxInLine = 0;
      int line = 0;
      for(int w = 0; w < getWidth(); w++){
        if(imageMap[h][w]){
          line += 1;
        }else{
          maxInLine = Math.max(line,maxInLine);
          line = 0;
        }
      }
      maxInLine = Math.max(line,maxInLine);
      maxWidth = Math.max(maxWidth,maxInLine);
    }
    //return maxWidth;
    return (float) maxWidth / (float) getWidth();
  }

  // method for painting background to black
  public static void paintBlack(boolean[][] map,int h, int w){

    LinkedList<Point> queue = new LinkedList<Point>();
    if(map[h][w] == true){
      return;
    }
    queue.add(new Point(h,w));
    while(queue.size() > 0){
      Point p = queue.removeFirst();
      if(!map[p.h][p.w]){
        map[p.h][p.w] = true;
        if(p.h - 1 >= 0){
          queue.add(new Point(p.h-1,p.w));
        }
        if(p.h + 1 < map.length){
          queue.add(new Point(p.h+1,p.w));
        }
        if(p.w + 1 < map[0].length){
          queue.add(new Point(p.h,p.w+1));
        }
        if(p.w - 1 >= 0){
          queue.add(new Point(p.h,p.w-1));
        }
      }
    }
  }

  //output character
  public void print(){
    if(this.charactor == '*'){
      System.out.println("Reject character in ( "+this.x+","+this.y+ ")");
    }else{
      System.out.println("Found character "+ this.charactor +" in ( "+this.x+","+this.y+ ")");
    }
  }

  public void dump(){
    print();
    System.out.println("hole:" + getHole());
    System.out.print("Height:" + getHeight());
    System.out.println(", Width:" + getWidth());
    System.out.print("MaxHeight:" + getMaxHeight());
    System.out.println(", MaxWidth:" + getMaxWidth());
    System.out.print("Ratio:" + getRatio());
    System.out.println(", BlackRatio:" + getBlackRatio());
    System.out.println("");
  }

  public static boolean[][] copyArray(boolean[][] array){
    boolean[][] n = new boolean[array.length][array[0].length];
    for(int h = 0 ; h < array.length ; h++){
      for( int w = 0; w < array[0].length ; w++){
        n[h][w] = array[h][w];
      }
    }
    return n;
  }
}

class Point{
  public int h;
  public int w;
  public Point(int h,int w){
    this.h = h;
    this.w = w;
  }
}
