package com.Normalization1;

import org.fluttercode.datafactory.impl.DataFactory;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by Josh on 3/31/16.
 */
public class dataGenerator {

    public dataGenerator(int number) {

        generateData(number);

    }

    //method to randomly generate data
    public void generateData(int num) {
        DataFactory df = new DataFactory();

        String name;
        String address;
        String company;
        String model;
        String color;
        String category;
        String multispeed;
        String wheelSize;
        String output;

        //set custom values to be randomly generated
        String[] companyValues = {"Giant", "Trek", "Evil", "Specialized", "Kona", "Santa Cruz", "Pivot", "Ibis"};
        String[] modelValues = {"Trance", "Remedy", "Wreckoning", "Stumpjumper", "Honzo", "Bronson", "Firebird", "Mojo"};
        String[] colorValues = {"Red", "Blue", "Black", "Purple", "Grey", "Green"};
        String[] categoryValues = {"XC", "Trail", "All Mountain", "Enduro"};
        String[] multiValues = {"yes", "no"};
        String[] wheelSizeValues = {"26", "27.5", "29"};

        try {
            FileWriter writer = new FileWriter("Info.csv");
            BufferedWriter bufferedWriter = new BufferedWriter(writer);
            //export randomly generated data to a csv file
            for (int i = 0; i < num; ++i) {
                name = df.getFirstName() + ',' + df.getLastName() + ',';
                address = df.getAddress() + ',' + df.getCity() + ',' + df.getNumberText(5) + ',';
                company = df.getItem(companyValues) + ',';
                model = df.getItem(modelValues) + ',';
                color = df.getItem(colorValues) + ',';
                category = df.getItem(categoryValues) + ',';
                multispeed = df.getItem(multiValues) + ',';
                wheelSize = df.getItem(wheelSizeValues);
                output = name + address + company + model + color + category + multispeed + wheelSize + '\n';
                bufferedWriter.write(output);
            }

            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
