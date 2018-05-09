/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Student;

/**
 *
 * @author Than
 */
public class ConnectionHandle {
    private Connection conn;
    private final String CONNECTION_PREFIX = "jdbc:mysql://localhost:3306/";
    private final String DATABASE_NAME = "sw_student_manager";
    private final String UTF8_CONFIG = "?useUnicode=true&characterEncoding=utf-8";
    private final String USERNAME = "root";
    private final String PASSWORD = "";
    
    private static ConnectionHandle connectionHandle;

    public static ConnectionHandle getInstance() {
        if (null == connectionHandle) {
            connectionHandle = new ConnectionHandle();
        }
        return connectionHandle;
    }

    public Connection getConnection() throws SQLException {
        if (null == conn || conn.isClosed()) {
            conn = DriverManager.getConnection(CONNECTION_PREFIX + DATABASE_NAME + UTF8_CONFIG, USERNAME, PASSWORD);
        }
        return conn;
    }
}
