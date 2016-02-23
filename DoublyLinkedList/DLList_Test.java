import java.io.*;
import java.util.Scanner;

class Student {
    String sId, sName, sDob;

    public Student(String id, String name, String dob) {
        sId = id;
        sName = name;
        sDob = dob;
    }
    
    // Just to create a quick and dirty object for comparison.
    public Student(String id) {
        this(id, "", "");
    }

    public String toString() {
        return sId + " " + sName + " " + sDob + "\n";    
    }

    public boolean equals(Student o) {
        return sId.equals(o.sId);
    }

}

class Node {
    Student s;
    Node next;
    Node prev;

    public Node(Student s, Node prev, Node next) {
        this.s = s;
        this.prev = prev;
        this.next = next;
    }

    public String toString() {
        return s + prev.s.sName + next.s.sName;    
    }

    public boolean equals(Node o) {
        return this.s.equals(o.s);    
    }
        
}

class DLList {
    private Node head;
    private Node tail;
    private int size;
    
    public DLList() {
        head = null;    
        tail = null;
        size = 0;
    }

    public void addFirst(Student stud) {
        Node node = new Node(stud, null, null);

        if(head == null) {
            head = node;
            tail = head;
        }
        else {
            tail.prev = node;
            node.next = head;
            head = node;
        }
        size++;
    }
    
    public void addLast(Student stud) {
        Node node = new Node(stud, null, null);
        
        if(head == null) {
            head = node;
            tail = head;
        }
        else {
            node.prev = tail;
            tail.next = node;
            tail = node;
        }
        size++;
    } 

    /* Handle 4 cases while removing stuff:
     *     1. head is the key
     *     2. tail is the key
     *     3. key is somewhere in the middle
     *     4. key is not found
     */
    public void remove(String key) {
        Student s = new Student(key);
        Node node = new Node(s, null, null);

        if(head.equals(node)) {
            if(size == 1) {
                head = null;
                tail = null;
                size = 0;
                return;
            }
            head = head.next;
            head.prev = null;
            size--;
            return;
        }

        if(tail.equals(node)) {
            tail = tail.prev;
            tail.next = null;
            size--;
            return;
        }
        
        Node temp;
        for(temp = head.next; temp != tail; temp = temp.next) {
            if(temp.equals(node)) {
                Node p = temp.prev;
                Node n = temp.next;

                p.next = n;
                n.prev = p;
                size--;
                return;
            }   
        }

        System.out.println("Key is not found");
    }

    public void traverseForward() {
        Node temp = head;

        while(temp != null) {
            System.out.println("" + temp.s);
            temp = temp.next;
        }
    }

    public void traverseBackwards() {
        Node temp = tail;

        while(temp != null) {
            System.out.println("" + temp.s);
            temp = temp.prev;
        }
    }
}

class DLList_Test {
    public static void main(String[] args) {
        Scanner inp = new Scanner(System.in);
        DLList list = new DLList();
        String line;

        try{
            BufferedReader ip = new BufferedReader(new FileReader("stud_in.dat"));

            while((line = ip.readLine()) != null) {
                String[] s = line.split(" ");
                Student temp = new Student(s[0], s[1], s[2]);
                list.addLast(temp);
            }
            ip.close();
            System.out.println("List loaded");

            int ch = 5;    // 5 has no meaning

            while(ch != 0) {
                System.out.print("Enter choice:\n" +
                        "\t1: Display List (Forward) \n" +
                        "\t2: Display List (Backward) \n" +
                        "\t3: Delete an item (using id)\n" +
                        "\t0: Exit \n " +
                        "\n" +
                        "Your choice: ");
                ch = inp.nextInt();
                
                // If you are bored, skip the switch statements and 
                // just do the things inside the cases
                switch(ch) {
                    case 1:
                        System.out.println("Traversing forwards");
                        list.traverseForward();
                        break;
                    case 2: 
                        System.out.println("Traversing backwards");
                        list.traverseBackwards();
                        break;
                    case 3:
                        System.out.println("Enter id no to delete");
                        String idNo = inp.next();
                        list.remove(idNo);
                        break;
                    case 0:
                        System.out.println("Exiting");
                        return;
                    default:
                        System.out.println("Enter a valid choice");
                }
            }
                        
        } catch(Exception e) {    // Please don't do this. Put good errors to use.
            e.printStackTrace();    
        }
    }    
}
