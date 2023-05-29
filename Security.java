public class Security {
    
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
}
