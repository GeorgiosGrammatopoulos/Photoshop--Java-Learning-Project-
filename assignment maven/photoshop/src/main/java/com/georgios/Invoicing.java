package com.georgios;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import com.google.gson.*;
import com.georgios.service.*;


public class Invoicing {                         //this class is meant to construct a repository, so it cannot be called to connstruct active objects. all methods will
                                                 //either be, or aid static methods.

                                                //what is an invoice? an identifying code, and one user for many items
    String identifier;       //the unique id for a user: a string comprising of the full datetime information
    ArrayList <Item> invoice;   //main invoice text: the items chosen
    User user;        //user information: combined with user information, every invoice becomes unique

    //getters and setters
    public Invoicing(String identifier, ArrayList<Item> invoice, User user) {     
        this.identifier = identifier;
        this.invoice = invoice;
        this.user = user;
    }


    public Invoicing () {


    }


    public String getIdentifier() {
        return this.identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public ArrayList<Item> getInvoice() {
        return this.invoice;
    }

    public void setInvoice(ArrayList<Item> invoice) {
        this.invoice = invoice;
    }

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }


    public static Invoicing storeInvoice(ArrayList<Item> basket, User user) {

        String datetime = LocalDateTime.now().toString();
        Invoicing invoice = new Invoicing((datetime+"_"+user.getName()), basket, user);


        Gson jsoninvoice = new Gson(); //Making new Gson object, and parsing the class information to it
        String newInvoice = jsoninvoice.toJson(invoice);

        String json_file_path = "C:\\Users\\GrammatopoulosGeorgi\\OneDrive - Calco\\Bureaublad\\Java\\practice runs\\assignment\\assignment maven\\photoshop\\src\\main\\java\\com\\georgios\\invoices" + invoice.getIdentifier() + ".json";

        try {
            // Check if the file exists
            if (Files.notExists(java.nio.file.Paths.get(json_file_path))) {
                // Create the file if it does not exist
                Files.createFile(java.nio.file.Paths.get(json_file_path));
            }
        } catch (IOException e) {
            e.printStackTrace();}

        try (FileWriter writer = new FileWriter(json_file_path)) {
            writer.write(newInvoice);
            System.out.println("Your invoice information was stored successfully!");
        } catch (IOException er) {
            er.printStackTrace();
            System.out.println("The invoice information could not be stored. Try again!");
            }
    

        return invoice;

    }


