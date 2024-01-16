package com.georgios;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.io.File;
import com.georgios.service.*;


public class Main {

    public static void main(String[] args) {

        // Unfortunately, no one can be told what a photoshop is. You have to see it for yourself. This means: do not skip the comments!
        System.out.println("\n\n\n\t\tWelcome to 'Oracle's Apartment'.\n" +
        "\n\t\tYou got the (illustrator's) gift, but it looks like you're waiting for something.\n" +
        "\t\tHigh-quality printers.\n" +
        "\t\tWe know you may have had that feeling where you are not sure if you are awake or dreaming.\n" +
        "\t\tYou are awake: we do have printing capactiy, available online!\n" +
        "\t\tYou were brought here by the question. You know the question. Just as I did.\n" +
        "\t\t'What is high-resolution industry-specific printing?'\n" +
        "\t\tSo now you know the truth: You don't have to ask anymore!\n");

        

        while (true) {  //This loop is for people who rebel against the system. Can you get back to reality?

            welcomeNeo();  

            System.out.println("Would you like to get back to the real world?"); //Exit program option
            Scanner exit = new Scanner (System.in);
            String escape = exit.nextLine();


            if (escape.equalsIgnoreCase("blue pill")) {
                System.out.println("See you, Cypher!"); //Rewarding good life choices.
                break;
            }
            
        }

    }



    public static void welcomeNeo() {           //the super-method that defines the life cycle of use: instantiate invoice, read previous or insert information?

        

            // Create variables externally, so that they are used before exiting the modification stuff
            String choice = "Illusion";  //Because that's what choice is, right?
            ArrayList<Item> order = new ArrayList<>();
            User defUser = new User();
            User activeUser = new User();

            //invoice naming system: first datetime, then (on submission) underscore and member name; accounting wise, very helpful
            //Also, helps recover previous invoices

            Boolean orderEnd = false;
            Invoicing invoice = new Invoicing(null, order, activeUser);
            //Within the lifecycle of a signle order, all this needs to happen only once


        while(true) {            
            

            //Offer the user choices
            System.out.println("\nNow, let's see how deep the rabbit hole goes.\nSelect:\n\"Basket\" , to modify your order basket\n\"User\" , to insert or change your information \n\"Invoices\", to print previous invoices");
            Scanner switcher = new Scanner(System.in);
            
            try {
                choice = switcher.nextLine();
            } catch (java.util.InputMismatchException e) {

                choice = "Choice";
            }

            switch (choice) {     //since we're nested-looping, switches are more efficient, contained and controllable. Also, Agent Smith prefers current redirection to human algorithmic logic.
                case "Basket":
                    order = orderManagement(order);
                    break;


                case "basket":
                    order = orderManagement(order);
                    break;

                case "User":
                    activeUser = loginFeature();
                    break;

                case "user":
                    activeUser = loginFeature();
                    break;

                case "Invoices":
                    printPreviousInvoices(activeUser);
                    break;

                case "invoices":
                    printPreviousInvoices(activeUser);
                    break;

                case "Illusion":
                    System.out.println("\n\n\n\n\n\n\nDo you know why you're here?\n Find out: https://www.amazon.com/Matrix-Reloaded-Keanu-Reeves/dp/B001EBWIV8");
                    break;

                case "return": 
                    break;

                    
                default:
                    // If the choice is not 1, 2 or 3, show the order information so far
                    System.out.println("You will not escape that easy!\n");}

            invoice.setUser(activeUser);
            invoice.setInvoice(order);

            orderEnd = finalizeOrder(order, activeUser);   //Checks if there is an active user and an active basket


            if (orderEnd) {

                System.out.println("\nIt seems we have everything we need! Would you like to keep editing?");

                LocalDateTime now = LocalDateTime.now();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                String formattedDateTime = now.format(formatter);
                String invoiceId = formattedDateTime;

                invoice.setIdentifier(invoiceId);

                choice = switcher.nextLine();

                if (!choice.equals("yes") && !choice.equals("Yes"))                    

                    {System.out.println("\n\n\n\n\n\nYou don't! Let me finalize your order:\n");

                    Invoicing.printInvoice(invoice);

                    System.out.println("\nDo you want to proceed to payment, and complete your purchase?");

                    choice = switcher.nextLine();

                    if (choice.equals("yes")) {

                        Invoicing.storeInvoice(invoice);

                        System.out.println("\n\n\n Thank you for shopping with us! \n Fun fact before you go:\n" +
                        "Did you know hope and despair are nearly identical in code?\n\n\n\n\n\n\n\n\n\n");
                        //(some people go their entire lives without hearing news that good)
                        break;

                    } else {System.out.println("You will now be redireced to main");}
                    
                    }
                }
            }
        
        }
    

