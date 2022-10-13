/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.sql.Timestamp;

/**
 *
 * @author Mountain
 */
public class Notification {

    private int id;
    private Account senderID;
    private Account receiveID;
    private String title;
    private String content;
    private boolean readMark;
    private Timestamp createdDate;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Account getSenderID() {
        return senderID;
    }

    public void setSenderID(Account senderID) {
        this.senderID = senderID;
    }

    public Account getReceiveID() {
        return receiveID;
    }

    public void setReceiveID(Account receiveID) {
        this.receiveID = receiveID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isReadMark() {
        return readMark;
    }

    public void setReadMark(boolean readMark) {
        this.readMark = readMark;
    }

    public Timestamp getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }

}
