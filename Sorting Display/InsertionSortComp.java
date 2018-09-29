/**
 * Emily Roberts
 * elr8cu
 */

import java.awt.Graphics;
import javax.swing.JComponent;

public class InsertionSortComp extends JComponent {
	private InsertionSort sorter;
	
	/**
	 * Constructor constructs a InsertionSort object
	 */
	public InsertionSortComp(int[] a) {
		sorter = new InsertionSort(a, this);
	}
	
	/**
	 * paintComponent method calls the InsertionSort's draw method
	 */
	public void paintComponent(Graphics g) {
		sorter.draw(g);
	}
	
	/**
	 * animate method constructs a thread that calls InsertionSort's sort method
	 */
	public void animate() {
		class AnimationRunnable implements Runnable{
			public void run() {
				try {
					sorter.sort();
				}
				catch (InterruptedException exception){	
				}
			}
		}
		Runnable run = new AnimationRunnable();
		Thread t = new Thread(run);
		t.start();
	}
	
}
