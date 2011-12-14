repositories.remote << 'http://repo1.maven.org/maven2'

Project.local_task :run
Project.local_task :crop

image_path = "images"
output_path = "images/output"

desc "computer vision project2 - simple OCR program"
define "cv-project2" do
  project.version = '0.1.0' 
  package :jar
  run.using :main => "edu.poly.tchao.HelloWorld"

  desc "processing images..."
  task :run => compile do
    images = ['IMG_1.bmp','IMG_2.bmp','IMG_3.bmp','IMG_4.bmp','IMG_5.bmp','IMG_6.bmp','sample.bmp']
    system 'java -cp target/classes edu.poly.tchao.Thresholder ' + images.join(" ")
  end

  desc "processing image extraction"
  task :crop => compile do 
    system 'java -cp target/classes edu.poly.tchao.Extractor'
  end

end
