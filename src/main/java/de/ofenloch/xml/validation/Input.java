package de.ofenloch.xml.validation;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;

import org.w3c.dom.ls.LSInput;

public class Input implements LSInput {

  private String publicId;

  private String systemId;

  private String encoding;

  public String getPublicId() {
    return publicId;
  }

  public void setPublicId(String publicId) {
    this.publicId = publicId;
  }

  public String getBaseURI() {
    return null;
  }

  public InputStream getByteStream() {
    return null;
  }

  public boolean getCertifiedText() {
    return false;
  }

  public Reader getCharacterStream() {
    return null;
  }

  public String getEncoding() {
    return null;
  }

  public String getStringData() {
    synchronized (inputStream) {
      try {
        byte[] input = new byte[inputStream.available()];
        inputStream.read(input);
        String contents = new String(input);
        return new String(contents.getBytes(encoding));
      } catch (IOException e) {
        e.printStackTrace();
        System.out.println("Exception " + e);
        return null;
      }
    }
  }

  public void setBaseURI(String baseURI) {
  }

  public void setByteStream(InputStream byteStream) {
  }

  public void setCertifiedText(boolean certifiedText) {
  }

  public void setCharacterStream(Reader characterStream) {
  }

  public void setEncoding(String encoding) {
    this.encoding = encoding;
  }

  public void setStringData(String stringData) {
  }

  public String getSystemId() {
    return systemId;
  }

  public void setSystemId(String systemId) {
    this.systemId = systemId;
  }

  public BufferedInputStream getInputStream() {
    return inputStream;
  }

  public void setInputStream(BufferedInputStream inputStream) {
    this.inputStream = inputStream;
  }

  private BufferedInputStream inputStream;

  public Input(String publicId, String sysId, InputStream input) {
    this.encoding = "utf-8";
    this.publicId = publicId;
    this.systemId = sysId;
    this.inputStream = new BufferedInputStream(input);
  }
} // public class Input implements LSInput