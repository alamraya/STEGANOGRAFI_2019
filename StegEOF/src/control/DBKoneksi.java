/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package control;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Zierviera
 */
public class DBKoneksi {
    public Connection openConnection() {
        Connection connect = null;
        try {
             Class.forName("com.mysql.jdbc.Driver");
             connect = DriverManager.getConnection("jdbc:mysql://localhost/db_tugasakhir","root","");
             return connect;
        }catch (SQLException se){	
            System.out.println("No Connection Open ! Error @ : "+se.getMessage());
            return null;
        }catch (Exception ex){	
            System.out.println("Failed Connect To Database ! Error @ : "+ex.getMessage());
            return null;
        }
    }
}
