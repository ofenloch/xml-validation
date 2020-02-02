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
import javax.xml.transform.Source;
import javax.xml.transform.dom.DOMResult;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.sax.SAXSource;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
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
    static final String XSD_DIRECTORY = "./data/OfficeOpenXML-XMLSchema/";

    static final String JAXP_SCHEMA_LANGUAGE = "http://java.sun.com/xml/jaxp/properties/schemaLanguage";
    static final String W3C_XML_SCHEMA = "http://www.w3.org/2001/XMLSchema";
    static final String JAXP_SCHEMA_SOURCE = "http://java.sun.com/xml/jaxp/properties/schemaSource";


    public static void main(String[] args) {

        // System.out.println("students.xml validates against students.xsd "
        //         + xmlvalidator.validateAgainstXSD("./data/students.xml", "./data/students.xsd"));

        try {

            // Try to validate a /word/document.xml from an extracted docx file
            try {

                SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
                factory.setResourceResolver(new ResourceResolver("./data/OfficeOpenXML-XMLSchema/"));
                // FEATURE_SECURE_PROCESSING disallows file access
                // trySetFeature(factory, XMLConstants.FEATURE_SECURE_PROCESSING, true);
                // TODO: It seems, none of the features is working ...
                // trySetFeature(factory, XMLConstants.ACCESS_EXTERNAL_SCHEMA, true);
                // trySetFeature(factory, XMLConstants.ACCESS_EXTERNAL_DTD, true);
                // trySetFeature(factory, XMLConstants.ACCESS_EXTERNAL_STYLESHEET, true);

                Schema ooxmlSchema = factory.newSchema(new Source[] {
                    new StreamSource(new FileInputStream("./data/OfficeOpenXML-XMLSchema/xml.xsd")),
                    // xml.xsd refers/loads XMLSchema.dtd and datatypes.dtd
                    // datatypes.dtd is intended only for incorporation in XMLSchema.dtd
                    // So we do not load XMLSchema.xsd
                    // new StreamSource(new FileInputStream("./data/OfficeOpenXML-XMLSchema/XMLSchema.xsd")),
                    // new StreamSource(new FileInputStream("./data/OfficeOpenXML-XMLSchema/dml-baseTypes.xsd")),
                    // new StreamSource(new FileInputStream("./data/OfficeOpenXML-XMLSchema/pml-baseTypes.xsd")),
                    // new StreamSource(new FileInputStream("./data/OfficeOpenXML-XMLSchema/sml-baseTypes.xsd")),
                    // new StreamSource(new FileInputStream("./data/OfficeOpenXML-XMLSchema/shared-bibliography.xsd")),
                    // new StreamSource(new FileInputStream("./data/OfficeOpenXML-XMLSchema/shared-customXmlDataProperties.xsd")),
                    // new StreamSource(new FileInputStream("./data/OfficeOpenXML-XMLSchema/shared-customXmlSchemaProperties.xsd")),
                    // new StreamSource(new FileInputStream("./data/OfficeOpenXML-XMLSchema/shared-documentPropertiesCustom.xsd")),
                    // new StreamSource(new FileInputStream("./data/OfficeOpenXML-XMLSchema/shared-documentPropertiesExtended.xsd")),
                    // new StreamSource(new FileInputStream("./data/OfficeOpenXML-XMLSchema/shared-documentPropertiesVariantTypes.xsd")),
                    // new StreamSource(new FileInputStream("./data/OfficeOpenXML-XMLSchema/shared-relationshipReference.xsd")),
                    // new StreamSource(new FileInputStream("./data/OfficeOpenXML-XMLSchema/shared-additionalCharacteristics.xsd")),

                    // new StreamSource(new FileInputStream("./data/OfficeOpenXML-XMLSchema/dml-documentProperties.xsd")),
                    // new StreamSource(new FileInputStream("./data/OfficeOpenXML-XMLSchema/dml-graphicalObject.xsd")),
                    // new StreamSource(new FileInputStream("./data/OfficeOpenXML-XMLSchema/dml-audioVideo.xsd")),
                    // new StreamSource(new FileInputStream("./data/OfficeOpenXML-XMLSchema/dml-baseStylesheet.xsd")),
                    // new StreamSource(new FileInputStream("./data/OfficeOpenXML-XMLSchema/dml-diagramColorTransform.xsd")),
                    // new StreamSource(new FileInputStream("./data/OfficeOpenXML-XMLSchema/dml-diagramDataModel.xsd")),
                    // new StreamSource(new FileInputStream("./data/OfficeOpenXML-XMLSchema/dml-diagramDefinition.xsd")),
                    // new StreamSource(new FileInputStream("./data/OfficeOpenXML-XMLSchema/dml-diagramElementPropertySet.xsd")),
                    // new StreamSource(new FileInputStream("./data/OfficeOpenXML-XMLSchema/dml-diagramLayoutVariables.xsd")),
                    // new StreamSource(new FileInputStream("./data/OfficeOpenXML-XMLSchema/dml-diagramStyleDefinition.xsd")),
                    // new StreamSource(new FileInputStream("./data/OfficeOpenXML-XMLSchema/dml-diagramTypes.xsd")),
                    // new StreamSource(new FileInputStream("./data/OfficeOpenXML-XMLSchema/dml-graphicalObjectAnimation.xsd")),
                    // new StreamSource(new FileInputStream("./data/OfficeOpenXML-XMLSchema/dml-graphicalObjectFormat.xsd")),
                    // new StreamSource(new FileInputStream("./data/OfficeOpenXML-XMLSchema/dml-gvml.xsd")),
                    // new StreamSource(new FileInputStream("./data/OfficeOpenXML-XMLSchema/dml-shape3DCamera.xsd")),
                    // new StreamSource(new FileInputStream("./data/OfficeOpenXML-XMLSchema/dml-shape3DLighting.xsd")),
                    // new StreamSource(new FileInputStream("./data/OfficeOpenXML-XMLSchema/dml-shape3DScenePlane.xsd")),
                    // new StreamSource(new FileInputStream("./data/OfficeOpenXML-XMLSchema/dml-shape3DScene.xsd")),
                    // new StreamSource(new FileInputStream("./data/OfficeOpenXML-XMLSchema/dml-shape3DStyles.xsd")),
                    // new StreamSource(new FileInputStream("./data/OfficeOpenXML-XMLSchema/dml-shapeEffects.xsd")),
                    // new StreamSource(new FileInputStream("./data/OfficeOpenXML-XMLSchema/dml-shapeGeometry.xsd")),
                    // new StreamSource(new FileInputStream("./data/OfficeOpenXML-XMLSchema/dml-shapeLineProperties.xsd")),
                    // new StreamSource(new FileInputStream("./data/OfficeOpenXML-XMLSchema/dml-shapeMiscellaneous.xsd")),
                    // new StreamSource(new FileInputStream("./data/OfficeOpenXML-XMLSchema/dml-shapeProperties.xsd")),
                    // new StreamSource(new FileInputStream("./data/OfficeOpenXML-XMLSchema/dml-shapeStyle.xsd")),
                    // new StreamSource(new FileInputStream("./data/OfficeOpenXML-XMLSchema/dml-styleDefaults.xsd")),
                    // new StreamSource(new FileInputStream("./data/OfficeOpenXML-XMLSchema/dml-stylesheet.xsd")),
                    // new StreamSource(new FileInputStream("./data/OfficeOpenXML-XMLSchema/dml-tableStyle.xsd")),
                    // new StreamSource(new FileInputStream("./data/OfficeOpenXML-XMLSchema/dml-table.xsd")),
                    // new StreamSource(new FileInputStream("./data/OfficeOpenXML-XMLSchema/dml-textBullet.xsd")),
                    // new StreamSource(new FileInputStream("./data/OfficeOpenXML-XMLSchema/dml-textCharacter.xsd")),
                    // new StreamSource(new FileInputStream("./data/OfficeOpenXML-XMLSchema/dml-textParagraph.xsd")),
                    // new StreamSource(new FileInputStream("./data/OfficeOpenXML-XMLSchema/dml-textRun.xsd")),
                    // new StreamSource(new FileInputStream("./data/OfficeOpenXML-XMLSchema/dml-text.xsd")),
                    
                    // new StreamSource(new FileInputStream("./data/OfficeOpenXML-XMLSchema/pml-comments.xsd")),
                    // new StreamSource(new FileInputStream("./data/OfficeOpenXML-XMLSchema/pml-embedding.xsd")),
                    // new StreamSource(new FileInputStream("./data/OfficeOpenXML-XMLSchema/pml-presentationProperties.xsd")),
                    // new StreamSource(new FileInputStream("./data/OfficeOpenXML-XMLSchema/pml-presentation.xsd")),
                    // new StreamSource(new FileInputStream("./data/OfficeOpenXML-XMLSchema/pml-slideSynchronizationData.xsd")),
                    // new StreamSource(new FileInputStream("./data/OfficeOpenXML-XMLSchema/pml-slide.xsd")),
                    // new StreamSource(new FileInputStream("./data/OfficeOpenXML-XMLSchema/pml-userDefinedTags.xsd")),
                    // new StreamSource(new FileInputStream("./data/OfficeOpenXML-XMLSchema/pml-viewProperties.xsd")),
                    // new StreamSource(new FileInputStream("./data/OfficeOpenXML-XMLSchema/sml-autoFilter.xsd")),
                    
                    // new StreamSource(new FileInputStream("./data/OfficeOpenXML-XMLSchema/sml-calculationChain.xsd")),
                    // new StreamSource(new FileInputStream("./data/OfficeOpenXML-XMLSchema/sml-comments.xsd")),
                    // new StreamSource(new FileInputStream("./data/OfficeOpenXML-XMLSchema/sml-customXmlMappings.xsd")),
                    // new StreamSource(new FileInputStream("./data/OfficeOpenXML-XMLSchema/sml-externalConnections.xsd")),
                    // new StreamSource(new FileInputStream("./data/OfficeOpenXML-XMLSchema/sml-pivotTableShared.xsd")),
                    // new StreamSource(new FileInputStream("./data/OfficeOpenXML-XMLSchema/sml-pivotTable.xsd")),
                    // new StreamSource(new FileInputStream("./data/OfficeOpenXML-XMLSchema/sml-queryTable.xsd")),
                    // new StreamSource(new FileInputStream("./data/OfficeOpenXML-XMLSchema/sml-sharedStringTable.xsd")),
                    // new StreamSource(new FileInputStream("./data/OfficeOpenXML-XMLSchema/sml-sharedWorkbookRevisions.xsd")),
                    // new StreamSource(new FileInputStream("./data/OfficeOpenXML-XMLSchema/sml-sharedWorkbookUserNames.xsd")),
                    // new StreamSource(new FileInputStream("./data/OfficeOpenXML-XMLSchema/sml-sheetMetadata.xsd")),
                    // new StreamSource(new FileInputStream("./data/OfficeOpenXML-XMLSchema/sml-sheet.xsd")),
                    // new StreamSource(new FileInputStream("./data/OfficeOpenXML-XMLSchema/sml-singleCellTable.xsd")),
                    // new StreamSource(new FileInputStream("./data/OfficeOpenXML-XMLSchema/sml-styles.xsd")),
                    // new StreamSource(new FileInputStream("./data/OfficeOpenXML-XMLSchema/sml-supplementaryWorkbooks.xsd")),
                    // new StreamSource(new FileInputStream("./data/OfficeOpenXML-XMLSchema/sml-table.xsd")),
                    // new StreamSource(new FileInputStream("./data/OfficeOpenXML-XMLSchema/sml-volatileDependencies.xsd")),
                    new StreamSource(new FileInputStream("./data/OfficeOpenXML-XMLSchema/sml-workbook.xsd")),

                    // new StreamSource(new FileInputStream("./data/OfficeOpenXML-XMLSchema/vml-presentationDrawing.xsd")),
                    // new StreamSource(new FileInputStream("./data/OfficeOpenXML-XMLSchema/vml-spreadsheetDrawing.xsd")),
                    // new StreamSource(new FileInputStream("./data/OfficeOpenXML-XMLSchema/vml-wordprocessingDrawing.xsd")),

                    // new StreamSource(new FileInputStream("./data/OfficeOpenXML-XMLSchema/shared-math.xsd")),
                    // new StreamSource(new FileInputStream("./data/OfficeOpenXML-XMLSchema/dml-lockedCanvas.xsd")),
                    // new StreamSource(new FileInputStream("./data/OfficeOpenXML-XMLSchema/dml-picture.xsd")),
                    // new StreamSource(new FileInputStream("./data/OfficeOpenXML-XMLSchema/vml-main.xsd")),
                    // new StreamSource(new FileInputStream("./data/OfficeOpenXML-XMLSchema/vml-officeDrawing.xsd")),
                    // new StreamSource(new FileInputStream("./data/OfficeOpenXML-XMLSchema/dml-spreadsheetDrawing.xsd")),
                    // new StreamSource(new FileInputStream("./data/OfficeOpenXML-XMLSchema/dml-wordprocessingDrawing.xsd")),
                    // new StreamSource(new FileInputStream("./data/OfficeOpenXML-XMLSchema/pml-animationInfo.xsd")),
                    // new StreamSource(new FileInputStream("./data/OfficeOpenXML-XMLSchema/dml-chartDrawing.xsd")),
                    // new StreamSource(new FileInputStream("./data/OfficeOpenXML-XMLSchema/dml-chart.xsd")),
                    // new StreamSource(new FileInputStream("./data/OfficeOpenXML-XMLSchema/dml-compatibility.xsd")),
                    new StreamSource(new FileInputStream("./data/OfficeOpenXML-XMLSchema/wml.xsd")),
                });

                System.out.println("\nCreated ooxmlSchema\n");

                String xmlFileName = "./data/word_document.xml";

                System.out.println("Validating XML in file " + xmlFileName + " ...\n");
                if (isValid(xmlFileName, ooxmlSchema)) {
                    System.out.println(" XML is valid\n");
                } else {
                    System.out.println(" XML is NOT valid\n");
                }


                System.out.println("\nCreated ooxmlSchema. Going to parse " + xmlFileName + " (with validation!) ...\n");
                SAXParserFactory spf = SAXParserFactory.newInstance();
                // always use namespace awarenes
                spf.setNamespaceAware(true);
                spf.setSchema(ooxmlSchema);
                SAXParser saxParser = spf.newSAXParser();
                // Property 'http://java.sun.com/xml/jaxp/properties/schemaLanguage' cannot be set when a non-null Schema object has already been specified.
                // saxParser.setProperty(JAXP_SCHEMA_LANGUAGE, W3C_XML_SCHEMA);

                XMLReader xmlReader = saxParser.getXMLReader();
                //xmlReader.setContentHandler(new contentHandler());
                //xmlReader.setErrorHandler(new validationErrorHandler());
                xmlReader.parse(xmlFileName);

                // Of course, we still could do something like this, if 
                // want parsing and validating to happen separately:
                // Path xmlPath = Paths.get(xmlFileName);
                // Reader reader = Files.newBufferedReader(xmlPath);
                // Validator validator = ooxmlSchema.newValidator();
                // validator.setErrorHandler(new validationErrorHandler());
                // SAXSource source = new SAXSource(new InputSource(reader));
                // validator.validate(source);

                DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
                DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
                Path xmlPath = Paths.get(xmlFileName);
                Document xmlDocument = dBuilder.parse(xmlPath.toFile());
                Element xmlDocumentDocumentElement = xmlDocument.getDocumentElement();
                xmlDocumentDocumentElement.normalize();
                System.out.println("Root element :" + xmlDocument.getDocumentElement().getNodeName());
                NodeList nodeList = xmlDocument.getElementsByTagName("student");
                
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
    }

    /**
     * validate xml in given file against given Schema
     * 
     * @param xmlFileName the name of the file to be validated
     * @param schema the Schema to validate against
     * @return return true if valid, false otherwise
     */
    static public boolean isValid(final String xmlFileName, Schema schema) {
        try {
            System.out.println("\nCreated ooxmlSchema. Going to validate " + xmlFileName + " ...\n");
            File xmlFile = new File(xmlFileName);
            PrintStream printStream = new PrintStream(new File(xmlFileName+"validation"), "UTF-8");
            InputStream xmlInStream = new FileInputStream(xmlFile);
            try {
                Validator validator = schema.newValidator();
                validator.setErrorHandler(new validationErrorHandler(printStream));
                validator.validate(new StreamSource(xmlInStream));
                // Document augmented = (Document) result.getNode();
                // do whatever you need to do with the augmented document...
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
    }
}
