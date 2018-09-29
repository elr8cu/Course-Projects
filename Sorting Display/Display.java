/**
 * Emily Roberts
 * elr8cu
 */

import java.awt.BorderLayout;
import java.util.Arrays;
import java.util.Random;

import javax.swing.JFrame;

public class Display implements Runnable{
	JFrame frame;
	
	/**
	 * Constructor
	 */
	public Display(JFrame f) {
		this.frame = f;
	}
	
	/**
	 * Main method
	 */
	public static void main(String[] args) {
		/**
		 * Create an array 1-50 and shuffle the elements
		 */
		int[] array = new int[50];
		for (int i = 0; i < 50; i++) {
			array[i] = i+1;
		}
		Random rand = new Random();
		for(int i=0; i<array.length; i++) {
			int randomPosition = rand.nextInt(array.length);
			int temp = array[i];
			array[i] = array[randomPosition];
			array[randomPosition] = temp;
		}
		
		/**
		 * Make two deep copies of the array
		 */
		int[] array2 = Arrays.copyOf(array, array.length);
		int[] array3 = Arrays.copyOf(array, array.length);
		
		/**
		 * Create a new frame for selection sort
		 */
		JFrame selectionSortFrame = new JFrame("Selection Sort");
		final SelectionSortComp sComponent = new SelectionSortComp(array);
		selectionSortFrame.add(sComponent, BorderLayout.CENTER);
		selectionSortFrame.setLocation(100,0);
		sComponent.animate();
		
		/**
		 * Create a new frame for insertion sort
		 */
		JFrame insertionSortFrame = new JFrame("Insertion Sort");
		final InsertionSortComp iComponent = new InsertionSortComp(array2);
		insertionSortFrame.add(iComponent, BorderLayout.CENTER);
		insertionSortFrame.setLocation(500,0);
		iComponent.animate();
		
		/**
		 * Create a new frame for bubble sort
		 */
		JFrame bubbleSortFrame = new JFrame("Bubble Sort");
		final BubbleSortComp bComponent = new BubbleSortComp(array3);
		bubbleSortFrame.add(bComponent, BorderLayout.CENTER);
		bubbleSortFrame.setLocation(900,0);
		bComponent.animate();
		
		/**
		 * Create new threads for each sort frame and start them
		 */
		Thread selectionSortThread = new Thread(new Display(selectionSortFrame));
		Thread insertionSortThread = new Thread(new Display(insertionSortFrame));
		Thread bubbleSortThread = new Thread(new Display(bubbleSortFrame));
		selectionSortThread.start();
		insertionSortThread.start();
		bubbleSortThread.start();
		
	}

	@Override
	public void run() {
		frame.setSize(300,200);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
}
