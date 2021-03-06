package Utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
/**
 * Created by Саоша on 05.11.2016.
 */

public class MD5 {

    public static String getHash(String password){

        String hashPassword = null;

        try {
            byte[] bytesOfPassword = password.getBytes("UTF-8");

            MessageDigest md = MessageDigest.getInstance("MD5");

            byte[] hashBytesPassword = md.digest(bytesOfPassword);

            char[] charHash = new char[hashBytesPassword.length];

            for (int i = 0; i < hashBytesPassword.length; i++) {
                charHash[i] = (char) hashBytesPassword[i];
            }

            hashPassword = String.valueOf(charHash);
        }catch (NoSuchAlgorithmException | UnsupportedEncodingException e){
            e.printStackTrace();
        }

        return hashPassword;
    }

}
