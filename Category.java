public class Category {
    // expiration date interval
    public int canned_good = 6;
    public int dairy = 7;          
    public int drinks = 6;
    public int fruit = 7;
    public int junk_food = 1;      
    public int sweet = 3;
    public int vegetable = 1;


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
}
