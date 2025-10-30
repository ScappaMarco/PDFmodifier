package com.PDFmodifier.PDFmodifier.model;

import java.util.Date;
import java.util.List;

public class PdfOperation {
    private Long documentId;
    private Long userId;
    private Operation documentOperation;
    private List<Long> pages;
    private Date operationDate;

    public PdfOperation(Long documentId, Long userId, Operation operation, List<Long> pages, Date operationDate) {
        this.documentId = documentId;
        this.userId = userId;
        this.documentOperation = operation;
        this.pages = pages;
        this.operationDate = operationDate;
    }

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

    public Date getOperationDate() {
        return this.operationDate;
    }

    public void setOperationDate(Date operationDate) {
        this.operationDate = operationDate;
    }
}