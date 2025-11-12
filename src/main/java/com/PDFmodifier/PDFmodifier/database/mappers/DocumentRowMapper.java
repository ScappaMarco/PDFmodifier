package com.PDFmodifier.PDFmodifier.database.mappers;

import com.PDFmodifier.PDFmodifier.model.Document;
import org.springframework.jdbc.core.RowMapper;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DocumentRowMapper implements RowMapper<Document> {

    @Override
    public Document mapRow(ResultSet rs, int rowNum) throws SQLException {
        Document d = new Document();
        d.setId(rs.getLong("id"));
        d.setFilerName(rs.getString("file_name"));
        d.setFilePath(rs.getString("file_path"));

        Date date = rs.getDate("upload_date");
        if(date != null) {
            d.setUploadDate(date.toLocalDate());
        }

        d.setPagesNumber(rs.getLong("pages_number"));
        d.setOwnerId(rs.getLong("owner_id"));

        return d;
    }
}