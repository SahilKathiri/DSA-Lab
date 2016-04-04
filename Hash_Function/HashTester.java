import java.io.*;
import java.util.ArrayList;

class HashTable {
    ArrayList<ArrayList<String>> table;

    public HashTable() {
        table = new ArrayList<ArrayList<String>>();
        init();
    }

    private void init() {
        for(int i = 0; i < 6; i++) {
            table.add(new ArrayList<String>());
        }    
    }

    public void display() {
        for(int i = 0; i < 6; i++){
            System.out.println();
            System.out.print(i + " --->  ");
            printval(i);
            System.out.println();
        }
    }

    public void printval(int k) {
        ArrayList<String> col = table.get(k);

        for(int i = 0; i < col.size(); i++) {
            System.out.print(col.get(i) + "   ");    
        }
    }

    public void add(String val) {
        int key = ((val.charAt(0) + val.charAt(2) + 2*val.charAt(1)) * 17 + 5) % 6;
        table.get(key).add(val);
    }
}

public class HashTester {
    public static void main(String[] args) {
        HashTable ht = new HashTable();

        try{
            String line;
            BufferedReader ip = new BufferedReader(new FileReader("inp.dat"));
            while((line = ip.readLine()) != null) {
                String[] s = line.split(" ");
                for(String st : s) {
                    ht.add(st);
                }
            }

            ht.display();

        } catch(Exception e) {
                e.printStackTrace();
        }
    }    
}
