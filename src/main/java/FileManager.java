package edu.poly.tchao;

import java.util.*;
import java.lang.*;
import java.io.*;
import java.awt.image.*;
import javax.imageio.ImageIO;

public class FileManager{

  private String path;
  private String filePath;
  private int count;

  public FileManager(){
    this.count = 1;
    filePath = "images/";
  }

  public void setPath(String path){
    this.path = path;
  }

  public BufferedImage open(String filename){
    try{
      return ImageIO.read(new File(filePath+filename));   
    }catch(IOException e){
      e.printStackTrace();
    } 
    return null;
  }

  public void save(String filename,BufferedImage image){
    try{
      String[] name = filename.split("[.]");
      boolean success = ImageIO.write(image,"bmp",new File(path+name[0]+"labeled_"+count+"."+name[1]));
      if(!success){
        throw new IOException("Output format error when Saving Image");
      }
      count += 1;
    }catch(Exception e){
      e.printStackTrace();
    }
  }
}
