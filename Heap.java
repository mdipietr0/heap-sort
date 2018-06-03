package com.michael;

//@SuppressWarnings("unchecked")
public class Heap<E extends Comparable<E>>{
    private E[] heap;
    private int heapsize;

    public static void main(String[] args) {
        Integer[] a = {43,76,43,87,98,4,783,65,-4,73};
        Heap<Integer> heap = new Heap<Integer>(a);
        System.out.println("original max-heap: " + heap.toString());
        heap.heapIncreaseKey(4, 105);
        System.out.println("increased max-heap: " + heap.toString());
        heap.heapSort(a);
        System.out.println("sorted max-heap: " + heap.toString());

    }
    public Heap(){
        this.heapsize = 0;
        this.heap = (E[]) new Comparable[1];
    }

    public Heap(E[] arr) {
        this.heap = (E[]) new Comparable[arr.length];
        System.arraycopy(arr, 0, heap, 0, arr.length);
        this.heapsize = arr.length;
        buildMaxHeap();
    }

    public E max(){
        return this.heap[0];
    }

    public E extractMax() throws Exception{
        if(this.heapsize < 1){
            System.out.println("heap underflow");
            throw new Exception();
        }
        E max = max();
        this.heap[0] = this.heap[this.heapsize-1];
        this.heapsize--;
        maxHeapify(0);
        return max;
    }

    public void heapIncreaseKey(int i, E key){
        if(key.compareTo(heap[i]) < 0){
            System.out.println("new key is smaller than current key");
        }
        heap[i] = key;
        while(i > 0 && this.heap[i].compareTo(this.heap[parent(i)]) > 0 ){
            E temp = this.heap[parent(i)];
            this.heap[parent(i)] = this.heap[i];
            this.heap[i] = temp;
            i = parent(i);
        }
    }
    
    public String toString()
    {
        String out = "";
        for(int k = 0; k < this.heapsize; k++) out += this.heap[k]+" ";
        return out;
    }

    private void maxHeapify(int i){
        int l = left(i);
        int r = right(i);
        int largest;
        if(l < this.heapsize && this.heap[l].compareTo(this.heap[i]) > 0){
            largest = l;
        }
        else {
            largest = i;
        }
        if(r < this.heapsize && this.heap[r].compareTo(this.heap[largest]) > 0){
            largest = r;
        }
        if(largest != i ){
            E temp = this.heap[i];
            this.heap[i] = this.heap[largest];
            this.heap[largest] = temp;
            maxHeapify(largest);
        }
    }

    private void buildMaxHeap(){
        for(int i = (int)Math.ceil(this.heapsize/2); i >= 0; i--){
            maxHeapify(i);
        }
    }

    public void heapSort(E[] a){
        this.heapsize = a.length;
        this.heap = (E[]) new Comparable[this.heapsize];
        System.arraycopy(a, 0, this.heap, 0, this.heapsize);
        buildMaxHeap();
        int tempsize = this.heapsize;
        for(int i = this.heapsize - 1; i > 0; i--) {
            E temp = this.heap[0];
            this.heap[0] = this.heap[i];
            this.heap[i] = temp;
            this.heapsize--;
            maxHeapify(0);
        }
        this.heapsize = tempsize;
    }

    public static int parent(int i){
        return (int)Math.floor((i-1)/2);
    }

    public static int left(int i ){
        return (2 * i) + 1;
    }

    public static int right(int i ){
        return (2 * i) + 2;
    }


}