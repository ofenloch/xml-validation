package de.ofenloch.xml.validation;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.io.Reader;
import java.net.URI;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.dom.DOMResult;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.sax.SAXResult;
import javax.xml.transform.sax.SAXSource;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.ls.LSResourceResolver;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.XMLReader;

/**
 * Hello world!
 *
 */
public class App {

    static final String DATA_DIRECTORY = "./data/";
    // https://www.ecma-international.org/publications/standards/Ecma-376.htm
    //
    //  1st edition (December 2006),
    //  2nd edition (December 2008),
    //  3rd edition (June 2011),
    //  4th edition (December 2012)
    //  and 5th edition (Part 3, December 2015; and Parts 1 & 4, December 2016)
    //
    //static final String XSD_DIRECTORY = "./data/OfficeOpenXML-XMLSchema";
    //static final String XSD_DIRECTORY = "./data/OfficeOpenXML-XMLSchema-Transitional-4thEd";
    static final String XSD_DIRECTORY = "./data/OfficeOpenXML-XMLSchema-Transitional-5thEd";

    static final String JAXP_SCHEMA_LANGUAGE = "http://java.sun.com/xml/jaxp/properties/schemaLanguage";
    static final String W3C_XML_SCHEMA = "http://www.w3.org/2001/XMLSchema";
    static final String JAXP_SCHEMA_SOURCE = "http://java.sun.com/xml/jaxp/properties/schemaSource";


    public static void main(String[] args) {

        try {

            // Try to validate a /word/document.xml from an extracted docx file
            try {

                SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
                factory.setResourceResolver(new ResourceResolver(XSD_DIRECTORY));
                // FEATURE_SECURE_PROCESSING disallows file access
                // trySetFeature(factory, XMLConstants.FEATURE_SECURE_PROCESSING, true);
                // TODO: It seems, none of the features is working ...
                // trySetFeature(factory, XMLConstants.ACCESS_EXTERNAL_SCHEMA, true);
                // trySetFeature(factory, XMLConstants.ACCESS_EXTERNAL_DTD, true);
                // trySetFeature(factory, XMLConstants.ACCESS_EXTERNAL_STYLESHEET, true);

                Schema ooxmlSchema = factory.newSchema(new Source[] {
                    new StreamSource(new FileInputStream(XSD_DIRECTORY + "/dml-main.xsd")),
                    new StreamSource(new FileInputStream(XSD_DIRECTORY + "/pml.xsd")),
                    new StreamSource(new FileInputStream(XSD_DIRECTORY + "/sml.xsd")),
                    new StreamSource(new FileInputStream(XSD_DIRECTORY + "/wml.xsd"))
                });

                System.out.println("\nCreated ooxmlSchema\n");

                String xmlFileName = "./data/word_document.xml";

/*
                System.out.println("Validating XML in file " + xmlFileName + " ...\n");
                if (isValid(xmlFileName, ooxmlSchema)) {
                    System.out.println(" XML is valid\n");
                } else {
                    System.out.println(" XML is NOT valid\n");
                }
*/

                System.out.println("\nCreated ooxmlSchema. Going to parse " + xmlFileName + " (with validation!) ...\n");
                Document xmlDocument = parseXmlFile(xmlFileName, ooxmlSchema);
                Element xmlDocumentDocumentElement = xmlDocument.getDocumentElement();
                xmlDocumentDocumentElement.normalize();
                System.out.println("Root element :" + xmlDocumentDocumentElement.getNodeName());
                NodeList bodyElements = xmlDocument.getElementsByTagName("w:body");
                int nBodyElements = bodyElements.getLength();
                if (nBodyElements != 1) {
                    System.out.println("Document contains " + nBodyElements + " <w:body> elements. Aborting.");
                }
                Node documentBody = bodyElements.item(0);
                
            } catch (SAXParseException e) {
                System.out.println("Caught SAXParseException:");
                System.out.println("  line     : " + e.getLineNumber());
                System.out.println("  column   : " + e.getColumnNumber());
                System.out.println("  publicId : " + e.getPublicId());
                System.out.println("  systemId : " + e.getSystemId());
                System.out.println("  message : " + e.getMessage());
                System.out.println("    cause : " + e.getCause());
                e.printStackTrace();
            } catch (SAXException e) {
                System.out.println("Caught SAXException:");
                System.out.println("  message : " + e.getMessage());
                System.out.println("    cause : " + e.getCause());
                e.printStackTrace();
            } catch (Exception e) {
                System.out.println("Caught Exception:");
                System.out.println("  message : " + e.getMessage());
                System.out.println("    cause : " + e.getCause());
                e.printStackTrace();
            }
        } catch (Exception ex) {
            System.out.println("Exception thrown:");
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }
    } // public static void main(String[] args)

