package net.petrikainulainen.spring.trenches;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * This class is used to configure the PDF creation process.
 *
 * @author Petri Kainulainen
 */
public class PdfFileRequest {

    private String fileName;
    private String sourceHtmlUrl;

    PdfFileRequest() {}

    public String getFileName() {
        return fileName;
    }

    public String getSourceHtmlUrl() {
        return sourceHtmlUrl;
    }

    /**
     * Sets the file name of the returned PDF file.
     * @param fileName
     */
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    /**
     * Sets the URL of the HTML document that is transformed into PDF.
     * @param sourceHtmlUrl
     */
    public void setSourceHtmlUrl(String sourceHtmlUrl) {
        this.sourceHtmlUrl = sourceHtmlUrl;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("fileName", this.fileName)
                .append("sourceHtmlUrl", this.sourceHtmlUrl)
                .toString();
    }
}
