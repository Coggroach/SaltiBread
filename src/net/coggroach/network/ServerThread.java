package net.coggroach.network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.URL;

import net.coggroach.options.Options;

public class ServerThread implements Runnable
{
	StringStream stream;
	Socket socket;
	int id;
	boolean isRunning;
	String ipAddress;
	
	public ServerThread(Socket s, int i)
	{
		this.socket = s;
		this.id = i;
		this.isRunning = true;
	}
	
	public void start()
	{	
		try
		{
			System.out.println("Connecting: " + id);
			stream = new StringStream(socket);
			ipAddress = getIp();
		}
		catch(IOException ex)
		{
			ex.printStackTrace();
		}
	}
	
	public void stop()
	{	
		try
		{
			System.out.println("Connection: " + id + " closed.");
			stream.close();
			socket.close();
		}
		catch(IOException ex)
		{
			ex.printStackTrace();
		}
	}
	
	private String getIp() throws IOException
	{
		URL ipCheck = new URL("http://checkip.amazonaws.com");
		
		BufferedReader in = new BufferedReader(new InputStreamReader(ipCheck.openStream()));

		String ip = in.readLine();
		
		return ip;
	}
	
	public void process(String s) throws IOException
	{
		System.out.println("[Received" + ":" + id + "]: " + s);
		if(s.contains("KILL_SERVICE"))
			isRunning = false;
		if(s.contains("HELO text"))
		{
			String message = "HELO text\nIP:[" + ipAddress + "]\nPort:[" + Options.DEFAULT_SOCKET_ID + "]\nStudentID:[" + Options.STUDENT_ID + "]\n";
			System.out.println("[Sending" + ":" + id + "]: " + message);
			stream.write(message);
		}					
	}

	@Override
	public void run()
	{
		start();
		while(isRunning)
		{
			try
			{
				if(stream.ready())
				{
					String packet = stream.read();
					process(packet);
				}
			} 
			catch (IOException e)
			{
				e.printStackTrace();
				isRunning = false;
			}
		}
		stop();		
	}
}
