/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package saltedhash;

import java.security.NoSuchAlgorithmException;

/**
 *
 * @author C019301
 */
public class SaltedHash {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws NoSuchAlgorithmException {
        // generamos el OTP y lo enviamos por SMS
        // los daos son de demostracion
        String bucUser = "12345678";
        String otp = RandomSecureGenerator.generateRandomValue(6, null);
        System.out.println(otp);
        String salt = RandomSecureGenerator.generateRandomValue(4, null);
        String ssha256Otp = PocSaltedHash.generateSaltedHashValue(otp,salt );
        System.out.println(ssha256Otp);
        DaoDemo dao = new DaoDemo();        
        dao.saveOtp(bucUser, ssha256Otp);
        DaoSmsDemo smsDao = new DaoSmsDemo();
        smsDao.sendSMS(bucUser, otp);
        
        // Validamos el Otp SMS  - caso correcto
        
        String recievedOtp = otp;
        String daoSshaValue = dao.getOtp(bucUser);
        String goodSalt = PocSaltedHash.getSalt(daoSshaValue);
        String hashedGoodData = PocSaltedHash.generateSaltedHashValue(recievedOtp, goodSalt);
        
        if (hashedGoodData.equals(daoSshaValue)){
            System.out.println("correcto");
        }
        
        // Validamos el Otp SMS  - caso incorrecto
        
        recievedOtp = "7826123862";
        daoSshaValue = dao.getOtp(bucUser);
        goodSalt = PocSaltedHash.getSalt(daoSshaValue);
        String hashedBadData = PocSaltedHash.generateSaltedHashValue(recievedOtp, goodSalt);
        
        if (!hashedBadData.equals(daoSshaValue)){
            System.out.println("incorrecto");
        }
        
        
        
        
    }
    
}
