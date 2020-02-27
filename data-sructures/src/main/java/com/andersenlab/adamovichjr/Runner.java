package com.andersenlab.adamovichjr;

import com.andersenlab.adamovichjr.impl.MyArrayList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Runner {
    public static void main(String[] args) {
        MyList<Integer>list = new MyArrayList<>(2);
        List<Integer> listForAdd = new ArrayList<>(Arrays.asList(5,6,7));
        System.out.println(String.format("Size after initialization = %s, capacity = %s",list.size(),list.capacity()));
        System.out.println();
        list.add(1);
        list.add(2);
        list.add(3);
        System.out.println(String.format("Size = %s and capacity = %s after add",list.size(),list.capacity()));
        System.out.println(String.format("List to String = %s",list.toString()));
        System.out.println();
        System.out.println("List.add(0,10)");
        list.add(0,10);
        System.out.println(list);
        System.out.println();
        System.out.println(String.format("list.addAll(listForAdd - %s)",listForAdd));
        list.addAll(listForAdd);
        System.out.println(list);
        System.out.println();
        System.out.println(String.format("list.isEmpty - %s",list.isEmpty()));
        System.out.println(String.format("list.contains(55) - %s",list.contains(55)));
        System.out.println(String.format("list.get(0) - %s",list.get(0)));
        System.out.println(String.format("list.indexOf(3) - %s",list.indexOf(3)));
        System.out.println();
        System.out.println("list.set(3,33)");
        list.set(3,33);
        System.out.println(list);
        System.out.println();
        System.out.println("list.remove(0)");
        list.remove(0);
        System.out.println(list);
        System.out.println();
        System.out.println("list.remove(new Integer(33))");
        list.remove(new Integer(33));
        System.out.println(list);
        System.out.println();
        System.out.println(String.format("Size = %s and Capacity = %s after operations",list.size(),list.capacity()));
    }
}
