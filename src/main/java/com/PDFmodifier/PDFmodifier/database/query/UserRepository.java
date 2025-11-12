package com.PDFmodifier.PDFmodifier.database.query;

import com.PDFmodifier.PDFmodifier.database.mappers.UserRowMapper;
import com.PDFmodifier.PDFmodifier.model.User;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Repository
public class UserRepository {

    private final JdbcTemplate jdbcTemplate;

    public UserRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void insertUser(User user) {
        String query = "INSERT INTO users (username, email, enc_password, account_create_date) VALUES (?, ?, ?, ?, ?)";
        String hashedPassword = BCrypt.hashpw(user.getEncodedPassword(), BCrypt.gensalt());
        jdbcTemplate.update(query, user.getUsername(), user.getEmail(), hashedPassword, user.getAccountCreateDate());
    }

    public User getUserById(Long id) {
        String query = "SELECT id, username, email, account_create_date FROM users WHERE id = ?";
        return jdbcTemplate.queryForObject(query, new UserRowMapper(), id);
    }

    public User getUSerByEmail(String email) {
        String query = "SELECT id, username, email, account_create_date FROM users WHERE email = ?";
        return jdbcTemplate.queryForObject(query, new UserRowMapper(), email);
    }

    public User getUserByUsername(String username) {
        String query = "SELECT id, username, email, account_create_date FROM users WHERE username = ?";
        return jdbcTemplate.queryForObject(query, new UserRowMapper(), username);
    }

    public List<User> getUserCreatedOnDate(LocalDate date) {
        String query = "SELECT id, username, email, account_create_date FROM users WHERE account_create_date = ?";
        return jdbcTemplate.query(query, new UserRowMapper(), Date.valueOf(date));
    }

    public List<User> getUsersCreatedBetweenDates(LocalDate startDate, LocalDate endDate) {
        String query = "SELECT id, username, email, account_create_date FROM users WHERE account_create_date BETWEEN ? AND ?";
        return jdbcTemplate.query(query, new UserRowMapper(), Date.valueOf(startDate), Date.valueOf(endDate));
    }

    public List<User> getAllUsers() {
        String query = "SELECT * FROM users";
        return jdbcTemplate.query(query, new UserRowMapper());
    }
}