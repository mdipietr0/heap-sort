package com.michael;

public class Heap {
    public int[] arr;
    public int heapsize;

    public static void main(String[] args) {
        int[] a = {43, -87, 324, -76, 3,87,34,98, 0, 4};
        a = Heap.heapSort(a);
        for (int i = 0 ; i < a.length; i++){
            System.out.println(a[i]);
        }
    }

    public Heap(int[] arr, int heapsize) {
        this.arr = arr;
        this.heapsize = heapsize;
    }

    public static void maxHeapify(Heap h, int i){
        int l = left(i);
        int r = right(i);
        int largest;
        if(l < h.heapsize && h.arr[l] > h.arr[i]){
            largest = l;
        }
        else {
            largest = i;
        }
        if(r < h.heapsize && h.arr[r] > h.arr[largest]){
            largest = r;
        }
        if(largest != i ){
            int temp = h.arr[i];
            h.arr[i] = h.arr[largest];
            h.arr[largest] = temp;
            maxHeapify(h, largest);
        }
    }

    public static Heap buildMaxHeap(int[] a){
        Heap h = new Heap(a, a.length);
        for(int i = (int)Math.ceil(h.heapsize/2); i >= 0; i--){
            maxHeapify(h, i);
        }
        return h;
    }

    public static int[] heapSort(int[] a){
        Heap h = buildMaxHeap(a);
        for(int i = h.arr.length - 1; i > 0; i--){
            int temp = h.arr[0];
            h.arr[0] = h.arr[i];
            h.arr[i] = temp;
            h.heapsize--;
            maxHeapify(h, 0);
        }
        return h.arr;
    }

    public static int parent(int i){
        return (int)Math.floor(i);
    }

    public static int left(int i ){
        return (2 * i) + 1;
    }

    public static int right(int i ){
        return (2 * i) + 2;
    }


}