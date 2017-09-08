import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by Bence Ludmány & Hakim EL Amri on 2017-09-03.
 */

public class StringLibrary {
    
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException{

        welcome();

        while (true) {
            System.out.println();
            char command = askForInput();
            handleCommand(command);
        }


    }

    //UI| Shows the user a nice sign when starting the program
    private static void welcome(){

        System.out.println("******************************");
        System.out.println("**                          **");
        System.out.println("**  Welcome to the DIT042   **");
        System.out.println("**      String Library      **");
        System.out.println("**                          **");
        System.out.println("******************************");

    }

    //UI| Explain the available commands to the user, prompt for input
    private static char askForInput() throws IOException{
        System.out.println("Please choose a String operation or press ’q’ to quit:");
        System.out.println("Press c for concatenation");
        System.out.println("Press e for equals");
        System.out.println("Press s for substring");
        System.out.println("Press t for trim");
        System.out.println("Press l for lastIndexOf");
        System.out.println("Press h for contains");
        return br.readLine().toLowerCase().charAt(0);
    }

    //CORE| Routes different commands to their corresponding functions
    private static void handleCommand(char command) throws IOException{

        String str,str2,finalString;
        int startIndex,stopIndex,lastIndex;
        boolean isSame;
        char c;


        switch(command){

            case 'c':
                System.out.print("Please enter the first string: ");
                str = br.readLine();
                System.out.print("Please enter the second string: ");
                str2 = br.readLine();

                finalString = concatenate(str,str2);

                System.out.println(concatenate("The result of concatenating ",str," and ",str2," is: ",finalString));
                break;
            case 'e':
                System.out.print("Please enter the first string: ");
                str = br.readLine();
                System.out.print("Please enter the second string: ");
                str2 = br.readLine();

                isSame=equals(str,str2);

                System.out.println(concatenate("The result of comparing ",str," and ",str2," is: ",Boolean.toString(isSame)));
                break;
            case 's':
                System.out.print("Please enter the first string: ");
                str = br.readLine();
                System.out.print("Please enter the first index: ");
                startIndex = Integer.parseInt(br.readLine());
                System.out.print("Please enter the second index: ");
                stopIndex = Integer.parseInt(br.readLine());

                finalString=substring(str,startIndex,stopIndex);
                if(equals(finalString,"")){
                    System.out.println("Error: Something went wrong. Please try again!");
                    handleCommand('s');
                }
                else{
                    System.out.println(concatenate("The resulting substring is: ",finalString));
                }
                break;
            case 't':
                System.out.print("Please enter a sentence to trim: ");
                str=br.readLine();

                finalString=trim(str);

                System.out.println(concatenate("The resulting trimmed sentence is: ",finalString));
                break;
            case 'l':
                System.out.print("Please enter a string: ");
                str=br.readLine();
                System.out.print("Please enter a character: ");
                c=br.readLine().charAt(0);

                lastIndex=lastIndexOf(str,c);

                System.out.println(concatenate("The index of character ",Character.toString(c)," in string ",str," is: ",Integer.toString(lastIndex)));
                break;
            case 'h':
                System.out.print("Please enter the first string: ");
                str = br.readLine();
                System.out.print("Please enter the second string: ");
                str2 = br.readLine();

                if(contains(str,str2)){
                    System.out.println(concatenate("The string ",str," contains the string ",str2));
                }
                else {
                    System.out.println(concatenate("The string ",str," does not contain the string ",str2));
                }
                break;
            case 'q':
                System.out.println("Thanks! Goodbye.");
                System.exit(0);
            default:
                System.out.println("This is not a valid choice. Please retry!");
                handleCommand(br.readLine().toLowerCase().charAt(0));

        }
    }

    //Concatenates every string parameter and returns the result
    private static String concatenate(String... strings){

        String finalString=strings[0];
        String currentString;
        for (int i = 1; i < strings.length; i++) {
            currentString = strings[i];
            for (int j = 0 ; j < currentString.length(); j++) {
                finalString +=  currentString.charAt(j);
            }
        }

        return finalString;

    }

    //Checks if 2 strings are completely the same, returns "" if invalid input
    private static boolean equals(String str1,String str2){

        boolean isSame = true;

        int s1Length = str1.length();
        int s2Length = str2.length();

        if(s1Length!=s2Length){
            return false;
        }
        for (int i = 0; i < s1Length; i++) {
            if(str1.charAt(i) != str2.charAt(i)){
                isSame=false;
                break;
            }
        }

        return isSame;

    }

    //Returns a portion of the string given from start index up until stop index
    private static String substring(String str, int startIndex, int stopIndex){

        String substr="";

        //Checking if inputs are valid, returning "" means error
        if(str.length()-1<startIndex || str.length()-1<stopIndex || startIndex > stopIndex){
            return "";
        }

        for (int i = startIndex; i < stopIndex; i++) {
            substr+=str.charAt(i);
        }

        return substr;

    }

    //Removes all whitespace from a string
    private static String trim(String str){

        String finalString="";

        for (int i = 0, slength = str.length(); i < slength; i++) {
            char currentChar = str.charAt(i);
            if(currentChar!=' '){
                finalString+=currentChar;
            }
        }

        return finalString;

    }

    //Returns index where character is last found, if not found returns -1
    private static int lastIndexOf(String str, char c){

        int index = -1;

        for (int i = 0, slength=str.length(); i < slength; i++) {

            if(str.charAt(i)==c){
                index = i;
            }

        }

        return index;

    }

    //Returns true if str2 is a substring of str1
    private static boolean contains(String str1, String str2){

        boolean contains = true;
        int counter = 0;
        int s2len=str2.length();
        int s1len=str1.length();

            for (int i = 0; i < s1len; i++) {
                if(counter == s2len){
                    return contains;
                }
                else if(s2len>s1len-i){
                    contains = false;
                    return contains;
                }
                for (int j = 0; j < s2len; j++) {
                    if(str2.charAt(j) != str1.charAt(i+j)){
                        counter=0;
                        break;
                    }
                    counter++;
                }
            }

        return contains;

    }

}
