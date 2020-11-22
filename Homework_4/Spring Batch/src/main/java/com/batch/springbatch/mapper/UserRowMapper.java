package com.batch.springbatch.mapper;

import com.batch.springbatch.model.User;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class UserRowMapper implements RowMapper<User> {

    @Override
    public User mapRow(ResultSet resultSet, int i) throws SQLException {
        String firstName = resultSet.getString("firstName");
        String lastName = resultSet.getString("lastName");
        Double balance = resultSet.getDouble("balance");
        String email = resultSet.getString("email");
        return new User(firstName, lastName, balance, email);
    }
}
