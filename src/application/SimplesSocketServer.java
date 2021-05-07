package application;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Base64;
import java.util.UUID;

public class SimplesSocketServer {
	
	
	
	public static void main(String[] args) {
		try {
			ServerSocket socketserver = new ServerSocket(9001);
			Socket s = socketserver.accept();

			DataInputStream dis = new DataInputStream(s.getInputStream());
			DataOutputStream dout = new DataOutputStream(s.getOutputStream());
			String user = (String) dis.readUTF();
			String senha = (String) dis.readUTF();

			String descUser = descriptografiaBase64Decode(user);
			String descSenha = descriptografiaBase64Decode(senha);
			
			Equacao eq = new Equacao();

			String equacao = dis.readUTF();

			if (descUser.equals("Leonardo") && descSenha.equals("123")) {

				
				dout.writeBoolean(true);
				dout.flush();
				
				String token = UUID.randomUUID().toString();
				
				dout.writeUTF(token);
				dout.flush();
				
				
				
				
			}else {
				dout.writeBoolean(false);
				dout.flush();
				
			}
			
			
			
			dout.writeInt(eq.resolveEquacao(equacao));

			socketserver.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	/** Realizando o inverso */
	public static String descriptografiaBase64Decode(String pValor) {
		return new String(Base64.getDecoder().decode(pValor.getBytes()));
	}

	
}