    public static ArrayList<Item> orderManagement (ArrayList<Item> order) {          //creating the list of the items.
                                                                                     //enter, parse list, choose, alter, change on command


        while (true) {

            System.out.println("\nPlease press:\n\"Add\", to add an item to your basket\n\"Adjust\", to adjust an item\n\"Delete\", to delete an item");
            Scanner scanner = new Scanner(System.in);
            String choice = scanner.nextLine();


            switch (choice) {       //this switch indicates

                case "Add":

                    //show a list of all the items

                    System.out.println("\n\n\nOur items:\n\n\n");  //print the item list- just when it's needed!

                    CsvReader.printFullTable("C:\\Users\\GrammatopoulosGeorgi\\OneDrive - Calco\\Bureaublad\\Java\\practice runs\\assignment\\assignment maven\\photoshop\\src\\main\\java\\com\\georgios\\PhotoShop_PriceList.csv");

                    System.out.println("\n\n\n");  //print the item list- just when it's needed!

                    try {
                        order.add(Item.addItem());     //adding an item; remember, this process runs every time something is added, so duplicate items can only happen once!
                    } catch (NullPointerException e) {
                        System.out.println("Your input has failed! You will be returned to main");      //if this returns null, it means that someone requested something that does not exist- repeat
                        break;
                    }

                    int addOn; // to store the quantity of duplicates
                    int targetIndex; // to store the index of the duplicate item
                
                    for (int i = 0; i < order.size(); i++) {
                        addOn = 0; // Reset addOn for each iteration
                        targetIndex = -1; // Reset targetIndex for each iteration
                
                        for (int j = i + 1; j < order.size(); j++) {
                            if (order.get(i).getindex() == order.get(j).getindex()) {
                                addOn += order.get(j).getQuantity(); // Accumulate quantity of duplicates
                                targetIndex = order.get(j).getindex();
                                order.remove(j); // Remove the duplicate item
                                j--; // Adjust the loop index after removal
                            }
                        }
                
                        if (targetIndex != -1) {
                            // Find the original item and update its quantity
                            for (int k = 0; k < order.size(); k++) {
                                if (order.get(k).getindex() == targetIndex) {
                                    order.get(k).setQuantity(order.get(k).getQuantity() + addOn);
                                    order.get(k).setTimeToPrint(order.get(k).calculatePrintTime());
                                    order.get(k).setPrice(order.get(k).calculateTotalPrice());
                                    break; // Exit the loop once the original item is found and updated
                                }
                            }
                        }
                    }
                
                    break; // Closing the switch

                case "Adjust":

                     System.out.println(
                        "\n\n" +
                        "No" + "\t" +
                        "Item" + "\t" +
                        "Quantity" + "\t" +
                        "Minutes to Print" + "\t" +                //print all the order items so far, so that the user may choose to adjust.
                        "Price" + "\t" +
                        "\n"
                        );


                    for (int i = 0; i < order.size(); i++) {

                        System.out.println(
                            order.get(i).getindex() + "\t" +
                            order.get(i).getName() + "\t" +
                            order.get(i).getQuantity() + "\t" +
                            order.get(i).getTimeToPrint() + "\t" +
                            order.get(i).getPrice() + "\t" +
                            "\n"
                        );

                    }

                      //print all the order items so far, so that the user may choose to adjust.

                    System.out.println("\nSelect the index item you would like to adjust, or \"exit\" to get back to the menu:");
                    choice = scanner.nextLine();
                    

                    while(!choice.equals("exit")) {

                    try {
                        Item itemChoice = Item.getItem(choice, order);     //get the item to be adjusted
                        itemChoice.adjustItem();
                        break;

                    } catch (NullPointerException e) {
                        System.out.println("Invalid input! Select a valid index, or \"exit\" to get back to the menu:");
                        choice = scanner.nextLine();
                        }
                    }

                    break;


                case "Delete":

                    System.out.println(          //printing all the order items so far
                        "\n\n" +
                        "No" + "\t" +
                        "Item" + "\t" +
                        "Quantity" + "\t" +
                        "Minutes to Print" + "\t" +
                        "Price" + "\t" +
                        "\n"
                        );


                    for (int i = 0; i < order.size(); i++) {

                        System.out.println(
                            
                            order.get(i).getindex() + "\t" +
                            order.get(i).getName() + "\t" +                   //print all the order items so far, so that the user may choose to adjust.
                            order.get(i).getQuantity() + "\t" +
                            order.get(i).getTimeToPrint() + "\t" +
                            order.get(i).getPrice() + "\t" +
                            "\n");}


                    System.out.println("Select the index item you would like to remove, or type in \"exit\" to get back to the menu:");
                    choice = scanner.nextLine();
                    

                    while(!choice.equals("exit")) {

                    try {
                        Item itemChoice = Item.getItem(choice, order);     //get the item to be adjusted
                        order.remove(itemChoice);
                        break;

                    } catch (NullPointerException e) {
                        System.out.println("Invalid input! Select a valid index, or \"exit\" to get back to the menu:");
                        choice = scanner.nextLine();
                        }
                    }

                    break;


                default:
                    	
                    System.out.println("");


            }

            System.out.println("\nWould you like to keep editing your order? (yes/no)");       //breaking loop if user chooses "yes"
            choice = scanner.nextLine();

            if (!choice.equals("yes")) {
            break;}
            }
        

        return order;      //return the remaining order

    }
    
