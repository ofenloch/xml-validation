package de.ofenloch.xml.validation;

import static org.junit.Assert.assertTrue;

import java.io.File;

import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;

import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class AppTest {
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue() {
        assertTrue(true);
    }

    @Test
    public void testValidateAgainstXsd() {
        // the following code was "stolen" from
        // https://github.com/Turreta/Validate-XML-against-an-XSD-that-imports-other-XSDs
        try {
            Schema schema = xmlvalidator.getSchemaToValidateAgainst();
            // Validates CustomerDetails01.xml
            schema.newValidator().validate(new StreamSource(
                    new File(xmlvalidator.class.getClassLoader().getResource("xml/CustomerDetails01.xml").getFile())));
            System.out.println("No Exceptions thrown. CustomerDetails01.xml is valid!");
        } catch (Exception e) {
            System.out.println("Exceptions thrown. CustomerDetails01.xml is invalid!");
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        try {
            Schema schema = xmlvalidator.getSchemaToValidateAgainst();
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
            Schema schema = xmlvalidator.getSchemaToValidateAgainst();
            // Validates CustomerDetails01.xml
            schema.newValidator().validate(new StreamSource(new File(
                    xmlvalidator.class.getClassLoader().getResource("xml/CustomerDetails02-Invalid.xml").getFile())));
            System.out.println("No Exceptions thrown. CustomerDetails02-Invalid.xml is valid!");
        } catch (Exception e) {
            System.out.println("Exceptions thrown. CustomerDetails02-Invalid.xml is invalid!");
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    } // public void testValidateAgainstXsd()
} // public class AppTest
