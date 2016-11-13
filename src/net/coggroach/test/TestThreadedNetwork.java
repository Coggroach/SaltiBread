package net.coggroach.test;

import net.coggroach.network.ThreadedNetwork;
import net.coggroach.options.Options;

public class TestThreadedNetwork
{
	public static void main(String[] args)
	{
		setup(args);
		
		ThreadedNetwork network = new ThreadedNetwork(Options.SOCKET_ID);
		
		network.start();
		network.stop();
	}
	
	private static void setup(String[] args)
	{
		if(args == null || args.length == 0)
		{
			Options.SOCKET_ID = Options.DEFAULT_SOCKET_ID;
			return;
		}
		Options.SOCKET_ID = Integer.parseInt(args[0]);
	}
}
