package com.app.process;

import com.app.model.Apartment;

import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Double.parseDouble;
import static java.lang.Integer.parseInt;

public class ImportData {
    List<Apartment> listData = new ArrayList<Apartment>();
    String data1 = "101"+ "\t"+"RENT"+"\t"+"9999999"+"\t"+"10";
    String data2 = "102"+ "\t"+"SALE"+"\t"+"9999999"+"\t"+"9";
    String data3 = "103"+ "\t"+"SALE"+"\t"+"9999999"+"\t"+"8";
    String data4 = "103"+ "\t"+"SALE"+"\t"+"9999999"+"\t"+"7";
    String data = data1 +"\n"+ data2 + "\n"+data3+ "\n"+data4;
    public void createFile(){
        try {
            FileOutputStream outputStream = new FileOutputStream("./src/main/java/com/app/data/data.txt");
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream, "UTF-16");
            BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);

            bufferedWriter.write(data);
            bufferedWriter.newLine();

            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void readFile() {
        try {
            FileReader reader = new FileReader("./src/main/java/com/app/data/data.txt", Charset.forName("UTF-16"));
            BufferedReader bufferedReader = new BufferedReader(reader);

            String line;

            while ((line = bufferedReader.readLine()) != null) {
                listData.add(createObject(line));
                System.out.println(line);
            }
            reader.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public Apartment createObject(String line){
        String[] splits = line.split("\t");
        Apartment apartment = new Apartment(parseInt(splits[0]),splits[1],parseDouble(splits[2]),parseInt(splits[3]));
        return apartment;
    }
    public ImportData(){
        createFile();
        readFile();
    }
    public List<Apartment> getData(){
        return listData;
    }

}
