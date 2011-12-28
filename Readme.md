#CS 6643 Project 2: character Recongnition

###Author: Te-Chun Chao
###Poly-id: 0416545

===============================

1. How to run the project?

unzip the project, run 

  java -jar App.jar 

To process images from IMG_1.bmp to IMG_6.bmp 

You can also install buildr to run other tests and tasks,
On Ruby installed envirment, type

  gem install buildr
  buildr run 

Can execute the project

===============================

2.Program Structures


/src
  /main
    /classifier
      Classifier.java   -- main classifier for detecting character from minimun character "box" 
    /filters
    /models
      CharImage.java    -- Extracted minimun character box, also include the feature logics
      CharRange.java    -- Use for detect position and update the box range of a character.
    Application.java    -- Entry point for program. process all images and output result.
    Extractor.java      -- Extract character from file.
    Thresholder.java    -- Filter , delete the background and noises.
  /test                 -- junit test files
/lib
/images/                -- Images for processing
  /output               -- The extracted and filtered images.
Buildfile               -- The buildfile and tasks for buildr 
App.jar                 -- Packaged application, **no images in jar, so still needed to execute in project directory.
result.txt              -- result of "java -jar App.jar > result.txt" 
                           Saving output of application.
Readme.md


3. Preprocessing 
  
  1. Using threshold method from project1 to filter the background noise.

  2. Using open filter to clean the noises left.

4. Labeling

  1. Building equivalant table for Labeling 

  2. Update the range of each label, than extract the characters from image. 

  3. Only extract the label is large enough, which exclude the small noises label.

5.Features 

  1. Width/Height Ratio:
    The image box's width and height ratio.

  2. Hole: 
    Using hole-counting algorithm on lecture, to count the hole of character.

  3. Max height line and max width line:
    The widest line/width and highest line/height ratio on the characters.

6. Classifier
  Using decision-theoretical classifier to detect character.

  1. Using Hole, distict character to the group with hole and group without hole.
     {0,4} and {1,3,4}

  2. Using height, width ratio to detect 1 

  3. Matching the max height line and max width line, detect character in certain range in groups {0,4} and {2,3} 

