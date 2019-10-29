package li.entrix;

import li.entrix.sort.SortingAlgorithms;

public class Main {

    public static void main(String[] args) {
        System.out.print(String.format("%-15s sort: ", "Bubble"));
        SortingAlgorithms.bubbleSort(new Integer[]{2,3,7,11,243,2,7,34,6,4,});
        System.out.print(String.format("%-15s sort: ", "Insertion"));
        SortingAlgorithms.insertionSort(new Integer[]{2,3,7,11,243,2,7,34,6,4,});
        System.out.print(String.format("%-15s sort: ", "Selection"));
        SortingAlgorithms.selectionSort(new Integer[]{2,3,7,11,243,2,7,34,6,4,});
        System.out.print(String.format("%-15s sort: ", "Lomuto quick "));
        SortingAlgorithms.lomutoQuickSort(new Integer[]{2,3,7,11,243,2,7,34,6,4,}, 0, 9);
        System.out.print(String.format("%-15s sort: ", "Hoare  quick"));
        SortingAlgorithms.hoareQuickSort(new Integer[]{2,3,7,11,243,2,7,34,6,4,}, 0, 9);
    }
}
