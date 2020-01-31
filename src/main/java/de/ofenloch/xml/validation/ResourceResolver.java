package de.ofenloch.xml.validation;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.InputStream;

import org.w3c.dom.ls.LSInput;
import org.w3c.dom.ls.LSResourceResolver;
import org.xml.sax.InputSource;

public class ResourceResolver implements LSResourceResolver {

  public static String DATA_DIRECTORY = "./data/";
  public static String XSD_DIRECTORY = "./data/OfficeOpenXML-XMLSchema/";

  public LSInput resolveResource(String type, String namespaceURI, String publicId, String systemId, String baseURI) {

    // type - The type of the resource being resolved. For XML [XML 1.0] resources (i.e. entities), applications must use the value "http://www.w3.org/TR/REC-xml". For XML Schema [XML Schema Part 1] , applications must use the value "http://www.w3.org/2001/XMLSchema". Other types of resources are outside the scope of this specification and therefore should recommend an absolute URI in order to use this method.
    // namespaceURI - The namespace of the resource being resolved, e.g. the target namespace of the XML Schema [XML Schema Part 1] when resolving XML Schema resources.
    // publicId - The public identifier of the external entity being referenced, or null if no public identifier was supplied or if the resource is not an entity.
    // systemId - The system identifier, a URI reference [IETF RFC 2396], of the external resource being referenced, or null if no system identifier was supplied.
    // baseURI - The absolute base URI of the resource being parsed, or null if there is no base URI.

    // note: in this sample, the XSD's are expected to be in XSD_DIRECTORY
    try {
      System.out.println("ResourceResolver: looking for systemId \"" + systemId + "\" ...");
      System.out.println("               type: " + type);
      System.out.println("       namespaceURI: " + namespaceURI);
      System.out.println("           publicId: " + publicId);
      System.out.println("            baseURI: " + baseURI);

      if (systemId != null) {
        InputStream resourceAsStream = new FileInputStream(XSD_DIRECTORY + systemId);
        System.out.println(" ---- found file \"" + XSD_DIRECTORY + systemId + "\" ...");
        return new Input(publicId, systemId, resourceAsStream);
      } else {
        InputStream resourceAsStream = new FileInputStream(XSD_DIRECTORY + "www.w3.org-XML-1998-namespace.xsd");
        return new Input(publicId, systemId, resourceAsStream);
      }
    } catch (Exception e) {
      System.out.println("Caught Exception:");
      System.out.println("  message : " + e.getMessage());
      System.out.println("    cause : " + e.getCause());
      e.printStackTrace();
      return new Input(publicId, systemId, null);
    }
  }

} // public class ResourceResolver implements LSResourceResolver
