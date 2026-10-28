package System;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.*;


class GStatementDialog extends JDialog {
	
	public GStatementDialog(JFrame parent, JScrollPane scrollPane) {
            super(parent, "", true); // true for modal dialog
            setUndecorated(true);
		
            JPanel mainPanel = new JPanel();
            mainPanel.setLayout(new BorderLayout());
            mainPanel.setBorder(new LineBorder(Color.black, 2));
        
            JPanel panel = new JPanel(new GridBagLayout());
	    panel.setBackground(new Color(0xDDE5F4));
	    panel.setBorder(BorderFactory.createEmptyBorder(10, 25, 10, 25));

            GridBagConstraints gbc = new GridBagConstraints();
            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.anchor = GridBagConstraints.WEST;
            gbc.insets = new Insets(5, 0, 15, 0);
            
            JLabel title = new JLabel("Account Transactions");
            title.setFont(new Font("Gotham Rounded", Font.BOLD, 18));
            
            panel.add(title, gbc);
            
            gbc.gridy++;
            panel.add(scrollPane, gbc);

            // Get Loan Button
            JButton getBttn = new JButton("Continue");
            getBttn.setForeground(Color.white);
            getBttn.setBackground(new Color(0x002BB8));
            getBttn.setFont(new Font("SansSerif", Font.BOLD, 16));
            getBttn.setFocusPainted(false);
            getBttn.setBorder(BorderFactory.createEmptyBorder(7, 35, 7, 35));
            getBttn.setAlignmentX(Component.CENTER_ALIGNMENT);
            getBttn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            getBttn.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    dispose();
                }
            });
            getBttn.addMouseListener(new MouseAdapter() {
                public void mouseEntered(MouseEvent e) {
                    getBttn.setBackground(new Color(0x0837d1));
                }
                public void mouseExited(MouseEvent e) {
                    getBttn.setBackground(new Color(0x002BB8));
                }
            });
          gbc.gridy++;
          gbc.fill = GridBagConstraints.HORIZONTAL;
          gbc.anchor = GridBagConstraints.CENTER;
          panel.add(getBttn, gbc);

          mainPanel.add(GcashDialog.exitPanel(this), BorderLayout.NORTH);
          mainPanel.add(panel, BorderLayout.CENTER);

          add(mainPanel);
        
        pack(); // Adjust size to fit components
        setLocationRelativeTo(parent); // Center relative to the parent frame
        setVisible(true);
	}
}

class GLoanDialog extends JDialog {
    
        boolean getLoan = false;
	
	public GLoanDialog(JFrame parent, int feePercent, int interestRate, double loanAmount, double feeValue, double amountToReceive, int term, String monthlyDue) {
            super(parent, "", true); // true for modal dialog
            setUndecorated(true);
		
            JPanel mainPanel = new JPanel();
            mainPanel.setLayout(new BorderLayout());
            mainPanel.setBorder(new LineBorder(Color.black, 2));
        
            JPanel panel = new JPanel(new GridBagLayout());
	    panel.setBackground(new Color(0xDDE5F4));
	    panel.setBorder(BorderFactory.createEmptyBorder(10, 25, 10, 25));

            GridBagConstraints gbc = new GridBagConstraints();
            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.anchor = GridBagConstraints.WEST;
            gbc.insets = new Insets(5, 0, 15, 200);

        // "Summary" Label
        JLabel summaryLabel = new JLabel("Summary");
        summaryLabel.setFont(new Font("", Font.BOLD, 18));
        summaryLabel.setForeground(new Color(0x001C80));
        gbc.gridwidth = 2; // Span two columns
        panel.add(summaryLabel, gbc);
        
        gbc.insets = new Insets(5, 0, 5, 180);

        // "Loan Amount" Label
        JLabel loanAmountLabel = label("Loan Amount");
        loanAmountLabel.setForeground(new Color(0x80a1db));
        gbc.gridy++;
        gbc.gridwidth = 1; // Reset to one column
        panel.add(loanAmountLabel, gbc);
        
        // "3% Processing Fee" Label
        JLabel processingFeeLabel = label(feePercent + "% Processing Fee");
        processingFeeLabel.setForeground(new Color(0x80a1db));
        gbc.gridy++;
        panel.add(processingFeeLabel, gbc);
        
        // "Amount to Receive" Label
        JLabel amountToReceiveLabel = label("Amount to Receive");
        amountToReceiveLabel.setForeground(new Color(0x80a1db));
        gbc.gridy++;
        panel.add(amountToReceiveLabel, gbc);
        
        // Separator
        JSeparator separator = new JSeparator(SwingConstants.HORIZONTAL);
        gbc.gridy++;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 0, 10, 0);
        panel.add(separator, gbc);
        
        // "Interest Rate" Label
        JLabel interestRateLabel = label("Interest Rate");
        interestRateLabel.setForeground(new Color(0x80a1db));
        gbc.gridy++;
        gbc.gridwidth = 1; // Reset gridwidth
        gbc.fill = GridBagConstraints.NONE; // Reset fill
        gbc.insets = new Insets(5, 0, 5, 180);
        panel.add(interestRateLabel, gbc);
        
