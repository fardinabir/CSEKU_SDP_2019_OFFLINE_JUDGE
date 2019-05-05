package Server;

public class check {

	public static void main(String[] args) {
		String s = "#include<bits/stdc++.h>\n" + 
				"\n" + 
				"using namespace std;\n" + 
				"\n" + 
				"int main()\n" + 
				"{\n" + 
				"    int n;\n" + 
				"    cin >> n;\n" + 
				"    for(int i=0;i<1000000;i++)\n" + 
				"        cout << i+1 << endl;\n" + 
				"}\n" ;
		String s2 = "A";
		code c = new code();
		System.out.print(c.check(s, s2));

	}

}
