package com.epam.rd.repository;

import com.epam.rd.entity.FacultyUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FacultyUserRepository extends JpaRepository<FacultyUser, Long> {

    @Query("SELECT f FROM FacultyUser f WHERE f.userId = ?1")
    FacultyUser findFacultyUserByUserId(Long userId);
    List<FacultyUser> findAll();
    List<FacultyUser> findFacultyUserByRole(String role);
    List<FacultyUser> findByRoleOrderByTelephone(String role);
    FacultyUser save(FacultyUser entity);

    @Modifying
    @Query("DELETE FROM FacultyUser f WHERE f.userId = ?1")
    void deleteById(Long id);

    @Modifying
    @Query("UPDATE FacultyUser f SET f.firstName = ?1, f.lastName = ?2 WHERE f.userId = ?3")
    int updateTitleById(String firstName, String LastName, Long userId);
/*
    @Modifying
    @Query("UPDATE Note n SET n.title = ?1 WHERE n.id IN ?2")
    int bulkUpdateTitle(String title, Set<Long> id); */
}