        // "Term" Label
        JLabel termLabel = label("Term");
        termLabel.setForeground(new Color(0x80a1db));
        gbc.gridy++;
        panel.add(termLabel, gbc);
        
        // "Monthly Dues" Label
        JLabel monthlyDuesLabel = label("Monthly Dues");
        monthlyDuesLabel.setForeground(new Color(0x80a1db));
        gbc.gridy++;
        panel.add(monthlyDuesLabel, gbc);

        // Loan Amount Value
        JLabel loanAmountValue = label("₱" + loanAmount);
        loanAmountValue.setForeground(new Color(0x80a1db));
        gbc.gridx++;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.LINE_END;
        gbc.insets = new Insets(5, 0, 5, 0);
        panel.add(loanAmountValue, gbc);

        // "3% Processing Fee" Value
        JLabel processingFeeValue = label("-" + feeValue);
        processingFeeValue.setForeground(Color.RED);
        gbc.gridy++;
        panel.add(processingFeeValue, gbc);

        // "Amount to Receive" Value
        JLabel amountToReceiveValue = label("₱" + amountToReceive);
        amountToReceiveValue.setForeground(new Color(0x001C80));
        gbc.gridy++;
        panel.add(amountToReceiveValue, gbc);

        // "Interest Rate" Value
        JLabel interestRateValue = label(interestRate + "%");
        interestRateValue.setForeground(new Color(0x80a1db));
        gbc.gridy++;
        gbc.gridy++;
        panel.add(interestRateValue, gbc);

        // "Term" Value
        JLabel termValue = label(term + " months");
        termValue.setForeground(new Color(0x80a1db));
        gbc.gridy++;
        panel.add(termValue, gbc);

        // "Monthly Dues" Value
        JLabel monthlyDuesValue = label("₱" + monthlyDue);
        monthlyDuesValue.setForeground(new Color(0x001C80));
        gbc.gridy++;
        panel.add(monthlyDuesValue, gbc);

      // Get Loan Button
        JButton getBttn = new JButton("Get this Loan");
        getBttn.setForeground(Color.white);
        getBttn.setBackground(new Color(0x002BB8));
        getBttn.setFont(new Font("SansSerif", Font.BOLD, 16));
        getBttn.setFocusPainted(false);
        getBttn.setBorder(BorderFactory.createEmptyBorder(7, 35, 7, 35));
        getBttn.setAlignmentX(Component.CENTER_ALIGNMENT);
        getBttn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        getBttn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                getLoan = true;
                dispose();
            }
        });
        getBttn.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                    getBttn.setBackground(new Color(0x0837d1));
            }
            public void mouseExited(MouseEvent e) {
                    getBttn.setBackground(new Color(0x002BB8));
            }
        });
        gbc.insets = new Insets(20, 0, 10, 0);
        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(getBttn, gbc);

        mainPanel.add(GcashDialog.exitPanel(this), BorderLayout.NORTH);
        mainPanel.add(panel, BorderLayout.CENTER);

        add(mainPanel);
        
        pack(); // Adjust size to fit components
        setLocationRelativeTo(parent); // Center relative to the parent frame
        setVisible(true);
	}
	
	JLabel label(String name) {
		JLabel label = new JLabel(name);
		label.setFont(new Font("", Font.BOLD, 16));
		
		return label;
	}
	
}

