/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author Admin
 */
public class Patient {
    private String ID;
    private Room room;
    private String Note;
    private Boolean BackgroundDisease;

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public String getNote() {
        return Note;
    }

    public void setNote(String Note) {
        this.Note = Note;
    }

    public Boolean getBackgroundDisease() {
        return BackgroundDisease;
    }

    public void setBackgroundDisease(Boolean BackgroundDisease) {
        this.BackgroundDisease = BackgroundDisease;
    }
}
