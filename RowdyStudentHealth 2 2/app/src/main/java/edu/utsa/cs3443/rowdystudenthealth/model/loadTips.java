package edu.utsa.cs3443.rowdystudenthealth.model;

import android.content.res.AssetManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class loadTips {

    private String text;

    /**
     * Load tips constructor
     */
    public loadTips() {
        text = "";
    }

    /**
     * Getter for string text
     * @return
     */
    public String getText() {
        return text;
    }

    /**
     * Setter for string text
     * @param text
     */
    public void setText(String text) {
        this.text = text;
    }

    /**
     * Method called loadText to load information by reading the various files in the assets folder.
     *
     * @param manager
     * @param filename
     */
    public void loadText(AssetManager manager, String filename) {
        StringBuilder sb = new StringBuilder();
        try {
            InputStream file = manager.open(filename);
            BufferedReader br = new BufferedReader(new InputStreamReader(file));
            String line;
            while((line = br.readLine()) != null) {
                sb.append(line).append("\n");
            }
            br.close();
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        setText(sb.toString());
    }
}