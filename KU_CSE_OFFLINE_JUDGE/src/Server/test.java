package Server;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.StringReader;
import java.util.Scanner;

public class test {
	static PrintWriter out = null;
	static int res;
	public static void main(String[] args) throws IOException, InterruptedException {
	}
	static int ver(String st,String prblmN) throws IOException
	{
		Scanner sc = new Scanner(System.in);
		String stt = "";
		
		if(prblmN.equals("A"))
		{
			out = new PrintWriter("C:\\Users\\fardin~abir\\Desktop\\A.c");	
			BufferedReader reader = new BufferedReader(new StringReader(st));
			reader.lines().forEach(line -> out.println(line));
			out.close();
			res=test1.verdict('A');	
		}
		else if(prblmN.equals("B"))
		{
			out = new PrintWriter("C:\\Users\\fardin~abir\\Desktop\\B.c");
			BufferedReader reader = new BufferedReader(new StringReader(st));
			reader.lines().forEach(line -> out.println(line));
			out.close();
			res=test1.verdict('B');
		}
			
//		System.out.println(st);
//		out.print(st);
		
		
		
		
		return res;
	}

}
//UIYIYIU
//JKHKK
//
//JKHKHJK
//
//KJJKHK
//KJJL
//
//END