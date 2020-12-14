package com.faculty.security;

import com.faculty.exception.ValidationException;
import com.faculty.model.FacultyUser;
import com.faculty.repository.facultyUser.FacultyUserDao;
import com.faculty.service.FacultyUserService;
import com.faculty.service.Impl.FacultyUserServ;
import com.faculty.service.Impl.FacultyUserServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private FacultyUserService  facultyUserService;

    private static Logger logger = LoggerFactory.getLogger(MyUserDetailsService.class);

    public MyUserDetailsService() {}

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException { // (1)
        logger.info("username {}", username);
        FacultyUser facultyUser = null;
        List<GrantedAuthority> grantedAuthoritiesList = new ArrayList();
        System.out.println("user name: " + username);
        //String usernam = "alenadev";
        try {
            facultyUser = facultyUserService.getUserByLogin(username);
            logger.info("Found user : {} with role: {}", facultyUser.getLogin(), facultyUser.getRole());
            //grantedAuthorities.add(new SimpleGrantedAuthority(facultyUser.getRole()));
            //
            if (facultyUser.getRole().equals("admin")) {
                //GrantedAuthority authorityAdmin = new SimpleGrantedAuthority("ROLE_ADMIN"); // ROLE_ADMIN
                //GrantedAuthority authorityUser = new SimpleGrantedAuthority("ROLE_USER");
                GrantedAuthority authorityAdmin = new SimpleGrantedAuthority("ADMIN"); // ROLE_ADMIN
                GrantedAuthority authorityUser = new SimpleGrantedAuthority("USER");
                grantedAuthoritiesList.add(authorityAdmin);
                grantedAuthoritiesList.add(authorityUser);
            } else {
                //GrantedAuthority authorityUser = new SimpleGrantedAuthority("ROLE_USER");
                GrantedAuthority authorityUser = new SimpleGrantedAuthority("USER");
                grantedAuthoritiesList.add(authorityUser);
            }
//            for(Role role : facultyUser.getRole()){
//            }
            if (facultyUser == null) {
                logger.error("User with name " + username + " wasn't found in the database size " + username.length());
                throw new UsernameNotFoundException("User " + facultyUser.getFirstName() + " wasn't found in the database");
            }
        } catch (ValidationException e) {
            e.printStackTrace();
        }
        return new org.springframework.security.core.userdetails.User(facultyUser.getLogin(), facultyUser.getPassword(), grantedAuthoritiesList);
    }
}
