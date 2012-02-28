#Simple Optical Character Recongnition

Derived from Computer Vision Class Project

##How to run the project?

clone the project, run 

    java -jar App.jar 

To process images from IMG_1.bmp to IMG_6.bmp , IMG_8.bmp 

You can also install buildr to run other tests and tasks,
On Ruby installed environment, type

    gem install buildr

to install buildr, then set the java path of buildr and run:

    buildr 

Can execute the project

For running test, type 

    buildr test 

To test is IMG_1 to IMG 8 is matching.

##Program Structures


    /src
      /main
        /classifier
          Classifier.java   -- main classifier for detecting character from minimun character "box" 
        /filters
          IFilter.java      -- filter interface
          ThresholdFilter.java -- filter using threshold to delete the background and noises.
        /models
          CharImage.java    -- Extracted minimum character box, also include the feature logics
          CharRange.java    -- Use for detect position and update the box range of a character.
          Point.java        -- data model for point 
          FloatPoint.java   -- data model for float x,y (for ratio calculation)
        Application.java    -- Entry point for program. process all images and output result.
        Extractor.java      -- Extract character from file.
        FileManager.java    -- open and save image file.
      /test                 -- junit test files
    /lib
    /images/                -- Images for processing
      /output               -- The extracted and filtered images.
    Buildfile               -- The buildfile and tasks for buildr 
    Readme.md


## Preprocessing 
  
  1. Using threshold method to filter the background noise.

  2. Using open filter to clean the noises left.

##Labeling

  1. Building equivalant table for Labeling 

  2. Update the range of each label, than extract the characters from image. 

  3. Only extract the label that is large enough, which exclude the small noises.

##Features used

  1. Width/Height Ratio:
    The image box's width and height ratio.

  2. Hole: 
    Using hole-counting algorithm on lecture, to count the hole of character.

  3. Max height line and max width line:
    The widthest line/width and highest line/height ratio on the characters.

  4. Black ratio:
    The ratio of black pixels in image

  5. Center:
    The mean center(x,y) of all pixels (in ratio)

##Classifier
  Using simple decision-theoretical classifier tree to distinct character.

  1. Using Hole to distict character to the group with hole and group without hole.
     {0,4,6,8,9} and {1,2,3,5,7}

    for {0,4,6,8,9}:

    1. Using 2 hole to distict 8 

    2. Matching max height and max width to distinct 0,4

    3. Matching center position to distinct 6,9 
       the center of 9 will be higher than 6, since most of the pixel is in the upper part.

    for {1,2,3,5,7}:  

    1. Using height, width ratio to detect 1 

    2. Matching the max height line and max width line 
       Height /width ratio and center position to distinct 2,3,5,7
