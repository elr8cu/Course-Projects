/**
 * Emily Roberts
 * elr8cu
 */

import java.awt.Graphics;
import javax.swing.JComponent;

public class BubbleSortComp extends JComponent{
private BubbleSort sorter;
	
	/**
	 * Constructor constructs a BubbleSort object
	 */
	public BubbleSortComp(int[] a) {
		sorter = new BubbleSort(a, this);
	}
	
	/**
	 * paintComponent method calls the BubbleSort's draw method
	 */
	public void paintComponent(Graphics g) {
		sorter.draw(g);
	}
	
	/**
	 * animate method constructs a thread that calls BubbleSort's sort method
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
