package com.faculty.controller.unit;

import com.faculty.controller.UserCourses;
import com.faculty.converter.UserConverter;
import com.faculty.dto.FacultyUserDto;
import com.faculty.model.Course;
import com.faculty.model.FacultyUser;
import com.faculty.model.Topic;
import com.faculty.service.CourseService;
import com.faculty.service.FacultyUserService;
import com.faculty.service.TopicService;
import com.faculty.util.Command;
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

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


public class UserCoursesControllerUT {

    private TopicService topicService = Mockito.mock(TopicService.class);
    private CourseService courseService = Mockito.mock(CourseService.class);
    private FacultyUserService facultyUserService = Mockito.mock(FacultyUserService.class);
    //private HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
    private Command command = Mockito.mock(Command.class);
    UserCourses userCourses = new UserCourses();
    private final List<Topic> listTopic = new ArrayList();
    private final List<Course> listCourses = new ArrayList();
    private final List<FacultyUser> teachers = new ArrayList();
    private FacultyUser facultUser;
    private FacultyUserDto userDto;

    @Before
    public void init() {
        ReflectionTestUtils.setField(userCourses, "topicService", topicService);
        ReflectionTestUtils.setField(userCourses, "courseService", courseService);
        ReflectionTestUtils.setField(userCourses, "facultyUserService", facultyUserService);
        ReflectionTestUtils.setField(userCourses, "command", command);
        Topic topic_1 = new Topic(2, "HTML введение");
        Topic topic_2 = new Topic(3, "HTML ссылки, навигация");
        listTopic.add(topic_1);
        listTopic.add(topic_2);
        String strDate_1 = "2020-11-10 15:00";
        String strDate_2 = "2020-11-12 18:00";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime dateTime = LocalDateTime.parse(strDate_1, formatter);
        LocalDateTime dateTime2 = LocalDateTime.parse(strDate_2, formatter);
        Course course_1 = new Course(1L,"Java for beginners", dateTime,
                12, 0, 6,"Не начат",6000);
        Course course_2 = new Course(2L,"Html layout", dateTime2,
                10, 0, 6,"Не начат",5000);
        listCourses.add(course_1);
        listCourses.add(course_2);
        facultUser = new FacultyUser(6,"Георгий","Молчанов","georgiydev",
                "$2a$08$WPJaYy9a1dKH8yALypSu.O4Mjqq3N5wSjpS2v2/C/ZMCNNQ0bunFu","georgiy-epam@gmail.com",
                "0982278178","teacher", false);
        userDto = UserConverter.userToUserViewDto(facultUser);
        teachers.add(facultUser);
    }

    @Test
    public void getUserCourses() throws Exception {
        // GIVEN
        // WHEN
        when(topicService.listOfTopics()).thenReturn(listTopic);
        when(courseService.extractCourses()).thenReturn(listCourses);
        when(facultyUserService.getUserByRole("teacher")).thenReturn(teachers);
        MockHttpSession session = new MockHttpSession();
        session.setAttribute("listCourses", null);
        // session.setAttribute("listCourses", listCourses); // but we have to delete two last cases with verify
        final MockMvc mockMvc = MockMvcBuilders.standaloneSetup(userCourses).build();
        // THEN
        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get("/education-faculty.com.ua/user/courses")
                .session(session);
        mockMvc.perform(builder)
                .andExpect(status().is(200))
                .andExpect(view().name("user/userCourses"))
                .andExpect(forwardedUrl("user/userCourses"));
        verify(topicService, times(1)).listOfTopics();
        verify(courseService, times(1)).extractCourses();
        verify(facultyUserService, times(1)).getUserByRole("teacher");
        verifyNoMoreInteractions(topicService);
    }

    @Test
    public void postUserCourses() throws Exception  {
        // GIVEN
        // WHEN
        MockHttpSession session = new MockHttpSession();
        session.setAttribute("user", userDto);
        session.setAttribute("listCourses", listCourses);
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(userCourses).build();
        // THEN
        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post("/education-faculty.com.ua/user/courses/*")
                .session(session);
        mockMvc.perform(builder)
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(view().name("education-faculty.com.ua/user/courses/*"))
                .andExpect(forwardedUrl("education-faculty.com.ua/user/courses/*"));
    }
}
