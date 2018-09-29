/**
 * Emily Roberts
 * elr8cu
 */

import java.awt.Color;
import java.awt.Graphics;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import javax.swing.JComponent;

public class SelectionSort {
	private int[] array;
	private int selectedPosition = -1;
	private int sorted = -1;
	private JComponent comp;
	private Lock sortLock;
	private static final int DELAY = 50;
	
	/**
	 * Constructor
	 */
	public SelectionSort(int[] a, JComponent component) {
		this.array = a;
		sortLock = new ReentrantLock();
		this.comp = component;
	}
	
	/**
	 * Pause method repaints the component and sleeps for the number of steps*50 milliseconds
	 */
	public void pause(int numSteps) throws InterruptedException{
		comp.repaint();
		Thread.sleep(numSteps*DELAY);
	}
	
	/**
	 * Draw method draws elements of array as different colored sticks
	 * red for the selected element
	 * blue for the sorted elements 
	 * black for the other elements
	 */
	public void draw(Graphics g) {
		sortLock.lock();
		try {
			int changeInX = comp.getWidth() / array.length;
			for (int i = 0; i < array.length; i++) {
				if (i == selectedPosition) {
					g.setColor(Color.RED);
				}
				else if(i <= sorted) {
					g.setColor(Color.BLUE);
				}
				else {
					g.setColor(Color.BLACK);
				}
				g.drawLine(i*changeInX, 0, i*changeInX, array[i]);
			}
		}
		finally {
			sortLock.unlock();
		}
	}
	
	/**
	 * Sort method sorts the array using selection sort
	 */
	public void sort() throws InterruptedException{
		for (int i = 0; i < array.length -1; i++) {
			int minPos = minimumPosition(i);
			sortLock.lock();
			try {
				int temp = array[minPos];
				array[minPos] = array[i];
				array[i] = temp;
				sorted = i;
			}
			finally {
				sortLock.unlock();
			}
			pause(2);
		}
	}
	
	/**
	 * minimumPosition method finds the minimum position for the sort method
	 */
	private int minimumPosition(int from) throws InterruptedException{
		int minPos = from;
		for (int i = from + 1; i < array.length; i++) {
			sortLock.lock();
			try {
				if (array[i] < array[minPos]) {
					minPos = i;
				}
				selectedPosition = i;
			}
			finally {
				sortLock.unlock();
			}
			pause(2);
		}
		return minPos;
	}
}
