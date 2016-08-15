/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package saltedhash;

import java.io.StringWriter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.xml.bind.DatatypeConverter;

/**
 * 
 * 
 * Isban 2016
 * Santiago Bayo
 * @author C019301 
 */
public class PocSaltedHash {
        public static String generateSaltedHashValue(String otp,String salt) throws NoSuchAlgorithmException{
            String message = salt+otp;
            byte[] byteMessage = message.getBytes();
            //https://docs.oracle.com/javase/7/docs/api/java/security/MessageDigest.html
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashedData = md.digest(byteMessage);
            String hashEncode= DatatypeConverter.printBase64Binary(hashedData); // java7 jvm Sun.
            StringWriter writer = new StringWriter();
            writer.append(salt);
            writer.append("#");
            writer.append(hashEncode);
            return writer.toString();            
        }
        
        public static String getSalt(String generateSaltedHashValue){
            String [] data = generateSaltedHashValue.split("#");
            if (data.length==1){
                return "";
            }
            return data[0];
        }
        
}
