# counter

To run without docker 
1. Import the application as maven project and run as spring boot application.
2. Once build is completed - goto localhost:8080/swagger-ui.html for checking counter controller.

To run with docker 
1. Clone the repo 
2. build using, spring buildpacks - ./mvnw spring-boot:build-image
3. Once we see message : Successfully built image 'docker.io/library/counter:0.0.1-SNAPSHOT'
4. Run :- docker run -p 8080:8080 docker.io/library/counter:0.0.1-SNAPSHOT, make sure no other process is running on port 8080 of host machine, 
   if its running then modify XXXX - with any free port on the machine - docker run -p XXXX:8080 docker.io/library/counter:0.0.1-SNAPSHOT
5. In browser - open localhost:XXXX/swagger-ui.html - XXXX is the choosen port address here
