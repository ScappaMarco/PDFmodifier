package com.PDFmodifier.PDFmodifier.database.query;

import com.PDFmodifier.PDFmodifier.database.mappers.DocumentRowMapper;
import com.PDFmodifier.PDFmodifier.model.Document;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

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
        return jdbcTemplate.queryForObject(query, new DocumentRowMapper(), id);
    }

    public List<Document> getDocumetByName(String fileName) {
        String query = "SELECT id, email, username, account_create_date FROM documents WHERE file_name = ?";
        return jdbcTemplate.query(query, new DocumentRowMapper(), fileName);
    }

    public List<Document> getAllDocuments() {
        String query = "SELECT * FROM documents";
        return jdbcTemplate.query(query, new DocumentRowMapper());
    }
}