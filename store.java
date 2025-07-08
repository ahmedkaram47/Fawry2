package fawry2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class store 
{

HashMap<book, Integer> inventory = new HashMap<>();

void showinventory ()
{
    for (HashMap.Entry<book, Integer> entry : inventory.entrySet()) 
    {

        book b = entry.getKey();
        int stock = entry.getValue();

        System.out.println("Type      : " + b.type);
        System.out.println("Title     : " + b.title);
        System.out.println("Author    : " + b.name);
        System.out.println("ISBN      : " + b.ISBN);
        System.out.println("Year      : " + b.year_published);
        //This is a cleaner way to print out price instead of printing -1 in the constructor function at book.java
        System.out.println("Price     : " + (b.saleable ? "$" + b.price : "Not for Sale"));
        
        System.out.println("Stock     : " + stock);
        System.out.println("------------------------------");

    }

}

void seecart(HashMap<book,Integer> cart) 
{
    if (cart.isEmpty()) 
    {
        System.out.println("Cart is empty.");
    } 
    else 
    {
        System.out.println("Items in your cart:");
        for (HashMap.Entry<book, Integer> entry : cart.entrySet()) 
            {
                book p = entry.getKey();
                int count = entry.getValue();
                System.out.println(p.title + " - Quantity: " + count + " - Price per item: " + p.price + " - Total price: " + (p.price * count));
            }
    }
}

    // USABLE PARTS FROM FAWRY CHALLENGE 1 
    //Since no implementation required this is here for demonstrating purposes only
interface shippable 
{
    String getname();
    String gettype();
    //int getweight();
}

    // USABLE PARTS FROM FAWRY CHALLENGE 1
    //Since no implementation required this is here for demonstrating purposes only
class shippablebook implements shippable 
{
    book p;

    public shippablebook(book p) 
    {
        this.p = p;
    }

    @Override
    public String getname() 
    {
        return p.title;
    }

    @Override
    public String gettype() 
    {
        return p.type;
    }
    //USABLE PARTS FROM CHALLENGE 1
    // @Override
    // // public int getweight() 
    // // {
    // //     return p.weight;
    // // }
}

class shippingservice 
{
    public int shipitems(HashMap<shippablebook,Integer> items) 
    {
        
    // USABLE PARTS FROM FAWRY CHALLENGE 1
    //Since no implementation required this is here for demonstrating purposes only
        
    //i will create an imaginary shipping fee per item for example 15
        System.out.println("* Shipment Notice *");
        int topay=0;
        for (HashMap.Entry<shippablebook, Integer> entry : items.entrySet()) 
        {

            shippablebook p = entry.getKey();
            int count = entry.getValue();
            System.out.println(count+"x " + p.getname() );
            topay+=15;
        }
       

    return topay;
    }
}

    // USABLE PARTS FROM FAWRY CHALLENGE 1
    //Since no implementation required this is here for demonstrating purposes only
Boolean checkout( HashMap<book,Integer> cart )
{
    //I will make this function return either 0 or 1 in case of failure or success respectively
    
    //Mock user balance 
    int balance = 10000;
    int ordersubtotal=0;
    int ship=0;
    HashMap<shippablebook,Integer> toship = new HashMap<shippablebook,Integer>();
    shippingservice DHL = new shippingservice();

    if(cart.isEmpty())
    {
        System.out.println("\n Cart is EMPTY!");
        return false;
    }
    
    for (HashMap.Entry<book, Integer> entry : cart.entrySet()) 
    {
        book p = entry.getKey();
        int count = entry.getValue();
        if (p.saleable == true ) 
        {
            //paperbook tmp = new paperbook(p.name, p.title, p.ISBN, p.year_published, p.price, p.saleable, true);

            shippablebook sp = new shippablebook(p);
            if (sp.gettype()=="paperbook") 
            {
                toship.put(sp, count);
            }
            
        }
    }

    ship+=DHL.shipitems(toship);

        for (HashMap.Entry<book, Integer> entry : cart.entrySet()) 
        {
            book p = entry.getKey();
            int count = entry.getValue();
            ordersubtotal+=(p.price * count);
        }


    balance -= ship;
    balance -= ordersubtotal;
    if (balance<0) 
    {
        System.out.println("You dont have enough balance to pay.");
        return false;
    }

    System.out.println("Subtotal: "+ordersubtotal);
    System.out.println("Shipping: "+ship);
    System.out.println("Amount: "+(ship+ordersubtotal));
    System.out.println("Remaining Balance: "+balance);
    
    return true;
}


public static void main (String [] args)
{

    store fawry = new store();
    //Our mock books 
    // NOTE : BOOKS DATA ARE FROM INTERNET I DONT KNOW IF THEY ARE CORRECT OR NOT I JUST USE THEM AS MOCK DATA 

    fawry.inventory.put(new paperbook("George Orwell", "1984", "9780451524935", (short)1949, 15.99f, true, true), 10);
    fawry.inventory.put(new paperbook("J.K. Rowling", "Harry Potter and the Sorcerer's Stone", "9780590353427", (short)1997, 12.49f, true, false), 5);
    fawry.inventory.put(new paperbook("Herman Melville", "Moby Dick", "9781503280786", (short)1851, 0.0f, false, true), 2);

    fawry.inventory.put(new ebook("F. Scott Fitzgerald", "The Great Gatsby", "9780743273565", (short)1925, 9.99f, true, "pdf", true), 15);
    fawry.inventory.put(new ebook("Leo Tolstoy", "War and Peace", "9780199232765", (short)1869, 0.0f, false, "pdf", false), 3);
    fawry.inventory.put(new ebook("Mary Shelley", "Frankenstein", "9780486282114", (short)1818, 4.50f, true, "pdf", true), 5);

    fawry.inventory.put(new demobook("Isaac Asimov", "Foundation Sample", "9780008117498", (short)1951, 0.0f, false), 0);
    fawry.inventory.put(new demobook("Arthur Conan Doyle", "Sherlock Holmes Demo", "9780241952894", (short)1892, 0.0f, false), 0);
    fawry.inventory.put(new demobook("Jane Austen", "Pride and Prejudice Excerpt", "9780199535569", (short)1813, 0.0f, false), 0);
    
    HashMap<book, Integer> cart = new HashMap<>();

    System.out.println("\nWelcome to our store!");
    while (true) 
    {
        Scanner s = new Scanner(System.in);

        System.out.println("Please choose an operation:");
        System.out.println("1 - Add Book to Cart");
        System.out.println("2 - See Books");
        System.out.println("3 - See Cart");
        System.out.println("4 - Update Inventory");
        System.out.println("5 - Checkout");
        System.out.println("6 - Exit");
        System.out.print("Your choice: ");
        
        int choice = s.nextInt();
        s.nextLine(); //It is a good practise i do to empty the input buffer after the next int even if i am going to close the buffer rn
        //s.close();

        switch (choice) 
        {
            case 1:
                System.out.println("Please enter book name and count:");
                String input = s.nextLine();        
                int count = s.nextInt();             
                s.nextLine();                        

                boolean found = false;

                for (book b : fawry.inventory.keySet()) 
                {
                    if (b.title.equalsIgnoreCase(input)) 
                    {
                        found = true;
                        int availableStock = fawry.inventory.get(b);

                        if (availableStock >= count) {
                            
                            fawry.inventory.put(b, availableStock - count);

                            
                            cart.put(b, cart.getOrDefault(b, 0) + count);

                            System.out.printf("Added %d copy/copies of \"%s\" to cart.\n", count, b.title);
                        } 
                        else 
                        {
                            System.out.println("Not enough in stock! Available: " + availableStock);
                        }
                        break;
                    }
                }

                if (!found) 
                {
                    System.out.println(" Book not found.");
                }


                break;
            case 2:
                fawry.showinventory();
                break;
            
            case 3:
                //fawry showing cart
                fawry.seecart(cart);
                break;
            
            case 4 :
            ArrayList<book> toremove = new ArrayList<>();
            int current_year = 2025;
            int oldyearfactor=200;
            for (HashMap.Entry<book, Integer> entry : fawry.inventory.entrySet()) 
            {

                book p = entry.getKey();
                //int count = entry.getValue();
                if (current_year-p.year_published >=oldyearfactor ) toremove.add(p);
            }
            
            for (book b : toremove) 
            {
                fawry.inventory.remove(b);
            }
            System.out.println("Inventory Updated.");

            //AS WE CAN SEE HERE THAT FROM OUT MOCK DATA PRIDE AND PREJUDICE AND FRANKENSTEIN WERE REMOVED AS THEY WERE OLDER THAN 200 YRS

            break;
            
            case 5: 
            //This is a similar checkout process to task 1 
            //USEABLE CODE FROM FAWRY CHALLENGE 1 go to check out mock implementation
            
            fawry.checkout(cart);

            break;

            case 6:
                System.out.println("Thank you for visiting Goodbye!");
                s.close();
                return;
                
            
            default:
                System.out.println("Invalid choice please try again.");
                break;
        }

    }

}

}
