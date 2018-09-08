/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bridge;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Abhishek
 */
public class Excel2MySQL {
    public String excel2Mysql(String dbname, String hostname, String username, String password, String tablename, String cnames, String data1)throws IOException{
       
        String res = "";
        URL urlObj;
        HttpURLConnection lu = null;
        Logger logger = Logger.getLogger("my.log");
           logger.log(Level.SEVERE,"Inside Excel2MySQL");
      
           
        try{
            String url = "http://nihontechnologies.in/AppData/bridge/e2m.php";
            urlObj = new URL(url);
            lu = (HttpURLConnection) urlObj.openConnection();
            logger.log(Level.SEVERE,"Internet Connection established!");
        }catch(Exception e){
            logger.log(Level.SEVERE,e.toString());
        }
         
         try{  
        // Send data - if you don't need to send data 
        // ignore this section and just move on to the next one
        StringBuilder input = new StringBuilder();
        input.append(URLEncoder.encode("dbname", "UTF-8"));
        input.append("=");
        input.append(URLEncoder.encode(dbname, "UTF-8"));
        input.append("&");
        input.append(URLEncoder.encode("hostname", "UTF-8"));
        input.append("=");
        input.append(URLEncoder.encode(hostname, "UTF-8"));
        input.append("&");
        input.append(URLEncoder.encode("username", "UTF-8"));
        input.append("=");
        input.append(URLEncoder.encode(username, "UTF-8"));
        input.append("&");
        input.append(URLEncoder.encode("password", "UTF-8"));
        input.append("=");
        input.append(URLEncoder.encode(password, "UTF-8"));
        input.append("&");
        input.append(URLEncoder.encode("tablename", "UTF-8"));
        input.append("=");
        input.append(URLEncoder.encode(tablename, "UTF-8"));
        input.append("&");
        input.append(URLEncoder.encode("cnames", "UTF-8"));
        input.append("=");
        input.append(URLEncoder.encode(cnames, "UTF-8"));
        input.append("&");
        input.append(URLEncoder.encode("data", "UTF-8"));
        input.append("=");
        input.append(URLEncoder.encode(data1, "UTF-8"));
        
        /*String data = "dbname="+dbname+
                "&"+"hostname="+hostname+
                "&"+"username="+username+
                "&"+"password="+password+
                "&"+"tablename="+tablename+
                "&"+"cnames="+cnames+
                "&"+"data="+data1;*/
        
        String data = input.toString();
                
        System.out.println("query : "+data);
        lu.setRequestMethod("POST");
        lu.setDoInput(true);
        lu.setDoOutput(true);
        OutputStream os = lu.getOutputStream();
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter (os, "UTF-8"));
        writer.write(data);
        writer.flush();
        writer.close();
        //os.close();
        logger.log(Level.SEVERE,"Internet php post write successful!");
         }catch(Exception e){
             logger.log(Level.SEVERE,e.toString());
         }

        // Get the response
        
        try{
        System.out.println("res : "+lu.getResponseCode()+","+lu.getResponseMessage());
        BufferedReader rd = new BufferedReader(new InputStreamReader(lu.getInputStream()));
        res=rd.readLine();
        logger.log(Level.SEVERE,"Response : "+res);
        logger.log(Level.SEVERE,"Internet php read successful!");
        
        }catch(Exception e){
            logger.log(Level.SEVERE,e.toString());
        }finally{
            
            lu.disconnect();
        }
        
        return res;
    }
}
