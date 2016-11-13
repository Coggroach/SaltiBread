package net.coggroach.network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class StringStream
{
	private BufferedReader input;
	private PrintWriter output;
	
	public StringStream(Socket s) throws IOException
	{		
		this.output = new PrintWriter(s.getOutputStream());
		this.input = new BufferedReader(new InputStreamReader(s.getInputStream()));	
	}
	
	public String read() throws IOException
	{
		return input.readLine();		
	}
	
	public void write(String packet) throws IOException
	{
		output.write(packet);
	}
	
	public void close() throws IOException
	{
		this.output.close();
		this.input.close();
	}
	
	public void flush() throws IOException
	{
		this.output.flush();
	}
	
	public boolean ready() throws IOException
	{
		return this.input.ready();
	}
}
