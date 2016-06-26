import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class Client {
	
	public static final String IP_ADDR = "192.168.2.46";//服务器地址   
    public static final int PORT = 8888;//服务器端口号    
      
    public static void main(String[] args) {    
        while (true) {    
            Socket socket = null;  
            try {  
                //创建一个流套接字并将其连接到指定主机上的指定端口号  
                socket = new Socket(IP_ADDR, PORT);    
                    
                //读取服务器端数据    
                DataInputStream input = new DataInputStream(socket.getInputStream());    
                //向服务器端发送数据    
                DataOutputStream out = new DataOutputStream(socket.getOutputStream());    
                System.out.println("Please input the integer:");
                BufferedReader bufread = new BufferedReader(new InputStreamReader(System.in));
                String str = "http://52.27.136.210/test.php?integer="+bufread.readLine();    
                out.writeUTF(str);    
                    
                String ret = input.readUTF();     
                System.out.println("server return the message:  " + ret);    
                
                BufferedReader bufread2 = new BufferedReader(new InputStreamReader(System.in));
                String str2 = bufread2.readLine();    
                out.writeUTF(str2);    
             
                ret = input.readUTF();     
                System.out.println("The number is prime:" + ret);
                
                out.close();  
                input.close();  
            } catch (Exception e) {  
                System.out.println("client exception:" + e.getMessage());   
            } finally {  
                if (socket != null) {  
                    try {  
                        socket.close();  
                    } catch (IOException e) {  
                        socket = null;   
                        System.out.println("client finally exception:" + e.getMessage());   
                    }  
                }  
            }  
        }    
    } 
	

}
