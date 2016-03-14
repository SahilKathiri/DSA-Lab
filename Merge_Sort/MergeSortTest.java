import java.io.*;

class Student {
    String sDob, sPob;
    int sId;

    public Student(int a, String b, String c) {
        sId = a;
        sDob = b;
        sPob = c;
    }

    public String toString() {
        return sId + " " + sDob + " " + sPob + "\n";    
    }

}

class StudentList {
    Student[] list;
    Student[] temp;
    
    public StudentList(int len) {
        temp = new Student[len];
        list = new Student[len];
    }

    public void mergeSort() {
        mergeSortHelper(0, list.length-1);    
    }

    public void mergeSortHelper(int low, int high) {
        if(low < high) {
            int mid = (low + high) / 2;
            mergeSortHelper(low, mid);
            mergeSortHelper(mid+1, high);
            merge(low, mid, high);

        }    
    }

    public void add(int i, Student a) {
        list[i] = a;    
    }

    public void merge(int low, int mid, int high) {
        int h = low;
        int i = low;
        int j = mid + 1;
    
        while( (h <= mid) && (j <= high) ) {
            
            if(list[h].sId <= list[j].sId) {
                temp[i] = list[h];
                h++;
            } else {
                temp[i] = list[j];
                j++;
            }

            i++;

        }

        if(h > mid) {
            for(int k = j; k <= high; k++) {
                temp[i] = list[k];
                i++;
            }
        } else {
            for(int k = h; k <= mid; k++) {
                temp[i] = list[k];
                i++;
            }    
        }

        for(int k = low; k <= high; k++) {
            list[k] = temp[k];    
        }   
    }

    public void display() {
        for(int i = 0; i < list.length; i++) {
            System.out.println(list[i] + "");    
        }    
    }

    public Student get(int i) {
        return list[i];    
    }

    public int size() {
        return list.length;    
    }
}

public class MergeSortTest {
    public static void main(String[] args) {
        StudentList list = new StudentList(10);

        try{    
            String line;
            BufferedReader ip = new BufferedReader(new FileReader("stud_in.dat"));
            int i = 0;
            while((line = ip.readLine()) != null && i <10) {
                String[] s = line.split(" ");
                int id = Integer.parseInt(s[0]);
                Student tmp = new Student(id, s[1], s[2]);
                list.add(i, tmp);
                i++;
            }
            System.out.println("Read file");
            list.display();

            System.out.println("Sorting list...");
            list.mergeSort();
            
            System.out.println("Writing to file...");
            BufferedWriter op = new BufferedWriter(new FileWriter("stud_out.dat"));
            Student temp;
            for(i = 0; i < list.size(); i++) {
                op.append(list.get(i) + "");
                System.out.println(list.get(i) + "");
                op.flush();
            }

            ip.close();
            op.close();

        } catch(Exception e) {
            e.printStackTrace();    
        }

    }    
}
