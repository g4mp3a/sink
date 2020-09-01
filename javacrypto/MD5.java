import java.io.*;
import java.util.*;
import java.security.*;
import java.nio.charset.StandardCharsets;
import java.math.BigInteger;

public class MD5 {

    public static void main(String[] args) throws NoSuchAlgorithmException {
        Scanner s = new Scanner(System.in);
        String password = s.nextLine();
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        md5.update(StandardCharsets.UTF_8.encode(password));
        System.out.println("MD5: " + String.format("%032x", new BigInteger(1, md5.digest())));
        
        MessageDigest mdSHA256 = MessageDigest.getInstance("SHA-256");
        mdSHA256.update(StandardCharsets.UTF_8.encode(password));
        System.out.println(String.format("%064x", new BigInteger(1, mdSHA256.digest())));
    }
}