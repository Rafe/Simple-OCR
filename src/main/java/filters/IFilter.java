package edu.poly.tchao;

import java.awt.image.BufferedImage;

interface IFilter{
  public BufferedImage process(String file);
  public BufferedImage process(BufferedImage image); 
}
