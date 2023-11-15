package lab12;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Paths;
import java.util.Scanner;

public class Encrypter {

    private int shift;
    private String encrypted;

    /**
     * Default Constructor
     */
    public Encrypter() {
        this.shift = 1;
        this.encrypted = "";
    }

    /**
     * Non-default Constructor
     * @param s - custom shift amount
     */
    public Encrypter(int s) {
        this.shift = s;
        this.encrypted = "";
    }

    /**
     * Encrypts the content of a file and writes the result to another file.
     *
     * @param inputFilePath      the path to the file containing the text to be encrypted
     * @param encryptedFilePath the path to the file where the encrypted text will be written
     * @throws Exception if an error occurs while reading or writing the files
     */
    public void encrypt(String inputFilePath, String encryptedFilePath) throws Exception {
        //TODO: Call the read method, encrypt the file contents, and then write to new file
    	String d = readFile(inputFilePath);
    	String encrypt = "";
    	int c = 0;
    	for(int i = 0; i < d.length(); i++) {
    		/*if((int) d.charAt(i) >= 65 || (int) d.charAt(i) <= 90 || (int) d.charAt(i) >= 97  || (int) d.charAt(i) <= 127)	{
    			c = (int)d.charAt(i) + shift;
    			encrypt = encrypt + (char)c;
    			if(c == 91) {
    				c = 65;
    			}
    			if(c == 128) {
    				c = 97;
    			}
    			encrypt = encrypt + (char) c;
    		}
    		else {
    			encrypt = encrypt + d.charAt(i);
    			
    		}*/
    		encrypt = encrypt + shiftedLetter(d.charAt(i));
    	}
    	writeFile(encrypt, encryptedFilePath);
    }

    /**
     * Decrypts the content of an encrypted file and writes the result to another file.
     *
     * @param messageFilePath    the path to the file containing the encrypted text
     * @param decryptedFilePath the path to the file where the decrypted text will be written
     * @throws Exception if an error occurs while reading or writing the files
     */
    public void decrypt(String messageFilePath, String decryptedFilePath) throws Exception {
        //TODO: Call the read method, decrypt the file contents, and then write to new file
    	String h = readFile(messageFilePath);
    	String decrypt = "";
    	int k = 0;
    	for(int i = 0; i < h.length(); i++) {
    		/*if((int) h.charAt(i) >= 65 || (int) h.charAt(i) <= 90 || (int) h.charAt(i) >= 97  || (int) h.charAt(i) <= 127)	{
    			k = (int)h.charAt(i) - shift;
    			if(k == 64) {
    				k = 90;
    			}
    			if(k == 96) {
    				k = 127;
    			}
    			decrypt = decrypt + (char)k;
    		}
    		else {
    			decrypt = decrypt + h.charAt(i);
    		}	*/
    		decrypt = decrypt + shiftedLetter(h.charAt(i));	
    	}
    	
    	writeFile(decrypt, decryptedFilePath);
    }

    /**
     * Reads the content of a file and returns it as a string.
     *
     * @param filePath the path to the file to be read
     * @return the content of the file as a string
     * @throws Exception if an error occurs while reading the file
     */
    private static String readFile(String filePath) throws Exception {
        String message = "";
        //TODO: Read file from filePath
        try(Scanner fileReader = new Scanner(Paths.get(filePath))){
        	while(fileReader.hasNextLine()) {
        		String l = fileReader.nextLine();
        		message += l;
        	}
        	fileReader.close();
        }
        catch(Exception e) {
        	System.out.println("Error: " + e.toString());
        }
        return message;
    }

    /**
     * Writes data to a file.
     *
     * @param data     the data to be written to the file
     * @param filePath the path to the file where the data will be written
     */
    private static void writeFile(String data, String filePath) {
        //TODO: Write to filePath
    	try(FileWriter output = new FileWriter(filePath)){
    		output.write(data);
    		output.close();
    	}
    	catch(Exception e) {
    		System.out.println("Error: " + e.toString());
    	}
    }
    
    /**
     * Shifts the letter by converting it into a number and shifting
     * @param letter
     * @return the shifted character
     */
    private char shiftedLetter(char letter) {	
		int val = (int) letter;
		//Check if letter is uppercase, lowercase, or not a letter
		if(val <= 90 && val >= 65) { //uppercase
			int shifted = val + shift;	
			return shifted > 90 ? (char) (64 + shifted - 90) : (char) shifted;
		}
		else if(val <= 122 && val >= 97) { //lowercase
			int shifted = val + shift;
			return shifted > 122 ? (char) (96 + shifted - 122) : (char) shifted;
		}
		else 
			return letter;
    }
    

    /**
     * Returns a string representation of the encrypted text.
     *
     * @return the encrypted text
     */
    @Override
    public String toString() {
        return encrypted;
    }
}
