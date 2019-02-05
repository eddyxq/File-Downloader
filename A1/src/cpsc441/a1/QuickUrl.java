package cpsc441.a1;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class QuickUrl extends ConcurrentHttp 
{

	public void setConn(int conn) {
		// TODO Auto-generated method stub
		
	}

	public void getObject(String url) {
		// TODO Auto-generated method stub
		
	}

	public void parse(String url) 
	{
		//String[] parts = new String[2];
		
		String [] parts = url.split("/", 2);
		
		//System.out.println(parts[0]);
		//System.out.println(parts[1]);
		
		
		
		//if the url has a port specified
		if(parts[0].contains(":"))
		{ 
			String [] hostandport = parts[0].split(":", 2);
			String host = hostandport[0];
			String port = hostandport[1];
			String filepath = "/" + parts[1];
			
			System.out.println(host);
			System.out.println(port);
			System.out.println(filepath);
			
		}
		//if the url has not a port specified
		else
		{ 
			String host = parts[0];
			String port = "80"; // defaults port to 80 if not specified
			String filepath = "/" + parts[1];
			
			System.out.println(host);
			System.out.println(port);
			System.out.println(filepath);
		}
	}
	
	public void sendHeadRequest(String pathname, String hostname, String port) 
	{
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		DataOutputStream outputstream = new DataOutputStream(out);
		String requestLine_1 = "HEAD" + pathname + "HTTP/1.1\r\n";
		String requestLine_2 = "Host: " + hostname + ":" + port + "\r\n";
		String eoh_line = "\r\n";

		try
		{
			String http_header = requestLine_1 + requestLine_2 + eoh_line;
			byte[] http_header_in_bytes = http_header.getBytes("US-ASCII");
			outputstream.write(http_header_in_bytes);
			outputstream.flush();
		}
		catch (IOException e)
		{
			// code for Exception Handling
		}
	}
	
	public void sendRangeRequest(String pathname, String hostname, String port, String rangeStart, String rangeOffSet) 
	{
		DataOutputStream outputstream;
		String requestLine_1 = "HEAD " + pathname + " HTTP/1.1\r\n";
		String requestLine_2 = "Host: " + hostname + ":" + port + "\r\n";
		String requestLine_3 = "Range: " + "bytes=" + rangeStart + "-" + rangeOffSet + "\r\n";
		String eoh_line = "\r\n";

		
		/*
		try
		{
			//Send range request
		}
		catch (IOException e)
		{
			// code for Exception Handling
		}
		*/
	}

	public void receiveAndParse(DataInputStream inputStream)
	{
		byte[] responseBytes= new byte[2048];
		String responseString = "";
		try
		{
			int count;
			while((count = inputStream.read(responseBytes)) > -1)
			{
				//TODO: maybe use count
			}
			responseString = new String(responseBytes, "UTF-8");
		}
		catch (IOException e) 
		{
			//Exception handling � file download error
		}

		if(responseString.contains("404 NOT FOUND"))
		{
			// add logic to handle
		}
		
		else if(responseString.contains("200 OK"))
		{
			if (responseString.contains("Accept-Ranges: bytes"))// || !range)
			{
				/*
				for (String line : responseString ) 
				{
					if (line.contains("Content-Length:"))
					{
						//Find the length of the file in bytes
					}
				}
				*/
			}
		}
		//Does not support range request
		else
		{
			System.out.println("Error: The server does not support range request!");
			System.exit(0);
		}
	}
}