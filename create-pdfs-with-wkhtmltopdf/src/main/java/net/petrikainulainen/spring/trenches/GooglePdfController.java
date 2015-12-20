package net.petrikainulainen.spring.trenches;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * @author Petri Kainulainen
 */
@Controller
class GooglePdfController {

    private static final Logger LOGGER = LoggerFactory.getLogger(GooglePdfController.class);

    private final String pdfServiceUrl;
    private final RestTemplate restTemplate;

    @Autowired
    GooglePdfController(@Value("${pdf.service.url}") String pdfServiceUrl, RestTemplate restTemplate) {
        this.pdfServiceUrl = pdfServiceUrl;
        this.restTemplate = restTemplate;
    }

    @RequestMapping(value = "/pdf/google", method = RequestMethod.GET)
    void createPdfFromGoogle(HttpServletResponse response) {
        LOGGER.info("Creating PDF file from www.google.com");

        PdfFileRequest fileRequest = new PdfFileRequest();
        fileRequest.setFileName("google.pdf");
        fileRequest.setSourceHtmlUrl("http://www.google.com");

        byte[] pdfFile = restTemplate.postForObject(pdfServiceUrl,
                fileRequest, byte[].class);
        writePdfFileToResponse(pdfFile, "google.pdf", response);

        LOGGER.info("Created PDF file from www.google.com");
    }

    private void writePdfFileToResponse(byte[] pdfFile, String fileName, HttpServletResponse response) {
        try (InputStream in = new ByteArrayInputStream(pdfFile)) {
            OutputStream out = response.getOutputStream();
            IOUtils.copy(in, out);
            out.flush();

            response.setContentType("application/pdf");
            response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
        }
        catch (IOException ex) {
            LOGGER.error("Could not write PDF File to the response because an exception was thrown: ", ex);
            throw new RuntimeException("Error occurred when creating PDF file", ex);
        }
    }
}
