package Server;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.Timer;

public class window {

	private JFrame frame;
	private final JLayeredPane layeredPane = new JLayeredPane();
	private JPasswordField passwordField_1;
	private JTextField txtUsername;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					window window = new window();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @throws IOException 
	 */
	public window() throws IOException {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 * @throws IOException 
	 */
	private void initialize() throws IOException {
		frame = new JFrame();
		
		frame.setBounds(250, 50, 750, 600); 
		frame.setBackground(Color.black);
		frame.setTitle("OJ");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		layeredPane.setOpaque(true);
		layeredPane.setBackground(new Color(0, 0, 0));
		layeredPane.setForeground(Color.ORANGE);
		layeredPane.setBounds(0, 0, 1504, 1000);
		
		
		frame.getContentPane().add(layeredPane);
		layeredPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("KU_CSE Offline Judge (demo)");
		lblNewLabel.setFont(new Font("Viner Hand ITC", Font.BOLD, 30));
		lblNewLabel.setForeground(new Color(219, 112, 147));
		lblNewLabel.setBounds(123, 25, 477, 60);
		layeredPane.add(lblNewLabel);
		
		JTextArea txtar = new JTextArea();
		txtar.setBounds(93, 155, 577, 220);
		txtar.setFont(new Font("Consolas", Font.PLAIN, 18));
		layeredPane.add(txtar);
		
		
		JLabel lblpath = new JLabel("Submit code:");
		lblpath.setFont(new Font("Comic Sans MS", Font.PLAIN, 18));
		lblpath.setForeground(new Color(219, 112, 147));
		lblpath.setBounds(53, 100, 377, 60);
		layeredPane.add(lblpath);
		
		JLabel lblverdict = new JLabel("-----");
		lblverdict.setFont(new Font("Viner Hand ITC", Font.BOLD, 28));
		lblverdict.setForeground(new Color(219, 112, 147));
		lblverdict.setBounds(115, 395, 977, 60);
		layeredPane.add(lblverdict);
		
		
		JButton btnEnter = new JButton("Submit");
		btnEnter.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnEnter.setFocusable(false);
		btnEnter.setBackground(new Color(211, 211, 211));
		btnEnter.setFont(new Font("Trebuchet MS", Font.BOLD, 18));
		btnEnter.setForeground(new Color(219, 112, 147));
		btnEnter.setBounds(565, 395, 103, 30);
		layeredPane.add(btnEnter);
		
		
		JLabel lbltime = new JLabel("");
		lbltime.setToolTipText("System Time");
		lbltime.setForeground(new Color(219, 112, 147));
		lbltime.setHorizontalAlignment(SwingConstants.CENTER);
		lbltime.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
		lbltime.setBackground(new Color(105, 105, 105));
		lbltime.setBounds(613, 516, 121, 23);
		layeredPane.add(lbltime);
		
		JLabel lbldate = new JLabel("");
		lbldate.setToolTipText("System Time");
		lbldate.setForeground(new Color(219, 112, 147));
		lbldate.setHorizontalAlignment(SwingConstants.CENTER);
		lbldate.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
		lbldate.setBackground(new Color(105, 105, 105));
		lbldate.setBounds(613, 537, 121, 19);
		layeredPane.add(lbldate);
		
		
		
		
		
		SimpleDateFormat sdft = new SimpleDateFormat("hh:mm:ss a");
		SimpleDateFormat sdfd=new SimpleDateFormat("dd.MM.YY");
		ActionListener timerListener = new ActionListener()
	        {
	            public void actionPerformed(ActionEvent e)
	            {
	            	Calendar cl = Calendar.getInstance();
	    			lbltime.setText(sdft.format(cl.getTime()));
	    			
	    			lbldate.setText(sdfd.format(cl.getTime()));
	            }
	        };
		
		Timer SimpleTimer = new Timer(1000, timerListener);
		SimpleTimer.setInitialDelay(0);
		SimpleTimer.start();
				
				
		btnEnter.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				try {
					   // build the text to set into textarea2
					   String text = txtar.getText();
					   System.out.print(text);
					   txtar.setText("");
					
					
					
					lblverdict.setText("Judging...");
					lblverdict.setForeground(Color.red);
					test1 tt = new test1();
					int x=0;
					String prblmN = "A";
					x=test.ver(text,prblmN);
					System.out.println(x);
					
					if(x==-2)
					{
						lblverdict.setText("Compilation Error !");
					}
					else if(x==-1)
					{
						lblverdict.setText("Runtime Error !");
					}
					else if(x==-3)
					{
						lblverdict.setText("Wrong Answer !");
					}
					else if(x==-4)
					{
						lblverdict.setText("Accepted !");
						lblverdict.setForeground(Color.GREEN);
					}
					else if(x>0)
					{
						lblverdict.setText("Time Limit Exceed ! ( "+x+"ms ).");
					}
					System.out.println(test1.rer);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}	
			}
			
		});		
	}
	
	public void display1()
	{
		frame.dispose();
		//Frm1 fm =new Frm1();
		//fm.main(null);
		
	}
}