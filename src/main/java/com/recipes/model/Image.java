package com.recipes.model;

import org.apache.commons.io.FileUtils;

import javax.persistence.Entity;
import javax.persistence.Lob;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

@Entity
public class Image extends GenericEntity {
    private String placeholder;
    @Lob
    private byte[] byteArray;
    private String urlString;

    public Image() {
        super();
    }

    @SuppressWarnings("ResultOfMethodCallIgnored")
    public Image(String placeholder, String urlString) {
        this();
        this.placeholder = placeholder;
        this.urlString = urlString;
        URL url1;
        try {
            url1 = new URL(urlString);
            String tDir = System.getProperty("java.io.tmpdir");
            String path = tDir + "tmp" + ".pdf";
            File file = new File(path);
            FileUtils.copyURLToFile(url1, file);
            byteArray = new byte[(int) file.length()];
            FileInputStream fileInputStream = new FileInputStream(file);
            fileInputStream.read(byteArray);
            fileInputStream.close();
            file.delete();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getPlaceholder() {
        return placeholder;
    }

    public void setPlaceholder(String placeholder) {
        this.placeholder = placeholder;
    }

    public byte[] getByteArray() {
        return byteArray;
    }

    public void setByteArray(byte[] byteArray) {
        this.byteArray = byteArray;
    }

    public String getUrlString() {
        return urlString;
    }

    public void setUrlString(String urlString) {
        this.urlString = urlString;
    }
}
