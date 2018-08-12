package graphics;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.*;

import main.Main;
import model.Results;

public class MyFrame extends JFrame{
	
	private JLabel dateLabel;
	private JTextField dateTextField;
	
	private JLabel amountLabel;
	private JTextField amountTextField;
	
	private JLabel resultLabel;
	private JTextField resultTextField;
	private JButton button;
	
	private Results results = new Results();
	
	final JPanel panel = new JPanel();
	
	public MyFrame(){
		super("Расчет прибыли (убытка) по валютному активу");
		int w=350, h=350;
		setBounds(700,200,w,h);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		setLayout(null);
		
		dateLabel = new JLabel("Дата покупки валюты (в формате YYYY-MM-DD)");
		dateLabel.setBounds(10,10,w-25,30);
		dateTextField = new JTextField();
		dateTextField.setBounds(10,40,200,30);
		
		amountLabel = new JLabel("Количество покупаемой валюты");
		amountLabel.setBounds(10,90,w-25,30);
		amountTextField = new JTextField();
		amountTextField.setBounds(10,120, 200, 30);
		
		resultLabel = new JLabel("Ваша прибыль/убыток в рублях");
		resultLabel.setBounds(10,170,w-25,30);
		resultTextField = new JTextField();
		resultTextField.setBounds(dateTextField.getX(),200,200,30);
		resultTextField.setEditable(false);
			

		button = new JButton("Рассчитать"); 
		button.setBounds(resultTextField.getX(),250,200,30);
		button.setFocusPainted(false);
		button.addActionListener(new ActionListener() { 
			  public void actionPerformed(ActionEvent e) { 
				  	String date = dateTextField.getText();
				  	String amount = amountTextField.getText();
				  		 if (Main.isValidFormat("yyyy-MM-dd", date, Locale.ENGLISH) == false || Main.isInteger(amount) == false) {
				  			 if (Main.isValidFormat("yyyy-MM-dd", date, Locale.ENGLISH) == false) {
				  				JOptionPane.showMessageDialog(panel, "Пожалуйста, введите дату в формате YYYY-MM-DD", "Неверный формат даты", JOptionPane.ERROR_MESSAGE);
				  			 } else if (Main.isInteger(amount) == false) {
				  				JOptionPane.showMessageDialog(panel, "Пожалуйста, введите количество валюты в виде целого числа", "Неверный формат введенных данных", JOptionPane.ERROR_MESSAGE);
				  			 } 
				  		 } else {
					  			results.setDate(dateTextField.getText());
						  		results.setAmount(amountTextField.getText());
						  		String[] pastData = Main.getPastData(results.getDate());
						  		String[] currentData = Main.getCurrentData();
						  		resultTextField.setText(Main.calculationOfProfit(results.getAmount(), pastData[2], currentData[2]));
				  		 }
			  }
		});
				  		 
		add(dateLabel);
		add(dateTextField);
		
		add(amountLabel);
		add(amountTextField);
		
		add(resultLabel);
		add(resultTextField);
		
		add(button);
		
		setVisible(true);
	
		
	}
}

	
	


