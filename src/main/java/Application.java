package edu.poly.tchao;

import java.util.*;
import java.lang.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.ImageIO;

class Application{

  //process all images under /images
  public static void main (String [] args){
      String[] files = {"IMG_1.bmp","IMG_2.bmp","IMG_3.bmp",
                        "IMG_4.bmp","IMG_5.bmp","IMG_6.bmp"};
      for(String file:files){
        process(file);
      }
  }

  //process images
  public static void process(String file){
    try{
      ThresholdFilter thresholdFilter = new ThresholdFilter();
      Extractor extractor = new Extractor();


      //filter the image, remove noise
      BufferedImage source = thresholdFilter.process(file);
        
      //labeling image
      int labeledImage[][] = Extractor.labeling(source);

      //extract character image from labeledImage, to a list of CharImage
      ArrayList<CharImage> list = Extractor.extractImages(labeledImage);
      System.out.println(list.size() + " characters in image");
      
      IClassifier classifier = new Classifier();

      int n = 1;
      for(CharImage image : list){
        classifier.process(image);
        //image.print();
        image.dump();

        //save back to images/output/
        BufferedImage result = Extractor.mappingImage(image.toImage());
        ImageIO.write(result,"bmp",new File("images/output/"+file+"labeled"+n+".bmp"));
        n += 1;
      }
    }catch(Exception e){
      e.printStackTrace();
    }
  }
}
