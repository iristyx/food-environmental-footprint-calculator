import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CalculatorFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField_1;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					CalculatorFrame frame = new CalculatorFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public CalculatorFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(0, 2, 0, 0));

		// Panel on the left, with user input features
		// Input food item OR choose food from dropdown list
		// Followed by inputting weight of food item
		JPanel userInputPanel = new JPanel();
		contentPane.add(userInputPanel);
		userInputPanel.setLayout(null);

		// Calculator frame > userInputPanel: Food selection and input fields
		
		// (user instructions)
		JLabel lblInstructions = new JLabel("Input the food items below:");
		lblInstructions.setBounds(50, 48, 273, 16);
		userInputPanel.add(lblInstructions);
		
		// (food label)
		JLabel lblFood = new JLabel("Food");
		lblFood.setBounds(50, 94, 61, 16);
		userInputPanel.add(lblFood);

		// (food input field)
		String[] foodArrayList = { "Egg", "Chicken", "Milk", "Almond", "Strawberry" }; // Example only. Actual list to be read from database csv.
		JComboBox<String> foodComboBox = new JComboBox<>();
		foodComboBox.setEditable(true);
		foodComboBox.setModel(new DefaultComboBoxModel<>(foodArrayList));
		foodComboBox.setBounds(108, 89, 215, 27);
		userInputPanel.add(foodComboBox);

		// (weight label)
		JLabel lblWeight = new JLabel("Weight");
		lblWeight.setBounds(50, 122, 153, 16);
		userInputPanel.add(lblWeight);

		// (weight input field)
		JTextField weightTextField = new JTextField();
		weightTextField.setColumns(10);
		weightTextField.setBounds(108, 117, 144, 26);
		userInputPanel.add(weightTextField);
		
		// (amount weight unit selection)
		String[] weightUnitList = { "g", "kg"};
		JComboBox<String> weightComboBox = new JComboBox<>();
		weightComboBox.setModel(new DefaultComboBoxModel<>(weightUnitList));
		weightComboBox.setBounds(251, 118, 72, 27);
		userInputPanel.add(weightComboBox);
			
		
		// Calculator frame > addedFoodPanel: Displays list of foods added by user, before consolidating
		JPanel addedFoodPanel = new JPanel();
		contentPane.add(addedFoodPanel);
		addedFoodPanel.setLayout(null);
		
		// (table with added food items)
		String[] columnHeaders = {"Food", "Weight (g)"};
		DefaultTableModel model = new DefaultTableModel(columnHeaders,0); // initialize with 0 rows
		
		// (button to add food item into table)
		JButton btnAddFood = new JButton("Add Food");
		btnAddFood.setBounds(206, 173, 117, 29);
		userInputPanel.add(btnAddFood);
		
		btnAddFood.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (weightComboBox.getSelectedItem() == "kg") {
					// write method - if input food item is not found in list, find closest food item based on spelling
					model.addRow(new Object[]{foodComboBox.getSelectedItem(), Double.parseDouble(weightTextField.getText())*1000});
				} else {
					// write method - if input food item is not found in list, find closest food item based on spelling
					model.addRow(new Object[]{foodComboBox.getSelectedItem(), weightTextField.getText()});
				} }
		});
		
		// (scrollpane containing addedFoodTable)
		JScrollPane addedFoodPane = new JScrollPane();
		addedFoodPane.setBounds(44, 49, 325, 188);
		addedFoodPanel.add(addedFoodPane);
		JTable addedFoodTable = new JTable(model);
		addedFoodPane.setViewportView(addedFoodTable);
		
		// (button to delete 1 food item off the table
		JButton btnDeleteFood = new JButton("Delete Food");
		btnDeleteFood.setBounds(236, 248, 133, 29);
		addedFoodPanel.add(btnDeleteFood);
		btnDeleteFood.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					int selectedRow = addedFoodTable.getSelectedRow();
				    model.removeRow(selectedRow);
				    }catch(Exception ex)
				       {
				           JOptionPane.showMessageDialog(null, ex);
				       }
			}
		});
		
		JButton btnCalculate = new JButton("Calculate!");
		btnCalculate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (addedFoodTable.getRowCount() != 0) {
					// run backend program to calculate the EF of each food item
				} else {
					JOptionPane.showMessageDialog(contentPane, "Please select at least one food item.");
				}
			}
		});
		btnCalculate.setBounds(236, 276, 133, 29);
		addedFoodPanel.add(btnCalculate);
		
	

	}
}