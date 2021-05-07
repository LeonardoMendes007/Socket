package application;

import bsh.EvalError;
import bsh.Interpreter;

public class Equacao {
	
	Interpreter bsh = new Interpreter();
	public int resolveEquacao(String equacao) throws EvalError {
		
		bsh.eval("resultado = " + equacao);

		int result = (int) bsh.get("resultado");

		return result;
	}
}
