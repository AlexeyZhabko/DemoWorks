
package zhabko;

public class MyClass {
    public static void main(String[] args) {
        System.out.println("Hello, world!"); // Basic example


        String message = "Second example"; // Defining new variable "message"
        System.out.println(message); // Sending message to the terminal


        for (int i=0; i<args.length; i++) {
            System.out.println(args[i]); // To see the result you have to use "javac command" and terminal
        }
    }
}

