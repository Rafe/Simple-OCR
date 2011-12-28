package edu.poly.tchao;

import java.util.*;
import java.lang.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.ImageIO;

class Application{

  private IFilter filter;
  private FileManager fileManager;
  //process all images under /images
  public static void main (String [] args){
      String[] files = {//"IMG_1.bmp","IMG_2.bmp","IMG_3.bmp"};
                        "IMG_4.bmp","IMG_5.bmp","IMG_6.bmp"};
                      //  "IMG_7.bmp","IMG_8.bmp","IMG_9.bmp"};
      Application app = new Application();
      for(String file:files){
        app.process(file);
      }
  }

  public Application(){
    filter = new ThresholdFilter();
  }

  //process images
  public void process(String file){

    fileManager = new FileManager();
    fileManager.setPath("images/output/");

    System.out.println("Processing :: " + file);
    BufferedImage source = filter.process(fileManager.open(file));

    //filter the image, remove noise
    source = filter.process(source);
      
    //labeling image
    Extractor extractor = new Extractor();

    //extract characters
    ArrayList<CharImage> list = extractor.extractCharImages(source);

    System.out.println(list.size() + " characters in image");
    
    IClassifier classifier = new Classifier();

    for(CharImage image : list){
      classifier.process(image);
      //image.print();
      image.dump();

      //save back to images/output/
      BufferedImage result = Extractor.mappingImage(image.toImage());
      fileManager.save(file,result);
    }
  }
}
