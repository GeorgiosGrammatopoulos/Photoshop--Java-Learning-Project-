package com.georgios;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.ArrayList;
import com.georgios.service.*;


public class Item {  //All the items in the store are items. They may be in stock, or outbound. So, we are handling inventory using
                       //two child objects, which need to amount to the total inventory: stock and outbound

    private int index;
    private String name;       //A standard item in a copy store: it has a name, a quantity, the time it takes to print
    private int quantity;      //and a price per item
    private int timeToPrint;
    private double price;



    public Item(int index, String name, int quantity, int timeToPrint, double price) {
        this.index = index;
        this.name = name;
        this.quantity = quantity;
        this.timeToPrint = timeToPrint;
        this.price = price;
    }

    public int getindex() {       //not bothering to clean them: it's an index and cannot be null. you'll see that rows from the csv depend on it existing.
        return this.index;
    }

     public static int getIndex(Item item) {       //static getter, to make sure that we can compare duplicate items in lists
        return item.index;
    }

    public void setIndex(int index) {
        this.index = index;}

    
                                     //constructor, getters, setters
    public String getName() {       //not bothering to clean them: they cannot be null by definition. you'll see that rows from the csv depend on it existing.
        return this.name;
    }

    public void setName(String name) {
        this.name = name;}


    public int getQuantity() {
        return this.quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getTimeToPrint() {
        return this.timeToPrint;
    }

    public void setTimeToPrint(int timeToPrint) {
        this.timeToPrint = timeToPrint;
    }

    public double getPrice() {
        return this.price;
    }
    
    public void setPrice(double price) {
        this.price = price;
    }

    public static Item getItem(String index, ArrayList<Item> list) {

        Item foundItem = null;

        // Iterate through the list to find the item with the desired property value
        for (Item item : list) {
            if (item.getIndexUse().equals(index)) {
                foundItem = item;
                break; // Exit loop when the item is found
            }
        }
 
        // Check if the item was found
        if (foundItem != null) {
             System.out.println("Item found: " + foundItem.getName());
         } else {
            System.out.println("There is no item under the index " + index + ".\nTry again!");
        }

        return foundItem;
        
    }

    //Supplements to getters and setters:
    //The logic remains the same: with the same scanner, we are reading and parsing information. If it's not good enough, change it.
    //Only, this time, we're also gonna tell people.

    public String getIndexUse () {


        String index = String.valueOf(this.getindex());  //parse whatever input as a valid quantity

        return index;
    }

    public void setIndexUse (String index) {

        int indexInt = 0;
        try {
            indexInt = Integer.parseInt(index);  //parse whatever input as a valid quantity
        } catch (NumberFormatException e) {
            System.out.println("You have requested an item which does not exist! Please try again...");     //if the quantity is invalid, set it to 0- add to the basket but not create an order
        }
    }


    public void setQuantityUse(String quantity) {

        try {
            this.quantity = Integer.parseInt(quantity);  //parse whatever input as a valid quantity
        } catch (NumberFormatException e) {
            this.quantity = 0;     //if the quantity is invalid, set it to 0- add to the basket but not create an order
        }

        this.setPrice(this.price * this.quantity);              //the quantity of items affects the total price of the item package and its preparation time!
        this.setTimeToPrint(this.timeToPrint * this.quantity);
        
    }


    private void setTimeToPrintUse(String timeToPrint) {

        String hoursStr = timeToPrint.substring (0,1);   //same logic with the constructor, only with some messages
        String minutesStr = timeToPrint.substring (3, 5);
        int hours = 0;
        int mins= 0;
        int initialTime = this.timeToPrint; //weird, huh? this variable is like the Architect...
        try {
            hours = Integer.parseInt(hoursStr);
            this.timeToPrint = this.timeToPrint + hours*60;
        } catch (NumberFormatException n) {
            try {
                hoursStr = timeToPrint.substring(0, 0);
                hours = Integer.parseInt(hoursStr);
                this.timeToPrint = this.timeToPrint + hours*60;
                } catch (NumberFormatException f) {
                    hours = 0;
                }
        }
        try {
            mins = Integer.parseInt(minutesStr); 
            this.timeToPrint = this.timeToPrint + mins;
        } catch (NumberFormatException n) { 
            mins = 1; 
            this.timeToPrint = this.timeToPrint + mins;
        }

        if (this.timeToPrint == initialTime) {
            System.out.println("Unfortunately, the time you put in is invalid.\nYour actions have no consequence.\nAlso, your input was not registered!"); 
            //...a means of comparison with the original version, which helps us determine if the operation was successful.
        }
    }

    

    private void setPriceUse(String price) {
        try {
            this.price = Double.parseDouble(price);
        } catch (NumberFormatException e) {     //that is when you intended to offer a price, but it's not in the proper format
            System.out.println("This pricing is invalid! But the machines are hungry...\nAuto-setting to one euro.");
            this.price = 1.0;
        } catch (NullPointerException f) {  //that is when you try to pass null as a parameter
            System.out.println("The machines are thankful for your generous offer, to give prints for free!");
            this.price = 0.0;
        }

    }


    public int calculatePrintTime () {      //Calculation of printing time. one printer makes things easier!
        
       return this.getQuantity() * this.getTimeToPrint();

    }

    public double calculateTotalPrice () {   //Price calculator
        return this.getQuantity()* this.getPrice();
    }




    public static Item addItem () {

        Scanner input = new Scanner(System.in);
        System.out.println("Please choose the number of the item you would like to add:");  //select the item from the csv
        String indexName = input.nextLine();

        System.out.println("Please choose the amount of articles you would like to order");
        String quantity = "1";
        try {quantity = input.nextLine();}
        catch (InputMismatchException e) {               //select the quantity of items you would like to add
            System.out.println("You offered an invalid value! The quantity of articles is set to 1");
        }


        DataFrame itemProp = new DataFrame(); //create a data frame, which will contain the unique combinations between the item name and the properties I am interested in
        itemProp.populateDataFrame("C:\\Users\\GrammatopoulosGeorgi\\OneDrive - Calco\\Bureaublad\\Java\\practice runs\\assignment\\assignment maven\\photoshop\\src\\main\\java\\com\\georgios\\PhotoShop_PriceList.csv");

        String name = itemProp.getFields().get("Item_" + indexName);   //general logic: we know the names of our columns. with our naming of every field in the form of a dataframe, we can now select specific rows in a column

       //Index: parsing whatever string we input as integer

        int indexInt = 0;
        try {
            indexInt = Integer.parseInt(indexName);  //parse whatever input as a valid quantity
        } catch (NumberFormatException e) {
            indexInt = 0;     //if the quantity is invalid, set it to 0- add to the basket but not create an order
        }

        //Quantity: parsing whatever string we input as integer

        int quantityInt = 0;
        try {
            quantityInt = Integer.parseInt(quantity);  //parse whatever input as a valid quantity
        } catch (NumberFormatException e) {
            quantityInt = 0;     //if the quantity is invalid, set it to 0- add to the basket but not create an order
        }


        //Time to print: checking minutes to print, checking hours to print, calculate total in minutes by incrementing valid values
        String timeToPrint = itemProp.getFields().get("PrepTime_"+ indexName);  //parsing the field containing the time to print into minutes to prepare; notice how handy is the double form of the index
        String hoursStr;
        String minutesStr;

        if (timeToPrint.length() >= 4) {
            hoursStr = timeToPrint.substring(0, 2);
            minutesStr = timeToPrint.substring(3,5);
        } else {
            hoursStr = "00";
            minutesStr = "01";     //if offered with an invalid time, we're just gonna work harder!
        }
        
        int hours = 0;
        int mins= 0;
        int timeToPrintMin = 0;
        try {
            hours = Integer.parseInt(hoursStr); //checking the first two digits: if they are a number, they get passed; otherwise...
            timeToPrintMin = timeToPrintMin + hours*60; //remember: an hour is 60 minutes, and it is possible that the first time digit is the only digit
        } catch (NumberFormatException n) {
            try {
                hoursStr = timeToPrint.substring(0, 0);   //...checking only the first digit. if it's a number, so be it; otherwise...
                hours = Integer.parseInt(hoursStr);
                timeToPrintMin = timeToPrintMin + hours*60;
                } catch (NumberFormatException f) {
                    hours = 0;   //...someone messed up the format. Setting hours to 0, to be safe. No need to add 0 to anything
                }
        }
        try {
            mins = Integer.parseInt(minutesStr); //same process for minutes, this time for minutes
            timeToPrintMin = timeToPrintMin + mins;
        } catch (NumberFormatException n) { 
            mins = 1;  //remember: in no format can the number of minutes be other than 2!
            timeToPrintMin = timeToPrintMin + mins; //now, we need to add some stuff
        }

        timeToPrintMin = timeToPrintMin * quantityInt;     //the way we are parsing items, is in packages of the same kind. so, multiplying by the quantity

        //Price. Now, that's a double. Same process with quantity, only for doubles

        String price = itemProp.getFields().get("Price_"+ indexName);
        double priceD = 0.0;
        try {
            priceD = Double.parseDouble(price);  //parse whatever input as a valid quantity
        } catch (NumberFormatException e) {
            priceD = 1.0;     //if the quantity is invalid, set it to 0- add to the basket but not create an order
        } catch (NullPointerException f) {
            priceD = 0.0;     //if the price is null, you are probably giving it away for free
        }

        priceD = priceD * quantityInt; //you remember the logic...


        Item item = new Item(0, "No name", 0, 0, 0.0);     //create new item based on the selected name
        item.setIndex (indexInt);
        item.setName(name);
        item.setQuantity(quantityInt);
        item.setTimeToPrint(timeToPrintMin);          //set corresponding values
        item.setPrice(priceD);

        return item;   //observe how the index and the name of the item are also parsed into the array list; will come in handy later!
    }

    

     public void adjustItem() {


        while (true) {
            
            Scanner input = new Scanner(System.in);
            System.out.println("Select the new quantity:");   //Remember: the only parameter that may be adjusted is the quantity!
            String choice = input.nextLine();
            this.setQuantityUse(choice);

            System.out.println("Are you sure of your choice?");
            choice = input.nextLine();
            
            if (choice.equals("yes") || choice.equals("Yes")) {
                this.setTimeToPrint(this.calculatePrintTime());
                this.setPrice(this.calculateTotalPrice()); 
                break;
            }

        }
        

    }

}



