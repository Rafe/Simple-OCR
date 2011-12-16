package edu.poly.tchao;

import edu.poly.tchao.CharRange;

public class CharImage{
  public boolean[][] imageMap;
  public float ratio;
  public int x;
  public int y;

  public CharImage(int label,int[][] labeledImage){
    this(label,labeledImage,
        new CharRange(0,labeledImage[0].length-1,0,labeledImage.length-1,1));
  }

  public CharImage(int label,int[][] labeledImage,CharRange range){
    int width = range.getWidth();
    int height = range.getHeight();
    ratio = 0;
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
}
