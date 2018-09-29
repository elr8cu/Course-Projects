/** 
Emily Roberts 
elr8cu
*/

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class GPACalculator {
	ArrayList<String> enteredCourseGrades = new ArrayList<String>();
	ArrayList<String> enteredCourseCredits = new ArrayList<String>();
	DefaultListModel<String> enteredCourses = new DefaultListModel<String>();
	
	/**
	 * Assuming letter grades from A+ to F and credit hours from 1 to 4
	 */
	String[] gradeOptions = {"-", "A+", "A", "A-", "B+", "B", "B-", "C+", "C", "C-", "D+", "D", "D-", "F"};
    String[] creditHourOptions = {"-", "1", "2","3", "4"};
	
	
    public static void main(String[] args) {
        new GPACalculator();
    }

    public GPACalculator(){
    		/**
    		 * Create JFrame
    		 */
        JFrame guiFrame = new JFrame();
        guiFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        guiFrame.setTitle("GPA Calculator");
        guiFrame.setSize(400,400);
        guiFrame.setLocationRelativeTo(null);
        
        /**
         * Create top JPanel
         */
        JPanel topPanel = new JPanel();
        JLabel courseLabel = new JLabel("Course Name:");
        JTextField text = new JTextField(10);
        JLabel gradeLabel = new JLabel("Grade:");
        JComboBox<String> grades = new JComboBox<String>(gradeOptions);
        JLabel creditLabel = new JLabel("Credit Hours:");
        JComboBox<String> creditHours = new JComboBox<String>(creditHourOptions);
        JButton enterButton = new JButton("Enter");
        topPanel.add(courseLabel);
        topPanel.add(text);
        topPanel.add(gradeLabel);
        topPanel.add(grades);
        topPanel.add(creditLabel);
        topPanel.add(creditHours);
        topPanel.add(enterButton);
        
        /**
         * Create center JPanel
         */
        JLabel listLabel = new JLabel("Entered Courses: ");
        JScrollPane listPanel = new JScrollPane(listLabel); 
        JList<String> coursesList = new JList<>(enteredCourses);
        listPanel.add(listLabel);
        listPanel.setViewportView(coursesList);
        
        /**
         * Create bottom JPanel (Finish/ Summary Button)
         */
        JButton summaryButton = new JButton("Finish");
        
        
        /**
         * Create East JPanel
         */
        JPanel eastPanel = new JPanel();
        JButton addButton = new JButton("Add 15 Blank Credits");
        JButton removeButton = new JButton("Remove");
        JButton removeAllButton = new JButton("Remove All");
        eastPanel.add(removeButton);
        eastPanel.add(removeAllButton);
        eastPanel.add(addButton);
        
        
        /**
         * Create West JPanel
         */
        JPanel summaryPanel = new JPanel();
        JLabel summaryTitle = new JLabel("");
        JTextField targetInput = new JTextField(10);
        JButton targetButton = new JButton("Enter");
        JButton editButton = new JButton("Edit Entered Courses");
        summaryPanel.add(summaryTitle);
        summaryPanel.add(targetInput);
        summaryPanel.add(targetButton);
        summaryPanel.add(editButton);
        summaryPanel.setVisible(false);
        
        /**
         * Add JPanels to JFrame
         */
        guiFrame.add(topPanel, BorderLayout.NORTH);
        guiFrame.add(listPanel, BorderLayout.CENTER);
        guiFrame.add(summaryPanel, BorderLayout.WEST);
        guiFrame.add(eastPanel, BorderLayout.EAST);
        guiFrame.add(summaryButton, BorderLayout.SOUTH);
        
        /**
         * Add action listener for enter button
         */
       enterButton.addActionListener(new ActionListener()
       {
           @Override
           public void actionPerformed(ActionEvent event)
           {
        	   	String inputName = text.getText();
   			String inputGrade = (String) grades.getSelectedItem();
   			enteredCourseGrades.add(inputGrade);
   			String inputCredit = (String) creditHours.getSelectedItem();
   			enteredCourseCredits.add(inputCredit);
   			String newCourse = "NAME: "+ inputName + ", GRADE: " + inputGrade + ", CREDIT: " + inputCredit;
   			text.setText("");
   			grades.setSelectedIndex(0);
   			creditHours.setSelectedIndex(0);
   			listLabel.setText("Courses Entered:");
   			enteredCourses.addElement(newCourse);
           }
       });
       
       /**
        * Add action listener for remove button
        */
       removeButton.addActionListener(new ActionListener()
       {
           @Override
           public void actionPerformed(ActionEvent event)
           {
        	   if(!coursesList.isSelectionEmpty()) {
        		   int selectedIndex = coursesList.getSelectedIndex();
            	   enteredCourseCredits.remove(selectedIndex);
            	   enteredCourseGrades.remove(selectedIndex);
            	   
            	   String selectedCourse = (String) coursesList.getSelectedValue();
            	   enteredCourses.removeElement(selectedCourse);
        	   }
           }
       });
       
       /**
        * Add action listener for removeAll button
        */
       removeAllButton.addActionListener(new ActionListener()
       {
           @Override
           public void actionPerformed(ActionEvent event)
           {
        	   enteredCourses.removeAllElements();
        	   enteredCourseCredits.clear();
        	   enteredCourseGrades.clear();
           }
       });
       
       /**
        * Add action listener for summary button
        */
       summaryButton.addActionListener(new ActionListener()
       {
           @Override
           public void actionPerformed(ActionEvent event)
           {
        	   topPanel.setVisible(false);
        	   listPanel.setVisible(false);
        	   summaryButton.setVisible(false);
        	   eastPanel.setVisible(false);
        	   summaryPanel.setVisible(true);
        	   
        	   double GPA = 0.0;
        	   int totalCreditsTaken = 0;
        	   for(int i = 0; i < enteredCourseCredits.size(); i++) {
        		   int credit = Integer.parseInt(enteredCourseCredits.get(i));
        		   /**
    			    * Assume course is a previously taken course if array list of grades 
    			    * has a grade at that index
    			    * then, use the credit and grade at that index to calculate current GPA
    			    */
        		   if(!enteredCourseGrades.get(i).equals("-")) {
        			   totalCreditsTaken += credit;
        		   /**
        		    * Assume GPA = credits multiplied by point value for each letter grade 
        		    * divided total credit hours for those courses
        		    */
        			   if (enteredCourseGrades.get(i).equals("A+")) {
        				   GPA += credit * 4.00;
        			   }
        			   else if (enteredCourseGrades.get(i).equals("A")) {
        				   GPA += credit * 4.00;
        			   }
        			   else if (enteredCourseGrades.get(i).equals("A-")) {
        				   GPA += credit * 3.67;
        			   }
        			   else if (enteredCourseGrades.get(i).equals("B+")) {
        				   GPA += credit * 3.33;
        			   }
        			   else if (enteredCourseGrades.get(i).equals("B")) {
        				   GPA += credit * 3.00;
        			   }
        			   else if (enteredCourseGrades.get(i).equals("B-")) {
        				   GPA += credit * 2.67;
        			   }
        			   else if (enteredCourseGrades.get(i).equals("C+")) {
        				   GPA += credit * 2.33;
        			   }
        			   else if (enteredCourseGrades.get(i).equals("C")) {
        				   GPA += credit * 2.00;
        			   }
        			   else if (enteredCourseGrades.get(i).equals("C-")) {
        				   GPA += credit * 1.67;
        			   }
        			   else if (enteredCourseGrades.get(i).equals("D+")) {
        				   GPA += credit * 1.33;
        			   }
        			   else if (enteredCourseGrades.get(i).equals("D")) {
        				   GPA += credit * 1.00;
        			   }
        			   else if (enteredCourseGrades.get(i).equals("D-")) {
        				   GPA += credit * .67;
        			   }
        			   else {
        				   GPA += 0;
        			   }
        		   }
        	   }
        	   /**
        	    * Assume GPA rounded to two decimal places
        	    */
        	   double finalGPA = ((int) (GPA *100 / totalCreditsTaken)) / 100.0;
        	   summaryTitle.setText("Current GPA: " + finalGPA + "         Enter Target GPA: ");;
           }
          
       });
       
       /**
        * Add action listener for add button
        */
       addButton.addActionListener(new ActionListener()
       {
           @Override
           public void actionPerformed(ActionEvent event)
           {
        	   enteredCourseCredits.add("15");
        	   enteredCourses.addElement("NAME: , GRADE: -, CREDIT: 15");
        	   enteredCourseGrades.add("-");
           }
       });
       
       /**
        * Add action listener for target button
        */
       targetButton.addActionListener(new ActionListener()
       {
           @Override
           public void actionPerformed(ActionEvent event)
           {
        	   double targetGPA = Double.parseDouble(targetInput.getText());
        	   double GPA = 0.0;
        	   int creditsTaken = 0;
        	   int blankCredits = 0;
        	   for(int i = 0; i < enteredCourseCredits.size(); i++) {
        		   int credit = Integer.parseInt(enteredCourseCredits.get(i));
        		   /**
    			    * Assume course is a previously taken course if array list of grades 
    			    * has a grade at that index
    			    * then, use the credit and grade at that index to calculate current GPA
    			    */
        		   if(!enteredCourseGrades.get(i).equals("-")) {
        			   creditsTaken += credit;
        			   /**
        			    * Assume GPA = credits multiplied by point value for each letter grade 
        			    * divided total credit hours for those courses
        			    */
        			   if (enteredCourseGrades.get(i).equals("A+")) {
        				   GPA += credit * 4.00;
        			   }
        			   else if (enteredCourseGrades.get(i).equals("A")) {
        				   GPA += credit * 4.00;
        			   }
        			   else if (enteredCourseGrades.get(i).equals("A-")) {
        				   GPA += credit * 3.67;
        			   }
        			   else if (enteredCourseGrades.get(i).equals("B+")) {
        				   GPA += credit * 3.33;
        			   }
        			   else if (enteredCourseGrades.get(i).equals("B")) {
        				   GPA += credit * 3.00;
        			   }
        			   else if (enteredCourseGrades.get(i).equals("B-")) {
        				   GPA += credit * 2.67;
        			   }
        			   else if (enteredCourseGrades.get(i).equals("C+")) {
        				   GPA += credit * 2.33;
        			   }
        			   else if (enteredCourseGrades.get(i).equals("C")) {
        				   GPA += credit * 2.00;
        			   }
        			   else if (enteredCourseGrades.get(i).equals("C-")) {
        				   GPA += credit * 1.67;
        			   }
        			   else if (enteredCourseGrades.get(i).equals("D+")) {
        				   GPA += credit * 1.33;
        			   }
        			   else if (enteredCourseGrades.get(i).equals("D")) {
        				   GPA += credit * 1.00;
        			   }
        			   else if (enteredCourseGrades.get(i).equals("D-")) {
        				   GPA += credit * .67;
        			   }
        			   else {
        				   GPA += 0;
        			   }
        	   }
        		   /**
    			    * Assume course is a current or anticipated course if array list of grades has no
    			    * grade at that index, thus, count the credits as blank credits 
    			    * and use them to calculate required GPA for a target GPA
    			    */
        		   else {
        			   blankCredits += credit;
        		   }
        	   }
        	   int totalCredits = blankCredits + creditsTaken;
        	   /**
        	    * Assume required GPA needs to be rounded to two decimal places
        	    */
        	   double requiredGPA = ((int)((((targetGPA * totalCredits) - GPA)*100) / blankCredits)) / 100.0;
        	   /**
        	    * Assume program should give error message if no blank credits
        	    */
        	   if (blankCredits == 0) {
        		   JOptionPane.showMessageDialog(null, "ERROR: You did not enter any blank (future) credits, so required GPA cannot be calculated. Try adding more credit hours and recalculating.");
        	   }
        	   else if (requiredGPA > 4.0) {
        		   JOptionPane.showMessageDialog(null, "Required GPA for your " + blankCredits + " blank credits is " + requiredGPA + "\n" +"WARNING: GPA is greater than 4.0. You should try adding more credit hours and recalculating.");
        	   }
        	   else if (requiredGPA < 2.0) {
        		   JOptionPane.showMessageDialog(null, "Required GPA for your " + blankCredits + " blank credits is " + requiredGPA + "\n" +"Required GPA is less than 2.0, so you can take fewer credit hours.");
           }
        	   else {
        		   JOptionPane.showMessageDialog(null, "Required GPA for your " + blankCredits + " blank credits is " + requiredGPA);
        	   }
        		targetInput.setText("");   
        	   }
       });
       
       /**
        * Add action listener for edit button to re-display course input panels
        */
       editButton.addActionListener(new ActionListener()
       {
           @Override
           public void actionPerformed(ActionEvent event)
           {
        	   topPanel.setVisible(true);
        	   listPanel.setVisible(true);
        	   summaryButton.setVisible(true);
        	   eastPanel.setVisible(true);
        	   summaryPanel.setVisible(false);
           }
       });
      
       guiFrame.pack();
       guiFrame.setVisible(true);
       
    }
}
