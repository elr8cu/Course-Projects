/**
 * Emily Roberts
 * elr8cu
 */

import java.awt.Color;
import java.awt.Graphics;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import javax.swing.JComponent;

public class BubbleSort {
	private int[] array;
	private int selectedPosition = -1;
	private JComponent comp;
	private Lock sortLock;
	private static final int DELAY = 100;
	
	/**
	 * Constructor
	 */
	public BubbleSort(int[] a, JComponent component) {
		this.array = a;
		sortLock = new ReentrantLock();
		this.comp = component;
	}
	
	/**
	 * Pause method repaints the component and sleeps for the number of steps*100 milliseconds
	 */
	public void pause(int numSteps) throws InterruptedException{
		comp.repaint();
		Thread.sleep(numSteps*DELAY);
	}
	
	/**
	 * Draw method draws elements of array as different colored sticks:
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
	 * Sort method sorts the array using bubble sort
	 */
	public void sort() throws InterruptedException{
		for (int i = 0; i <array.length; i++) {
			for (int j=1; j < array.length; j++) {
				if(array[j-1] > array[j]) {
					selectedPosition = j;
					sortLock.lock();
				try {
					int temp = array[j-1];
					array[j-1] = array[j];
					array[j] = temp;
				}finally {
					sortLock.unlock();
				}
				pause(2);
			}
			}
		}
	}
}
