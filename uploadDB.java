package com.Normalization1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;

/**
 * Created by Josh on 4/1/16.
 */
public class uploadDB {

    public uploadDB() {
        readFile();
    }

    public void readFile() {

        try {
            //make a database connection
            DBConnection db = new DBConnection();
            Connection conn = db.getConnection();

            //get access to the file that contains the randomly generated data
            FileReader reader = new FileReader("Info.csv");
            BufferedReader bufferedReader = new BufferedReader(reader);

            String line = "";
            String[] uploadArray;

            //load each row of data into an array
            while ((line = bufferedReader.readLine()) != null) {
                uploadArray = line.split(",");
                insert(conn, uploadArray);
            }

            bufferedReader.close();

            //make sure connection closes properly
            try {
                conn.close();
                System.out.println("Connection closed.");
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            try {
                if (!conn.isClosed()) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //method to get the primary keys from updated tables
    //to use for foreign keys in other tables
    public int getPrimaryKey(PreparedStatement ps) {
        int key = 0;
        try {
            ResultSet rs = ps.getGeneratedKeys();
            key = 0;
            if (rs.next()) {
                key = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return key;
    }

    public void insert(Connection connect, String[] arr) {

        String insertOwner = "INSERT INTO BikeOwner(FirstName, LastName) VALUES(?,?)";
        String insertCompany = "INSERT INTO Company(CompanyName) VALUES(?)";
        String insertType = "INSERT INTO Type(Multispeed, WheelSize) VALUES(?,?)";
        String insertModel = "INSERT INTO Model(CompanyID, TypeID, ModelName) VALUES(?,?,?)";
        String insertAddr = "INSERT INTO OwnerAddress(OwnerID, Street, City, Zip) VALUES(?,?,?,?)";
        String insertBike = "INSERT INTO Bike(OwnerID, ModelID, Color, Category) VALUES(?,?,?,?)";

        //update the tables with prepared statements using data from the array
        try {

            PreparedStatement owner = connect.prepareStatement(insertOwner, Statement.RETURN_GENERATED_KEYS);
            owner.setString(1, arr[0]);
            owner.setString(2, arr[1]);

            owner.executeUpdate();
            int ownPK = getPrimaryKey(owner);

            PreparedStatement company = connect.prepareStatement(insertCompany, Statement.RETURN_GENERATED_KEYS);
            company.setString(1, arr[5]);

            company.executeUpdate();
            int coPK = getPrimaryKey(company);

            PreparedStatement type = connect.prepareStatement(insertType, Statement.RETURN_GENERATED_KEYS);
            type.setString(1, arr[9]);
            type.setDouble(2, Double.parseDouble(arr[10]));

            type.executeUpdate();
            int tPK = getPrimaryKey(type);

            PreparedStatement model = connect.prepareStatement(insertModel, Statement.RETURN_GENERATED_KEYS);
            model.setInt(1, coPK);
            model.setInt(2, tPK);
            model.setString(3, arr[6]);

            model.executeUpdate();
            int modPK = getPrimaryKey(model);

            PreparedStatement addr = connect.prepareStatement(insertAddr);
            addr.setInt(1, ownPK);
            addr.setString(2, arr[2]);
            addr.setString(3, arr[3]);
            addr.setString(4, arr[4]);

            addr.executeUpdate();

            PreparedStatement bike = connect.prepareStatement(insertBike);
            bike.setInt(1, ownPK);
            bike.setInt(2, modPK);
            bike.setString(3, arr[7]);
            bike.setString(4, arr[8]);

            bike.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
