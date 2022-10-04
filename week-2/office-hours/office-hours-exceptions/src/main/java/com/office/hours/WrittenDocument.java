package com.office.hours;

import com.office.hours.exceptions.FileTooLargeException;

import java.util.Objects;

public class WrittenDocument {
    private String text;
    private String filename;

    private static int MAX_FILENAME_SIZE = 50;
    private static int MAX_FILE_SIZE = 100;

    public WrittenDocument(String filename) throws Exception {
        if(filename.length() > MAX_FILENAME_SIZE) {
            throw new Exception("Filename must not exceed " +  MAX_FILENAME_SIZE + " characters");
        }
        this.filename = filename;
        this.text ="";
    }

    public int addText(String newText) {
        if (newText.length() + this.text.length() > MAX_FILE_SIZE) {
            throw new FileTooLargeException("Adding this text will exceed the maximum file size of " + MAX_FILE_SIZE);
        }
        this.text = this.text + newText;
        return this.text.length();
    }

    // Puts the filename at the top of the file.
    public int prependFilenameToFileBody() {
        String fileText = this.text;
        this.text = "";
        addText(this.filename);
        addText(fileText);

        return this.text.length();
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WrittenDocument that = (WrittenDocument) o;
        return Objects.equals(text, that.text) && Objects.equals(filename, that.filename);
    }

    @Override
    public int hashCode() {
        return Objects.hash(text, filename);
    }

    @Override
    public String toString() {
        return "WrittenDocument{" +
                "text='" + text + '\'' +
                ", filename='" + filename + '\'' +
                '}';
    }
}
