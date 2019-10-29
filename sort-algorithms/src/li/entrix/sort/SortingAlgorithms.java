package li.entrix.sort;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.BiFunction;

public class SortingAlgorithms {

    public static <T extends Comparable<T>> void bubbleSort(T[] elements) {
        AtomicInteger comparisonCounter = new AtomicInteger(0),
                swapCounter = new AtomicInteger(0);

        int i = 0, n = elements.length;

        int nextN = n;

        do {
            n = nextN;

            for (i = 0; i < n - 1; ++i) {
                if (elements[i].compareTo(elements[i + 1]) > 0) {
                    nextN = swap(elements, i, i + 1);
                    swapCounter.incrementAndGet();
                }
                comparisonCounter.incrementAndGet();
            }
        } while (n != nextN);

        printArray(elements, comparisonCounter.get(), swapCounter.get());
    }

    public static <T extends Comparable<T>> void insertionSort(T[] elements) {
        AtomicInteger comparisonCounter = new AtomicInteger(0),
                swapCounter = new AtomicInteger(0);

        for (int i = 2; i <= elements.length; ++i) {
            for (int j = i - 1; j > 0; --j) {
                if (elements[j - 1].compareTo(elements[j]) > 0) {
                    swap(elements, j - 1, j);
                    swapCounter.incrementAndGet();
                }
                comparisonCounter.incrementAndGet();
            }
        }

        printArray(elements, comparisonCounter.get(), swapCounter.get());
    }

    public static <T extends Comparable<T>> void selectionSort(T[] elements) {
        AtomicInteger comparisonCounter = new AtomicInteger(0),
                swapCounter = new AtomicInteger(0);

        for (int pivot = 0, minInd; pivot < elements.length - 1; ++pivot) {
            minInd = pivot;
            for (int  i = pivot + 1; i < elements.length; ++i) {
                if (elements[i].compareTo(elements[minInd]) < 0) {
                    minInd = i;
                }
                comparisonCounter.incrementAndGet();
            }
            if (minInd != pivot) {
                swap(elements, pivot, minInd);
                swapCounter.incrementAndGet();
            }
        }

        printArray(elements, comparisonCounter.get(), swapCounter.get());
    }

    public static <T extends Comparable<T>> void lomutoQuickSort(T[] elements, int low, int high) {
        AtomicInteger comparisonCounter = new AtomicInteger(0),
                swapCounter = new AtomicInteger(0);

        BiFunction<Integer, Integer, Integer> partition = (lo, hi) -> {
            int i = lo;
            for (int pivot = hi, j = lo; j < hi; ++j) {
                if (elements[pivot].compareTo(elements[j]) > 0) {
                    swap(elements, i, j);
                    i++;

                    swapCounter.incrementAndGet();
                }
                comparisonCounter.incrementAndGet();
            }
            swap(elements, i, hi);
            swapCounter.incrementAndGet();
            return i;
        };
        if (low < high) {
            int p = partition.apply(low, high);
            lomutoQuickSort(elements, low, p - 1);
            lomutoQuickSort(elements, p + 1, high);
        }

        if (low == 0 && high == elements.length - 1) printArray(elements, comparisonCounter.get(), swapCounter.get());
    }

    public static <T extends Comparable<T>> void hoareQuickSort(T[] elements, int low, int high) {
        AtomicInteger comparisonCounter = new AtomicInteger(0),
                swapCounter = new AtomicInteger(0);

        BiFunction<Integer, Integer, Integer> partition = (lo, hi) -> {
            T pivot = elements[lo + (hi - lo)/2];
            int i = lo - 1, j = hi + 1;
            while (true){
                do {
                    i++;
                    comparisonCounter.incrementAndGet();
                }
                while (pivot.compareTo(elements[i]) > 0);
                do {
                    j--;
                    comparisonCounter.incrementAndGet();
                } while (pivot.compareTo(elements[j]) < 0);
                if (i >= j) return j;
                swap(elements, i, j);
                swapCounter.incrementAndGet();
            }
        };
        if (low < high) {
            int p = partition.apply(low, high);
            hoareQuickSort(elements, low, p);
            hoareQuickSort(elements, p + 1, high);
        }

        if (low == 0 && high == elements.length - 1) printArray(elements, comparisonCounter.get(), swapCounter.get());

    }

    private static <T extends Comparable<T>> int  swap(T[] elements, int a, int b) {
        T sw = elements[a];
        elements[a] = elements[b];
        elements[b] = sw;
        return b;
    }

    private static <T extends Comparable<T>> void printArray(T[] elements, int conparisonCount, int swapCount) {
        System.out.print("[");
        for (int i = 0; i < elements.length - 1; ++i) {
            System.out.print(elements[i]);
            System.out.print(",");
        }
        System.out.print(elements[elements.length - 1]);
        System.out.println (String.format("], comparisons: %2d, swaps: %2d", conparisonCount, swapCount));
    }

}
