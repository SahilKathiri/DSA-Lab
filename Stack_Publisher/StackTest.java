import java.io.*;

class Publisher{
    String pubId;
    String pubName;
    String pin;
    
    public Publisher(String a, String b, String c) {
        pubId = a;
        pubName = c;
        pin = b;
    }

    public String toString() {
        return pubId + " " + pubName + " " + pin + "\n";
    }
}

class Stack {

    Publisher[] list;
    int size = -1;                           //If stack empty, size=-1

    public Stack(int size){
        list = new Publisher[size];
    }

    public void push(Publisher p) {
        if(size == list.length){
            // OverFlow
            System.out.println("Overflow occured");
            return;
        }
        
        // If no overflow, add obj to list
        size++;
        list[size] = p;
    }

    public Publisher pop() {
        if(size < 0) {
            // UnderFlow
            size = -1;
            return null;
        }    

        // If no underflow, delete obj from list
        Publisher temp = list[size];
        list[size] = null;
        size--;

        return temp;
    }

    // Not used, but another operation. Used to see what the top of the stack is.
    public Publisher peek() {
        if(size >= 0)
            return list[size];
        return null;
    }

    // Again, not used. Still can be useful.
    public boolean isEmpty() {
        if(size == -1) 
            return true;
        return false;
    }

    public void display() {
        for(int i = 0; i <= size; i++) {
            System.out.println(list[i]);    
        }    
    }
}


public class StackTest{
    public static void main(String[] args) {
        Stack p = new Stack(8);

        try{
            String line;
            
            // Reading a file with BufferedReader is easier (for me atleast)
            BufferedReader ip = new BufferedReader(new FileReader("Publisherin.dat"));
            while((line = ip.readLine()) != null) {
                String[] s = line.split(" ");
                String id =  s[0];
                String pin = s[1];
                String name = s[2];
                
                Publisher temp = new Publisher(id, pin, name);
                p.push(temp);
            }

            System.out.println("Objects pushed to stack: ");
            p.display();

            System.out.println("Popping objects from stack to Publisherout.dat...");
            BufferedWriter op = new BufferedWriter(new FileWriter("Publisherout.dat"));
            Publisher temp;
            while((temp = p.pop()) != null) {
                op.append(temp.toString());
                System.out.print(temp.toString());
                op.flush();
            }

            // No big harm would be done here if you don't close the file. 
            // But it would be better if you did.
            // Or use try-with-resources.
            ip.close();
            op.close();

        }  catch(Exception e) {
            // Don't do this. Catch specific exceptions. 
            // I did this because I was lazy.
            e.printStackTrace();
        }
    }   
}