    /**
     * this is from src/ooxml/java/org/apache/poi/xssf/extractor/XSSFExportToXml.java
     */
    public static void trySetFeature(SchemaFactory sf, String feature, boolean enabled) {
        try {
            sf.setFeature(feature, enabled);
        } catch (Exception e) {
            System.out.println("SchemaFactory: Feature \"" + feature + "\" unsupported : " + e);
        } catch (AbstractMethodError ame) {
            System.out.println("SchemaFactory: Cannot set Feature \"" + feature + "\" because outdated XML parser in classpath : " + ame);
        }
    } // public static void trySetFeature(SchemaFactory sf, String feature, boolean enabled)

    /**
     * validate xml in given file against given Schema
     * 
     * @param xmlFileName the name of the file to be validated
     * @param schema the Schema to validate against
     * @return return true if valid, false otherwise
     */
    static public boolean isValid(final String xmlFileName, Schema schema) {
        try {
            File xmlFile = new File(xmlFileName);
            PrintStream printStream = new PrintStream(new File(xmlFileName+"validation"), "UTF-8");
            InputStream xmlInStream = new FileInputStream(xmlFile);
            validationErrorHandler vH = new validationErrorHandler(printStream);
            try {
                Validator validator = schema.newValidator();
                validator.setErrorHandler(vH);
                InputSource inputSource = new InputSource(xmlInStream);
                Source saxSource = new SAXSource(inputSource);
                Result saxResult = new SAXResult();
                validator.validate(saxSource, saxResult);
                // no clue what to do with the result ...
                if (vH.getnFatals()>0) {
                    return false;
                }
            }
            catch (SAXException ex) {
                System.out.println("XML in file " + xmlFileName + " is not valid because ");
                System.out.println(ex.getMessage());
                return false;
            }
            printStream.close();
            return true;
        } catch (Exception ex) {
            System.out.println("Could not validate file " + xmlFileName + " because ");
            System.out.println(ex.getMessage());
            return false;
        }
    } // static public boolean isValid(final String xmlFileName, Schema schema)


    /**
     * parse given XML file and validate it against the given schema
     * (if no schema is given, no validation is done)
     * 
     * @param xmlFileName the XML file's name
     * @param schema the schema (may be null if no validation is desired)
     * @return the parsed document (null if there was an parsing error)
     */
    static public Document parseXmlFile(final String xmlFileName, Schema schema) {
        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            dbFactory.setNamespaceAware(true);
            if (schema != null) {
                dbFactory.setSchema(schema);
                dbFactory.setValidating(true);
            } else {
                dbFactory.setValidating(false);
            }
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            PrintStream printStream = new PrintStream(new File(xmlFileName+"validation"), "UTF-8");
            validationErrorHandler vH = new validationErrorHandler(printStream);
            dBuilder.setErrorHandler(vH);
            Path xmlPath = Paths.get(xmlFileName);
            Document xmlDocument = dBuilder.parse(xmlPath.toFile());
            if (schema != null) {
                if (vH.getnFatals()>0) {
                    System.out.println("Got " + vH.getnFatals() + " fatal errors while parsing and validating file " + xmlFileName);
                    return null;
                }
                if (vH.getnErrors()>0) {
                    System.out.println("Got " + vH.getnErrors() + " recoverable errors while parsing and validating file " + xmlFileName);
                }
                if (vH.getnWarnings()>0) {
                    System.out.println("Got " + vH.getnWarnings() + " recoverable errors while parsing and validating file " + xmlFileName);
                }
            }
            return xmlDocument;
        } catch (SAXParseException ex) {
            printSaxParseException(ex);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    } // static public Document parseXmlFile(final String xmlFileName, Schema schema)

    public static void printSaxParseException(SAXParseException ex) {
        printSaxParseException(ex, System.out);
    }
    public static void printSaxParseException(SAXParseException ex, PrintStream printStream) {
        printStream.println("line " + ex.getLineNumber() + ", column " + ex.getColumnNumber());
        printStream.println(ex.getMessage());
    }
}
