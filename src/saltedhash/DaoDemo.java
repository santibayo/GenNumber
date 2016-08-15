/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package saltedhash;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author C019301
 */
public class DaoDemo {
    Map <String,String> data = new HashMap<>();
    public void saveOtp(String user, String ssha256Otp){
        data.put(user, ssha256Otp);
    }
    
    public String getOtp(String user){
        return data.get(user);
    }
}
