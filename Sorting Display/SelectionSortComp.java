/**
 * Emily Roberts
 * elr8cu
 */

import java.awt.Graphics;
import javax.swing.JComponent;

public class SelectionSortComp extends JComponent{
	private SelectionSort sorter;
	
	/**
	 * Constructor constructs a SelectionSort object
	 */
	public SelectionSortComp(int[] a) {
		sorter = new SelectionSort(a, this);
	}
	
	/**
	 * paintComponent method calls the SelectionSort's draw method
	 */
	public void paintComponent(Graphics g) {
		sorter.draw(g);
	}
	
	/**
	 * animate method constructs a thread that calls SelectionSort's sort method
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
