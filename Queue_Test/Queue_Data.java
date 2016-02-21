import java.io.*;

class Dept {
    String deptId;
    String deptName;
    String noOfStudents;

    public Dept(String id, String name, String no) {
        deptId = id;
        deptName = name;
        noOfStudents = no;
    }

    public String toString() {
        return deptName + " " + deptId + " " + noOfStudents + "\n";
    }

    public boolean equals(Dept o) {
        return o.deptId.equals(deptId) && o.deptName.equals(deptName) && o.noOfStudents.equals(noOfStudents);    
    }
}

class Queue {
    final int capacity = 5;
    Dept[] list;
    final int N;
    int front = 0;
    int rear = 0;


    public Queue(int c) {
        N = c;
        list = new Dept[N];
    }

    int size() {
        if(rear > front) 
            return rear - front;
        return N - front - rear;
    }

    public boolean isEmpty() {
        return (rear == front)? true : false;    
    }

    public boolean isFull() {
        int diff = rear - front;
        if(diff == -1 || diff == (N - 1))
            return true;
        return false;
    }

    public void enqueue(Dept obj) {
        if(isFull()) {
            System.out.println("Queue is full");    
        }    
        else {
            list[rear] = obj;
            rear = (rear + 1) % N;
        }
    }

    public Dept dequeue() {
        Dept temp = null;

        if(isEmpty()) {
            System.out.println("Queue is Empty");
        }
        else {
            temp = list[front];
            list[front] = null;
            front = (front + 1) % N;
        }
        return temp;
    }

}




class Queue_Data {
    public static void main(String[] args) {
        String line;
        BufferedReader ip;
        BufferedWriter op;
        Queue q = new Queue(10);

        try{
            ip = new BufferedReader(new FileReader("Dept_in.dat"));
            
            while((line = ip.readLine()) != null) {
                String[] s = line.split(" ");
                Dept temp = new Dept(s[0], s[1], s[2]);
                q.enqueue(temp);
            }
            // System.out.println("Objects pushed to Queue...");
            
            Dept temp;
            op = new BufferedWriter(new FileWriter("Dept_out.dat"));
            while((temp = q.dequeue()) != null) {
                op.append(temp.toString());
                System.out.println(temp.toString());
                op.flush();
            }
            // System.out.println("Objects popped to file");

            ip.close();
            op.close();

        } catch(Exception e) {
            e.printStackTrace();
        }
    }    
}