public class GcashDialog extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 597913862210991627L;

	public GcashDialog(JFrame parent, String labelTitle, int titleSize, String label, int labelSize, String buttonTitle, ImageIcon imageIcon) {
		super(parent, "", true); // true for modal dialog
		setUndecorated(true);
		
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());
		mainPanel.setBorder(new LineBorder(Color.black, 2));
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(0x3478F2));
		panel.setLayout(new GridBagLayout());
		panel.setBorder(BorderFactory.createEmptyBorder(15, 100, 20, 100));
		
		GridBagConstraints gbc = new GridBagConstraints();
		
		gbc.gridx = 0;
		gbc.gridy = 0;
		
		
		if (imageIcon != null) {
		    JLabel icon = new JLabel(imageIcon);
		    icon.setAlignmentX(Component.CENTER_ALIGNMENT);
		    icon.setPreferredSize(new Dimension(70, 70));
		    panel.add(icon, gbc);
		    gbc.gridy++;
		}
		
		// Welcome Text
		if(labelTitle != null) {
			JLabel welcomeLabel = new JLabel(labelTitle);
		    welcomeLabel.setForeground(Color.WHITE);
		    welcomeLabel.setFont(new Font("SansSerif", Font.BOLD, titleSize));
		    welcomeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		    panel.add(welcomeLabel, gbc);
		    gbc.gridy++;
		    gbc.insets = new Insets(5, 0, 0, 0);
		}
		
		// Account Created Text
		JLabel createdLabel = new JLabel(label);
		createdLabel.setForeground(new Color(0xdde5f4));
		createdLabel.setFont(new Font("SansSerif", Font.PLAIN, labelSize));
		createdLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel.add(createdLabel, gbc);
		gbc.gridy++;
		gbc.insets = new Insets(20, 0, 0, 0);
		
		// Proceed Button
		JButton proceedBttn = new JButton(buttonTitle);
		proceedBttn.setForeground(new Color(0x002BB8));
		proceedBttn.setBackground(Color.WHITE);
		proceedBttn.setFont(new Font("SansSerif", Font.BOLD, 16));
		proceedBttn.setFocusPainted(false);
		proceedBttn.setBorder(BorderFactory.createEmptyBorder(7, 35, 7, 35));
		proceedBttn.setAlignmentX(Component.CENTER_ALIGNMENT);
		proceedBttn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		proceedBttn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		proceedBttn.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				proceedBttn.setBackground(new Color(0xdedede));
			}
			public void mouseExited(MouseEvent e) {
				proceedBttn.setBackground(Color.WHITE);
			}
		});
		panel.add(proceedBttn, gbc);
		
		mainPanel.add(exitPanel(this), BorderLayout.NORTH);
		mainPanel.add(panel, BorderLayout.CENTER);
		
		add(mainPanel);
		pack(); // Adjust size to fit components
		setLocationRelativeTo(parent); // Center relative to the parent frame
		setVisible(true);
    }
	
    public static JPanel exitPanel(JDialog dialog) {
        String exitPath = "C:\\Users\\rafae\\Desktop\\GCASH_HARDCODED\\src\\Resources\\exit.png";   // Palitan lang path nung pics
        String minPath = "C:\\Users\\rafae\\Desktop\\GCASH_HARDCODED\\src\\Resources\\min.png";
        String maxPath = "C:\\Users\\rafae\\Desktop\\GCASH_HARDCODED\\src\\Resources\\max.png";

        // Scaled ImageIcons
        ImageIcon scaledDeleteIcon = createScaledIcon(exitPath, 20, 20);
        ImageIcon scaledMinIcon = createScaledIcon(minPath, 15, 15);
        ImageIcon scaledMaxIcon = createScaledIcon(maxPath, 15, 15);
        
        JButton exitBttn = button(scaledDeleteIcon);
        exitBttn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    dialog.dispose();
            }
        });

        JButton minBttn = button(scaledMinIcon);
        JButton maxBttn = button(scaledMaxIcon);
        
        JPanel exitPanel = new JPanel(new FlowLayout(2, 8, 5));
        exitPanel.setBorder(new LineBorder(Color.black, 2));
        exitPanel.add(minBttn);
        exitPanel.add(maxBttn);
        exitPanel.add(exitBttn);
        
        return exitPanel;
	}

	static ImageIcon createScaledIcon(String path, int width, int height) {
        ImageIcon originalIcon = new ImageIcon(path);
        Image scaledImage = originalIcon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(scaledImage);
    }
	
	static JButton button(Icon icon) {
		Dimension buttonDimension = new Dimension(25, 25);
		
		JButton button = new JButton(icon);
		button.setPreferredSize(buttonDimension);
		button.setMinimumSize(buttonDimension);
		button.setMaximumSize(buttonDimension);
		button.setFocusPainted(false);
		button.setContentAreaFilled(false);
		button.setBorder(new LineBorder(Color.black, 2));
		button.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				button.setContentAreaFilled(true);
				button.setBackground(new Color(0xdedede));
			}
			public void mouseExited(MouseEvent e) {
				button.setContentAreaFilled(false);
			}
		});
        
        return button;
	}

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 500);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        
        String iconPath = "C:\\Users\\rafae\\Desktop\\GCASH_HARDCODED\\src\\Resources\\user.png";  
        ImageIcon scaledIcon = createScaledIcon(iconPath, 75, 75);

        // SAMPLES
        
       //new GcashDialog(frame, "Welcome to GCash!", 24, "<html><div style='text-align: center;'>We successfully created your account.<br>Log in now to start using GCash!</div></html>", 16, "Proceed", scaledIcon);
        
//       new GcashDialog(frame, "Cash In Successful!", 24, "<html><div style='text-align: center; width: 100%'>Your funds have been <br> added to your wallet.</div></html>", 20, "Confirm", null);	
        
//        new GcashDialog(frame, null, 0, "<html><div style='text-align: center;'>Your funds have been transferred <br>successfully. Please wait while the <br>transaction is being reflected.</div></html>", 18, "Confirm", null);
        
//        new GcashDialog(frame, "Your GLoan is Successful!", 24, "<html><div style='text-align: center;'>Funds are on the way—time to <br>make your goals happen!</div></html>", 20, "Confirm", null);
    
//        new GLoanDialog(frame, 3, 25000.00, 750.00, 24250.00, 9, 3517.17);
        
//        new GcashDialog(frame, null, 0, "Transaction failed: ", 16, "Confirm", null);

//         new GcashDialog(frame, "Loan successful!", 22, "", 0, "Confirm", null);

//        new GcashDialog(frame, "ERROR!", 24, "<html><div style='text-align: center;'>Insufficient balance for the transfer<br>(including transfer fee).</div><html>", 18, "Okay", null);

//        JScrollPane scrollPane = new JScrollPane();
//        new GStatementDialog(frame, scrollPane);
    }
}







