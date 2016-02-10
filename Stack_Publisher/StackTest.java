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
        return pubId + " " + pubName + " " + pin;    
    }
}

class Stack {

    Publisher[] list;
    int size = -1;

    public Stack(int size){
        list = new Publisher[size];
    }

    public void push(Publisher p) {
        if(size == list.length){
            System.out.println("Overflow occured");
        }

        size++;
        list[size] = p;
    }

    public Publisher pop() {
        if(size < 0) {
            // System.out.println("Underflow occured");
            size = -1;
            return null;
        }    

        Publisher temp = list[size];
        list[size] = null;
        size--;

        return temp;
    }

    public Publisher peek() {
        if(size >= 0)
            return list[size];
        return null;
    }

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
            BufferedReader ip = new BufferedReader(new FileReader("Publisherin.dat"));
            while((line = ip.readLine()) != null) {
                String[] s = line.split(" ");
                String id =  s[0];
                String pin = s[1];
                String name = s[2];
                
            //  System.out.println(id + " " + pin + " " + name);
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
                op.append("\n");
                op.flush();
            }


            ip.close();
            op.close();

        }  catch(Exception e) {
            e.printStackTrace();
        }
    }   
}