    public static LocalDateTime deliveryDateTime(int printingTime) {          //The amount of time to print- calculated or lambda.
        
        LocalDateTime datetimeOfDelivery = LocalDateTime.now();              //The idea: add 8-hour increments from the total work hours into the the expected time.

        DataFrame openingHours = new DataFrame(null, null, null);   //a dataframe containing fields with the exact information from the csv
        openingHours.populateDataFrame ("C:\\Users\\GrammatopoulosGeorgi\\OneDrive - Calco\\Bureaublad\\Java\\practice runs\\assignment\\assignment maven\\photoshop\\src\\main\\java\\com\\georgios\\PhotoShop_OpeningHours.csv");

        while (printingTime > 0) {
         
            if (printingTime >= 1) {     //printing time is in minutes. So, 60 minutes for one hour
                datetimeOfDelivery = datetimeOfDelivery.plusMinutes(1);                 //Making sure that the remainder is incremented into the total time
            } else {datetimeOfDelivery = datetimeOfDelivery.plusMinutes(printingTime);}       //The time when it will be printed, without factoring operation times. Attention: emphasising on minutes-to-hours

            printingTime = printingTime - 1;     //making sure to reduce the increment from the total


            DayOfWeek dateOfInterest = datetimeOfDelivery.getDayOfWeek(); 

            int desiredDayOfWeek;
            

            switch (dateOfInterest) {
                case SUNDAY: desiredDayOfWeek =1; break;
                case MONDAY: desiredDayOfWeek =2; break;
                case TUESDAY: desiredDayOfWeek =3; break;
                case WEDNESDAY: desiredDayOfWeek =4; break;
                case THURSDAY: desiredDayOfWeek =5; break;
                case FRIDAY: desiredDayOfWeek =6; break;
                case SATURDAY: desiredDayOfWeek =7; break;
                default: desiredDayOfWeek =0; break;
            }


        //getting the day of the week the delivery will happen


        String openTimeKey = "OpenFrom_" + desiredDayOfWeek;
            String openTimeValue = openingHours.getFields().get(openTimeKey);
            LocalTime openTime = LocalTime.parse(openTimeValue);
            LocalDateTime timestampOpen = datetimeOfDelivery.toLocalDate().atTime(openTime);     //getting the opening time on the day of expected delivery

            String closeTimeKey = "OpenTill_" + desiredDayOfWeek;
            String closeTimeValue = openingHours.getFields().get(closeTimeKey);
            LocalTime closeTime = LocalTime.parse(closeTimeValue);
            LocalDateTime timestampClose = datetimeOfDelivery.toLocalDate().atTime(closeTime);  //getting the closing time on the day of expected delivery



            while (

                datetimeOfDelivery.isBefore(timestampOpen) ||
                datetimeOfDelivery.isAfter(timestampClose)||                        //Translate the store's horary into periods of activity and inactivity.
                datetimeOfDelivery.getDayOfWeek() == DayOfWeek.SUNDAY               //Then, the following equation:
                                                                                    //t(fin) = dt(active) + t(inactive) => t(fin) = (t(2) - t(1)) + t(inactive)
            ){


                openTimeKey = "OpenFrom_" + desiredDayOfWeek;
                openTimeValue = openingHours.getFields().get(openTimeKey);
                openTime = LocalTime.parse(openTimeValue);
                timestampOpen = datetimeOfDelivery.toLocalDate().atTime(openTime);    //re-caculating opening time based on the last iteration

                closeTimeKey = "OpenTill_" + desiredDayOfWeek;
                closeTimeValue = openingHours.getFields().get(closeTimeKey);
                closeTime = LocalTime.parse(closeTimeValue);
                timestampClose = datetimeOfDelivery.toLocalDate().atTime(closeTime);  //re-calculating closing time based on the last iteration


                if (datetimeOfDelivery.getDayOfWeek() == DayOfWeek.SUNDAY) {    //The order matters: Sunday pushes one day forward, so that it becomes Monday
                    datetimeOfDelivery = datetimeOfDelivery.plusDays(1);
                }
                else if (datetimeOfDelivery.isBefore(timestampOpen)) {         //Since, no matter what, we're within the week, checking opening time
                    datetimeOfDelivery = datetimeOfDelivery.plusHours(timestampOpen.getHour());
                }
                else if (datetimeOfDelivery.isAfter(timestampClose)) {   //Since, no matter what, we're within the week and after opening time, checking closing time
                    datetimeOfDelivery = datetimeOfDelivery.plusHours(24-timestampClose.getHour());
                }
            }

        }

            return datetimeOfDelivery;

    }

    public String totalPrintTime () {

        String printingTime;
        int printTime = 0;

        for (int i=0;  i< this.getInvoice().size(); i++) {
            printTime = printTime + this.getInvoice().get(i).getTimeToPrint();        
        }

        int mins = printTime % 60;
        int hours = printTime / 60;

        printingTime = String.valueOf(hours) + ":" + String.valueOf(mins);

        return printingTime;

    }

    public String totalPrice () {

        String totalPricing;
        double price = 0;

        for (int i=0;  i< this.getInvoice().size(); i++) {
            price = price + this.getInvoice().get(i).getPrice();        
        }

        totalPricing = String.valueOf(price);

        return totalPricing;
    }



