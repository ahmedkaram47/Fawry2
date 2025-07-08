package fawry2;

public class demobook extends book
{

   demobook(String name, String title, String ISBN, short year_published, float price ,boolean saleable)
    {
        super(name, title, ISBN, year_published, price , false) ; 
        this.type ="demobook" ;
    }

}