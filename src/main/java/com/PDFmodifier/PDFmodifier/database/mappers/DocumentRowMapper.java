package com.PDFmodifier.PDFmodifier.database.mappers;

import com.PDFmodifier.PDFmodifier.model.Document;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class DocumentRowMapper implements RowMapper<Document> {

    @Override
    public Document mapRow(ResultSet rs, int rowNum) throws SQLException {
        Document d = new Document();
        d.setId(rs.getLong("id"));
        d.setFilerName(rs.getString("file_name"));
        d.setFilePath(rs.getString("file_path"));
        Timestamp timestamp = rs.getTimestamp("upload_date");
        if(timestamp != null) {
            d.setUploadDate(timestamp.toLocalDateTime());
        }
        d.setPagesNumber(rs.getLong("pages_number"));
        d.setOwnerId(rs.getLong("owner_id"));

        return d;
    }
}