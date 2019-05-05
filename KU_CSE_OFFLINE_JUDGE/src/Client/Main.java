package Client;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JComboBox;

import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.awt.event.ActionEvent;
import javax.swing.UIManager;
import java.awt.Color;
import java.awt.Font;
import java.awt.Cursor;
import javax.swing.JTextArea;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class Main extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main fame = new Main("",0,"");
					fame.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Main(String ip,int port,String Username) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 850, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panelbtns = new JPanel();
		panelbtns.setBackground(new Color(211, 211, 211));
		panelbtns.setBounds(0, 0, 1200, 100);
		contentPane.add(panelbtns);
		panelbtns.setLayout(null);
		
		JButton btnProblems = new JButton("Problems");
		btnProblems.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				contentPane.removeAll();
				contentPane.add(panelbtns);
				
				contentPane.repaint();
				contentPane.revalidate();
				
			}
		});
		btnProblems.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnProblems.setFocusTraversalKeysEnabled(false);
		btnProblems.setFocusPainted(false);
		btnProblems.setFocusable(false);
		btnProblems.setBorder(null);
		btnProblems.setForeground(new Color(0, 128, 128));
		btnProblems.setBackground(new Color(211, 211, 211));
		btnProblems.setFont(new Font("Dialog", Font.BOLD, 18));
		btnProblems.setBounds(42, 38, 128, 25);
		panelbtns.add(btnProblems);
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				contentPane.removeAll();
				contentPane.add(panelbtns);
				
				JPanel panelSubmit = new JPanel();
				panelSubmit.setBounds(0, 100, 1200, 600);
				contentPane.add(panelSubmit);
				
			    String[] choises = {"A","B","C"};
			    panelSubmit.setLayout(null);
			    JComboBox<String> pname = new JComboBox(choises);
			    pname.setFocusTraversalKeysEnabled(false);
			    pname.setFocusable(false);
			    pname.setFont(new Font("Dialog", Font.BOLD, 18));
			    pname.setBounds(225, 28, 129, 24);
			    panelSubmit.add(pname);
			    
			    JLabel lblProblemName = new JLabel("Problem Name");
			    lblProblemName.setForeground(new Color(0, 128, 128));
			    lblProblemName.setFont(new Font("Dialog", Font.BOLD, 16));
			    lblProblemName.setBounds(60, 24, 147, 32);
			    panelSubmit.add(lblProblemName);
			    
			    JLabel lblLanguage = new JLabel("Language");
			    lblLanguage.setForeground(new Color(0, 128, 128));
			    lblLanguage.setFont(new Font("Dialog", Font.BOLD, 16));
			    lblLanguage.setBounds(102, 55, 105, 32);
			    panelSubmit.add(lblLanguage);
			    
			    String[] Language = {"C","C++"};
			    JComboBox lang = new JComboBox(Language);
			    lang.setFocusTraversalKeysEnabled(false);
			    lang.setFocusable(false);
			    lang.setFont(new Font("Dialog", Font.BOLD, 18));
			    lang.setBounds(225, 64, 129, 24);
			    panelSubmit.add(lang);
			    
			    JTextArea codeArea = new JTextArea();
			    codeArea.setBounds(225, 100, 500, 300);
			    codeArea.setFont(new Font("Dialog", Font.BOLD, 15));
			    JScrollPane scrollBar = new JScrollPane(codeArea);
			    scrollBar.setBounds(225, 100, 500, 300);
			    panelSubmit.add(scrollBar);
			    
			    JButton btnSubmit_1 = new JButton("Submit");
			    btnSubmit_1.addActionListener(new ActionListener() {
			    	public void actionPerformed(ActionEvent arg0) {
			    		try {
							Socket socket = new Socket(ip,port);
							DataInputStream sin = new DataInputStream(socket.getInputStream());
						    DataOutputStream sout = new DataOutputStream(socket.getOutputStream());
						    sout.writeUTF("submit");
						    sout.writeUTF(Username);
						    sout.writeUTF(pname.getSelectedItem().toString());
						    sout.writeUTF(lang.getSelectedItem().toString());
						    sout.writeUTF(codeArea.getText());

						    sout.close();
						    sin.close();
						    socket.close();
							
						} catch (Exception e) {
							e.printStackTrace();
						} 
			    		
			    	}
			    });
			    btnSubmit_1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			    btnSubmit_1.setForeground(new Color(0, 128, 128));
			    btnSubmit_1.setFont(new Font("Dialog", Font.BOLD, 18));
			    btnSubmit_1.setFocusable(false);
			    btnSubmit_1.setFocusTraversalKeysEnabled(false);
			    btnSubmit_1.setFocusPainted(false);
			    btnSubmit_1.setBorder(null);
			    btnSubmit_1.setBackground(new Color(211, 211, 211));
			    btnSubmit_1.setBounds(226, 412, 128, 25);
			    panelSubmit.add(btnSubmit_1);
				
			    
				contentPane.repaint();
				contentPane.revalidate();
			}
		});
		btnSubmit.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnSubmit.setFocusPainted(false);
		btnSubmit.setFocusTraversalKeysEnabled(false);
		btnSubmit.setFocusable(false);
		btnSubmit.setForeground(new Color(0, 128, 128));
		btnSubmit.setFont(new Font("Dialog", Font.BOLD, 18));
		btnSubmit.setBorder(null);
		btnSubmit.setBackground(new Color(211, 211, 211));
		btnSubmit.setBounds(154, 38, 128, 25);
		panelbtns.add(btnSubmit);
		
		JButton btnStatus = new JButton("Status");
		btnStatus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				contentPane.removeAll();
				contentPane.add(panelbtns);
				
				try {
					Socket socket = new Socket(ip,port);
					DataOutputStream sout = new DataOutputStream(socket.getOutputStream());
				    sout.writeUTF("status");
				    ObjectInputStream oin = new ObjectInputStream(socket.getInputStream());
				    DefaultTableModel tb = (DefaultTableModel) oin.readObject();
				    JTable table = new JTable(tb);
					table.setBounds(12, 70, 476, 273);
					JScrollPane tbl = new JScrollPane(table);
					tbl.setBounds(54, 154, 600, 350);
					contentPane.add(tbl);
					sout.close();
					oin.close();
					socket.close();
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
				contentPane.repaint();
				contentPane.revalidate();
			}
		});
		btnStatus.setForeground(new Color(0, 128, 128));
		btnStatus.setFont(new Font("Dialog", Font.BOLD, 18));
		btnStatus.setFocusable(false);
		btnStatus.setFocusTraversalKeysEnabled(false);
		btnStatus.setFocusPainted(false);
		btnStatus.setBorder(null);
		btnStatus.setBackground(new Color(211, 211, 211));
		btnStatus.setBounds(266, 38, 128, 25);
		panelbtns.add(btnStatus);
		
		JButton btnStandings = new JButton("Standings");
		btnStandings.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				contentPane.removeAll();
				contentPane.add(panelbtns);
				
				try {
					Socket socket = new Socket(ip,port);
					DataOutputStream sout = new DataOutputStream(socket.getOutputStream());
				    sout.writeUTF("standings");
				    ObjectInputStream oin = new ObjectInputStream(socket.getInputStream());
				    DefaultTableModel tb = (DefaultTableModel) oin.readObject();
				    JTable table = new JTable(tb);
					table.setBounds(12, 70, 476, 273);
					JScrollPane tbl = new JScrollPane(table);
					tbl.setBounds(54, 154, 600, 350);
					contentPane.add(tbl);
					sout.close();
					oin.close();
					socket.close();
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}	
				contentPane.repaint();
				contentPane.revalidate();
			}
		});
		btnStandings.setForeground(new Color(0, 128, 128));
		btnStandings.setFont(new Font("Dialog", Font.BOLD, 18));
		btnStandings.setFocusable(false);
		btnStandings.setFocusTraversalKeysEnabled(false);
		btnStandings.setFocusPainted(false);
		btnStandings.setBorder(null);
		btnStandings.setBackground(new Color(211, 211, 211));
		btnStandings.setBounds(406, 38, 128, 25);
		panelbtns.add(btnStandings);
	}
}
