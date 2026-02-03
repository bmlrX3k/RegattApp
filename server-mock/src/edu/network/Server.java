package edu.network;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Inet4Address;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.ServerSocket;

public class Server {
	
	public static void main(String[] args) throws IOException {
		Inet4Address ip = (Inet4Address) Inet4Address.getByName("0.0.0.0");
		int port = 8080;
		InetSocketAddress bindpoint = new InetSocketAddress(ip, port);
		new Server(bindpoint,0);
	}
	
	private ServerSocket serverSocket;
	
	public Server(InetSocketAddress bindpoint, int timeout) throws IOException {
		try {
			this.serverSocket = new ServerSocket();
			this.serverSocket.bind(bindpoint);
			this.serverSocket.setSoTimeout(timeout);
			while(true) {
				try {
					Socket socket = this.waitForConnection();
					byte[] request = this.read(socket);
					System.out.println(new String(request));
					System.out.println("OK");
					byte[] response = this.handle();
					this.write(socket,response);
					System.out.println("path-request answer sent ...");
					socket.close();
				}
				catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public Socket waitForConnection() throws IOException {
		System.out.println("Waiting for connection ...");
		return this.serverSocket.accept();
	}
	
	public byte[] read(Socket socket) throws IOException {
		byte[] request = new byte[100];
		InputStream inputStream = socket.getInputStream();
		inputStream.read(request);
		return request;
	}

	public byte[] handle() {
		String response = "08237210";
		return response.getBytes();
//		String var = new String(request);
//		System.out.println(var);
//		String response = "hello " + var;
//		System.out.println(response);
//		return response.getBytes();
	}
	
	public void write(Socket socket, byte[] response) throws IOException {
		OutputStream outputStream = socket.getOutputStream();
		outputStream.write(response);
		outputStream.flush();
	}
	
}
