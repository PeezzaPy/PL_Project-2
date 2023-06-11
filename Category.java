public class Category {
    // expiration date interval
    public int canned_good = 6;
    public int dairy = 7;          
    public int drinks = 6;
    public int fruit = 7;
    public int junk_food = 1;      
    public int sweet = 3;
    public int vegetable = 1;

    public static String category[] = {
        "Category: \n", 
        "(1) Canned Goods", 
        "(2) Dairy", 
        "(3) Drink", 
        "(4) Fruit", 
        "(5) Junk Food", 
        "(6) Sweet", 
        "(7) Vegetable"
    };

    public static String setGetCategory(){
        String catChoiceString = "";
        do {
            for(int i=0; i<category.length; i++){
                System.out.println(category[i]);
            }
            System.out.print("\nSelect: ");
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
}
