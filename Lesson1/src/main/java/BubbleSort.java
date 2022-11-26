import org.jetbrains.annotations.NotNull;

import java.util.Arrays;

public class BubbleSort {
    public static void main(String[] args) {
        int[] myArray = {43, -1, 35, 7, -99, 36}; //define numbers to compare
        sort(myArray); //sort the selected numbers
        System.out.print(Arrays.toString(myArray)); //display sorted numbers as a String
    }

    public static void sort(@NotNull int[] myArray) { //describe the bubble sort method
        for (int i = 0; i < myArray.length - 1; i++) {  //iterate over array elements for first compared number
            // set the first maximum and minimum value
            int max = i;
            int min = myArray[i];
            for (int j = i + 1; j < myArray.length; j++) { //iterate over array elements for second compared number
                if (myArray[j] < min) {
                    min = myArray[j]; // put the minimum value from j to min
                    max = j;//j becomes the position where we will put the maximum number from the myArray[i]
                }
            } //Swap the maximum and minimum
            myArray[max] = myArray[i];
            myArray[i] = min;
        }
    }
}