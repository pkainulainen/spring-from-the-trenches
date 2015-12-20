package net.petrikainulainen.spring.trenches;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nitorcreations.junit.runners.NestedRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static info.solidsoft.mockito.java8.AssertionMatcher.assertArg;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author Petri Kainulainen
 */
@RunWith(NestedRunner.class)
public class PdfControllerTest {

    private MockMvc mockMvc;
    private PdfFileCreator pdfFileCreator;

    @Before
    public void initSystemUnderTest() {
        pdfFileCreator = mock(PdfFileCreator.class);
        mockMvc = MockMvcBuilders.standaloneSetup(new PdfController(pdfFileCreator))
                .build();
    }

    public class CreatePdf {

        private final String PDF_FILE_NAME = "file.pdf";
        private final String SOURCE_HTML_URL = "http://www.google.com";

        private PdfFileRequest fileRequest;

        @Before
        public void createFileRequest() {
            fileRequest = new PdfFileRequest();
            fileRequest.setFileName(PDF_FILE_NAME);
            fileRequest.setSourceHtmlUrl(SOURCE_HTML_URL);
        }

        @Test
        public void shouldReturnResponseStatusOk() throws Exception {
            mockMvc.perform(post("/api/pdf")
                    .contentType(MediaType.APPLICATION_JSON_UTF8)
                    .content(convertObjectToJsonBytes(fileRequest))
            )
                    .andExpect(status().isOk());
        }

        @Test
        public void shouldPassFileNameForwardToPdfCreator() throws Exception {
            mockMvc.perform(post("/api/pdf")
                    .contentType(MediaType.APPLICATION_JSON_UTF8)
                    .content(convertObjectToJsonBytes(fileRequest))
            );

            verify(pdfFileCreator, times(1)).writePdfToResponse(
                    assertArg(fileRequest -> assertThat(fileRequest.getFileName()).isEqualTo(PDF_FILE_NAME)),
                    isA(HttpServletResponse.class)
            );
        }

        @Test
        public void shouldPassSourceHtmlUrlForwardToPdfCreator() throws Exception {
            mockMvc.perform(post("/api/pdf")
                    .contentType(MediaType.APPLICATION_JSON_UTF8)
                    .content(convertObjectToJsonBytes(fileRequest))
            );

            verify(pdfFileCreator, times(1)).writePdfToResponse(
                    assertArg(fileRequest -> assertThat(fileRequest.getSourceHtmlUrl()).isEqualTo(SOURCE_HTML_URL)),
                    isA(HttpServletResponse.class)
            );
        }

        private byte[] convertObjectToJsonBytes(Object object) throws IOException {
            ObjectMapper mapper = new ObjectMapper();
            mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
            return mapper.writeValueAsBytes(object);
        }
    }
}
