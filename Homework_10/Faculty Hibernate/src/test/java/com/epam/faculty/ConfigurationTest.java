package com.epam.faculty;

import org.hibernate.SessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.persistence.metamodel.EntityType;
import java.util.Set;

//@ExtendWith(value = {SpringExtension.class})
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = HibernateConfig.class)
public class ConfigurationTest {

    @Autowired
    private SessionFactory sessionFactory;

    @Test
    public void testConfigurationWorks() {
        Set<EntityType<?>> entities = sessionFactory.getMetamodel().getEntities();
        for (EntityType<?> entity : entities) {
            System.out.println(entity.getName());
        }
    }

}
