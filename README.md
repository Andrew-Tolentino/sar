# CS445 Final Project - Share A Ride

An application used to help car poolers create and find rides online

## Getting Started

The instructions to install, build, and deploy the application are designed to be run on [Ubuntu 18.04 LTS](https://ubuntu-18.04.4-desktop-amd64.iso)

## Instructions

### 1. Installation and Configuration

Update Packaging Tool

```bash
$ sudo apt update
```

Install Java 11 JDK

```bash
$ sudo apt install openjdk-11-jdk-headless
```

Install Unzip

```bash
$ sudo apt install unzip
```

Install Gradle through PPA

```bash
$ sudo apt -y install vim apt-transport-https dirmngr wget software-properties-common
```

```bash
$ sudo add-apt-repository ppa:cwchien/gradle
```

```bash
$ sudo apt-get update
```

```bash
$ sudo apt -y install gradle
```

Install Apache-Tomcat

```bash
$ wget https://downloads.apache.org/tomcat/tomcat-9/v9.0.34/bin/apache-tomcat-9.0.34.zip
```
Go to the directory where Apache-Tomcat is downloaded at 
```bash
$ unzip apache-tomcat-9.0.34.zip
```
```bash
$ sudo mv apache-tomcat-9.0.34 /opt/tomcat 
```

### 2. Build

Go to any working directory where you would like to clone the repository

```bash
$ git clone https://github.com/Andrew-Tolentino/sar.git <FinalProject>
```
The repository will be saved locally in the directory "FinalProject". You may choose any name for this directory but FinalProject will be used as a reference. Just replace "FinalProject" with the directory name chosen from the Instructions.

```bash
$ cd FinalProject/sar
```
Create the gradle wrapper

```bash
$ gradle wrapper
```

Build the "executable"

```bash
$ ./gradlew build
```

### 3. Deploy

Go to the WAR file produced after building

```bash
$ cd build/libs
```

Copy and move the sar.war file into the Apache-Tomcat webapps directory

```bash
$ cp sar.war /opt/tomcat/webapps
```

Go to the Apache-Tomcat bin directory

```bash
$ cd /opt/tomcat/bin
```

Compile the catalina.sh file

```bash
$ chmod +x ./catalina.sh
```

Run the catalina.sh executable

```bash
$ ./catalina.sh run
```
Press Ctrl+C anytime you want to stop the server.

Now Apache-Tomcat will use the sar.war file for deployment and create the sar directory when running the server. If you wish to rebuild with any changes, you must remove the sar.war file and sar directory in the webapps directory ( /opt/tomcat/webapps ) and then add in the new sar.war file produced after building.

## Known bugs

When running the server you will get some warnings thrown on the terminal but it does not prevent the application from working.

## Testing

You can produce a testing coverage report through jacoco after building
```bash
$ ./gradlew jacocoTestReport
```

Now use your browser to open the file ~/../FinalProject/build/reports/jacoco/test/html
  
This is a detailed unit-test coverage report.

## Credits and Acknowledgements
I would to thank professor Virgil Bistriceanu and the TA Ritvij Saxena for helping me out throughout the lifecycle of this project. I have gained a lot of insight on designing and creating RESTful applications which I will I continue to practice on.

## License
This project is licensed under the MIT License - see the [LICENSE.md](https://choosealicense.com/licenses/mit/) file for details
[LICENSE.md ](https://choosealicense.com/licenses/mit/)
