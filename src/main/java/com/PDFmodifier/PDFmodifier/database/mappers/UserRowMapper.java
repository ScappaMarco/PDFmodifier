package com.PDFmodifier.PDFmodifier.database.mappers;

import com.PDFmodifier.PDFmodifier.model.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRowMapper implements RowMapper<User> {

    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        User u = new User();
        u.setId(rs.getLong("id"));
        u.setUsername(rs.getString("username"));
        u.setEmail(rs.getString("email"));

        Date date = rs.getDate("account_create_date");
        if(date != null) {
            u.setAccountCreateDate(date.toLocalDate());
        }

        return u;
    }
}