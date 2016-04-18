import java.io.*;
import java.util.Scanner;

class Student {
    String id, name;
    double cgpa;

    public Student(String a, String b, String c) {
        id = a;
        name = b;
        cgpa = Double.parseDouble(c);
    }

    public String toString() {
        return id + " : " + name + " : " + cgpa + "\n";    
    }

    public int compareTo(Student o) {
        return this.id.compareTo(o.id);
    }

}

class Node {
    Student stud;
    Node parent;
    Node Lchild, Rchild;

    public Node(Student val, Node l, Node r, Node parent) {
        stud = val;
        Lchild = l;
        Rchild = r;
        this.parent = parent;
    }

    public String toString() {
        return stud + "";
    }

    public int compareTo(Node o) {
        return stud.compareTo(o.stud);   
    }
}

class BST {
    Node root;
    int size = 0;

    public BST() {
        root = new Node(null, null, null, null);

    }   

    public void insert(Student val) {
        insertH(val, root);    
    }
    public void preOrder() {
        preOrderH(root);    
    }
    public void inOrder() {
        inOrderH(root);    
    }
    public void postOrder() {
        postOrderH(root);    
    }

    public void insertH(Student val, Node p) {
        if(size == 0) {
            root.stud = val;
            size++;
            return;
        }
        if(val.compareTo(p.stud) <= 0) {
            if(p.Lchild != null)
                insertH(val, p.Lchild); 
            else {
                p.Lchild = new Node(val, null, null, p);
                size++;
            }
        }
        else if(val.compareTo(p.stud) > 0) {
            if(p.Rchild != null)
                insertH(val,p.Rchild);    
            else{
                p.Rchild = new Node(val, null, null, p);
                size++;
            }
        }

    }

    public void preOrderH(Node p) {
        System.out.print("" + p);
        if(p.Lchild != null) {
            preOrderH(p.Lchild);
        }
        if(p.Rchild != null) {
            preOrderH(p.Rchild);    
        }
    }

    public void postOrderH(Node p) {
        if(p.Lchild != null) {
            postOrderH(p.Lchild);
        }
        if(p.Rchild != null) {
            postOrderH(p.Rchild);    
        }
        System.out.print("" + p);
    }

    public void inOrderH(Node p) {
        if(p.Lchild != null) {
            inOrderH(p.Lchild);
        }
        System.out.print("" + p);
        if(p.Rchild != null) {
            inOrderH(p.Rchild);    
        }
    }

}


class BST_Tester {
    public static void main(String[] args) { 
        Scanner inp = new Scanner(System.in);
        int ch = 0;
        BST t = new BST();
        int count = 0;

        String line;

        try {
            BufferedReader ip = new BufferedReader(new FileReader("stud_in.dat"));
            while((line = ip.readLine()) != null) {
                String[] s = line.split(" : ");
                Student temp = new Student(s[0], s[1], s[2]);
                t.insert(temp);
            }

            System.out.println("File Loaded");
            if (t != null) 
                System.out.println("Tree populated");
            else 
                System.out.println("Error in populating tree");
            
            while(true) {
                System.out.println("Enter your choice: \n" +
                    "1. PreOrder Traversal \n" +
                    "2. PostOrder Traversal \n" + 
                    "3. InOrder Traversal \n" +
                    "4. Exit"
                );    
                ch = inp.nextInt();

                switch(ch) {
                    case 1: 
                        System.out.println("PreOrder traversal...");
                        t.preOrder();
                        break;

                    case 2: 
                        System.out.println("PostOrder traversal...");
                        t.postOrder();
                        break;
                    case 3:
                        System.out.println("InOrder traversal...");
                        t.inOrder();
                        break;
                    case 4:
                        return;
                    default:
                        System.out.println("Enter a valid choice:");
                }
            }


                
        } catch(Exception e) {
            e.printStackTrace();
        }

/*
        while(true) {
            System.out.println("Enter your choice: \n" +
                "1. Insert a node \n" +
                "2. PreOrderTraversal \n" + 
                "4. Exit"
            );    
            ch = inp.nextInt();

            switch(ch) {
                case 1: 
                    System.out.print("Enter value: ");
                    int v = inp.nextInt();
                    if(v == 0) break;
                    if(count == 0) {
                        t = new BST(v);    
                    } else {
                        t.insert(v, t.root);    
                    }
                    count++;
                    break;

                case 2: 
                    System.out.println("PreOrder traversal...");
                    t.preOrder(t.root);
                    break;
                case 4:
                    return;
                default:
                    System.out.println("Enter a valid choice:");
            }
        }
/**/
        
    
    }
}
