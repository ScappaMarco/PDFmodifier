package com.PDFmodifier.PDFmodifier.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "pdf_operations")
public class PdfOperation {
    private Long documentId;
    private Long userId;
    private Operation documentOperation;
    private List<Long> pages;
    private LocalDateTime operationDate;

    public PdfOperation(Long documentId, Long userId, Operation operation, List<Long> pages, LocalDateTime operationDate) {
        this.documentId = documentId;
        this.userId = userId;
        this.documentOperation = operation;
        this.pages = pages;
        this.operationDate = operationDate;
    }

    public PdfOperation() {}

    public Long getDocumentId() {
        return this.documentId;
    }

    public void setDocumentId(Long documentId) {
        this.documentId = documentId;
    }

    public Long getUserId() {
        return this.userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Operation getDocumentOperation() {
        return this.documentOperation;
    }

    public void setDocumentOperation(Operation documentOperation) {
        this.documentOperation = documentOperation;
    }

    public List<Long> getPages() {
        return this.pages;
    }

    public void addPage(Long page) {
        this.getPages().add(page);
    }

    public void removePage(Long page) {
        this.getPages().remove(page);
    }

    public void setPages(List<Long> pages) {
        this.pages = pages;
    }

    public LocalDateTime getOperationDate() {
        return this.operationDate;
    }

    public void setOperationDate(LocalDateTime operationDate) {
        this.operationDate = operationDate;
    }
}