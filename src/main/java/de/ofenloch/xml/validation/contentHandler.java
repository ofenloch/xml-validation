package de.ofenloch.xml.validation;

import java.io.File;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

import javax.xml.stream.events.Namespace;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class contentHandler extends DefaultHandler {

    private Hashtable<String, Integer> tags;
    private Hashtable<String, Integer> qnames;
    private HashMap<String, String> namespaces;

    public contentHandler() {
        namespaces = new HashMap<String, String>();
        namespaces.put("http://schemas.openxmlformats.org/drawingml/2006/main", "a");
        namespaces.put("http://schemas.openxmlformats.org/drawingml/2006/chart", "c");
        namespaces.put("http://schemas.openxmlformats.org/drawingml/2006/wordprocessingDrawing", "wp");
        namespaces.put("http://schemas.openxmlformats.org/officeDocument/2006/math", "m");
        namespaces.put("http://schemas.openxmlformats.org/officeDocument/2006/relationships", "r");
        namespaces.put("http://schemas.openxmlformats.org/officeDocument/2006/docPropsVTypes", "vt");
        namespaces.put("http://schemas.openxmlformats.org/presentationml/2006/main", "p");
        namespaces.put("http://schemas.openxmlformats.org/wordprocessingml/2006/main", "w");
        namespaces.put("http://schemas.microsoft.com/office/word/2006/wordml", "wne");
    }

    public void startDocument() throws SAXException {
        tags = new Hashtable<String, Integer>();
        qnames = new Hashtable<String, Integer>();
    }

    public void endDocument() throws SAXException {
        Enumeration<String> e = tags.keys();
        while (e.hasMoreElements()) {
            String tag = (String) e.nextElement();
            int count = ((Integer) tags.get(tag)).intValue();
            System.out.println("contentHandler.endDocument: Local Name \"" + tag + "\" occurs " + count + " times");
        }
        Enumeration<String> e2 = qnames.keys();
        while (e2.hasMoreElements()) {
            String qname = (String) e2.nextElement();
            int count = ((Integer) qnames.get(qname)).intValue();
            System.out.println("contentHandler.endDocument: qName \"" + qname + "\" occurs " + count + " times");
        }
    }

    public void startElement(String namespaceURI, String localName, String qName, Attributes attributes)
            throws SAXException {

        String key = localName;
        Object value = tags.get(key);

        String key2 = qName;
        Object value2 = qnames.get(key2);

        if (value == null) {
            tags.put(key, Integer.valueOf(1));
        } else {
            int count = ((Integer) value).intValue();
            count++;
            tags.put(key, Integer.valueOf(count));
        }

        if (value2 == null) {
            qnames.put(key2, Integer.valueOf(1));
        } else {
            int count2 = ((Integer) value).intValue();
            count2++;
            qnames.put(key2, Integer.valueOf(count2));
        }
    }

    public static String convertToFileURL(String filename) {
        String path = new File(filename).getAbsolutePath();
        if (File.separatorChar != '/') {
            path = path.replace(File.separatorChar, '/');
        }

        if (!path.startsWith("/")) {
            path = "/" + path;
        }
        return "file:" + path;
    }

    // ...
} // public class contentHandler extends DefaultHandler