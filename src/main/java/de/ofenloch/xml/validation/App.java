package de.ofenloch.xml.validation;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) {
        System.out.println( "students.xml validates against students.xsd " + 
            xmlvalidator.validateAgainstXSD("./data/students.xml", "./data/students.xsd" ));
    }
}
