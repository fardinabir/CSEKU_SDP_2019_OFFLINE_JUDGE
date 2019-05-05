package Server;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class code {
	
	public String check(String code,String p)
	{
		try {
			
			Files.write( Paths.get("code.cpp"), code.getBytes(), StandardOpenOption.CREATE);
		    Process proc;
		    proc = Runtime.getRuntime().exec("g++ code.cpp");
			proc.waitFor();
			int j = proc.exitValue();
			System.out.println(j);
			if(j!=0)
				return "Compailer Error";
			
			String[] cmd = {"/bin/sh", "-c", "timeout 5s ./a.out +x <in.txt  >op.txt"};
			proc = Runtime.getRuntime().exec(cmd);
			proc.waitFor();
			j = proc.exitValue();
			System.out.println(j);
			if(j==124)
				return "Time Limit Execed";
			if(j!=0)
				return "Runtime Error";
		//	diff op.txt a.txt
			String[] cmd1 = {"/bin/sh", "-c", "diff op.txt a.txt"};
			proc = Runtime.getRuntime().exec(cmd1);
			proc.waitFor();
			j = proc.exitValue();
			if(j==0)
				return "Accepted";
			else
				return "Wrong Answer";
				
		}
		catch(Exception e)
		{
			
		}
			 
		return "Not running";
    
	}
   
}
