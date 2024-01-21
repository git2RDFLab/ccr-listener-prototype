# Prototype for accepting git repositories and providing status information

## Spring Initializr Template
https://start.spring.io/#!type=maven-project&language=java&platformVersion=3.2.2&packaging=jar&jvmVersion=21&groupId=de.leipzig.htwk.gitrdf&artifactId=listener&name=listener&description=Archetype%20project%20for%20HTWK%20Leipzig%20-%20Project%20to%20transform%20git%20to%20RDF&packageName=de.leipzig.htwk.gitrdf.listener&dependencies=web,lombok,devtools,data-jpa,postgresql,testcontainers

### CURL-Example to upload file
curl -XPOST -F "file=@gitexample.zip;name=field1;filename=gitexample.zip;type=application/zip" localhost:8080/api/v1/git/upload

curl -XPOST -F "file=@gitexample.zip;filename=gitexample.zip;type=application/zip" localhost:8080/api/v1/git/upload?name=abc