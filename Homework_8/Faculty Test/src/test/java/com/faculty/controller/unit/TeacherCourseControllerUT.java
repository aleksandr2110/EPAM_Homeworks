package com.faculty.controller.unit;


import com.faculty.controller.TeacherCourse;
import com.faculty.exception.CrudException;
import com.faculty.model.*;
import com.faculty.service.*;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

public class TeacherCourseControllerUT {

    private TopicService topicService = Mockito.mock(TopicService.class);
    private CourseService courseService = Mockito.mock(CourseService.class);
    private FacultyUserService facultyUserService = Mockito.mock(FacultyUserService.class);
    private CourseTopicService courseTopicService = Mockito.mock(CourseTopicService.class);
    private RegistrationService registrationService = Mockito.mock(RegistrationService.class);
    private MockHttpSession session = new MockHttpSession();

    TeacherCourse teacherCourse = new TeacherCourse();
    private final List<Topic> listTopic = new ArrayList();
    private final List<FacultyUser> listUsers = new ArrayList();
    private final List<CourseTopic> listCourseTopics = new ArrayList();
    private final List<Registration> registrationStudents = new ArrayList();
    Course course;

    @Before
    public void init() {
        initialize();
    }

    @Test
    public void getTeacherCourse() throws Exception {
        // GIVEN
        // WHEN
        when(topicService.getTopicsPerCourse(any())).thenReturn(listTopic);
        when(courseService.extractCourse(any())).thenReturn(course);
        when(courseTopicService.getListTopicById(any())).thenReturn(listCourseTopics);
        when(registrationService.getListRegistrationOnCourseId(any())).thenReturn(registrationStudents);
        when(facultyUserService.getUserOnListUserId(any())).thenReturn(listUsers);
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(teacherCourse).build();
        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get("/education-faculty.com.ua/teacher-page/course/id=2")
                .session(session);
        mockMvc.perform(builder)
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(view().name("teacher/teacherCourse"))
                .andExpect(forwardedUrl("teacher/teacherCourse"));
        verify(courseService, times(1)).extractCourse(any());
        verify(courseTopicService, times(1)).getListTopicById(any());
        verify(topicService, times(1)).getTopicsPerCourse(any());
        verify(registrationService, times(1)).getListRegistrationOnCourseId(any());
        verify(facultyUserService, times(1)).getUserOnListUserId(any());
    }

    public void initialize()
    {
        String strDate_1 = "2020-11-10 15:00";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime dateTime = LocalDateTime.parse(strDate_1, formatter);
        course = new Course(1L,"Java for beginners", dateTime,
                12, 0, 6,"Не начат",6000);
        CourseTopic courseTopic1 = new CourseTopic(1, 1, 1);
        CourseTopic courseTopic2 = new CourseTopic(2, 1, 2);
        CourseTopic courseTopic3 = new CourseTopic(3, 1, 3);
        listCourseTopics.add(courseTopic1);
        listCourseTopics.add(courseTopic2);
        listCourseTopics.add(courseTopic3);
        Topic topic_1 = new Topic(1, "HTML введение");
        Topic topic_2 = new Topic(2, "HTML ссылки, навигация");
        Topic topic_3 = new Topic(3, "HTML медиаконтент");
        listTopic.add(topic_1);
        listTopic.add(topic_2);
        listTopic.add(topic_3);
        Registration registration_1 = new Registration(1, 1, 2, 5, true);
        Registration registration_2 = new Registration(2, 1, 3, 8, true);
        registrationStudents.add(registration_1);
        registrationStudents.add(registration_2);
        FacultyUser user_1 = new FacultyUser(2,"Георгий","Молчанов","georgiydev",
                "$2a$08$WPJaYy9a1dKH8yALypSu.O4Mjqq3N5wSjpS2v2/C/ZMCNNQ0bunFu","georgiy-epam@gmail.com",
                "0982278171","user", false);
        FacultyUser user_2 = new FacultyUser(3,"Андрей","Андриянов","adnrey",
                "$2a$08$WPJaYy9a1dKH8yALypSu.O4Mjqq3N5wSjpS2v2/C/ZMCNNQ0bunFu","andrey@gmail.com",
                "0982278173","user", false);
        listUsers.add(user_1);
        listUsers.add(user_2);
        ReflectionTestUtils.setField(teacherCourse, "courseService", courseService);
        ReflectionTestUtils.setField(teacherCourse, "topicService", topicService);
        ReflectionTestUtils.setField(teacherCourse, "registrationService", registrationService);
        ReflectionTestUtils.setField(teacherCourse, "facultyUserService", facultyUserService); // listCourseTopics
        ReflectionTestUtils.setField(teacherCourse, "courseTopicService", courseTopicService);
    }
}
