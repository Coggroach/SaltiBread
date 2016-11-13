package net.coggroach.test;

import net.coggroach.network.ThreadedNetwork;
import net.coggroach.options.Options;

public class TestThreadedNetwork
{
	public static void main(String[] args)
	{
		ThreadedNetwork network = new ThreadedNetwork(Options.SOCKET_ID);
		
		network.start();
	}
}
