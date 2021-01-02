package visualizer4j.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.ServerSocket;
import java.net.Socket;

class PortListener {


	public static void main(String args[]) throws Exception {
		ServerSocket ss = new ServerSocket(8080);
		Socket s = ss.accept();
		BufferedReader stream = new BufferedReader(new InputStreamReader(s.getInputStream()));
		String test = "";
		while (test.contains("keep-alive") == false) {
			test = stream.readLine();
			System.out.println("-->" + test);
		}
		FileReader fileReader =new FileReader(new File(args[0]));
		BufferedReader reader = new BufferedReader(fileReader);
		Writer w = new OutputStreamWriter(s.getOutputStream());
		String nextLine;
		w.append("HTTP/1.1 200 OK\n");
		w.append("Content-type: text/xml");
		w.append("\n\n");
		while ((nextLine = reader.readLine()) != null) {
			w.append(nextLine);
			System.out.println("<--" + nextLine);
			w.flush();
		}
		w.append("\n");
		w.flush();
		stream.close();
		s.close();
		System.out.println("***");
		s = ss.accept();
		stream = new BufferedReader(new InputStreamReader(s.getInputStream()));
		test = "";
		char c;
		while (true) {
			c = (char)stream.read();
			System.out.print(c);
			if (c < 0) {
				stream.close();
				s.close();
				ss.close();
				reader.close();				
			}
		}
	}


}
