import java.io.*;
import java.util.Random;


class Heap{
    int[] list;
    int N;

    public Heap(int[] x) {
        list = x;    
    } 

    public void swap(int i, int j) {
        int temp = list[i];
        list[i] = list[j];
        list[j] = temp;
    }

    public void buildHeap() {
        N = list.length - 1;

        for(int i = N/2; i >= 0; i--) {
            minHeap(i);    
        }
    }

    public void minHeap(int i) {
        int left = 2 * i;
        int right = 2*i + 1;
        int max = i;

        if(left <= N && list[left] > list[i])
            max = left;
        if(right <= N && list[right] > list[i])
            max = right;

        if(max != i) {
            swap(i, max);
            minHeap(max);
        }
    }

    public void sort() {
        buildHeap();

        for(int i = N; i > 0; i--) {
            swap(0, i);
            N = N - 1;
            minHeap(0);
        }
    }

    public void disp() {
        for(int i = 0; i < list.length; i++) {
            System.out.print(list[i] + "  ");    
        }    
        System.out.println();
    }

}

public class HeapSort_Tester{
    public static void main(String[] args) {
        int[] list = new int[10];
        Random ran = new Random(1234L);
        for(int i = 0; i < list.length; i++) 
            list[i] = ran.nextInt(50);

        Heap h = new Heap(list);
        System.out.println("The list before building heap is...");
        h.disp();

       // System.out.println("The list after building heap is...");
       // h.buildHeap();
       // h.disp();

        System.out.println("After sorting...");
        h.sort();
        h.disp();
    }    
}
