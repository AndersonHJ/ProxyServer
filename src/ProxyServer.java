import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Proxy;
import java.net.ServerSocket;
import java.net.Socket;


public class ProxyServer {
	Proxy proxy = null;
	private Socket socket = null ;
	int num = 0;
	
	public void createSocket(Socket s){
		this.socket = s ;
	}
	
	public int action() throws Exception {
		if (this.socket == null){
			return -1;
		}
		BufferedReader br = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
		
		String[] temp = br.readLine().split(" ");
		
		System.out.println(Integer.valueOf(temp[1].substring(2)));
		DataOutputStream out = new DataOutputStream(socket.getOutputStream());
		out.writeUTF("Please enter the password");
		DataInputStream input = new DataInputStream(socket.getInputStream());  
		String ret = input.readUTF();     
        System.out.println("Password:  " + ret);    
		input.close();
		out.close();	
		br.close();
		if(ret.equals("123456"))
			return Integer.valueOf(temp[1].substring(2));
		else
			return -1;
	}
	
	public static void main(String[] args) {
		Proxy_test prox = new Proxy_test();
		ServerSocket server;
		Socket socket;
		boolean ret = false;
		try {
			server = new ServerSocket(8888);
			while(true){
				socket = server.accept();
				ProxyServer ap = new ProxyServer();
				ap.createSocket(socket);
				int res = ap.action();
				if(res>0){
					ret = prox.httpProxy(res);
					DataOutputStream out = new DataOutputStream(socket.getOutputStream());
					out.writeUTF(""+ret);
					out.close();
				}
			}
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	
	}

}

