package com.georgios.service;



public class Contains {   //this class is like the population of Zion: helpful, but just on the background. Also, as vulnerable, but we gotta give the machines a chance.

    public static boolean IntegerOrPunctuation (String value) {  //simple data validation rule: some strings can/cannot contain integers and punctuation

        if (value.contains(String.valueOf('0'))
            || value.contains(String.valueOf('1'))
            || value.contains(String.valueOf('2'))
            || value.contains(String.valueOf('3'))
            || value.contains(String.valueOf('4'))
            || value.contains(String.valueOf('5'))
            || value.contains(String.valueOf('6'))
            || value.contains(String.valueOf('7'))
            || value.contains(String.valueOf('8'))
            || value.contains(String.valueOf('9'))
            || value.contains(String.valueOf(','))
            || value.contains(String.valueOf('.'))
            || value.contains(String.valueOf('/'))
            || value.contains(String.valueOf('?'))
            || value.contains(String.valueOf(';'))
            || value.contains(String.valueOf('\"'))
            || value.contains(String.valueOf('\\'))
            || value.contains(String.valueOf('`'))
            || value.contains(String.valueOf('-'))
            || value.contains(String.valueOf('_'))
            || value.contains(String.valueOf('+'))
            || value.contains(String.valueOf('='))
            || value.contains(String.valueOf('!'))
            || value.contains(String.valueOf('@'))
            || value.contains(String.valueOf('#'))
            || value.contains(String.valueOf('$'))
            || value.contains(String.valueOf('%'))
            || value.contains(String.valueOf('^'))
            || value.contains(String.valueOf('&'))
            || value.contains(String.valueOf('*'))
            || value.contains(String.valueOf('('))
            || value.contains(String.valueOf(')'))
            || value.contains(String.valueOf('['))
            || value.contains(String.valueOf(']'))
            || value.contains(String.valueOf('{'))
            || value.contains(String.valueOf('}'))
            || value.contains(String.valueOf('|'))
            ){
                return true;
            } else {return false;}
    }


        public static boolean Punctuation (String value) {  //simple data validation rule: some strings can/cannot contain punctuation

        if (value.contains(String.valueOf(','))
            || value.contains(String.valueOf('.'))
            || value.contains(String.valueOf('/'))
            || value.contains(String.valueOf('?'))
            || value.contains(String.valueOf(';'))
            || value.contains(String.valueOf('\"'))
            || value.contains(String.valueOf('\\'))
            || value.contains(String.valueOf('`'))
            || value.contains(String.valueOf('-'))
            || value.contains(String.valueOf('_'))
            || value.contains(String.valueOf('+'))
            || value.contains(String.valueOf('='))
            || value.contains(String.valueOf('!'))
            || value.contains(String.valueOf('@'))
            || value.contains(String.valueOf('#'))
            || value.contains(String.valueOf('$'))
            || value.contains(String.valueOf('%'))
            || value.contains(String.valueOf('^'))
            || value.contains(String.valueOf('&'))
            || value.contains(String.valueOf('*'))
            || value.contains(String.valueOf('('))
            || value.contains(String.valueOf(')'))
            || value.contains(String.valueOf('['))
            || value.contains(String.valueOf(']'))
            || value.contains(String.valueOf('{'))
            || value.contains(String.valueOf('}'))
            || value.contains(String.valueOf('|'))
            || value.equals(null)
            ){
                return true;
            } else {return false;}
    }

        public static boolean Integer (String value) {  //simple data validation rule: some strings can/cannot contain integers

        if (value.contains(String.valueOf('0'))
            || value.contains(String.valueOf('1'))
            || value.contains(String.valueOf('2'))
            || value.contains(String.valueOf('3'))
            || value.contains(String.valueOf('4'))
            || value.contains(String.valueOf('5'))
            || value.contains(String.valueOf('6'))
            || value.contains(String.valueOf('7'))
            || value.contains(String.valueOf('8'))
            || value.contains(String.valueOf('9'))
            ){
                return true;
            } else {return false;}
    }


    
    
}
