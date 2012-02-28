repositories.remote << 'http://repo1.maven.org/maven2'

Project.local_task :run
Project.local_task :line

desc "computer vision project2 - simple OCR program"
define "cv-project2" do

  resources.include 'images/*.bmp'
  project.version = '0.1.0' 
  package(:jar, :file => "App.jar").with(:manifest=>{
    'Main-Class'=>'edu.poly.tchao.Application'
  })
  run.using :main => "edu.poly.tchao.Application"

  task :line => compile do
    system 'java -cp target/classes edu.poly.tchao.LineExtractor'
  end
end
