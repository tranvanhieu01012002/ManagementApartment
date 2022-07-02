package com.app.model.crawlData;

import com.app.model.Account;
import com.app.model.Travel;
import com.app.model.scope.GlobalScope;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.*;
import java.util.ArrayList;
import java.util.List;

public class crawlData {


    private  static HttpURLConnection connection;


    public String getAPI(String endPoint){

        BufferedReader reader;
        String line;
        String data = null;
        StringBuffer responseContent = new StringBuffer();
        try {
            URL url = new URL(GlobalScope.API+"/" +endPoint);
            connection = (HttpURLConnection) url.openConnection();
            //request setup
            connection.setRequestMethod("GET");
//            connection.setConnectTimeout(5000);
//            connection.setReadTimeout(5000);

            // status of connect
            int status = connection.getResponseCode();
            if (status != 200) {
                reader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
                while ((line = reader.readLine()) != null) {
                    responseContent.append(line);
                }
                reader.close();
            } else {
                reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                while ((line = reader.readLine()) != null) {
                    responseContent.append(line);
                }
                reader.close();
            }
                data = responseContent.toString();
        } catch (MalformedURLException e) {
//            throw new RuntimeException(e);
            e.printStackTrace();
        } catch (ProtocolException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            //final connect we need to disconnect to save data
            connection.disconnect();
        }
        return  data;
    }
    public List<Travel> stringParser(String res){
        List<Travel> list = new ArrayList<>();
        JSONArray array = new JSONArray(res);
        for(int i = 0; i <array.length();i++){
            JSONObject item = array.getJSONObject(i);
            String name = item.getString("name");
            String time = item.getString("time");
            String start = item.getString("start_end");
            String img = item.getString("img");
            String price = item.getString("price");
            String id = item.getString("id");
            listTravel(convertToTravel(name,time,start,img,price,id),list);
        }
        return  list;
    }
    public List<Account> strParUser(String res){
        List<Account> accList = new ArrayList<>();
        JSONArray array = new JSONArray(res);
        System.out.println(res);
        for(int i = 0; i <array.length();i++){
            JSONObject item = array.getJSONObject(i);
            String name = item.getString("name");
            String account = item.getString("account");
            String password = item.getString("password");
            String id = item.getString("id");
            addAccount(convertToAccount(name,account,password,id),accList);
        }
        return accList;
    }
    public List<Account> addAccount (Account account,List<Account> accounts){
         accounts.add(account);
         return  accounts;
    }
    public Account convertToAccount(String name,String account,String password, String id){
           return  new Account(account,password,name,Integer.parseInt(id));
    }
    public static Travel convertToTravel(String name, String time, String start,String img ,String price,String id){
           Travel travel = new Travel();
           travel.setImg(img);
           travel.setName(name);
           travel.setTime(time);
           travel.setStart_end(start);
           travel.setPrice(Integer.parseInt(price.replaceAll(",","")));
           travel.setId(Integer.parseInt(id));
        return travel;
    }
    public static List<Travel> listTravel(Travel travel, List<Travel> list){
        list.add(travel);
        return list;
    }
    public boolean postRequest(String urlStr, String jsonBodyStr) {
        HttpURLConnection httpURLConnection;
        try {
            URL url = new URL(GlobalScope.API+"/"+urlStr);
            httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setRequestMethod("POST");
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        httpURLConnection.setRequestProperty("Content-Type", "application/json");

        try (OutputStream outputStream = httpURLConnection.getOutputStream()) {
            outputStream.write(jsonBodyStr.getBytes());
            outputStream.flush();
            System.out.println(true);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // check with out Bufferloer
        try {
            if (httpURLConnection.getResponseCode() == 201) {
                try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()))) {
                    String line;
                    while ((line = bufferedReader.readLine()) != null) {
                        // ... do something with line
                        System.out.println(line);
                    }
                }
            } else {
                // ... do something with unsuccessful response
                System.out.println(false);

            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // disconnect
        httpURLConnection.disconnect();
        return true;
    }
    public boolean deleteRequest(String id) throws IOException {
        boolean res = false;
        URL url = new URL(GlobalScope.API+"/traveling/"+id);
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
        httpURLConnection.setDoOutput(true);
        httpURLConnection.setRequestMethod("DELETE");
        int resCode = httpURLConnection.getResponseCode();
        if(resCode <300){
//            System.out.println(true);
            res = true;
        }
//      \
        // disconnect

        httpURLConnection.disconnect();
        return res;
    }

    public boolean updateRequest(String id,String jsonBodyStr){
        HttpURLConnection httpURLConnection;
        try {
            URL url = new URL(GlobalScope.API+"/traveling/"+id);
            httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setRequestMethod("PUT");
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        httpURLConnection.setRequestProperty("Content-Type", "application/json");

        try (OutputStream outputStream = httpURLConnection.getOutputStream()) {
            outputStream.write(jsonBodyStr.getBytes());
            outputStream.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // check with out Bufferloer
        try {
            if (httpURLConnection.getResponseCode() == 201) {
                try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()))) {
                    String line;
                    while ((line = bufferedReader.readLine()) != null) {
                        // ... do something with line
                        System.out.println(line);
                    }
                }
            } else {
                // ... do something with unsuccessful response
                System.out.println(false);

            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // disconnect
        httpURLConnection.disconnect();
        return true;
    }
}
