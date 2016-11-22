package com.Normalization1;
import org.fluttercode.datafactory.impl.DataFactory;

import java.sql.*;

/**
 * Created by Josh on 3/31/16.
 */
public class main {

    public static void main(String[] args) {
        //randomly generate the data using command line parameter for how many tuples to create
        dataGenerator dg = new dataGenerator(Integer.parseInt(args[0]));
        //upload the data to the database
        uploadDB upload = new uploadDB();
        System.out.println("Data uploaded successfully");
    }

}
