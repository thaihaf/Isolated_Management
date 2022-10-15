/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import entity.Account;
import entity.Notification;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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

    public ArrayList<Notification> listByAccountID(String userID) {
        ArrayList<Notification> notifs = new ArrayList<>();
        try {
            String sql = "SELECT [ID]\n"
                    + "      ,[Sender_ID]\n"
                    + "      ,[Title]\n"
                    + "      ,[Content]\n"
                    + "      ,[ReadMark]\n"
                    + "      ,[CreateDate]\n"
                    + "  FROM [Notification]\n"
                    + "  WHERE [Receive_ID] = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, userID);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Notification n = new Notification();
                n.setId(rs.getInt("ID"));
                Account senderAcc = new Account();
                senderAcc.setUserName(rs.getString("Sender_ID"));
                n.setSenderID(senderAcc);
                n.setTitle(rs.getNString("Title"));
                n.setContent(rs.getNString("Content"));
                n.setReadMark(rs.getBoolean("ReadMark"));
                n.setCreatedDate(rs.getTimestamp("CreateDate"));
                notifs.add(n);
            }
        } catch (SQLException ex) {
        }
        return notifs;
    }

    @Override
    public Notification get(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public boolean confirmNotifInAccount(int id, String userID) {
        try {
            String sql = "SELECT [ID]\n"
                    + "      ,[Receive_ID]\n"
                    + "  FROM [Notification]\n"
                    + "  WHERE [ID] = ? AND [Receive_ID] = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, id);
            stm.setString(2, userID);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (SQLException ex) {
        }
        return false;
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
        try {
            String sql = "DELETE FROM [Notification]\n"
                    + "      WHERE [ID] = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, model.getId());
            stm.executeUpdate();
        } catch (SQLException ex) {
        }
    }

    public void markNotifAsRead(Notification model) {
        try {
            String sql = "UPDATE [Notification]\n"
                    + "   SET [ReadMark] = 1\n"
                    + " WHERE [ID] = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, model.getId());
            stm.executeUpdate();
        } catch (SQLException ex) {
        }
    }

}
