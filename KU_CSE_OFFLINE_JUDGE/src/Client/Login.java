package Client;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Point;
import java.net.Socket;
import java.net.URL;
import java.net.UnknownHostException;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.GridBagConstraints;
import java.awt.Font;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.UIManager;
import java.awt.Color;
import javax.swing.border.TitledBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.BevelBorder;
import java.awt.SystemColor;
import javax.swing.JSeparator;
import javax.swing.JButton;
import java.awt.Cursor;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;

public class Login extends JFrame {

	private JPanel contentPane;
	private JTextField port;
	private JTextField ip;
	private JTextField Uname;
	public String ipaddress,portaddress,username;
	private String password;
	private JPasswordField Pword;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		Login frame = new Login();
		frame.setVisible(true);
		frame.setTitle("Login");
		frame.setResizable(false);
	}

	/**
	 * Create the frame.
	 */
	public Login() {
		getContentPane().setBackground(SystemColor.window);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(300, 50, 700, 600);
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setFocusable(true);
		panel.setBorder(new TitledBorder(new LineBorder(new Color(135, 206, 250), 2, true), "Login", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(47, 79, 79)));
		panel.setBackground(new Color(211, 211, 211));
		panel.setBounds(142, 127, 417, 364);
		getContentPane().add(panel);
		
		JLabel lblPort = new JLabel("Port");
		lblPort.setFont(new Font("Dialog", Font.BOLD, 18));
		lblPort.setBounds(226, 69, 59, 15);
		panel.add(lblPort);
		
		JLabel label_1 = new JLabel("ip");
		label_1.setFont(new Font("Dialog", Font.BOLD, 18));
		label_1.setBounds(25, 69, 19, 22);
		panel.add(label_1);
		
		
		
		JLabel label_2 = new JLabel("Username");
		label_2.setFont(new Font("Dialog", Font.BOLD, 18));
		label_2.setBounds(41, 144, 121, 30);
		panel.add(label_2);
		
		JLabel label_3 = new JLabel("Password");
		label_3.setFont(new Font("Dialog", Font.BOLD, 18));
		label_3.setBounds(41, 217, 121, 22);
		panel.add(label_3);
		
		port = new JTextField();
		port.setFont(new Font("Dialog", Font.BOLD, 16));
		port.setBorder(null);
		port.setBackground(new Color(211, 211, 211));
		port.setBounds(294, 58, 105, 30);
		panel.add(port);
		port.setColumns(10);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(295, 89, 105, 2);
		panel.add(separator);
		
		ip = new JTextField();
		ip.setFont(new Font("Dialog", Font.BOLD, 16));
		ip.setColumns(10);
		ip.setBorder(null);
		ip.setBackground(new Color(211, 211, 211));
		ip.setBounds(65, 58, 143, 30);
		panel.add(ip);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(66, 89, 142, 2);
		panel.add(separator_1);
		
		Uname = new JTextField();
		Uname.setFont(new Font("Dialog", Font.BOLD, 16));
		Uname.setColumns(10);
		Uname.setBorder(null);
		Uname.setBackground(new Color(211, 211, 211));
		Uname.setBounds(166, 141, 143, 30);
		panel.add(Uname);
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setBounds(167, 172, 142, 2);
		panel.add(separator_2);
		
		JSeparator separator_3 = new JSeparator();
		separator_3.setBounds(167, 240, 142, 2);
		panel.add(separator_3);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ipaddress = ip.getText();
				portaddress = port.getText();
				username = Uname.getText();
				password = Pword.getText();
				try {
					Socket socket = new Socket(ipaddress,Integer.parseInt(portaddress));
					DataInputStream sin = new DataInputStream(socket.getInputStream());
				    DataOutputStream sout = new DataOutputStream(socket.getOutputStream());
				    sout.writeUTF("login");
				    sout.writeUTF(username);
				    sout.writeUTF(password);
				    int x = sin.read();
				    sout.close();
				    sin.close();
				    socket.close();
				    if(x==1)
				    {
				    	dispose();
				    	Main frame = new Main(ipaddress,Integer.parseInt(portaddress),username);
						frame.setVisible(true);
				    }
				    	
				    else
				    {
				    	JOptionPane.showMessageDialog(null,"Invalid User");
				    }
				    
				}
				catch(Exception e)
				{
					JOptionPane.showMessageDialog(null,"Server Not connected");
					System.out.println("Sever not coonected");
				}
			}
		});
		btnLogin.setBorder(null);
		btnLogin.setBackground(new Color(211, 211, 211));
		btnLogin.setFont(new Font("Dialog", Font.BOLD, 18));
		btnLogin.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnLogin.setBounds(184, 287, 152, 38);
		panel.add(btnLogin);
		
		Pword = new JPasswordField();
		Pword.setBounds(166, 209, 143, 30);
		Pword.setFont(new Font("Dialog", Font.BOLD, 16));
		Pword.setBackground(new Color(211, 211, 211));
		Pword.setBorder(null);
		panel.add(Pword);
		
		JLabel lblKhulnaUniversityProgramming = new JLabel("Khulna University Programming Contest");
		lblKhulnaUniversityProgramming.setFont(new Font("Dialog", Font.BOLD, 26));
		lblKhulnaUniversityProgramming.setBounds(55, 31, 600, 67);
		getContentPane().add(lblKhulnaUniversityProgramming);
		
		
			
		
		
	}
}
