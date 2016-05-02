import java.util.*;

class Heap {

    int[] list;
    int N;


    public Heap(int[] a) {
        list = a;
    }
    public void buildheap() {
        N = list.length-1;
        for(int i = N/2; i >= 0; i--){
            minheap(i);
        }
    }

    public void minheap(int i) {
        int left = 2*i;
        int right = 2*i+1;
        int min = i;

        if(left <= N && list[left] > list[i])
            min=left;

        if(right <= N && list[right] > list[min]) {
            min=right;
        }
        if(min != i ) {
            swap(i, min);
            minheap(min);
        }
    }

    public void swap(int i, int j) {
        int t = list[i];
        list[i] = list[j];
        list[j] = t;
    }

    public void sort() {
        buildheap();
        for(int i = N; i > 0; i--) {
            swap(0, i);
            N = N-1;
            minheap(0);
        }
    }

    public void disp() {
        for(int i = 0; i < list.length/10; i++) {
            int k = i * 10;
            for(int j = 0; j < 10; j++) {
                System.out.print(String.format("%4d", list[j+k]));    
            }    
            System.out.println();
        }
            System.out.println();
            System.out.println();
    }
}

public class HeapSort_Tester {
    public static void main(String[] args) {
        int[] list = new int[100];

        Random rand = new Random(123L);
        for(int i = 0; i < list.length; i++)
            list[i] = rand.nextInt(100);

        Heap h = new Heap(list);
        System.out.println("The list before sorting/heaping");
        h.disp();

        System.out.println("After building heap");
        h.buildheap();
        h.disp();

        System.out.println("The list after sorting");
        h.sort();
        h.disp();
    }
}