    public static User loginFeature() {
        System.out.println("\nDo you have the credentials to enter the \"Nebuchadnezzar?\"");   //applying login feature
  
        User user = User.confirmEditDefault();

        boolean name = false;
        boolean address = false;
        boolean no = false;
        try { 
            name = user.getName().equals(null);         //check if there are the important information in the user selected
            address = user.getAddress().equals(null);
            no = user.getHouseNo().equals(null);
        } catch (NullPointerException e)
        {

        }

        finally {
            if (!name && !address && !no) {
                System.out.println("\nYou are successfully logged in!");
            } else {
                System.out.println("\nThere was an error with your login... Please try again!");  //messages about it
            }
        }
    
        return user;
    }
    
    public static void printPreviousInvoices(User user) {

        if (user.getName() == null) {

            System.out.println("\nYou are not logged in! You don't have the permissions to access invoices...");   //checker for input method

        } else {

            getPrevious(user);

        }

        
    }

    public static boolean finalizeOrder(List<Item> order, User activeUser) {   //checking if the order is complete: that is, containing both user information and a semi-full basket
        if (isUserInformationComplete(activeUser)  && isOrderValid(order)) {
            // Perform actions to confirm the order
            return true;
        } else {
            return false;
        }
    }
    
    private static boolean isUserInformationComplete(User user) {
        // Check if the user object has all necessary information filled
        return user.getName() != null && user.getAddress() != null && user.getHouseNo() != null; //those fields should be just enough to check; anything else is not necessary for delivery
    }
    
    private static boolean isOrderValid(List<Item> order) {
        // Check if the order list is not empty and contains valid items
        return order != null && !order.isEmpty();
    }

    public static void getPrevious(User user) {
        Scanner scanner = new Scanner(System.in);
    
        File folder = new File("C:\\Users\\GrammatopoulosGeorgi\\OneDrive - Calco\\Bureaublad\\Java\\practice runs\\assignment\\assignment maven\\photoshop\\src\\main\\java\\com\\georgios\\invoices");
    
        // Retrieve files' path
        String path = "C:\\Users\\GrammatopoulosGeorgi\\OneDrive - Calco\\Bureaublad\\Java\\practice runs\\assignment\\assignment maven\\photoshop\\src\\main\\java\\com\\georgios\\invoices\\";
    
        File[] files = folder.listFiles(); // Get list of files
    
        System.out.println("\nYour invoices (invoice number/datetime):");
        for (int i = 0; i < files.length; i++) {
            String fileName = files[i].getName();
            String endgame = fileName.substring(fileName.lastIndexOf("\\") + 1, fileName.length()-5);

            if (endgame.substring(15).equals(user.getName())) {
            
            System.out.println((i + 1) + ". " + endgame.substring(0,4) + "/" + endgame.substring(4, 6) + "/" + endgame.substring(6, 8) + "\t" 
            + endgame.substring(8,10) + ":" + endgame.substring(10, 12) + ":" + endgame.substring(12, 14));} else {continue;} // Print index and file name
        }
    
        System.out.println("\nEnter the number of the file you want to select:");
        int selectedIndex = scanner.nextInt();
    
        if (selectedIndex > 0 && selectedIndex <= files.length) {
            String selectedFileName = files[selectedIndex - 1].getName();
            String concat = path + selectedFileName;
            Invoicing.printInvoiceJson(concat);
        } else {
            System.out.println("Invalid index!");
        }
    }
    

}