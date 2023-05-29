public class Security {
    final private static String admin_fp = "admin";
    final private static String cashier_fp = "cashier";

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


    public static String getAdminFileName(){
        return admin_fp;
    }

    public static String getCashierFileName(){
        return cashier_fp;
    }
}
