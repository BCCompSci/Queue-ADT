import java.util.*;

@SuppressWarnings("unchecked")

public class ResizingArrayQueue<T> implements Queue<T> {

  // Implements a queue in a circular resizeing array.

  private int N;
  private int front, back;
  private T[] items;

  public ResizingArrayQueue() {
    this.N = 0;
    this.front = 0;
    this.back = 0;
    this.items = (T[]) new Object[2];
  }

  public void enqueue(T item) {
    if (this.N == this.items.length) resize(this.N * 2);
    this.items[this.back++] = item;
    this.N++;
    if (this.back == this.items.length) this.back = 0;
  }

  public T dequeue() {
    if(this.N == 0) throw new NoSuchElementException("Empty queue");
    T item = this.items[this.front];
    this.items[this.front++] = null;
    if (this.front == this.items.length) this.front = 0;
    this.N--;
    if (this.N < this.items.length / 4) resize(this.items.length / 2);
    return item;
  }

  public String toString() {
    StringBuilder sb = new StringBuilder();
    for(int i = 0; i < this.N; i++)
      sb.append(this.items[this.front + i % items.length]);
    return sb.toString();
  }

  public int size() { return this.N; }

  public T peek() {
    if(isEmpty())
      throw new NoSuchElementException("Empty queue");
    return this.items[this.front];
  }

  public boolean isEmpty() { return this.N == 0; }

  private void resize(int n) {
    T[] newItems = (T[]) new Object[n];
    for(int i = 0; i < this.N; i++)
      newItems[i] = this.items[(this.front + i) % items.length];
    items = newItems;
    this.front = 0;
    this.back = this.N;
  }

  public static void main(String[] args) {
    Queue<String> myq = new ResizingArrayQueue<String>();
    myq.enqueue("California");
    myq.enqueue("Arizona");
    myq.enqueue("Nevada");

    while (!myq.isEmpty())
      System.out.format("%s%n", myq.dequeue());
    }
  }
