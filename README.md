# XML Validation wiht Java

## How To

This is an example how you can validate an XML file against an XSD file in Java.

## OOXML

The xsd files in folder ./data/OfficeOpenXML-XMLSchema/ are used to 
validate OOXML files. I took them from the Apache POI project ...

Some of the xsd files from MicroSoft have to be converted to UTF-8. Otherwise 
we get 'content in prolog' errors.

## Set Up Project with Maven

```bash
ofenloch@teben:~/workspaces/java$ mvn archetype:generate -DgroupId=de.ofenloch.xml.validation -DartifactId=xml-validation -DarchetypeArtifactId=maven-archetype-quickstart -DarchetypeVersion=1.4 -DinteractiveMode=false
WARNING: An illegal reflective access operation has occurred
WARNING: Illegal reflective access by com.google.inject.internal.cglib.core.$ReflectUtils$1 (file:/usr/share/maven/lib/guice.jar) to method java.lang.ClassLoader.defineClass(java.lang.String,byte[],int,int,java.security.ProtectionDomain)
WARNING: Please consider reporting this to the maintainers of com.google.inject.internal.cglib.core.$ReflectUtils$1
WARNING: Use --illegal-access=warn to enable warnings of further illegal reflective access operations
WARNING: All illegal access operations will be denied in a future release
[INFO] Scanning for projects...
[INFO] 
[INFO] ------------------< org.apache.maven:standalone-pom >-------------------
[INFO] Building Maven Stub Project (No POM) 1
[INFO] --------------------------------[ pom ]---------------------------------
[INFO] 
[INFO] >>> maven-archetype-plugin:3.1.2:generate (default-cli) > generate-sources @ standalone-pom >>>
[INFO] 
[INFO] <<< maven-archetype-plugin:3.1.2:generate (default-cli) < generate-sources @ standalone-pom <<<
[INFO] 
[INFO] 
[INFO] --- maven-archetype-plugin:3.1.2:generate (default-cli) @ standalone-pom ---
[INFO] Generating project in Batch mode
[INFO] ----------------------------------------------------------------------------
[INFO] Using following parameters for creating project from Archetype: maven-archetype-quickstart:1.4
[INFO] ----------------------------------------------------------------------------
[INFO] Parameter: groupId, Value: de.ofenloch.xml.validation
[INFO] Parameter: artifactId, Value: xml-validation
[INFO] Parameter: version, Value: 1.0-SNAPSHOT
[INFO] Parameter: package, Value: de.ofenloch.xml.validation
[INFO] Parameter: packageInPathFormat, Value: de/ofenloch/xml/validation
[INFO] Parameter: package, Value: de.ofenloch.xml.validation
[INFO] Parameter: groupId, Value: de.ofenloch.xml.validation
[INFO] Parameter: artifactId, Value: xml-validation
[INFO] Parameter: version, Value: 1.0-SNAPSHOT
[INFO] Project created from Archetype in dir: /data/sdb1/home/ofenloch/workspaces/java/xml-validation
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time:  2.719 s
[INFO] Finished at: 2020-01-30T21:48:13+01:00
[INFO] ------------------------------------------------------------------------

 ```

## Set Up Git Repository

```bash
ofenloch@teben:~/workspaces/java$ cd xml-validation/
ofenloch@teben:~/workspaces/java/xml-validation$ git init
Initialized empty Git repository in /data/sdb1/home/ofenloch/workspaces/java/xml-validation/.git/
ofenloch@teben:~/workspaces/java/xml-validation$ git add .
ofenloch@teben:~/workspaces/java/xml-validation$ git commit -a -m"initial commit"
[master (root-commit) 757c57c] initial commit
 3 files changed, 108 insertions(+)
 create mode 100644 pom.xml
 create mode 100644 src/main/java/de/ofenloch/xml/validation/App.java
 create mode 100644 src/test/java/de/ofenloch/xml/validation/AppTest.java
ofenloch@teben:~/workspaces/java/xml-validation$ code .
ofenloch@teben:~/workspaces/java/xml-validation$ git remote add origin https://github.com/ofenloch/xml-validation.git
ofenloch@teben:~/workspaces/java/xml-validation$ git push -u origin master
Enumerating objects: 49, done.
Counting objects: 100% (49/49), done.
Delta compression using up to 8 threads
Compressing objects: 100% (26/26), done.
Writing objects: 100% (49/49), 5.04 KiB | 1.26 MiB/s, done.
Total 49 (delta 4), reused 0 (delta 0)
remote: Resolving deltas: 100% (4/4), done.
To https://github.com/ofenloch/xml-validation.git
 * [new branch]      master -> master
Branch 'master' set up to track remote branch 'master' from 'origin'.
ofenloch@teben:~/workspaces/java/xml-validation/.git$ cd ..
ofenloch@teben:~/workspaces/java/xml-validation$ code .
ofenloch@teben:~/workspaces/java/xml-validation$ 
```