package pl.it.portfolio;

import org.apache.commons.codec.digest.DigestUtils;

import java.util.Arrays;

public class cos {
    public static void main(String[] args) {
        double siema = 22.77;
     //  String prize ="[876 666 555,00zł]";
       // System.out.println(prize.substring(1, prize.length() - 3).replace(" ", "").replace(",","."));
        System.out.println(DigestUtils.md5Hex("admin"));
    }
}
