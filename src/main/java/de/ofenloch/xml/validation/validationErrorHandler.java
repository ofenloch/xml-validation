package de.ofenloch.xml.validation;

import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

public class validationErrorHandler implements ErrorHandler {

    public void warning(SAXParseException e) {
        System.err.print("WARNING: ");
        System.out.println("  " + e.getMessage());
        System.out.println("  line " + e.getLineNumber() + " column " + e.getColumnNumber());
        System.out.println(" ");
    }

    public void error(SAXParseException e) {
        System.err.print("ERROR: ");
        System.out.println("  " + e.getMessage());
        System.out.println("  line " + e.getLineNumber() + " column " + e.getColumnNumber());
        System.out.println(" ");
    }

    public void fatalError(SAXParseException e) throws SAXException {
        System.err.print("FATAL: ");
        System.out.println("  " + e.getMessage());
        System.out.println("  line " + e.getLineNumber() + " column " + e.getColumnNumber());
        throw e;
    }

} // public class validationErrorHandler implements ErrorHandler