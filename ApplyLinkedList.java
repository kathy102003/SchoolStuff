class NodeTwo<E> {
   private NodeTwo<E> next;
   private NodeTwo<E> prev; /*for doubly linkedlist*/
   private E element;

    public NodeTwo() {
       next = null;
       element = null;
   }
    public NodeTwo<E> getNext() { return next; }
    public E getData() { return element; }
    public NodeTwo (E elem) {
       next = null;
       element = elem;
    }
    public void setNext (NodeTwo<E> node) { next = node; }
    public void setElement (E elem) { element = elem; }

}


public class ApplyLinkedList{
    public static void main(String[] args) {
        LinkedList<Integer> myList= new LinkedList();
        myList.addToFront(5);
        myList.addToRear(6);
        myList.addToRear(7);
        myList.addToRear(8);
        myList.addToRear(9);
        myList.addToRear(10);
        myList.toPrint();

        myList.addTo(8,4);
        myList.toPrint();

        myList.addAfter(11, 10);
        myList.toPrint();

        myList.removeFromEnd();
        myList.toPrint();

        myList.removeFromStart();
        myList.toPrint();

        myList.removeAtIndex(2);
        myList.toPrint();

        myList.remove_Xth_NodeFromTheEnd(2);
        myList.toPrint();
    }
}

class LinkedList<T> {
   private NodeTwo<T> front;

   public LinkedList() { front = null; }

    public void addToFront (T data) {
        NodeTwo<T> newNode = new NodeTwo<>(data);
        if (front == null){
            front = newNode;
            return;
        }
        newNode.setNext(front);
        front = newNode;
    }

    public void addToRear(T data){
        NodeTwo<T> newNode = new NodeTwo<>(data);
        NodeTwo<T> curr = front;
        while (curr.getNext() != null){
            curr = curr.getNext();
        }
        curr.setNext(newNode);
    }

     /* After adding, it is in position pos of the list (1, 2, ..., pos, ...) */
    public void addTo (T data, int pos) {
        NodeTwo<T> newNode = new NodeTwo<>(data);
        NodeTwo<T> curr = front;
        NodeTwo<T> prev = null;
        int count = 1;
        if (pos == 1){
            newNode.setNext(front);
            front = newNode;
            return;
        }
        while (curr != null && count != pos){
            count++;
            prev = curr;
            curr = curr.getNext();
        }
        prev.setNext(newNode);
        newNode.setNext(curr);
    }

     /* If ‘after’ is not in the linked list, 
      * print ”The data is not in the linked list”. 
      * Else, add a new LinearNode and attach it to the linked list such that 
      * it comes after the LinearNode storing ‘after’  */
    public void addAfter(T data, T after){
        NodeTwo<T> newNode = new NodeTwo<>(data);
        NodeTwo<T> curr = front;
        T getElementInAfter;
        while (curr != null){
            getElementInAfter = curr.getData();
            if (after.equals(getElementInAfter)){
                newNode.setNext(curr.getNext());
                curr.setNext(newNode);
                return;
            }
            curr = curr.getNext();
        }
        System.out.println
        ("The data is not in the linked list");
    }

    public void removeFromEnd(){
        NodeTwo<T> curr = front;
        if (curr == null) System.out.println("Empty");
        if (curr.getNext() == null)
        System.out.println("There's only 1 node!");

        while (curr.getNext() != null){
            curr = curr.getNext();
            if (curr.getNext().getNext() == null){
            System.out.println("DELETED: " +
             curr.getNext().getData());
                curr.setNext(null);
                return;
            }
        }
    }
   
    public void removeFromStart(){
        NodeTwo<T> curr = front;
        if (curr == null){ System.out.println("Empty"); }
        if (curr.getNext() == null){ System.out.println("There's only 1 node!"); }
 
        System.out.println("DELETED: "+ curr.getData());
        front = front.getNext();
        curr = null;
    }
 
    /*Base 0 */
    public void removeAtIndex(int index){
        NodeTwo<T> curr = front;
        NodeTwo<T> nodeRemoved = null;
        int count = -1;
        if (curr == null){ System.out.println("Empty"); }
        while (curr != null){
            count++;
            if (count == index-1){
                nodeRemoved = curr.getNext();
                System.out.println("REMOVED: " + nodeRemoved.getData());
                curr.setNext(nodeRemoved.getNext());
                break;
            }
            curr = curr.getNext(); 
        }
    }

    public void toPrint(){
        NodeTwo<T> curr = front;
        if (curr == null){ 
            System.out.println("Empty");
            return;  
        }
        while (curr != null){
            System.out.print(curr.getData() + " -> ");
            curr = curr.getNext();
        }
        System.out.println();
    }

/** public void remove_Xth_NodeFromTheEnd(int index){
        NodeTwo<T> curr = front;
        NodeTwo<T> nodeRemoved = null;
        int length = -1;
        int fromTheStart = 0;
 
        if (curr == null){ System.out.println("Empty"); }
 
        // Get the length:
        while (curr != null){
            curr = curr.getNext();
            length++;
        }
 
        // Reset
        if (curr == null){
            fromTheStart = (length+1) - index;
            System.out.println("Length: " + (length+1));
            System.out.println("From the Start:" + fromTheStart);
            length = -1;
            curr = front;
        }
 
        while (curr != null){
            curr = curr.getNext();
            length++;
            if (length == fromTheStart - 2){
                nodeRemoved = curr.getNext();
                System.out.println
               ("REMOVED: " + nodeRemoved.getData());
                curr.setNext(curr.getNext().getNext());
            }
        }
 
    } 
**/ 

    public int retrieveData(int data){
        int index = -1;
        NodeTwo curr = front;
        while (curr != null){ // when curr is ref, count is 0
            index++;
            if (curr.getData() instanceof Integer) {
                int dataRetrieved = (Integer) curr.getData();
                if (dataRetrieved == data){
                    return index;
                }
                else { curr = curr.getNext();  }
            }
        }
        return -1;
    }

//     public int returnAt(int index){
//         int count = 0;
//         NodeTwo curr = front;
//         while (curr.getNext() != null){
//                 count++;
//                 curr = curr.getNext();
//                 if (count == index){ return curr.getData(); }
//                 if (index >= count && curr.getNext() == null){ return -1; }
//         return -1;
//     }
// }


}
