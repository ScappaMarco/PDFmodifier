package com.PDFmodifier.PDFmodifier.database.query;

import com.PDFmodifier.PDFmodifier.database.mappers.UserRowMapper;
import com.PDFmodifier.PDFmodifier.model.User;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.dao.EmptyResultDataAccessException;
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
        String query = "INSERT INTO users (first_name, last_name, birth_date, username, email, enc_password, account_create_date) VALUES (?, ?, ?, ?, ?, ?, ?)";
        String hashedPassword = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
        jdbcTemplate.update(query, user.getUsername(), user.getEmail(), hashedPassword, user.getAccountCreateDate());
    }

    public User getUserById(Long id) {
        String query = "SELECT * FROM users WHERE id = ?";
        try {
            return jdbcTemplate.queryForObject(query, new UserRowMapper(), id);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public User getUserByEmail(String email) {
        String query = "SELECT * FROM users WHERE email = ?";
        try {
            return jdbcTemplate.queryForObject(query, new UserRowMapper(), email);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public User getUserByUsername(String username) {
        String query = "SELECT * FROM users WHERE username = ?";
        try {
            return jdbcTemplate.queryForObject(query, new UserRowMapper(), username);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public List<User> getUserCreatedOnDate(LocalDate date) {
        String query = "SELECT * FROM users WHERE account_create_date = ?";
        return jdbcTemplate.query(query, new UserRowMapper(), Date.valueOf(date));
    }

    public List<User> getUsersCreatedBetweenDates(LocalDate startDate, LocalDate endDate) {
        String query = "SELECT * FROM users WHERE account_create_date BETWEEN ? AND ?";
        return jdbcTemplate.query(query, new UserRowMapper(), Date.valueOf(startDate), Date.valueOf(endDate));
    }

    public List<User> getAllUsers() {
        String query = "SELECT * FROM users";
        return jdbcTemplate.query(query, new UserRowMapper());
    }

    public void deleteUserById(Long id) {
        String query = "DELETE FROM users WHERE id = ?";
        jdbcTemplate.query(query, new UserRowMapper(), id);
    }

    public void deleteUserByEmail(String email) {
        String query = "DELETE FROM users where email = ?";
        jdbcTemplate.query(query, new UserRowMapper(), email);
    }

    public void deleteUserByUsername(String username) {
        String query = "DELETE FROM users WHERE username = ?";
        jdbcTemplate.query(query, new UserRowMapper(), username);
    }

    public void deleteAllUsers() {
        String query = "DELETE FROM users";
        jdbcTemplate.update(query);
    }
}