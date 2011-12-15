package edu.poly.tchao;

import java.awt.image.BufferedImage;

interface IFilter{
  public void process(BufferedImage image,BufferedImage result, int x, int y);
}
