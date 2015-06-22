package model;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import presenter.Properties;


/**
 * a class that represents a server, running on the tcp protocol
 * @author Yehuda Hido Pinto & Adir Ben Avi
 * 
 */
public class MyTCPIPServer{
	int port;
	Boolean stop;
	ExecutorService pool;
	ClientHandler ch;
	
	/**
	 * general constructor
	 * @param tcp_port
	 * @param ch
	 */
	public MyTCPIPServer(int tcp_port, ClientHandler ch) {
		this.port = tcp_port;
		this.ch = ch;
		this.stop = false;
	}
	
	/**
	 * general constructor
	 * @param pro - a Properties
	 * @param ch
	 */
	public MyTCPIPServer(Properties pro, ClientHandler ch) {
		this.port = pro.getPort();
		this.ch = ch;
		this.stop = false;
	}
	
	/**
	 * a method that starts the server, with a limit of clients
	 * @param max_clients
	 */
	public void start(int max_clients){
		if(max_clients!=0)
			pool = Executors.newFixedThreadPool(max_clients);
		else
			pool = Executors.newCachedThreadPool();
		
		ServerSocket server;
		try {
			server = new ServerSocket(this.port);
			server.setSoTimeout(500);
			
			while(!stop){
				try{
				final Socket someClient = server.accept();
				pool.execute(new Runnable() {
					
					@Override
					public void run() {
						try {
							ch.handleClient(someClient.getInputStream(), someClient.getOutputStream());
							InputStream in = someClient.getInputStream();
							OutputStream out = someClient.getOutputStream();
							in.close();
							out.close();
							someClient.close();
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				});
				
				} catch(SocketTimeoutException e){}
			}
			
			pool.shutdown();
			server.close();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
	}
	
	/**
	 * a method that stops the server
	 */
	public void stop(){
		stop=true;
	}

	/**
	 * a method that starts the server
	 */
	public void start() {
		pool = Executors.newCachedThreadPool();
		
		ServerSocket server;
		try {
			server = new ServerSocket(this.port);
			server.setSoTimeout(500);
			
			while(!stop){
				try{
				final Socket someClient = server.accept();
				
				pool.execute(new Runnable() {
					
					@Override
					public void run() {
						try {
							ch.handleClient(someClient.getInputStream(), someClient.getOutputStream());
							InputStream in = someClient.getInputStream();
							OutputStream out = someClient.getOutputStream();
							in.close();
							out.close();
							someClient.close();
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				});
				
				} catch(SocketTimeoutException e){}
			}
			
			pool.shutdown();
			server.close();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
}
