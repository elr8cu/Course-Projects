/**
 * Emily Roberts
 * elr8cu
 */

import java.awt.Color;
import java.awt.Graphics;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import javax.swing.JComponent;

public class InsertionSort {
	private int[] array;
	private int selectedPosition = -1;
	private JComponent comp;
	private Lock sortLock;
	private static final int DELAY = 200;
	
	/**
	 * Constructor
	 */
	public InsertionSort(int[] a, JComponent component) {
		this.array = a;
		sortLock = new ReentrantLock();
		this.comp = component;
	}
	
	/**
	 * Pause method repaints the component and sleeps for the number of steps*200 milliseconds
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
			for (int i = 1; i < array.length; i++) {
				if (i == selectedPosition) {
					g.setColor(Color.RED);
				}
				else if(array[i] == i+1) {
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
	 * Sort method sorts the array using insertion sort
	 */
	public void sort() throws InterruptedException{
		for (int i = 0; i < array.length; i++) {
			sortLock.lock();
			selectedPosition = i;
			int next = array[i];
			try {
				int j = i;
				while(j > 0 && array[j - 1] > next) {
					array[j] = array[j - 1];
					j--;
				}
				array[j] = next;
			}
			finally {
				sortLock.unlock();
			}
			pause(2);
		}
	}
}
