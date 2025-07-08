package fawry2;

public abstract class book // There will be no objects of type book it is just a blueprint thats why i made it abstract
{
    String type = "book"; // I can use int instead but would need to map each number to its type
    String name ; 
    String title; 
    String ISBN ; 
    short year_published;
    float price;
    boolean saleable;
    public book(String name, String title, String ISBN, short year_published, float price , boolean saleable) 
    {
        this.name = name;
        this.title = title;
        this.ISBN = ISBN;
        this.year_published = year_published;
        this.saleable = saleable ; 
        if (saleable) 
        {
           this.price = price; 
        }
        else this.price = -1;
        

    }
}
