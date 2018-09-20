public interface Queue<Item> {

  void enqueue(Item item);
  Item dequeue();
  Item peek();

  boolean isEmpty();
  int size();
  String toString();
}
