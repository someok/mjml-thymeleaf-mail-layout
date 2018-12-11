package com.example.mail;

import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.DefaultMockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

/**
 * @author wjx
 * @version 1.0.0
 */
@ContextConfiguration
public abstract class AbstractControllerTest extends AbstractTest {

    @Autowired
    protected WebApplicationContext context;

    protected MockMvc mockMvc;

    public MockMvc getMockMvc() {
        return mockMvc;
    }

    @Before
    public void initMockMvc() {
        mockMvc = mockMvcBuilder().build();
    }

    protected DefaultMockMvcBuilder mockMvcBuilder() {
        return MockMvcBuilders.webAppContextSetup(context);
    }
}