    public static void printInvoice (Invoicing invoice) {
        
        int printTime = 0;     //Initial printing time in minutes

        for (int i=0;  i< invoice.getInvoice().size(); i++) {
            printTime = printTime + invoice.getInvoice().get(i).getTimeToPrint();        //Accumulating printing time in minutes into a single variable. Remember: It's in minutes!
        }

        DateTimeFormatter shortDate = DateTimeFormatter.ofPattern("dd-MM-yyyy");     //patterns for short date and short time - will come in handy
        DateTimeFormatter shortTime = DateTimeFormatter.ofPattern("HH:mm");


        System.out.println(
        "ORACLE'S APARTMENT\nInvoice No: " + invoice.getIdentifier() + "\t\t\t\tShop Assistant:" + "\n" +      //get the identifier for that invoice
        "Customer: " + invoice.getUser().getName() + "\t\t\t\tTrinity" +"\n" +
        invoice.getUser().getAddress() + " " + invoice.getUser().getHouseNo() + "\t\t\t\t42" + "\n" +       //from this moment on, simply passing the properties of the items contained in the invoice as strings
        invoice.getUser().getPostCode() + "\n" +
        invoice.getUser().getCity() + "\n" +
        invoice.getUser().getEmail() + "\n" + 
        invoice.getUser().getPhoneNumber().substring(0, 2) + "-" + invoice.getUser().getPhoneNumber().substring(2) + "\n\n\n" +
        "Order specifications:\nOrder number " + "\t\t\t\t" + invoice.getIdentifier().replaceAll("[^0-9]", "") + "\n" + 
        "Order date" + "\t\t\t\t" + invoice.getIdentifier().substring(0,4)+"/"+invoice.getIdentifier().substring(5,7)+"/"+invoice.getIdentifier().substring(8,10) + "\t" + invoice.getIdentifier().substring(11,13)+":"+invoice.getIdentifier().substring(14,16) + "\n" +
        "Production time in working hours: " + "\t\t\t\t" + invoice.totalPrintTime() + "\n" +
        "You can pick up your order at" + "\t\t\t\t" + deliveryDateTime(printTime).getDayOfWeek() + "\t" + deliveryDateTime(printTime).format(shortDate) + "\t" + "after\t" + deliveryDateTime(printTime).format(shortTime) + "\t" +
        "\n\n\nOrder items\nItem No./Item Name/Item Quantity/Prep Time/Price per item:\n\n");
        
        for (int i = 0; i < invoice.getInvoice().size(); i++) {

            System.out.println(
                
                invoice.getInvoice().get(i).getindex() + "\t" +
                invoice.getInvoice().get(i).getName() + "\t" +                   //print all the order items so far, so that the user may choose to adjust.
                invoice.getInvoice().get(i).getQuantity() + "\t" +
                invoice.getInvoice().get(i).getTimeToPrint() + "\t" +
                invoice.getInvoice().get(i).getPrice() + "\t" +
                "\n");}

        System.out.println("Total price: " + invoice.totalPrice());


        

        //parser to json

    }

    public static void storeInvoice (Invoicing invoice) {      //make empty invoice
        Gson gson = new Gson();
        String jsonInvoice = gson.toJson(invoice);
    
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
        String username = invoice.getUser().getName().replaceAll("\\s", " ");     //knowing the identifier contents, I can retrieve the datetime and create the file
        String fileName = timestamp + "_" + username + ".json";
    
        String jsonFilePath = "C:\\Users\\GrammatopoulosGeorgi\\OneDrive - Calco\\Bureaublad\\Java\\practice runs\\assignment\\assignment maven\\photoshop\\src\\main\\java\\com\\georgios\\invoices\\" + fileName; // Update the path to your directory

        try (FileWriter writer = new FileWriter(jsonFilePath)) {     //write the information into the file
            writer.write(jsonInvoice);
            System.out.println("Invoice information stored as JSON: " + fileName);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Failed to store the invoice information as JSON.");
        }
    }

    //reader for existing invoices
    public static void printInvoiceJson(String filepath) {

        try (Reader reader = new FileReader(filepath)) {     //reverse-engineering the previous function, I know what information to read
            Gson gson = new Gson();
            Invoicing selects = gson.fromJson(reader, Invoicing.class);

            printInvoice(selects);
            
        } catch (Exception f) {
            System.out.println("I am afraid we cannot retrieve any invoices at the moment!");
        }
    }
}