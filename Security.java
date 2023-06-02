import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Security {
    final private static String admin_fp = "admin";
    final private static String cashier_fp = "cashier";
    final public static String secret_key = "key";
    private static String esecretKey_fp;

    public static String encrypt(String text, int key){
        String encryptedText = "";
        for(int i=0; i<text.length(); i++){
            char ch = text.charAt(i);
            ch += key;
            encryptedText += ch;
        }
        return encryptedText;
    }


    public static String decrypt(String encryptedText, int key){
        String decryptedText = "";
        for(int i=0; i<encryptedText.length(); i++){
            char ch = encryptedText.charAt(i);
            ch -= key;
            decryptedText += ch;
        }
        return decryptedText;
    }

    
    public static void changeSecretKey(int my_secretKey){
        
        try(BufferedReader reader = new BufferedReader(new FileReader("E:\\2nd Yeaer\\2nd sem\\PL\\java\\New folder\\PL_Project-2\\account\\" + secret_key + ".txt"))){
            String data = reader.readLine();
            String decData = decrypt(data, my_secretKey);
        } catch (IOException e){
        }

        try(FileWriter writer = new FileWriter("E:\\2nd Yeaer\\2nd sem\\PL\\java\\New folder\\PL_Project-2\\account\\" + secret_key + ".txt")){
            writer.write(Integer.toString(my_secretKey));  
            writer.close();      
        } catch (IOException e){
            e.printStackTrace();
        }
    }


    public static int getSecretKey(){
        try(BufferedReader reader = new BufferedReader(new FileReader("E:\\2nd Yeaer\\2nd sem\\PL\\java\\New folder\\PL_Project-2\\account\\" + secret_key + ".txt"))){
            String data = reader.readLine();
            reader.close();
            return Integer.parseInt(data);

        } catch (IOException e){
            e.printStackTrace();
        }
        return -1;
    }


    public static String getAdminFileName(){
        return admin_fp;
    }

    public static String getCashierFileName(){
        return cashier_fp;
    }
}
