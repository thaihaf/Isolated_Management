/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Mountain
 */
public class ArrayListUtils {

    public ArrayList<Integer> different(ArrayList<Integer> list1, ArrayList<Integer> list2) {
        for (Integer num : list2) {
            if (list1.contains(num)) {
                list1.remove(num);
            }
        }
        return list1;
    }

    public static void main(String[] args) {
        ArrayList<Integer> list1 = new ArrayList<>();
        ArrayList<Integer> list2 = new ArrayList<>();
        for (Integer integer : list2) {
            if (list1.contains(integer)) {
                list1.remove(integer);
            }
        }
        System.out.println(list1);
    }
}
