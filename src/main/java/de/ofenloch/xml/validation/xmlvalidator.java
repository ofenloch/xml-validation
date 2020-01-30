package de.ofenloch.xml.validation;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

public class xmlvalidator {

    static boolean validateAgainstXSD(final String xmlFileName, final String xsdFileName) {
        try {
        InputStream xml = new FileInputStream(new File(xmlFileName));
        InputStream xsd = new FileInputStream(new File(xsdFileName));
        return validateAgainstXSD(xml, xsd);
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    } // static boolean validateAgainstXSD(final String xmlFileName, final String xsdFileName)

    static boolean validateAgainstXSD(InputStream xml, InputStream xsd) {
        try {
            SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = factory.newSchema(new StreamSource(xsd));
            Validator validator = schema.newValidator();
            validator.validate(new StreamSource(xml));
            return true;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    } // static boolean validateAgainstXSD(InputStream xml, InputStream xsd)

} // public class xmlvalidator