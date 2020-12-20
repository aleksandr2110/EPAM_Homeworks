package com.epam.faculty.dao;

import com.epam.faculty.entity.Registration;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;
import java.util.UUID;

@Repository
public class RegistrationDaoImpl implements RegistrationDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void save(Registration registration) {
        Session session = sessionFactory.getCurrentSession();
        session.save(registration);
    }

    @Override
    public Registration get(UUID registrationId) {
        Session session = sessionFactory.getCurrentSession();
        Registration registration = session.get(Registration.class, registrationId);
        return Objects.requireNonNull(registration, "Registration not found on registration id: " + registrationId);
    }
}
