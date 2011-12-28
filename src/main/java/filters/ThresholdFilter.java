/**
 * @author Te-Chun Chao
 * @usage java Thresholder [file1] [file2] [file3] ...
 */
package edu.poly.tchao;

import java.util.*;
import java.lang.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.ImageIO;

public class ThresholdFilter implements IFilter{

  /** Transform image to mono image, pixel under threshold will be set to 0,
   * pixel over the threshold will be set to grayValue
   * @param image source image of Transform
   * @param threshold the threshold gray level value for Transform
   * @param grayValue the gray value to be set for pixel over threshold
   * @return BufferedImage as Transformed image
   */

  public static final int THRESHOLD = 120;
  public BufferedImage thresholdTransform(BufferedImage image,double threshold, int grayValue){
    if(image == null){
      return null;
    }

    int width = image.getWidth();
    int height = image.getHeight();

    //calculate color as RGB integer
    int color = (grayValue << 16) | (grayValue << 8) | grayValue;

    BufferedImage result = new BufferedImage(width,height,BufferedImage.TYPE_BYTE_GRAY);
    for(int h=0;h<height;h++){
      for(int w=0;w<width;w++){

        int pixel = image.getRGB(w,h) & 0xff;
        if(pixel >= threshold){
          //set to background color
          result.setRGB(w,h,color);
        }else{
          result.setRGB(w,h,0);
          //splash 1.5 pixels
          if(w>1 && w<width-1 && h>1 && h<height-1){
            result.setRGB(w-1,h,0);
            result.setRGB(w,h-1,0);
            result.setRGB(w+1,h,0);
            result.setRGB(w,h+1,0);
            result.setRGB(w-1,h-1,0);
            result.setRGB(w-1,h+1,0);
            result.setRGB(w+1,h-1,0);
            result.setRGB(w+1,h+1,0);
            result.setRGB(w+2,h,0);
            result.setRGB(w-2,h,0);
            result.setRGB(w,h-2,0);
            result.setRGB(w,h+2,0);
          }
        }
      }
    }
    return result;
  }

  /** Save image to file
   * @param image image to be saved
   * @param fileName name for the new image
   */
  public void saveImage(BufferedImage image,String fileName){
    try{
      boolean r = ImageIO.write(image,"bmp",new File(fileName));
      if(!r){
        throw new IOException("Output format error when Saving Image");
      }
    }catch(IOException e){
      System.out.println(e.getMessage());
    }
  }

  public BufferedImage process(String file){
    try{
      System.out.println("processing::" + file);
      BufferedImage image = ImageIO.read(new File("images/"+file));   
      return process(image);

    }catch(IOException e){
      System.err.println(e.getMessage());
    }catch(IllegalArgumentException e){
      System.err.println(e.getMessage());
    }
    return null;
  }

  public BufferedImage process(BufferedImage image){
    return thresholdTransform(image,THRESHOLD,255);
  }

  public ThresholdFilter(){
  
  }
}
