package com.book.entity;

import java.util.Date;

public class BookInfo {
    private Integer id;

    private String bookCode;

    private String bookCover;

    private String bookName;

    private String bookType;

    private String bookAuthor;

    private Date createTime;

    private String createUser;

    private Date updateTime;

    private String updateUser;

    private String feild1;

    private String feild2;

    private String feild3;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBookCode() {
        return bookCode;
    }

    public void setBookCode(String bookCode) {
        this.bookCode = bookCode == null ? null : bookCode.trim();
    }

    public String getBookCover() {
        return bookCover;
    }

    public void setBookCover(String bookCover) {
        this.bookCover = bookCover == null ? null : bookCover.trim();
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName == null ? null : bookName.trim();
    }

    public String getBookType() {
        return bookType;
    }

    public void setBookType(String bookType) {
        this.bookType = bookType == null ? null : bookType.trim();
    }

    public String getBookAuthor() {
        return bookAuthor;
    }

    public void setBookAuthor(String bookAuthor) {
        this.bookAuthor = bookAuthor == null ? null : bookAuthor.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser == null ? null : createUser.trim();
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser == null ? null : updateUser.trim();
    }

    public String getFeild1() {
        return feild1;
    }

    public void setFeild1(String feild1) {
        this.feild1 = feild1 == null ? null : feild1.trim();
    }

    public String getFeild2() {
        return feild2;
    }

    public void setFeild2(String feild2) {
        this.feild2 = feild2 == null ? null : feild2.trim();
    }

    public String getFeild3() {
        return feild3;
    }

    public void setFeild3(String feild3) {
        this.feild3 = feild3 == null ? null : feild3.trim();
    }
}