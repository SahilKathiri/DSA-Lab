import java.io.*;
import java.util.Scanner;

class Student {
    String sId, sName, sDob;

    public Student(String id, String name, String dob) {
        sId = id;
        sName = name;
        sDob = dob;
    }

    public String toString() {
        return sId + " " + sName + " " + sDob + "\n";    
    }

    public boolean equals(Student o) {
        return sId.equals(o.sId) && sName.equals(o.sName) && sDob.equals(o.sDob);
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
        
}

class DLList {
    private Node head;
    private Node tail;
    private int size;

    public DLList() {
        head = null;    
        tail = null;
        size = -1;
    }

    public void addFirst(Student stud) {
        head = new Node(stud, null, head);
        size++;

        if(size == 1) tail = head;
    }
    
    public void addLast(Student stud) {
        if(head == null)
            addFirst(stud);
        else {
            Node temp = head;

            while(temp.next != null)
                temp = temp.next;

            temp.next = new Node(stud, temp, null);
            tail = temp.next;
            size++;
        }

    } 

    public void remove(String key) {
        
        if(head == null) {
            System.out.println("List is empty");
            return;
        }
        if(head.s.sId.equals(key)) {
            head = head.next;
            head.prev = null;
            return;
        }

        Node curr = head;
        Node pre = head.prev;
        Node nex = head.next;

        int i = 0;
        while(curr != null && !curr.s.sId.equals(key)) {
            
            pre = curr;
            curr = curr.next;
            i++;
        }

        if(size == i) {
            pre = curr.prev;
            curr.next = null;
            curr.prev = pre;
            return;
        }

        if(curr == null) {
            System.out.println("Not found");
            return;
        }

        pre.next = curr.next;
        nex.prev = pre;
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
                System.out.println(line);
                String[] s = line.split(" ");
                Student temp = new Student(s[0], s[1], s[2]);
                list.addLast(temp);
            }
            ip.close();

            list.traverseBackwards();
            System.out.println("Enter id no to delete");
            String idNo = inp.next();
            list.remove(idNo);
            
            System.out.println("Traversing forwards");
            list.traverseForward();

            System.out.println("Traversing backwards");
            list.traverseBackwards();
        } catch(Exception e) {
            e.printStackTrace();    
        }
    }    
}
