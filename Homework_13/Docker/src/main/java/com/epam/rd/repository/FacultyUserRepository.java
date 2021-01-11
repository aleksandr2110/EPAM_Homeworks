package com.epam.rd.repository;

import com.epam.rd.entity.FacultyUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FacultyUserRepository extends JpaRepository<FacultyUser, Long> {

    @Query("SELECT f FROM FacultyUser f WHERE f.userId = ?1")
    FacultyUser findFacultyUserByUserId(Long userId);
    List<FacultyUser> findAll();
    List<FacultyUser> findFacultyUserByRole(String role);
    List<FacultyUser> findByRoleOrderByTelephone(String role);
}
