package com.PDFmodifier.PDFmodifier.database.query;

import com.PDFmodifier.PDFmodifier.database.mappers.DocumentRowMapper;
import com.PDFmodifier.PDFmodifier.model.Document;
import com.PDFmodifier.PDFmodifier.model.User;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Repository
public class DocumentRepository {

    private final JdbcTemplate jdbcTemplate;

    public DocumentRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void insertDocument(Document document) {
        String query = "INSERT INTO documents (file_name, file_path, upload_date, pages_number, owner_id) VALUES (?, ?, ?, ?, ?)";
        jdbcTemplate.update(query, document.getFilerName(), document.getFilePath(), document.getUploadDate(), document.getPagesNumber(), document.getOwnerId());
    }

    public Document getDocumentById(Long id) {
        String query = "SELECT * FROM documents WHERE id = ?";
        try {
            return jdbcTemplate.queryForObject(query, new DocumentRowMapper(), id);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public List<Document> getDocumentsByName(String fileName) {
        String query = "SELECT * FROM documents WHERE file_name = ?";
        try {
            return jdbcTemplate.query(query, new DocumentRowMapper(), fileName);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public List<Document> getDocumentsByDate(LocalDate date) {
        String query = "SELECT * FROM documents WHERE upload_date = ?";
        try {
            return jdbcTemplate.query(query, new DocumentRowMapper(), Date.valueOf(date));
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public List<Document> getDocumentsBetweenDates(LocalDate startDate, LocalDate endDate) {
        String query = "SELECT * FROM documents WHERE upload_date BETWEEN ? AND ?";
        try {
            return jdbcTemplate.query(query, new DocumentRowMapper(), Date.valueOf(startDate), Date.valueOf(endDate));
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public List<Document> getAllDocuments() {
        String query = "SELECT * FROM documents";
        try {
            return jdbcTemplate.query(query, new DocumentRowMapper());
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public void deleteDocumentById(Long id) {
        String query = "DELETE FROM documents WHERE id = ?";
        jdbcTemplate.query(query, new DocumentRowMapper(), id);
    }

    public void deleteAllDocuments() {
        String query = "DELETE FROM documents";
        jdbcTemplate.update(query);
    }

    public void deleteAllDocumentsOwnedByUserId(Long id) {
        String query = "DELETE FROM documents WHERE owner_id = ?";
        jdbcTemplate.query(query, new DocumentRowMapper(),id);
    }
}