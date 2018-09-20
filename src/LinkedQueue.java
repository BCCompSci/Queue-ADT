import java.util.*;

public class LinkedQueue<T> implements Queue<T> {

  private Node first, last;
  private int N;

  private class Node {
    T info;
    Node next;

    private Node(T info, Node next) {
      this.info = info;
      this.next = next;
    }
  }

  public LinkedQueue() {
    first = null;
    last = null;
    N = 0;
  }

  public void enqueue(T item) {
    Node newLast = new Node(item, null);
    if (last == null)
      first = newLast;
    else
      last.next = newLast;
    last = newLast;
    N++;
  }

  public T dequeue() {
    if(isEmpty())
      throw new NoSuchElementException("Empty queue");
    T answer = first.info;
    first = first.next;
    if (first == null) last = null;
    N--;
    return answer;
  }

  public T peek() {
    if(isEmpty())
      throw new NoSuchElementException("Empty queue");
    return first.info;
  }

  public boolean isEmpty() { return N == 0; }

  public int size(){ return N; }

  public String toString() {
    StringBuilder sb = new StringBuilder();
    Node current = first;
    while (current != null) {
      sb.append(current.info + " ");
      current = current.next;
    }
    return sb.toString();
  }

  public static void main(String[] args) {
    Queue<String> myq = new LinkedQueue<String>();
    myq.enqueue("California");
    myq.enqueue("Arizona");
    myq.enqueue("Nevada");

    while (!myq.isEmpty())
      System.out.format("%s%n", myq.dequeue());
  }
}
