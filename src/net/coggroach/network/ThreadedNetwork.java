package net.coggroach.network;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ThreadedNetwork
{
	int port;
	ServerSocket server;
	Socket client;
	int connections;
	boolean isRunning;
	
	public ThreadedNetwork(int port)
	{
		this.port = port;
	}
	
	public void start()
	{
		try
		{
			System.out.println("Starting Server on " + port);
			server = new ServerSocket(port);
			isRunning = true;
		}
		catch(IOException ex)
		{
			ex.printStackTrace();
		}
		
		while(isRunning)
		{
			try
			{
				client = server.accept();
				if(client != null)
				{					
					connections++;
					ServerThread sThread = new ServerThread(client, connections);
					new Thread(sThread).start();
				}
			} 
			catch (IOException e)
			{
				e.printStackTrace();
				isRunning = false;
			}
		}
	}
	
	public void stop()
	{
		try
		{
			System.out.println("Stopping Server...");
			server.close();
			client.close();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	
}
