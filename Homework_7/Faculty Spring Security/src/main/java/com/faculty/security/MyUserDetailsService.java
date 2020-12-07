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
    @Qualifier("faculty")
    private FacultyUserServ facultyUserService;
////    private FacultyUserDao facultyUserService;

//    public FacultyUserServ facultyUserServ() {
//        return new FacultyUserServ();
//    }
    private static Logger logger = LoggerFactory.getLogger(MyUserDetailsService.class);

    public MyUserDetailsService() {}

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException { // (1)
        FacultyUser facultyUser = null;
        try {
            facultyUser = facultyUserService.getUserByLogin(username);
            if (facultyUser == null) {
                throw new UsernameNotFoundException("User " + facultyUser.getFirstName() + " wasn't found in the database");
            }

        } catch (ValidationException e) {
            e.printStackTrace();
        }
        logger.info("Found User: " + facultyUser.toString());
        List<GrantedAuthority> grantedAuthoritiesList = new ArrayList<GrantedAuthority>();
        if(facultyUser.getRole().equals("admin")) {
            GrantedAuthority authorityAdmin = new SimpleGrantedAuthority("ROLE_ADMIN");
            GrantedAuthority authorityUser = new SimpleGrantedAuthority("ROLE_USER");
            grantedAuthoritiesList.add(authorityAdmin);
            grantedAuthoritiesList.add(authorityUser);

        }
        else {
            GrantedAuthority authorityUser = new SimpleGrantedAuthority("ROLE_USER");
            grantedAuthoritiesList.add(authorityUser);

        }
        final FacultyUser user = facultyUser;
        UserDetails userDetails = new UserDetails() {
            @Override
            public Collection<? extends GrantedAuthority> getAuthorities() {
                return grantedAuthoritiesList;
            }

            @Override
            public String getPassword() {
                return user.getPassword();
            }

            @Override
            public String getUsername() {
                return user.getLogin();
            }

            @Override
            public boolean isAccountNonExpired() {
                return true;
            }

            @Override
            public boolean isAccountNonLocked() {
                return true;
            }

            @Override
            public boolean isCredentialsNonExpired() {
                return true;
            }

            @Override
            public boolean isEnabled() {
                return true;
            }
        };
        return userDetails;
    }
}
