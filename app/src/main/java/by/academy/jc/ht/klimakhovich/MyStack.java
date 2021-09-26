package by.academy.jc.ht.klimakhovich;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;




    public class MyStack<E> implements List<E> {

        private int size = 0;
        private Item<E> element;

        private static class Item<E> {

            E data;
            Item<E> next;

            Item(E element , Item<E> next) {
                this.data = element;
                this.next = next;
            }
        }

        class MyIterator implements Iterator<E> {

            private Item<E> current;
            private int nextIndex;

            MyIterator() {
                current = element;
                nextIndex = 0;
            }

            @Override
            public boolean hasNext() {
                return nextIndex < size;
            }

            @Override
            public E next() {
                if (!hasNext())
                    throw new NoSuchElementException();

                Item<E> lastReturned = current;
                current = current.next;
                nextIndex++;
                return lastReturned.data;
            }
        }

        public void push(E item) {
            final Item<E> newItem = new Item<E>(item, null);
            newItem.next = element;
            element = newItem;
            size++;
        }

        public void pop() {
            if (isEmpty())
                throw new NoSuchElementException("MyStack is empty");
            element = element.next;
            size--;
        }


        public E peek() {
            if (isEmpty())
                return null;
            return element.data;
        }

        @Override
        public int size() {
            return 0;
        }

        @Override
        public boolean isEmpty() {
            return false;
        }

        @Override
        public boolean contains(Object o) {
            return false;
        }

        @Override
        public Iterator<E> iterator() {
            return null;
        }

        @Override
        public Object[] toArray() {
            return new Object[0];
        }

        @Override
        public <T> T[] toArray(T[] a) {
            return null;
        }

        @Override
        public boolean add(E e) {
            return false;
        }

        @Override
        public boolean remove(Object o) {
            return false;
        }

        @Override
        public boolean containsAll(Collection<?> c) {
            return false;
        }

        @Override
        public boolean addAll(Collection<? extends E> c) {
            return false;
        }

        @Override
        public boolean addAll(int index, Collection<? extends E> c) {
            return false;
        }

        @Override
        public boolean removeAll(Collection<?> c) {
            return false;
        }

        @Override
        public boolean retainAll(Collection<?> c) {
            return false;
        }

        @Override
        public void clear() {

        }

        @Override
        public E get(int index) {
            return null;
        }

        @Override
        public E set(int index, E element) {
            return null;
        }

        @Override
        public void add(int index, E element) {

        }

        @Override
        public E remove(int index) {
            return null;
        }

        @Override
        public int indexOf(Object o) {
            return 0;
        }

        @Override
        public int lastIndexOf(Object o) {
            return 0;
        }

        @Override
        public ListIterator<E> listIterator() {
            return null;
        }

        @Override
        public ListIterator<E> listIterator(int index) {
            return null;
        }

        @Override
        public List<E> subList(int fromIndex, int toIndex) {
            return null;
        }



}