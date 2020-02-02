package de.ofenloch.xml.validation;

import java.io.PrintStream;

import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

public class validationErrorHandler implements ErrorHandler {
    PrintStream out;

    public validationErrorHandler(PrintStream printStream) {
        this.out = printStream;
    }

    public void warning(SAXParseException e) {
        out.print("WARNING: ");
        out.println("  " + e.getMessage());
        out.println("  line " + e.getLineNumber() + " column " + e.getColumnNumber());
        out.println(" ");
    }

    public void error(SAXParseException e) {
        out.print("ERROR: ");
        out.println("  " + e.getMessage());
        out.println("  line " + e.getLineNumber() + " column " + e.getColumnNumber());
        out.println(" ");
    }

    public void fatalError(SAXParseException e) throws SAXException {
        out.print("FATAL: ");
        out.println("  " + e.getMessage());
        out.println("  line " + e.getLineNumber() + " column " + e.getColumnNumber());
        throw e;
    }

} // public class validationErrorHandler implements ErrorHandler