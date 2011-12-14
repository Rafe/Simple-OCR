package edu.poly.tchao;

import java.util.*;
import java.lang.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.ImageIO;
import java.util.HashMap;

public class Extractor{

  public static void main(String[] args){
    try{
      BufferedImage image = ImageIO.read(new File("images/output/IMG_1.bmp"));   
      int label = 0;
      HashMap equalTable = new HashMap();
      int width = image.getWidth();
      int height = image.getHeight();
      int LabeledImage[][] = new int[height][width]; 

      System.out.println("starting labeling...");
      for(int h = 1 ; h < height-1; h++){
        for(int w = 1 ; w < width-1; w++){
          int pixel = image.getRGB(w,h) & 0xff;    
          if(pixel == 0){
            int closeLabel =  getCloseLabel(LabeledImage,h,w);
            if(closeLabel > 0){
              LabeledImage[h][w] = closeLabel;
            }else{
              LabeledImage[h][w] = ++label; 
            }
          }
        }
      }

      //scan & set equalivalant
      for(int h=1;h<height-1;h++){
        for(int w=1;w<width-1;w++){
        }
      }

      BufferedImage result = new BufferedImage(width,height,BufferedImage.TYPE_BYTE_GRAY);
      for(int h = 0 ; h < height; h++){
        for(int w = 0 ; w < width; w++){
          if(LabeledImage[h][w] != 0){
            int pixel = (LabeledImage[h][w] * 15) % 255;
            int color = (pixel << 16) | (pixel << 8) | pixel;
            result.setRGB(w,h,color);
          }else{
            result.setRGB(w,h,0xffffff);
          }
        }
      }
      ImageIO.write(result,"bmp",new File("images/output/labeled.bmp"));
    }catch(Exception e){
      System.out.println(e.getMessage());
    }
  }

  public static int getCloseLabel(int[][] LabeledImage,int h,int w){
    if(LabeledImage[h-1][w-1] > 0){
      return LabeledImage[h-1][w-1];
    }else if(LabeledImage[h-1][w] > 0){
      return LabeledImage[h-1][w];
    }else if(LabeledImage[h-1][w+1]>0){
      return LabeledImage[h-1][w+1];
    }else if(LabeledImage[h][w-1]>0){
      return LabeledImage[h][w-1];
    }else{
      return 0;
    }
  }
}
