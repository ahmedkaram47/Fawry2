package fawry2;

public class paperbook extends book
{
    
    boolean ship ; 
    paperbook(String name, String title, String ISBN, short year_published, float price ,boolean saleable , boolean ship)
    {
        super(name, title, ISBN, year_published, price , saleable) ; 
        this.type ="paperbook" ; 
        this.ship = ship;

        // I will not increase the counter of stock as creation of a book doesnt mean it is in stock
    }

}
