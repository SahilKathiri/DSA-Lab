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
}

class Queue {
    int front = -1;
    int back = -1;
    int size;

    Dept[] queue;

    public Queue(int i) {
        queue = new Dept[i];
        size = i;
    }

    public boolean isEmpty() {
        return (front == back)? true: false;    
    }

    public int nextPos(int x) {
        x++;
        if(!(x < size)) {
            x = 0;   
        }   
        return x;
    }

    public int prePos(int x) {
        x--;
        if(x < 0) {
            x = size - 1;
        }
        return x;
    }

    public void enqueue(Dept d) {
        if(front <= nextPos(back)) {
            back = nextPos(back);
            queue[back] = d;
        }
        
        if(nextPos(back) == front) {
            //Overflow
            System.out.println("Overflow");
            return;
        }
    }

    public Dept dequeue() {
        Dept temp = null;

        if(nextPos(front) <= back) {
            if(front == -1) {
                temp = new Dept("","","");
                front++;
            }
            else {
                temp = queue[front];
                queue[front] = null;
                front = nextPos(front);
            }
        }
        
        if(front == back) {
            //Underflow
            System.out.println("Underflow");
            return null;
        }
        return temp;
    }

    public void display() {
        for(int i = 0; i < size; i++) {
            if(queue[i] != null)
                System.out.println(queue[i].toString());    
        }    
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
            System.out.println("Objects pushed to Queue...");
            q.display();

            Dept temp;
            op = new BufferedWriter(new FileWriter("Dept_out.dat"));
            while((temp = q.dequeue()) != null) {
                op.append(temp.toString());
                System.out.println(temp.toString());
                op.flush();
            }
            System.out.println("Objects popped to file");

            ip.close();
            op.close();

        } catch(Exception e) {
            e.printStackTrace();
        }
    }    
}
