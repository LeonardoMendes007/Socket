package application;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.Base64;
import java.util.Scanner;  
public class SimplesSocketClient {  
	public static void main(String[] args) {  
		try{      
			Socket socket =new Socket("localhost",9001);  
			DataOutputStream dout=
					new DataOutputStream(socket.getOutputStream());  
			
			DataInputStream din=
					new DataInputStream(socket.getInputStream()); 
			
			Scanner scn = new Scanner(System.in);
			
			System.out.print("Username: ");
			String username = scn.next();
			dout.writeUTF(criptografiaBase64Encoder(username));  
			dout.flush(); 
			
			System.out.print("Senha: ");
			String senha = scn.next();
			dout.writeUTF(criptografiaBase64Encoder(senha));  
			dout.flush();  
			
			System.out.print("Equação: ");
			dout.writeUTF(scn.next());
			dout.flush();  
			
			if(din.readBoolean()) {
				
			   System.out.println(din.readUTF());
			   
			   System.out.println("O resultado da equação é: " + din.readInt());
			}else {
				
			   System.out.println("Usuario não autorizado");
			}
			
			dout.close();  
			socket.close();  
		}catch(Exception e){
			System.out.println(e);
		}  
	}  
	
	/** Criptografando */
	public static String criptografiaBase64Encoder(String pValor) {
	    return new String(Base64.getEncoder().encode(pValor.getBytes()));
	}
}  