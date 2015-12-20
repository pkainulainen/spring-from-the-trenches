package net.petrikainulainen.spring.trenches;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

/**
 * Provides public API that is used to create PDF documents from HTML documents.
 * @author Petri Kainulainen
 */
@RestController
class PdfController {

    private static final Logger LOGGER = LoggerFactory.getLogger(PdfController.class);

    private final PdfFileCreator pdfFileCreator;

    @Autowired
    PdfController(PdfFileCreator pdfFileCreator) {
        this.pdfFileCreator = pdfFileCreator;
    }

    /**
     * Creates a PDF file from an HTML document and writes the created file to the response.
     * @param fileRequest   Configures the PDF creation process.
     * @param response      The HTTP response in which the created PDF file is written.
     */
    @RequestMapping(value = "/api/pdf", method = RequestMethod.POST)
    void createPdf(@RequestBody PdfFileRequest fileRequest, HttpServletResponse response) {
        LOGGER.info("Creating PDF file from request: {}", fileRequest);

        pdfFileCreator.writePdfToResponse(fileRequest, response);

        LOGGER.info("Created PDF file from request: {}", fileRequest);
    }
}
