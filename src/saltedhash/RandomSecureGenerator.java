/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package saltedhash;

import java.io.StringWriter;
import java.security.SecureRandom;

/**
 *
 * @author C019301
 */
public class RandomSecureGenerator {
    /**
     * Implementacion para la generacion de un numero aleatorio seguro acotado a n caracteres.
     * @param size
     * @return 
     */
    public static String generateRandomValue(int size, byte[] data){
        SecureRandom sRand = new SecureRandom();
        if (data!=null){
            sRand.setSeed(data);
        }
        long l = sRand.nextLong();
        if (l<0){
            l = l * -1;          
        }
        StringWriter tmpModulus = new StringWriter();
        for (int a=0;a<size;a++){
            tmpModulus.write('9');
        }
        String modStr = tmpModulus.toString();
        //System.out.println(modStr);
        int value = Integer.parseInt(modStr);
        long remainder = l % value;
        String dataOtp =  Long.toString(remainder);
       
        return String.format("%0"+size+"d", Integer.parseInt(dataOtp));
        //return String.format("%0"+size+"d", Integer.parseInt("66"));
    }
}
