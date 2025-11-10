package com.PDFmodifier.PDFmodifier.database.mappers;

import com.PDFmodifier.PDFmodifier.model.PdfOperation;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

public class PdfOperationMapper implements RowMapper<PdfOperation> {

    @Override
    public PdfOperation mapRow(ResultSet rs, int rowNum) throws SQLException {
        PdfOperation pdfOperation = new PdfOperation();
        pdfOperation.setDocumentId(rs.getLong("document_id"));
        pdfOperation.setUserId(rs.getLong("user_id"));
        pdfOperation.setDocumentOperation(rs.getString("operation"));
        pdfOperation.setPages((List<Long>) rs.getObject("pages"));
        Timestamp timestamp = rs.getTimestamp("operation_date");
        if(timestamp != null) {
            pdfOperation.setOperationDate(timestamp.toLocalDateTime());
        }

        return pdfOperation;
    }
}