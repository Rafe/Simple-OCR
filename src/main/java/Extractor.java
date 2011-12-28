package edu.poly.tchao;

import java.util.*;
import java.lang.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.ImageIO;
import java.util.HashMap;

public class Extractor{
  public static final int BLACK = 0;
  public int[][] labeledImage;
  public HashMap<Integer,Integer> equalTable;

  public Extractor(){
    equalTable = new HashMap<Integer,Integer>();
  }

  public int[][] labeling(BufferedImage image){
      int label = 0;
      int width = image.getWidth();
      int height = image.getHeight();
      labeledImage = new int[height][width]; 

      //start labeling
      for(int h = 1 ; h < height-1; h++){
        for(int w = 1 ; w < width-1; w++){
          int pixel = image.getRGB(w,h) & 0xff;    
          if(pixel == BLACK){
            int minLabel = getMinLabel(h,w);
            if(minLabel > 0){
              labeledImage[h][w] = minLabel;
            }else{
              label += 1;
              labeledImage[h][w] = label; 
            }
          }
        }
      }

      //reset label base on equalivalant
      for(int h=1;h<height-1;h++){
        for(int w=1;w<width-1;w++){
          if(equalTable.containsKey(labeledImage[h][w])){
             labeledImage[h][w] = findEqualivalant(labeledImage[h][w]);
          }
        }
      }
      return labeledImage;
  }

  public int findEqualivalant(int value){
    if(!equalTable.containsKey(value)){
      return value;
    }else{
      int equal = equalTable.get(value);
      return equal < value ? findEqualivalant(equalTable.get(value)) : value;
    }
  }

  public int getMinLabel(int h,int w){
    int label = Integer.MAX_VALUE;
    int min = 0;

    /*  check  OOO
     *         OX      get minimum value for labeling
     *                 also check if there is smaller value for equalTable
     */
    for(int i = -1; i<2; i++){
      if(labeledImage[h-1][w+i] > 0){
        min = Math.min(label,labeledImage[h-1][w+i]);
        if(min < label){
          if(!equalTable.containsKey(label)){
            equalTable.put(label,min);
          }
          label = min;
        }
      }
    }

    if(labeledImage[h][w-1] > 0){
      min = Math.min(label,labeledImage[h][w-1]);
      if(min < label){
        if(!equalTable.containsKey(label)){
          equalTable.put(label,min);
        }
        label = min;
      }
    }

    return label == Integer.MAX_VALUE ? 0 : label;
  }

  public static BufferedImage mappingImage(int[][] image){
    int width = image[0].length;
    int height = image.length;

    BufferedImage result = new BufferedImage(width,height,BufferedImage.TYPE_BYTE_GRAY);
    for(int h = 0 ; h < height; h++){
      for(int w = 0 ; w < width; w++){
        if(image[h][w] != 0){
          int pixel = (image[h][w] * 15) % 255;
          int color = (pixel << 16) | (pixel << 8) | pixel;
          result.setRGB(w,h,color);
        }else{
          result.setRGB(w,h,0xffffff);
        }
      }
    }
    return result;
  }

  public ArrayList<CharImage> extractCharImages(BufferedImage source){

    //labeling image to this.labeledImage
    labeling(source);

    int width = labeledImage[0].length;
    int height = labeledImage.length;

    HashMap<Integer,CharRange> map = new HashMap<Integer,CharRange>();
    ArrayList<CharImage> result = new ArrayList<CharImage>();

    //count charactor range
    for(int h = 0; h < height; h++){
      for(int w = 0; w < width; w++){

        int key = labeledImage[h][w];

        if(!map.containsKey(key)){
          CharRange range = new CharRange(w,h);
          map.put(key,range);
        }else{
          map.get(key).update(w,h);
        }
      }
    }

    //crop charactor
    Integer[] keys = new Integer[map.keySet().size()]; 
    map.keySet().toArray(keys);
    Arrays.sort(keys);
    for(Integer key : keys){
      CharRange range = map.get(key);
      if(range.isValid()){
        result.add(new CharImage(key,labeledImage,range));
      }
    }
    return result;
  }
}
