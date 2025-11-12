package com.PDFmodifier.PDFmodifier.database.mappers;

import com.PDFmodifier.PDFmodifier.model.PdfOperation;
import org.springframework.jdbc.core.RowMapper;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class PdfOperationMapper implements RowMapper<PdfOperation> {

    @Override
    public PdfOperation mapRow(ResultSet rs, int rowNum) throws SQLException {
        PdfOperation pdfOperation = new PdfOperation();
        pdfOperation.setId(rs.getLong("id"));
        pdfOperation.setDocumentId(rs.getLong("document_id"));
        pdfOperation.setUserId(rs.getLong("user_id"));
        pdfOperation.setDocumentOperation(rs.getString("operation"));
        pdfOperation.setPages((List<Long>) rs.getObject("pages"));

        Date date = rs.getDate("operation_date");
        if(date != null) {
            pdfOperation.setOperationDate(date.toLocalDate());
        }

        return pdfOperation;
    }
}