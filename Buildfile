repositories.remote << 'http://repo1.maven.org/maven2'

Project.local_task :run

define "cv-project2" do
  project.version = '0.0.0' 
  package :jar

  run.using :main => "edu.poly.tchao.HelloWorld"

end
