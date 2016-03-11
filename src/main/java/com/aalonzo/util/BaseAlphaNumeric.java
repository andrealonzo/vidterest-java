package com.aalonzo.util;


/**
 * @author aalonzo
 * Base encoder/decoder that converts numbers into letters
 * 
 * ex.
 * 
 * 1 = a
 * 
 * 62 = ab
 *
 */
 public final class BaseAlphaNumeric {
	 static String alphabet = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
	 static int base_count = alphabet.length();

	 public static String encode(int input){

         if (input == 0) return String.valueOf(alphabet.charAt(0));
		 String encoded = "";
	     while (input > 0) {
	       encoded = String.valueOf(alphabet.charAt(input % base_count)).concat(encoded);
	       input = input / base_count;
	   }
	
	   return new StringBuilder(encoded).reverse().toString();
	 }	 
	 public static int decode(String input){
         double decoded = 0;
         
         for (int i = 0; i < input.length(); i++){
        	    char character = input.charAt(i);   
        	    decoded += alphabet.indexOf(character) * Math.pow(base_count, i);
        	}

        return (int)decoded;
	 }
	 


}
