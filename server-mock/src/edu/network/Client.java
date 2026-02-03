package edu.network;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Inet4Address;
import java.net.InetSocketAddress;
import java.net.Socket;

public class Client {
	
	public static void main(String[] args) throws IOException {
		Inet4Address ip = (Inet4Address) Inet4Address.getLocalHost();
		int port = 25856;
		InetSocketAddress bindpoint = new InetSocketAddress(ip, port);
		port = 8080;
		InetSocketAddress endpoint = new InetSocketAddress(ip, port);
		new Client(bindpoint,endpoint);
	}
	
	private InetSocketAddress endpoint;
	
	public Client(InetSocketAddress bindpoint, InetSocketAddress endpoint) throws IOException {
		this.endpoint = endpoint;
		Socket socket = new Socket();
		socket.bind(bindpoint);
		this.connectToServer(socket);
		String request = "client_x";
		this.write(socket,request.getBytes());
		byte[] response = this.read(socket);
		System.out.println(new String(response));
		socket.close();
	}
	
	public void connectToServer(Socket socket) throws IOException {
		socket.connect(this.endpoint);
	}
	
	public void write(Socket socket, byte[] request) throws IOException {
		OutputStream outputStream = socket.getOutputStream();
		outputStream.write(request);
		outputStream.flush();
	}
	
	public byte[] read(Socket socket) throws IOException {
		byte[] response = new byte[100];
		InputStream inputStream = socket.getInputStream();
		inputStream.read(response);
		return response;
	}
	
}
