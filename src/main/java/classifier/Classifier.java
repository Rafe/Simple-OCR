package edu.poly.tchao;

import edu.poly.tchao.IClassifier;

public class Classifier implements IClassifier{
  public boolean process(CharImage image){
    if(image.getHole() == 1){
      //0 or 4
      //4
      if(areEquals(image.getMaxWidth(),1.0f,0.1f) && 
         areEquals(image.getMaxHeight(),1.0f,0.1f)){

        image.setCharactor('4');   

      } else if(areEquals(image.getMaxWidth(),0.44f,0.2f) && 
                areEquals(image.getMaxHeight(),0.85f,0.1f)){

        image.setCharactor('0');
      }

    }else if(image.getHole() == 0){
      //1,2,3
      //1 
      if(areEquals(image.getRatio(),2.6f,0.5f)){

        image.setCharactor('1');

      }else if(areEquals(image.getMaxWidth(),0.9f,0.1f) &&
               areEquals(image.getMaxHeight(),0.5f,0.1f)){

        image.setCharactor('2');

      }else if(areEquals(image.getMaxWidth(),0.75f,0.1f) &&
               areEquals(image.getMaxHeight(),0.45f,0.1f)){

        image.setCharactor('3');
      }
    }

    return image.getCharactor() == 'w' ? false : true;

  };

  public boolean areEquals(float ratio,float pattern, float range){
    if(ratio <= pattern + range && ratio >= pattern - range){
      return true;
    }
    return false;
  }
}
