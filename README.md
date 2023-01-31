# Compiling and setup instructions:
## Building ( Class-dependent .jar file )
##### 1. In your terminal, navigate to Maven root directory (i.e. the directory with pom.xml)
##### 2. Enter command
> mvn package
##### This command will build the game, while executing the JUnit tests. The compiled project will be created in /target/
## Running ( Class-dependent .jar file )
##### 1. Ensure that you are in the Maven root directory
##### 2. After building, enter command
> java -cp target/graphics.jar com.group4.app.AppWindow
##### 3. Enjoy the game!
## Building ( Independent, executable .jar file )
##### 1. In your terminal, navigate to Maven root directory (i.e. the directory with pom.xml)
##### 2. Enter command
> mvn clean compile package assembly:single
##### This command will go through the same process as the other, while additionally building an extra executable .jar file. The compiled artifacts will be created in /target/
## Generating Javadoc
##### 1. Ensure that you are in the Maven root directory
##### 2. Enter command
> mvn javadoc:javadoc
##### 3. The Javadoc will be outputted to /target/javadoc-report
## Running ( Independent, executable .jar file )
##### 1. Ensure that you are in the Maven root directory
##### 2. After building, enter command
> java -jar target/App-jar-with-dependencies.jar
##### or, if you want to take the executable elsewhere, simply navigate to its directory and enter the command
> java -jar App-jar-with-dependencies.jar
##### 3. Enjoy the game!
## Testing
##### 1. Ensure that you are in the Maven root directory
##### 2. Enter command
> mvn clean test
##### 3. The JUnit test results will be outputted to .txt files in /target/surefire-reports

## Video Link: 
### https://drive.google.com/file/d/1h_825hc3hRr1vR8n3COf_YIuw3bJK6hs/view?usp=sharing
