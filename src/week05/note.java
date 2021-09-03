package week05;

import java.util.ArrayList;

public class note {
	
	public static void main(String[] args) {
		int array[] = { 1,2,3,4,5 };
        int copy[] = new int[5];

        System.arraycopy(array, 0, copy, 1, 3);

        for(int i =0;i<array.length;i++)
                System.out.print(array[i]);
        System.out.println();
        for(int i =0;i<copy.length;i++)
                System.out.print(copy[i]);


	}

}
