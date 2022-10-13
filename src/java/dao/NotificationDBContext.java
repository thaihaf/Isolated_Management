/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import entity.Notification;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Mountain
 */
public class NotificationDBContext extends DBContext<Notification> {

    @Override
    public ArrayList<Notification> list() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Notification get(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void insert(Notification model) {
        try {
            String sql = "INSERT INTO [Notification]\n"
                    + "           ([Sender_ID]\n"
                    + "           ,[Receive_ID]\n"
                    + "           ,[Title]\n"
                    + "           ,[Content]\n"
                    + "           ,[ReadMark]\n"
                    + "           ,[CreateDate])\n"
                    + "     VALUES\n"
                    + "           (?\n"
                    + "           ,?\n"
                    + "           ,?\n"
                    + "           ,?\n"
                    + "           ,?\n"
                    + "           ,?)";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, model.getSenderID().getUserName());
            stm.setString(2, model.getReceiveID().getUserName());
            stm.setString(3, model.getTitle());
            stm.setString(4, model.getContent());
            stm.setBoolean(5, model.isReadMark());
            stm.setTimestamp(6, model.getCreatedDate());
            stm.executeUpdate();
        } catch (SQLException ex) {
        }
    }

    @Override
    public void update(Notification model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(Notification model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
