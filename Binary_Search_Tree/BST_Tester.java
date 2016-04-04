import java.io.*;
import java.util.Scanner;

class Node {
    int value;
    Node parent;
    Node Lchild, Rchild;

    public Node(int val, Node l, Node r, Node parent) {
        value = val;
        Lchild = l;
        Rchild = r;
        this.parent = parent;
    }

    public String toString() {
        return value + "  ";    
    }

    public boolean equals(int o) {
        return value == o;   
    }
}

class BST {
    Node root;

    public BST(int val) {
        root = new Node(val, null, null, null);

    }   

    public void insert(int val, Node p) {
        if(val < p.value) {
            if(p.Lchild != null)
                insert(val, p.Lchild); 
            else
                p.Lchild = new Node(val, null, null, p);
        }
        else if(val > p.value) {
            if(p.Rchild != null)
                insert(val,p.Rchild);    
            else
                p.Rchild = new Node(val, null, null, p);
        }

    }

/*
    public void delete(int val, Node p = root) {
         if(val < p.value) {
            if(!p.Lchild.equals(val))
                delete(val, p.Lchild); 
            else {
                // p.Lchild = new Node(val, null, null, p);
                p.Lchild.parent = p;
                p.Rchild.parent = p;
                p.Lchild = null;
            }
        }
        else if(val > p.value) {
            if(p.Rchild != null)
                insert(val.p.Rchild);    
            else
                p.Rchild = new Node(val, null, null, p);
        }

   
    }
/**/

    public void preOrder(Node p) {
        System.out.print("" + p);
        if(p.Lchild != null) {
            preOrder(p.Lchild);
        }
        if(p.Rchild != null) {
            preOrder(p.Rchild);    
        }
    }

}


class BST_Tester {
    public static void main(String[] args) { 
        Scanner inp = new Scanner(System.in);
        int ch = 0;
        BST t = null;
        int count = 0;

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

    
    }
}
