package com.epam.rd.java.basic.practice2;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class StackImpl implements Stack {

    Object[] values;
    int size = 0;

    public StackImpl() {
        values = new Object[0];
    }

    @Override
    public void clear() {
        values = new Object[0];
        size = 0;
    }

    @Override
    public int size() {
        return size;
    }

    public Iterator<Object> iterator() {
        return new IteratorImpl();
    }

    private class IteratorImpl implements Iterator<Object> {
        private int pos = values.length-1;

        @Override
        public boolean hasNext() {
            return pos>=0;
        }

        @Override
        public Object next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            } else {
                return values[pos--];
            }
        }

    }

    @Override
    public void push(Object element) {
        Object[] tmp = values;
        if (tmp == null) {
            values = new Object[1];
            values[0] = element;
        }
        else {
            values = new Object[tmp.length + 1];
            System.arraycopy(
                    tmp, 0,// source
                    values, 0, // destination
                    tmp.length
            );
            values[values.length - 1] = element;
        }
        size++;
    }

    @Override
    public Object pop() {
        if (size == 0) {
            return null;
        }
        else {
            Object[] tmp = values;
            values = new Object[tmp.length - 1];
            System.arraycopy(
                    tmp, 0, // source
                    values, 0, //destination
                    tmp.length - 1
            );
            Object toReturn = tmp[tmp.length - 1];
            size--;
            return toReturn;
        }
    }

    @Override
    public Object top() {
        if (size == 0) { return null; }
        else { return values[values.length - 1]; }
    }

    @Override
    public String toString() {
        int index = 0;
        StringBuilder toReturn = new StringBuilder();
        if (this.size() >= 0) {
            toReturn.append("[");

            for (Object obj : values) {
                index++;
                if (index == this.size()) {
                    toReturn.append(obj);
                } else {
                    toReturn.append(obj);
                    toReturn.append(", ");
                }
            }
            toReturn.append("]");
        }
        return toReturn.toString();
    }

    public static void main(String[] args) {
        Stack stack = new StackImpl();
        stack.push(1);
        stack.push("A");
        stack.push('A');
        stack.clear();
        stack.pop();
        stack.size();
        stack.top();
        stack.clear();
        stack.push(1);
    }
}