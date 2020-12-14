package com.faculty.controller.integration;

import com.faculty.service.CourseService;
import com.faculty.service.FacultyUserService;
import com.faculty.service.TopicService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

//import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.*;

public class UserControllerITest extends AbstractBaseSpringTest {

    private MockMvc mockMvc;

    @Autowired
    private TopicService topicService;

    @Autowired
    private CourseService courseService;

    @Autowired
    private FacultyUserService facultyUserService;

    @Autowired
    public WebApplicationContext wac;

    @Before
    public void init() {
        mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

    @Test
    @Transactional
    public void testUserController() throws Exception {
        mockMvc.perform(get("/education-faculty.com.ua/user/courses"))
                .with(user("alenadev").password("iq450").roles("USER"))
                .andExpect(MockMvcResultMatchers.status().is(200));
    }

}
