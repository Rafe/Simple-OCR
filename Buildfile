repositories.remote << 'http://repo1.maven.org/maven2'

Project.local_task :run
Project.local_task :process

desc "computer vision project2 - simple OCR program"
define "cv-project2" do

  resources.include 'images/*.bmp'
  project.version = '0.1.0' 
  package(:jar, :file => "App.jar").with(:manifest=>{
    'Main-Class'=>'edu.poly.tchao.Application'
  })
  run.using :main => "edu.poly.tchao.Application"

  desc "processing images..."
  task :process => compile do
    images = ['IMG_1.bmp','IMG_2.bmp','IMG_3.bmp','IMG_4.bmp','IMG_5.bmp','IMG_6.bmp']
    system 'java -cp target/classes edu.poly.tchao.Application ' + images.join(" ")
  end
end
