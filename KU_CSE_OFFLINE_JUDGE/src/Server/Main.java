package Server;


import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;


import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;
import javax.swing.JButton;
import java.awt.event.ActionListener;

import java.net.ServerSocket;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.awt.event.ActionEvent;
import javax.swing.JTable;

public class Main extends JFrame implements Runnable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField port;
	ServerSocket server;
	public static boolean start = false;
	public static int portnumber ;
	private JTextField textField;
	private JTable table;
	private JTextField password;
	private JTextField username;

	/**
	 * Launch the application.
	 */
	public void run() {
		try {
			Main frame = new Main();
			frame.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the frame.
	 */
	public Main() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(122, 122, 122));
		panel.setBounds(500, 0, 300, 500);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblPort = new JLabel("Port");
		lblPort.setFont(new Font("Dialog", Font.BOLD, 18));
		lblPort.setBounds(45, 58, 59, 15);
		panel.add(lblPort);
		
		port = new JTextField("0");
		port.setFont(new Font("Dialog", Font.BOLD, 16));
		port.setBorder(null);
		port.setBackground(new Color(211, 211, 211));
		port.setBounds(110, 50, 105, 30);
		panel.add(port);
		port.setColumns(10);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(103, 80, 109, 9);
		panel.add(separator);
		
		JButton btnStart = new JButton("Start");
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(btnStart.getText()=="Start")
				{
					start = true;
					btnStart.setText("Stop");
					portnumber = Integer.parseInt(port.getText());
				}
				else
				{
					start= false;
					btnStart.setText("Stoped");
				}	
				contentPane.repaint();
				contentPane.revalidate();
			}
		});
		btnStart.setBorder(null);
		btnStart.setBackground(new Color(211, 211, 211));
		btnStart.setFont(new Font("Dialog", Font.BOLD, 17));
		btnStart.setBounds(95, 98, 117, 25);
		panel.add(btnStart);
		
		JButton btnStartContest = new JButton("Start Contest");
		btnStartContest.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Calendar cal  = Calendar.getInstance();
				Time.set();
				database db = new database();
				boolean con = db.connect();
				if(con)
				{
				 try {
						db.deletestandings();
					} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
					}
				}
				db.close();
				
			    db = new database();
				 con = db.connect();
				if(con)
				{
				 try {
						db.newstandings();
					} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
					}
				}
				db.close();
			}
		});
		btnStartContest.setFont(new Font("Dialog", Font.BOLD, 17));
		btnStartContest.setBorder(null);
		btnStartContest.setBackground(new Color(211, 211, 211));
		btnStartContest.setBounds(81, 155, 170, 25);
		panel.add(btnStartContest);
		
		JButton btnUsers = new JButton("Users");
		btnUsers.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				contentPane.removeAll();
				contentPane.add(panel);
				
				password = new JTextField("");
				password.setFont(new Font("Dialog", Font.BOLD, 16));
				password.setColumns(10);
				password.setBorder(null);
				password.setBackground(new Color(211, 211, 211));
				password.setBounds(201, 86, 160, 30);
				contentPane.add(password);
				
				JSeparator separator_1 = new JSeparator();
				separator_1.setBounds(201, 117, 160, 4);
				contentPane.add(separator_1);
				
				username = new JTextField("");
				username.setFont(new Font("Dialog", Font.BOLD, 16));
				username.setColumns(10);
				username.setBorder(null);
				username.setBackground(new Color(211, 211, 211));
				username.setBounds(201, 32, 160, 36);
				contentPane.add(username);
				
				JSeparator separator_2 = new JSeparator();
				separator_2.setBounds(201, 70, 160, 9);
				contentPane.add(separator_2);
				
				JLabel lblUsername = new JLabel("UserName");
				lblUsername.setFont(new Font("Dialog", Font.BOLD, 18));
				lblUsername.setBounds(61, 32, 126, 36);
				contentPane.add(lblUsername);
				
				JLabel lblPassword = new JLabel("Password");
				lblPassword.setFont(new Font("Dialog", Font.BOLD, 18));
				lblPassword.setBounds(61, 98, 126, 23);
				contentPane.add(lblPassword);
				
				JButton btnAddUser = new JButton("Add User");
				btnAddUser.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						
						database db = new database();
						boolean con = db.connect();
						if(con)
						{
						 try {
								boolean d = db.useradd(username.getText(), password.getText());
								if(d==true)
								{
									JOptionPane.showMessageDialog(null, "User added");
								}
							} catch (Exception e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
							}
						}
						db.close();
						
		     			 db = new database();
						con = db.connect();
						if(con)
						{
						 try {
									table = new JTable(db.user());
									table.setBounds(12, 70, 476, 273);
									JScrollPane tbl = new JScrollPane(table);
									tbl.setBounds(12, 200, 476, 273);
									contentPane.add(tbl);
							} catch (Exception e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
							}
						}
						db.close();
						
					}
				});
				btnAddUser.setFont(new Font("Dialog", Font.BOLD, 17));
				btnAddUser.setBorder(null);
				btnAddUser.setBackground(new Color(211, 211, 211));
				btnAddUser.setBounds(223, 138, 117, 25);
				contentPane.add(btnAddUser);
				
				
				database db = new database();
				boolean con = db.connect();
				if(con)
				{
				 try {
							table = new JTable(db.user());
							table.setBounds(12, 70, 476, 273);
							JScrollPane tbl = new JScrollPane(table);
							tbl.setBounds(12, 200, 476, 273);
							contentPane.add(tbl);
					} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
					}
				}
				db.close();
				contentPane.repaint();
			}
		});
		btnUsers.setFont(new Font("Dialog", Font.BOLD, 17));
		btnUsers.setBorder(null);
		btnUsers.setBackground(new Color(211, 211, 211));
		btnUsers.setBounds(110, 206, 117, 25);
		panel.add(btnUsers);
		
		JButton btnProblems = new JButton("Problems");
		btnProblems.setFont(new Font("Dialog", Font.BOLD, 17));
		btnProblems.setBorder(null);
		btnProblems.setBackground(new Color(211, 211, 211));
		btnProblems.setBounds(110, 259, 117, 25);
		panel.add(btnProblems);
		
		JButton btnSubmissions = new JButton("Submissions");
		btnSubmissions.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				contentPane.removeAll();
				contentPane.add(panel);
				JButton btnRefresh = new JButton("Refresh");
				btnRefresh.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						database db = new database();
						boolean con = db.connect();
						if(con)
						{
						 try {
									table = new JTable(db.submission());
									table.setBounds(12, 70, 476, 273);
									JScrollPane tbl = new JScrollPane(table);
									tbl.setBounds(12, 70, 476, 273);
									contentPane.add(tbl);
							} catch (Exception e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
							}
						}
						db.close();
					}
				});
				btnRefresh.setBounds(337, 33, 117, 25);
				contentPane.add(btnRefresh);
				contentPane.repaint();
				database db = new database();
				boolean con = db.connect();
				if(con)
				{
				 try {
							table = new JTable(db.submission());
							table.setBounds(12, 70, 476, 273);
							JScrollPane tbl = new JScrollPane(table);
							tbl.setBounds(12, 70, 476, 273);
							contentPane.add(tbl);
					} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
					}
				}
				db.close();
				
			}
		});
		btnSubmissions.setFont(new Font("Dialog", Font.BOLD, 17));
		btnSubmissions.setBorder(null);
		btnSubmissions.setBackground(new Color(211, 211, 211));
		btnSubmissions.setBounds(110, 315, 117, 25);
		panel.add(btnSubmissions);
		
		JButton btnStandings = new JButton("Standings");
		btnStandings.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				contentPane.removeAll();
				contentPane.add(panel);
     			database db = new database();
				boolean con = db.connect();
				if(con)
				{
				 try {
							table = new JTable(db.standings());
							table.setBounds(12, 70, 476, 273);
							JScrollPane tbl = new JScrollPane(table);
							tbl.setBounds(12, 50, 476, 400);
							contentPane.add(tbl);
					} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
					}
				}
				db.close();
				contentPane.repaint();
			}
		});
		btnStandings.setFont(new Font("Dialog", Font.BOLD, 17));
		btnStandings.setBorder(null);
		btnStandings.setBackground(new Color(211, 211, 211));
		btnStandings.setBounds(110, 365, 117, 25);
		panel.add(btnStandings);
		
		textField = new JTextField();
		textField.setBounds(44, 70, -150, 19);
		contentPane.add(textField);
		textField.setColumns(10);
		
	
		
		
		
	}
}
