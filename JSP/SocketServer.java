package socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketServer {

	public static void main(String[] args) {
		try {
			ServerSocket serversocket= new ServerSocket(8012);
			while(true) {
			System.out.println("연결 대기중..");
			Socket socket=serversocket.accept();
			System.out.println("연결 성공");
			
			BufferedReader in=new BufferedReader(new InputStreamReader(socket.getInputStream()));
			PrintWriter out=new PrintWriter(socket.getOutputStream(),true);
			
			String rev=in.readLine();
			System.out.println("Received : "+rev);
			
			String id=rev.split("~")[0];
			String pw=rev.split("~")[1];
			System.out.println("Split : "+id+pw);
			
			ConnectDB conn=ConnectDB.getInstance();
			String returns=conn.connectionDB(id, pw);
			
			out.println(returns);
			System.out.println("Send : "+returns);
			
			socket.close();
			}
			
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}

}
