package com.isa.web;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
public class ApplicationTest {

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    @Before
    public void setUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    @Test
    public void shouldFetchIndexPage() throws Exception {
        mockMvc.perform(get("/index.html")).andDo(print()).andExpect(status().isOk());
    }

    @Test
    public void shouldFetchPage_UnderPublicFolder() throws Exception {
        mockMvc.perform(get("/underPublic.html")).andDo(print()).andExpect(status().isOk());
    }

    @Test
    public void shouldFetchPage_UnderResourcesFolder() throws Exception {
        mockMvc.perform(get("/underResources.html")).andDo(print()).andExpect(status().isOk());
    }

    @Test
    public void shouldFetchPage_UnderCustomFolder() throws Exception {
        mockMvc.perform(get("/custom/underCustom.html")).andDo(print()).andExpect(status().isOk());
    }

    @Test
    public void shouldFetchFile() throws Exception {
        mockMvc.perform(get("/files/file.txt")).andDo(print()).andExpect(status().isOk());
    }

    @Test
    public void shouldFetchFile_FromAlternativeLocation() throws Exception {
        mockMvc.perform(get("/other-files/file.txt")).andDo(print()).andExpect(status().isOk());
    }

    @Test
    public void shouldFetchFile_InZippedFormat() throws Exception {
        mockMvc.perform(get("/files/file.txt").header("Accept-Encoding", "gzip, deflate"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(header().string("Content-Encoding", "gzip"));

    }

    @Test
    public void shouldFetchFile_InZippedFormat_FromAlternativeLocation() throws Exception {
        mockMvc.perform(get("/other-files/file.txt").header("Accept-Encoding", "gzip", "deflate"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(header().string("Content-Encoding", "gzip"));
    }

    @Test
    public void shouldFetchResource() throws Exception {
        mockMvc.perform(get("/images/spring.jpg")).andDo(print()).andExpect(status().isOk());
    }

    @Test
    public void shouldFetchResource_FromAlternativeLocation() throws Exception {
        mockMvc.perform(get("/other-images/spring.jpg")).andDo(print()).andExpect(status().isOk());
    }
}
