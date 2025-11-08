package com.PDFmodifier.PDFmodifier.database;

import com.PDFmodifier.PDFmodifier.model.Document;
import com.PDFmodifier.PDFmodifier.model.PdfOperation;
import com.PDFmodifier.PDFmodifier.model.User;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

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

    }
}