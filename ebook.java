package fawry2;

public class ebook extends book
{
    
    String filetype ; // this will be the extention i will appened the input of file type to it ; 
    boolean mailable ; 
    ebook(String name, String title, String ISBN, short year_published, float price ,boolean saleable , String filetype, boolean mailable)
    {
        super(name, title, ISBN, year_published, price , saleable) ; 
        this.type ="ebook" ;
        this.filetype = "." + filetype;
        this.mailable = mailable;
        
        // I will not increase the counter of stock as creation of a book doesnt mean it is in stock
    }


}






