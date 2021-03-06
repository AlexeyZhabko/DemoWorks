package by.academy.jc.mutex;

import java.util.LinkedList;

// This class has a list, producer (adds items to list
// and consumer (removes items).
public class Monitor {

  private Monitor() {
  }

  public static Monitor of() {
    return new Monitor();
  }

  // Create a list shared by producer and consumer
  // Size of list is 2.
  LinkedList<Integer> list = new LinkedList<>();
  int capacity = 2;

  // Function called by producer thread
  public void produce() throws InterruptedException {
    int value = 0;
    while (true) {
      synchronized (this) {
        // producer thread waits while list
        // is full
        while (list.size() == capacity) {
          wait();
        }

        System.out.println("Producer produced-" + value);

        // to insert the jobs in the list
        list.add(value++);

        // notifies the consumer thread that
        // now it can start consuming
        // once you have something in list, you can have the consumer thread consume it,
        // or if you have consumed something, you can have the producer produce something
        notify();

        // makes the working of program easier to understand
        // ( just make the output of program run in step wise manner and not display
        // everything all at once so that you can see what actually is happening in the program)
        Thread.sleep(1000);
      }
    }
  }

  // Function called by consumer thread
  public void consume() throws InterruptedException {
    while (true) {
      synchronized (this) {
        // consumer thread waits while list
        // is empty
        while (list.size() == 0) {
          wait();
        }

        // to retrieve the ifrst job in the list
        int val = list.removeFirst();

        System.out.println("Consumer consumed-" + val);

        // Wake up producer thread to let it produce something
        notify();

        // and sleep
        // see explanation above, reason is the same
        Thread.sleep(1000);
      }
    }
  }
}
