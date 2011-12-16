package edu.poly.tchao;

import edu.poly.tchao.IClassifier;

public class OneClassifier implements IClassifier{
  public boolean isValid(CharImage image){

    return image.getRatio() > 0.8 ? true : false;

  };
  public char getChar(){
    return '1';
  };
}
