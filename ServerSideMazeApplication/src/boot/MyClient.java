package boot;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;


public class MyClient {
	
	public static void main(String[] args) throws Exception{
		System.out.println("CLIENT SIDE");
		Socket myServer = new Socket("127.0.0.1", 7000); //127.0.0.1 needed to be changed to the server ip. works here because they are on the same computer.
		
		BufferedReader inFromServer = new BufferedReader(new InputStreamReader(myServer.getInputStream()));
		PrintWriter outToServer = new PrintWriter(new OutputStreamWriter(myServer.getOutputStream()));
		BufferedReader inFromUser= new BufferedReader(new InputStreamReader(System.in));
		String line;
		
		while(!((line=inFromUser.readLine()).equals("exit"))){
			outToServer.println(line);
			outToServer.flush();

			String serverMSG = inFromServer.readLine();
			System.out.println(serverMSG);
		}
		outToServer.println(line);
		outToServer.flush();
		
		System.out.println(inFromServer.readLine());
		
		inFromServer.close();
		outToServer.close();
		inFromUser.close();
		myServer.close();
		
	}
}
