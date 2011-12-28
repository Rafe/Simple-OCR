package edu.poly.tchao;

import edu.poly.tchao.IClassifier;

public class Classifier implements IClassifier{

  //processing character image and set the result 
  public boolean process(CharImage image){

    //first, count hole to distinct 0,4
    if(image.getHole() == 1){
      //Using maximun width , height ratio to distinct 0 and 4
      if(areEquals(image.getMaxWidth(),1.0f,0.1f) && 
         areEquals(image.getMaxHeight(),1.0f,0.1f)){

        image.setCharactor('4');   

      } else if(areEquals(image.getMaxWidth(),0.44f,0.2f) && 
                areEquals(image.getMaxHeight(),0.85f,0.1f)){

        image.setCharactor('0');
      }

    }else{
      //distinct 1,2,3
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

    //if return false, means the image is rejected
    return image.getCharactor() == '*' ? false : true;

  };

  public boolean areEquals(float ratio,float pattern, float range){
    if(ratio <= pattern + range && ratio >= pattern - range){
      return true;
    }
    return false;
  }
}
