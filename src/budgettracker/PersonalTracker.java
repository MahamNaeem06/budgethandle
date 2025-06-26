package budgettracker;
//gui components used 
import javax.swing.*;
import java.util.HashMap;
import java.util.Map;

public class PersonalTracker {
	//keep tracking 
	private double totalIncome = 0;
	private double totalExpense = 0;

    // 👇 new: track expense limits per category
    private Map<String, Double> categoryLimits = new HashMap<>();
    private Map<String, Double> categoryExpenses = new HashMap<>();
    
    public static void main(String[] args) {
    	//gui to run smottly 
        SwingUtilities.invokeLater(() -> new PersonalTracker().createAndShowGUI());
    }
//main gui window
    public void createAndShowGUI() {
    	//new window 
        JFrame frame = new JFrame("Personal Budget Tracker");
        //h and w 
        frame.setSize(500, 400);
        //close completely
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
// bottuns 
        JButton addIncomeButton = new JButton("Add Income");
        JButton addExpenseButton = new JButton("Add Expense");

        // 👇 new buttons 
        JButton setLimitButton = new JButton("Set Expense Limit");
        JButton viewLimitsButton = new JButton("View Limits");

//panel container to hold the buttons
        JPanel panel = new JPanel();
        //add button 
        panel.add(addIncomeButton);
        panel.add(addExpenseButton);
        panel.add(setLimitButton);     // 👈 new
        panel.add(viewLimitsButton);  // 👈 new
//button to window 
        frame.add(panel);
        //visibile
        frame.setVisible(true);
        
        // income to add
        addIncomeButton.addActionListener(e -> {
            String input = JOptionPane.showInputDialog(frame, "Enter Income Amount You Have :) :");
            
            if (input != null && !input.isEmpty()) {
                try {
                    double amount = Double.parseDouble(input);
                    totalIncome += amount;
                    JOptionPane.showMessageDialog(frame, "Income Added Congratulation!\nTotal Income: " + totalIncome);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Invalid number. Please try again.");
                }
            }
        });

        // expensive to add
        addExpenseButton.addActionListener(e -> {
            String category = JOptionPane.showInputDialog(frame, "Enter Expense Category:(house,car)");
            String input = JOptionPane.showInputDialog(frame, "Enter Expense Amount Number:");
            if (input != null && !input.isEmpty() && category != null && !category.isEmpty()) {
                try {
                    double amount = Double.parseDouble(input);
                    totalExpense += amount;
                    
                    // categary expensive
                    categoryExpenses.put(category, categoryExpenses.getOrDefault(category, 0.0) + amount);
                    
                    double used = categoryExpenses.get(category);
                    double limit = categoryLimits.getOrDefault(category, Double.MAX_VALUE);
                    
                    String message = "Expense Added!\nCategory: " + category + 
                                     "\nUsed: " + used + "\nLimit: " + (limit == Double.MAX_VALUE ? "No Limit" : limit);
                    
                    if (used > limit) {
                        message += "\n⚠ Warning: Limit Exceeded!";
                    } else if (used > 0.8 * limit) {
                        message += "\n⚠ Warning: You're close to the limit.";
                    }

                    JOptionPane.showMessageDialog(frame, message + "\nTotal Expense: " + totalExpense);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Invalid number. Please try again.");
                }
            }
        });

        // set the limit catagegy vise
        setLimitButton.addActionListener(e -> {
            String category = JOptionPane.showInputDialog(frame, "Enter Category to Set Limit: (house, car...)");
            String input = JOptionPane.showInputDialog(frame, "Enter Limit Amount Number:");
            if (category != null && !category.isEmpty() && input != null && !input.isEmpty()) {
                try {
                    double limit = Double.parseDouble(input);
                    categoryLimits.put(category, limit);
                    JOptionPane.showMessageDialog(frame, "Limit Set for " + category + ": " + limit);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Invalid number. Please try again.");
                }
            }
        });

        // all limit
        viewLimitsButton.addActionListener(e -> {
            if (categoryLimits.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "No limits set.");
            } else {
                StringBuilder report = new StringBuilder("Category-wise Limits:\n");
                for (String category : categoryLimits.keySet()) {
                    double limit = categoryLimits.get(category);
                    double used = categoryExpenses.getOrDefault(category, 0.0);
                    report.append("• ").append(category)
                          .append(" → Used: ").append(used)
                          .append(" / Limit: ").append(limit).append("\n");
                }
                JOptionPane.showMessageDialog(frame, report.toString());
            }
        });
    }
}
