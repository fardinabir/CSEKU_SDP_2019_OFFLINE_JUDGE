package Server;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Date;

import javax.swing.table.DefaultTableModel;

public class ClientThread implements Runnable {

	Socket socket = null;
	
	ClientThread(Socket socket)
	{
		this.socket = socket;
	}
	@Override
	public void run() {
		try {
			DataInputStream sin = new DataInputStream(socket.getInputStream());
			DataOutputStream sout = new DataOutputStream(socket.getOutputStream());
			String task = sin.readUTF();
			System.out.println("server ready for "+ task);
			if(task.equals("login"))
			{
				System.out.println("Login entered");
				String username = sin.readUTF();
				String password = sin.readUTF();
			    boolean done = login(username,password);
			    if(done)
			    	sout.write(1);
			    else
			    	sout.write(0);		
			}
				
			if(task.equals("submit"))
			{
				System.out.println("Submit entered");
				String username = sin.readUTF();
				String problem = sin.readUTF();
				String lang = sin.readUTF();
				String code = sin.readUTF();
				System.out.println(username);
				System.out.println(problem);
				CodeVar var = new CodeVar();
				var.username = username;
				var.code = code;
				var.p = problem;
				var.lang = lang;
				Date d = new Date();
				var.d = d;
				Start.checker.q.add(var);
				System.out.println(Start.checker.q.size());
				
			}
			if(task.equals("status"))
			{
				ObjectOutputStream oout = new ObjectOutputStream(socket.getOutputStream());
				DefaultTableModel tb = null;
				database db = new database();
				boolean con = db.connect();
				if(con)
				{
					tb = db.submission();
					db.close();
				}
				oout.writeObject(tb);
				oout.close();
			}
			if(task.equals("standings"))
			{
				ObjectOutputStream oout = new ObjectOutputStream(socket.getOutputStream());
				DefaultTableModel tb = null;
				database db = new database();
				boolean con = db.connect();
				if(con)
				{
					tb = db.standings();
					db.close();
				}
				oout.writeObject(tb);
				oout.close();
			}
			
		}
		catch(Exception e)
		{
			
		}
	}
	private boolean login(String user,String pass)
	{
		boolean done = false;
		database db = new database();
		boolean con = db.connect();
		if(con)
		{
			if(db.usercheck(user, pass))
				done = true;
			db.close();
		}
		
		return done;
	}

}
