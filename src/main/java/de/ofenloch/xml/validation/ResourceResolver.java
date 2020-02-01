package de.ofenloch.xml.validation;

import com.sun.org.apache.xerces.internal.dom.DOMInputImpl;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Objects;

import org.w3c.dom.ls.LSInput;
import org.w3c.dom.ls.LSResourceResolver;

public class ResourceResolver implements LSResourceResolver {

  private String basePath;

  public ResourceResolver(String basePath) {
    this.basePath = basePath;
  }

  private String buildPath(String systemId) {
    return basePath == null ? systemId : String.format("%s/%s", basePath, systemId);
  }

  /**
   * this is an improvment of the original resolveResource that uses its own implementation of LSInput in class Input
   * 
   * class Input is not needed any more if we use this version
   * 
   * see thread on SO:
   * https://stackoverflow.com/questions/2342808/how-to-validate-an-xml-file-using-java-with-an-xsd-having-an-include
   */
  @Override
  public LSInput resolveResource(String type, String namespaceURI, String publicId, String systemId, String baseURI) {
    // note: in this sample, the XSD's are expected to be in the root of the
    // classpath
    // InputStream resourceAsStream = this.getClass().getClassLoader()
    // .getResourceAsStream(buildPath(systemId));
    try {
      System.out.println("ResourceResolver: looking for systemId \"" + systemId + "\" ...");
      System.out.println("               type: " + type);
      System.out.println("       namespaceURI: " + namespaceURI);
      System.out.println("           publicId: " + publicId);
      System.out.println("            baseURI: " + baseURI);
      if (systemId == null) {
        if (namespaceURI.compareTo("http://www.w3.org/XML/1998/namespace")==0) {
          systemId = "xml.xsd";
        }
      }
      String xsdPath = buildPath(systemId);
      InputStream resourceAsStream = new FileInputStream(xsdPath);
      System.out.println(" ---- found file \"" + xsdPath + "\" ...");
      Objects.requireNonNull(resourceAsStream, String.format("Could not find the specified xsd file: %s", systemId));
      return new DOMInputImpl(publicId, systemId, baseURI, resourceAsStream, "UTF-8");
    } catch (Exception ex) {
      ex.printStackTrace();
      return null;
    }
  }

  public LSInput resolveResourceUsingOwnLSInput(String type, String namespaceURI, String publicId, String systemId,
      String baseURI) {

    // type - The type of the resource being resolved. For XML [XML 1.0] resources
    // (i.e. entities), applications must use the value
    // "http://www.w3.org/TR/REC-xml". For XML Schema [XML Schema Part 1] ,
    // applications must use the value "http://www.w3.org/2001/XMLSchema". Other
    // types of resources are outside the scope of this specification and therefore
    // should recommend an absolute URI in order to use this method.
    // namespaceURI - The namespace of the resource being resolved, e.g. the target
    // namespace of the XML Schema [XML Schema Part 1] when resolving XML Schema
    // resources.
    // publicId - The public identifier of the external entity being referenced, or
    // null if no public identifier was supplied or if the resource is not an
    // entity.
    // systemId - The system identifier, a URI reference [IETF RFC 2396], of the
    // external resource being referenced, or null if no system identifier was
    // supplied.
    // baseURI - The absolute base URI of the resource being parsed, or null if
    // there is no base URI.

    // note: in this sample, the XSD's are expected to be in XSD_DIRECTORY
    try {
      System.out.println("ResourceResolver: looking for systemId \"" + systemId + "\" ...");
      System.out.println("               type: " + type);
      System.out.println("       namespaceURI: " + namespaceURI);
      System.out.println("           publicId: " + publicId);
      System.out.println("            baseURI: " + baseURI);

      if (systemId != null) {
        String xsdPath = buildPath(systemId);
        InputStream resourceAsStream = new FileInputStream(xsdPath);
        System.out.println(" ---- found file \"" + xsdPath + "\" ...");
        return new Input(publicId, systemId, resourceAsStream);
      } else {
        InputStream resourceAsStream = new FileInputStream(basePath + "/www.w3.org-XML-1998-namespace.xsd");
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
