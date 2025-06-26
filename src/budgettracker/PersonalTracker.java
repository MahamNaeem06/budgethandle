package budgettracker;
//gui components used 
import javax.swing.*;

public class PersonalTracker {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new PersonalTracker().createAndShowGUI());
    }
//main gui window
    public void createAndShowGUI() {
    	//new window 
        JFrame frame = new JFrame("Personal Budget Tracker");
        //h and w 
        frame.setSize(400, 300);
        //close completely
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
// bottuns 
        JButton addIncomeButton = new JButton("Add Income");
        JButton addExpenseButton = new JButton("Add Expense");
//panel container to hold the buttons
        JPanel panel = new JPanel();
        //add button 
        panel.add(addIncomeButton);
        panel.add(addExpenseButton);
//button to window 
        frame.add(panel);
        //visibile
        frame.setVisible(true);
    }
}
