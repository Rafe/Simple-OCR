package edu.poly.tchao;

import edu.poly.tchao.IClassifier;

public class Classifier implements IClassifier{

  //processing character image and set the result 
  public boolean process(CharImage image){

    //first, count hole to distinct 0,4,6,8,9
    if(image.getHole() == 1){
      //Using maximun width , height ratio to distinct 0 and 4
      if(areEquals(image.getMaxWidth(),1.0f,0.1f) && 
         areEquals(image.getMaxHeight(),1.0f,0.1f)){

        image.setCharactor('4');   
      } else if(areEquals(image.getMaxWidth(),0.44f,0.2f) && 
                areEquals(image.getMaxHeight(),0.85f,0.1f)){
        image.setCharactor('0');
      } else if(areEquals(image.getMaxWidth(),0.85f,0.3f) && 
                areEquals(image.getMaxHeight(),0.76f,0.3f)){

        //using center point to distinct 6,9
        if(areEquals(image.getCenter().y,0.44f,0.03f)){

          image.setCharactor('9');
           
        }else if(areEquals(image.getCenter().y,0.55f,0.03f)){
        
          image.setCharactor('6');
        }
      }
    }if(image.getHole() == 2){ // 8 have two holes

      image.setCharactor('8');

    }else{
      if(areEquals(image.getRatio(),2.6f,0.5f)){
        // 1 have different ratio
        image.setCharactor('1');
      //2,3,5,7
      }else if(areEquals(image.getMaxWidth(),0.88f,0.05f) &&
               areEquals(image.getMaxHeight(),0.53f,0.05f) &&
               areEquals(image.getRatio(),1.53f,0.07f) &&
               areEquals(image.getCenter().x,0.54f,0.02f)){

        image.setCharactor('2');

      }else if(areEquals(image.getMaxWidth(),0.75f,0.05f) &&
               areEquals(image.getRatio(),1.75f,0.11f) &&
               areEquals(image.getCenter().x,0.63f,0.04f)){

        image.setCharactor('3');

      }else if(areEquals(image.getMaxWidth(),0.83f,0.03f) &&
               areEquals(image.getMaxHeight(),0.50f,0.03f) &&
               areEquals(image.getRatio(),1.84f,0.05f) &&
               areEquals(image.getCenter().x,0.52f,0.05f) &&
               areEquals(image.getCenter().y,0.47f,0.05f)){

        image.setCharactor('5');

      }else if(areEquals(image.getMaxWidth(),0.87f,0.03f) &&
               areEquals(image.getMaxHeight(),0.53f,0.03f) &&
               areEquals(image.getRatio(),1.58f,0.05f) &&
               areEquals(image.getCenter().x,0.58f,0.05f) &&
               areEquals(image.getCenter().y,0.33f,0.05f)){

        image.setCharactor('7');
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
