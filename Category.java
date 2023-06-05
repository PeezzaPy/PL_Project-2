import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Category {
    // expiration date interval
    private int canned_good = 6;
    private int dairy = 7;          
    private int drinks = 6;
    private int fruit = 7;
    private int junk_food = 1;      
    private int sweet = 3;
    private int vegetable = 1;


    public static String setGetCategory(){
        String catChoiceString = "";
        do {
            System.out.println("Category: \n");
            System.out.println("(1) Canned Goods");
            System.out.println("(2) Dairy");
            System.out.println("(3) Drink");
            System.out.println("(4) Fruit");
            System.out.println("(5) Junk Food");
            System.out.println("(6) Sweet");
            System.out.println("(7) Vegetable \n");
            System.out.print("Select: ");
            Authen.inputValidation();
            
        } while(!Main.validInput || Main.choice < 1 || Main.choice > 7);

        int catChoice = Main.choice;
        Main.choice = -1;       // reset to default;
        Terminal.clearScreen();

        // set category
        if(catChoice == 1)
            catChoiceString = "Canned Goods";
        else if(catChoice == 2)
            catChoiceString = "Dairy";
        else if(catChoice == 3)
            catChoiceString = "Drink";
        else if(catChoice == 4)
            catChoiceString = "Fruit";
        else if(catChoice == 5)
            catChoiceString = "Junk Food";
        else if(catChoice == 6)
            catChoiceString = "Sweet";
        else if(catChoice == 7)
            catChoiceString = "Vegetable";
        
        return catChoiceString.toUpperCase();
    }

    public static String setGetExpirationDate(String now_date_time, String prod_category){
        Category category = new Category();
        LocalDateTime expirationDateTime = null;
        String expirationDateTimeString;

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd / HH:mm:ss");
        LocalDateTime currentDateTime = LocalDateTime.parse(now_date_time, formatter);
        
        if(prod_category.equalsIgnoreCase("CANNED GOODS"))
            expirationDateTime = currentDateTime.plusMonths(category.canned_good);
        else if(prod_category.equalsIgnoreCase("DAIRY"))
            expirationDateTime = currentDateTime.plusDays(category.dairy);
        else if(prod_category.equalsIgnoreCase("DRINK"))
            expirationDateTime = currentDateTime.plusMonths(category.drinks);
        else if(prod_category.equalsIgnoreCase("FRUIT"))
            expirationDateTime = currentDateTime.plusDays(category.fruit);
        else if(prod_category.equalsIgnoreCase("JUNK FOOD"))
            expirationDateTime = currentDateTime.plusYears(category.junk_food);
        else if(prod_category.equalsIgnoreCase("SWEET"))
            expirationDateTime = currentDateTime.plusMonths(category.sweet);
        else if(prod_category.equalsIgnoreCase("VEGETABLE"))
            expirationDateTime = currentDateTime.plusDays(category.vegetable);

        expirationDateTimeString = expirationDateTime.format(formatter);
        
        return expirationDateTimeString;
    }
}
