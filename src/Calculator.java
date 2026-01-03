import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

public class Calculator implements ActionListener, KeyListener{
	
	JFrame jf;
	JLabel displayLabel;
	
	JButton sevenBtn, eightBtn, nineBtn, fourBtn, fiveBtn, sixBtn;
    JButton oneBtn, twoBtn, threeBtn, zeroBtn, doublezeroBtn, dotBtn;
    JButton divBtn, mulBtn, minBtn, addBtn, equalBtn;
    JButton cBtn, ceBtn, delBtn;
    
    JButton[] buttons;
	
	double firstNum = 0;
	String operator = "";
	boolean isOperatorClicked = false;
	
	public Calculator() {
		UIManager.put("Button.font", new Font("Arial", Font.PLAIN, 22));
		UIManager.put("Label.font", new Font("Arial", Font.BOLD, 40));
		
		jf=new JFrame("Calculator");
		jf.setLayout(null);
		jf.setSize(600, 600);
		jf.setLocationRelativeTo(null); // We can use jf.setLocation(0, 0); also, but this one is better.
		
		Font font=new Font("Arial", Font.BOLD, 25);
		
		
		displayLabel=new JLabel("0");
		displayLabel.setBounds(20, 30, 550, 65);
		displayLabel.setBackground(Color.GRAY);
		displayLabel.setOpaque(true);
		displayLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		displayLabel.setForeground(Color.white);
		jf.add(displayLabel);
		displayLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 10));
		
		sevenBtn=new JButton("7");
		sevenBtn.setBounds(20 ,200 ,70 ,70);
		jf.add(sevenBtn);
		
		eightBtn=new JButton("8");
		eightBtn.setBounds(110 ,200 ,70 ,70);
		jf.add(eightBtn);
		
		nineBtn=new JButton("9");
		nineBtn.setBounds(200 ,200 ,70 ,70);
		jf.add(nineBtn);
		
		fourBtn=new JButton("4");
		fourBtn.setBounds(20 ,290 ,70 ,70);
		jf.add(fourBtn);
		
		fiveBtn=new JButton("5");
		fiveBtn.setBounds(110 ,290 ,70 ,70);
		jf.add(fiveBtn);

		sixBtn=new JButton("6");
		sixBtn.setBounds(200 ,290 ,70 ,70);
		jf.add(sixBtn);
		
		oneBtn=new JButton("1");
		oneBtn.setBounds(20 ,380 ,70 ,70);
		jf.add(oneBtn);
		
		twoBtn=new JButton("2");
		twoBtn.setBounds(110 ,380 ,70 ,70);
		jf.add(twoBtn);

		threeBtn=new JButton("3");
		threeBtn.setBounds(200 ,380 ,70 ,70);
		jf.add(threeBtn);
		
		doublezeroBtn=new JButton("00");
		doublezeroBtn.setBounds(20 ,470 ,70 ,70);
		jf.add(doublezeroBtn);
		
		zeroBtn=new JButton("0");
		zeroBtn.setBounds(110 ,470 ,70 ,70);
		jf.add(zeroBtn);
		 
		dotBtn=new JButton(".");
		dotBtn.setBounds(200 ,470 ,70 ,70);
		jf.add(dotBtn);
		dotBtn.setFont(new Font("Arial", Font.PLAIN, 50));
		
		// Operators
		divBtn=new JButton("/");
		divBtn.setBounds(290 ,110 ,70 ,70);
		jf.add(divBtn);
		
		mulBtn=new JButton("x");
		mulBtn.setBounds(290 ,200 ,70 ,70);
		jf.add(mulBtn);
		
		minBtn=new JButton("-");
		minBtn.setBounds(290 ,290 ,70 ,70);
		jf.add(minBtn);
		
		
		addBtn=new JButton("+");
		addBtn.setBounds(290 ,380 ,70 ,70);
		jf.add(addBtn);
		
		equalBtn=new JButton("=");
		equalBtn.setBounds(290 ,470 ,70 ,70);
		jf.add(equalBtn);
		
		// Clearing Buttons
		cBtn=new JButton("C");
		cBtn.setBounds(20 ,110 ,70 ,70);
		jf.add(cBtn);
		
		ceBtn=new JButton("CE");
		ceBtn.setBounds(110 ,110 ,70 ,70);
		jf.add(ceBtn);
		

		delBtn=new JButton("←");
		delBtn.setBounds(200 ,110 ,70 ,70);
		jf.add(delBtn);
		
		buttons = new JButton[] {
		        sevenBtn, eightBtn, nineBtn, fourBtn, fiveBtn, sixBtn, 
		        oneBtn, twoBtn, threeBtn, zeroBtn, doublezeroBtn, dotBtn, equalBtn, 
		        divBtn, mulBtn, minBtn, addBtn, cBtn, ceBtn, delBtn
		    };

		for (JButton b : buttons) {
		    b.addActionListener(this);
		    b.setFocusable(false); 
		    b.setFont(new Font("Arial", Font.PLAIN, 20));
		}
		
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.setVisible(true);
		
		jf.addKeyListener(this);
		jf.setFocusable(true);
		jf.requestFocusInWindow();
		
	}
	
	public static void main(String [] args) {
		new Calculator();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// Get the text of whichever button was clicked
	    String text = ((JButton)e.getSource()).getText();
	    String currentText = displayLabel.getText();
	    
	    // 1. CLEAR (Resets everything)
	    if (text.equals("C")) {
	        displayLabel.setText("0");
	        firstNum = 0;
	        operator = "";
	        isOperatorClicked = false;
	    } 
	    // 2. CLEAR ENTRY (Wipes current screen)
	    else if (text.equals("CE")) {
	        displayLabel.setText("0");
	    } 
	    // 3. Delete
	    else if (text.equals("←")) {
	        if (currentText.length() > 1) {
	            displayLabel.setText(currentText.substring(0, currentText.length() - 1));
	        } else {
	            displayLabel.setText("0"); // If last char is deleted, show 0
	        }
	    }
	    // 4.OPERATORS
	    else if ("+-x/".contains(text)) {
	        if (!operator.isEmpty() && !isOperatorClicked) {
	            double secondNum = Double.parseDouble(currentText);
	            
	            if (operator.equals("+")) firstNum += secondNum;
	            else if (operator.equals("-")) firstNum -= secondNum;
	            else if (operator.equals("x")) firstNum *= secondNum;
	            else if (operator.equals("/")) {
	                if (secondNum != 0) firstNum /= secondNum;
	                else { displayLabel.setText("Error"); return; }
	            }
	            displayLabel.setText(firstNum % 1 == 0 ? String.valueOf((int)firstNum) : String.valueOf(firstNum));
	        } else {
	            firstNum = Double.parseDouble(currentText);
	        }
	        
	        operator = text;          
	        isOperatorClicked = true;
	    }
	    // 5.EQUALS
	    else if (text.equals("=")) {
	    	if (!operator.isEmpty()) {
	            double secondNum = Double.parseDouble(currentText);
	            double result = 0;

	            if (operator.equals("+")) result = firstNum + secondNum;
	            else if (operator.equals("-")) result = firstNum - secondNum;
	            else if (operator.equals("x")) result = firstNum * secondNum;
	            else if (operator.equals("/")) {
	                if (secondNum != 0) result = firstNum / secondNum;
	                else { displayLabel.setText("Error"); return; }
	            }

	            displayLabel.setText(result % 1 == 0 ? String.valueOf((int)result) : String.valueOf(result));	            
	            firstNum = 0;
	            operator = "";
	            isOperatorClicked = true; 
	        }
	    }
	    // 6. NUMBER & DOT
	    else if (text.equals(".")) {
	        if (!displayLabel.getText().contains(".")) {
	            displayLabel.setText(displayLabel.getText() + ".");
	        }
	    } else {	    
	        if (isOperatorClicked || currentText.equals("0")) {
	            if (text.equals(".")) {
	                displayLabel.setText("0.");
	            } else if (text.equals("00")) {
	                displayLabel.setText("0"); 
	            } else {
	                displayLabel.setText(text);
	            }
	            isOperatorClicked = false;
	        } else {
	            if (text.equals(".")) {
	                if (!currentText.contains(".")) displayLabel.setText(currentText + ".");
		         } else if (text.equals("00")) {
		                displayLabel.setText(currentText + "00");
		           } else {
		                displayLabel.setText(currentText + text);
		             }
	          }  
	    }
	    
	    
	    
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
	    char c = e.getKeyChar();

	    // 1. NUMBERS (0-9)
	    if (Character.isDigit(c)) {
	        for (JButton b : buttons) {
	            if (b.getText().equals(String.valueOf(c))) {
	                b.doClick();
	                break;
	            }
	        }
	    }

	    // 2. OPERATORS & SPECIAL KEYS
	    else if (c == '+') addBtn.doClick();
	    else if (c == '-') minBtn.doClick();
	    else if (c == '*') mulBtn.doClick();
	    else if (c == '/') divBtn.doClick();
	    else if (c == '.') dotBtn.doClick();
	    
	    else if (key == KeyEvent.VK_O) doublezeroBtn.doClick();
	    
	    // 3. ENTER, BACKSPACE, ESCAPE
	    else if (key == KeyEvent.VK_ENTER) equalBtn.doClick();
	    else if (key == KeyEvent.VK_BACK_SPACE) delBtn.doClick();
	    else if (key == KeyEvent.VK_ESCAPE) cBtn.doClick();
	    else if (key == KeyEvent.VK_DELETE) ceBtn.doClick();
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
		
}

