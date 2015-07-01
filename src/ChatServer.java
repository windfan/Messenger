import java.io.*;
import java.net.*;
import java.util.StringTokenizer;
import java.util.Vector;


public class ChatServer {
	static Vector ClientSockets;
	static Vector LoginNames;
	
	ChatServer() throws IOException{
		ServerSocket server = new ServerSocket(5217);
		ClientSockets = new Vector();
		LoginNames = new Vector();
		
		while(true) {
			Socket client = server.accept();
			AcceptClient acceptClient = new AcceptClient(client);
		}
		
	}
	
	public static void main(String[] args) throws IOException {
		ChatServer server = new ChatServer();
	}
	
	
	class AcceptClient extends Thread{
		Socket ClientSocket;
		DataInputStream din;
		DataOutputStream dout;
		AcceptClient(Socket client) throws IOException {
			ClientSocket = client;
			din = new DataInputStream(ClientSocket.getInputStream());
			dout = new DataOutputStream(ClientSocket.getOutputStream());
			
			String LoginName = din.readUTF();
			LoginNames.add(LoginName);
			ClientSockets.add(ClientSockets);
			
			start();
		}
		
		public void run(){
			while(true){
				try {
					String msgFromClient = din.readUTF();
					StringTokenizer st = new StringTokenizer(msgFromClient);
					String loginName = st.nextToken();
					String msgType = st.nextToken();
					
					for(int i = 0; i < loginName.length(); i++) {
						Socket pSocket = (Socket) ClientSockets.elementAt(i);
						DataOutputStream pout = new DataOutputStream(pSocket.getOutputStream());
						pout.writeUTF(loginName + " has logged in"); 
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		}
	}
	
}
