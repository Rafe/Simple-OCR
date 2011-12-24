package edu.poly.tchao;

import edu.poly.tchao.CharRange;

public class CharImage{
  public boolean[][] imageMap;
  public float ratio;
  public int x;
  public int y;
  public int hole;
  enum Direction { WEST,NORTH,EAST,SOUTH,CENTER}; 

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
    this.imageMap = new boolean[height][width];

    for(int h = range.top;h<=range.down;h++){
      for(int w = range.left;w<=range.right;w++){
        if(labeledImage[h][w] == label){
          imageMap[h-range.top][w-range.left] = true;
        }
      }
    }
  }

  public int getWidth(){
    return imageMap[0].length;
  }

  public int getHeight(){
    return imageMap.length;
  }

  public int[][] toImage(){
    int[][] image = new int[getHeight()][getWidth()];
    for(int h =0;h<getHeight();h++){
      for(int w=0;w<getWidth();w++){
        image[h][w] = imageMap[h][w] ? 1 : 0;
      }
    }
    return image;
  }

  public int getHole(){
    if(hole > -1){
      return hole;
    }

    boolean[][] map = imageMap.clone();

    paintBlack(map,0,0);

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

  public float getRatio(){
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

  public float getMaxHeight(){
    return 1;
  }

  public float getMaxWidth(){
    return 1;
  }

  public static void paintBlack(boolean[][] map,int h, int w, Direction d){
    if(map[h][w] == true){
      return;
    }
    map[h][w] = true;

    if(h - 1 >= 0 && d != Direction.SOUTH){
      paintBlack(map,h-1 , w, Direction.NORTH);
    }
    if(w + 1 < map[0].length && d != Direction.WEST){
      paintBlack(map,h, w + 1,Direction.EAST);
    }
    if(h + 1 < map.length && d != Direction.NORTH){
      paintBlack(map,h + 1 , w,Direction.SOUTH);
    }
    if(w - 1 >= 0 && d != Direction.EAST){
      paintBlack(map,h,w - 1,Direction.WEST);
    }
  }

  public static void paintBlack(boolean[][] map,int h, int w){
    paintBlack(map,h,w,Direction.CENTER);
  }


}
