import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.ProxySelector;
import java.net.SocketAddress;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Proxy_test {

	public static boolean httpProxy(int res) throws IOException {

        ProxySelector.setDefault(new ProxySelector() {

            @Override
            public List<Proxy> select(URI uri) {
                List<Proxy> list = new ArrayList<Proxy>();
                list.add(new Proxy(Proxy.Type.HTTP, new InetSocketAddress(
                        "192.168.2.46", 8887)));
                return list;
            }

            @Override
            public void connectFailed(URI uri, SocketAddress sa, IOException ioe) {
                System.out.println("wait...");
            }

        });
        // ��Ҫ���ʵĵ�ַ
        String urlStr = "http://52.27.136.210/test.php?integer="+res;
        // ��������
        URL url = new URL(urlStr);
       URLConnection conn = url.openConnection();
        // ������ʽ��
        try {
            Scanner scan = new Scanner(conn.getInputStream());
            StringBuilder builder = new StringBuilder();
            while (scan.hasNextLine()) {
                builder.append(scan.nextLine());
            }
            System.out.println(builder.toString());
            if(builder.toString().equals("true"))
            	return true;
            else
            	return false;
        } catch (Exception e) {
            e.printStackTrace();
        }
		return false;
    }
	
//	public static void main(String[] args) {
//		Proxy_test prox = new Proxy_test();
//		
//		try {
//			prox.httpProxy();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//	}

}
