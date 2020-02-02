package de.ofenloch.xml.validation;

import java.io.PrintStream;

import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

public class validationErrorHandler implements ErrorHandler {

    private PrintStream out;
    private int nWarnings;
    private int nErrors;
    private int nFatals;

    public validationErrorHandler(PrintStream printStream) {
        this.out = printStream;
        this.nWarnings = 0;
        this.nErrors = 0;
        this.nFatals = 0;
    }

    public void warning(SAXParseException e) {
        nWarnings++;
        out.print("WARNING: ");
        out.println("  " + e.getMessage());
        out.println("  line " + e.getLineNumber() + " column " + e.getColumnNumber());
        out.println(" ");
    }

    public void error(SAXParseException e) {
        nErrors++;
        out.print("ERROR: ");
        out.println("  " + e.getMessage());
        out.println("  line " + e.getLineNumber() + " column " + e.getColumnNumber());
        out.println(" ");
    }

    public void fatalError(SAXParseException e) throws SAXException {
        nFatals++;
        out.print("FATAL: ");
        out.println("  " + e.getMessage());
        out.println("  line " + e.getLineNumber() + " column " + e.getColumnNumber());
        throw e;
    }

    public int getnWarnings() {
        return nWarnings;
    }

    public int getnErrors() {
        return nErrors;
    }

    public int getnFatals() {
        return nFatals;
    }

    public void reset() {
        this.nWarnings = 0;
        this.nErrors = 0;
        this.nFatals = 0;
    }

} // public class validationErrorHandler implements ErrorHandler