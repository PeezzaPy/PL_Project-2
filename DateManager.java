import java.time.LocalDate;

public class DateManager {

    public static String setDate(){
        LocalDate date = LocalDate.now();
        String dateString = date.toString();

        return dateString;
    }
    public static String setGetExpirationDate(String prod_category){
        Category category = new Category();
        LocalDate expirationDate = null;
        String expirationDateTimeString;

        LocalDate currentDate = LocalDate.now();
        
        if(prod_category.equalsIgnoreCase("CANNED GOODS"))
            expirationDate = currentDate.plusMonths(category.canned_good);
        else if(prod_category.equalsIgnoreCase("DAIRY"))
            expirationDate = currentDate.plusDays(category.dairy);
        else if(prod_category.equalsIgnoreCase("DRINK"))
            expirationDate = currentDate.plusMonths(category.drinks);
        else if(prod_category.equalsIgnoreCase("FRUIT"))
            expirationDate = currentDate.plusDays(category.fruit);
        else if(prod_category.equalsIgnoreCase("JUNK FOOD"))
            expirationDate = currentDate.plusYears(category.junk_food);
        else if(prod_category.equalsIgnoreCase("SWEET"))
            expirationDate = currentDate.plusMonths(category.sweet);
        else if(prod_category.equalsIgnoreCase("VEGETABLE"))
            expirationDate = currentDate.plusDays(category.vegetable);

        expirationDateTimeString = expirationDate.toString();
        
        return expirationDateTimeString;
    }
}
