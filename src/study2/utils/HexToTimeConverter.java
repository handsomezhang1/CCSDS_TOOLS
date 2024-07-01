package study2.utils;

import java.util.Date;
import java.util.TimeZone;
import java.math.BigInteger;
import java.text.SimpleDateFormat;

public class HexToTimeConverter {

    public static String convertHexToEST(String hexTimestamp) {
        long timestamp = Long.parseLong(hexTimestamp, 16);
        Date date = new Date(timestamp * 1000L); // Convert seconds to milliseconds

        // Create a formatter that will convert the date to a string in the desired format
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        
        // Set the formatter to use the UTC+8 timezone
        sdf.setTimeZone(TimeZone.getTimeZone("GMT+8"));

        return sdf.format(date);
    }
    /**
     * Converts a hexadecimal string to a decimal integer.
     *
     * @param hexString The hexadecimal string to convert.
     * @return The decimal representation of the hexadecimal string.
     * @throws NumberFormatException If the hexString is not a valid hexadecimal number.
     */
    public static String hexToDecimal(String hexString) throws NumberFormatException {
        BigInteger decimalValue = new BigInteger(hexString, 16);
        return decimalValue.toString();
    }
 /**
  *    public static void main(String[] args) {
        String hexOutput = "65a25e82";
        String estTime = convertHexToEST(hexOutput);
        System.out.println("The EST time is: " + estTime);
    }
  * 
  * */
}
