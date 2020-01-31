package de.ofenloch.xml.validation;

import java.io.File;

import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) {
        System.out.println("students.xml validates against students.xsd "
                + xmlvalidator.validateAgainstXSD("./data/students.xml", "./data/students.xsd"));

        try {
            // the following code was "stolen" from 
            // https://github.com/Turreta/Validate-XML-against-an-XSD-that-imports-other-XSDs
            Schema schema = xmlvalidator.getSchemaToValidateAgainst();
            try {
                // Validates CustomerDetails01.xml
                schema.newValidator().validate(new StreamSource(new File(
                        xmlvalidator.class.getClassLoader().getResource("xml/CustomerDetails01.xml").getFile())));
                System.out.println("No Exceptions thrown. CustomerDetails01.xml is valid!");
            } catch (Exception e) {
                System.out.println("Exceptions thrown. CustomerDetails01.xml is invalid!");
                System.out.println(e.getMessage());
                e.printStackTrace();
            }
            try {
                // Validates Purchase01.xml
                schema.newValidator().validate(new StreamSource(
                        new File(xmlvalidator.class.getClassLoader().getResource("xml/Purchase01.xml").getFile())));
                System.out.println("No Exceptions thrown. Purchase01.xml is valid!");
            } catch (Exception e) {
                System.out.println("Exceptions thrown. Purchase01.xml is invalid!");
                System.out.println(e.getMessage());
                e.printStackTrace();
            }
            try {
                // Validates CustomerDetails01.xml
                schema.newValidator().validate(new StreamSource(new File(xmlvalidator.class.getClassLoader()
                        .getResource("xml/CustomerDetails02-Invalid.xml").getFile())));
                System.out.println("No Exceptions thrown. CustomerDetails02-Invalid.xml is valid!");
            } catch (Exception e) {
                System.out.println("Exceptions thrown. CustomerDetails02-Invalid.xml is invalid!");
                System.out.println(e.getMessage());
                e.printStackTrace();
            }
        } catch (Exception ex) {
            System.out.println("Exception thrown:");
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }
    } // public static void main(String[] args)
}
