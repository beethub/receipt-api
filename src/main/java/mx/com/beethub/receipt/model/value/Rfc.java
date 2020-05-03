package mx.com.beethub.receipt.model.value;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import mx.com.beethub.receipt.exception.ValidationException;

@Data
@Slf4j
public class Rfc {
	
	private static final String EXP =  "^([A-Z&Ñ]{3,4})(\\d{2}(?:0[1-9]|1[0-2])(?:0[1-9]|[12]\\d|3[01]))([A-Z\\d]{2})([A\\d])$";
	private String value;
	
	public Rfc(){
		
	}
	
	public Rfc(String valor) {
		this.setValor(valor);
	}
	

	public boolean sameValueAs(Rfc other) {
		return super.equals(other);
	}


	public String getPattern() {
		return EXP;
	}
	
	public void setValor(String value){
		try{
			if(!Valido(value))
				throw new ValidationException("Patron no cumplido para el valor: " + value);
			this.value = value;
		}catch(ValidationException e){
			log.error("Inicializar RFC valor:{} expresion:{}" , value, EXP, e);
			throw new ValidationException("Error de formato para el campo RFC");
		}
			
	}
	
	static public boolean Valido(String rfc){
		boolean ret = false;
		Pattern patron = Pattern.compile(EXP);
		Matcher matcher = patron.matcher(rfc);
		ret = matcher.matches();
		ret = ret && ValidaFecha(matcher.group(2));
		return ret;
	}
	static public boolean ValidaFecha(String fecha){
		boolean ret = false;
		
		SimpleDateFormat formatter = new SimpleDateFormat("yyMMdd");
		Date currentDate = new Date();
		
		try{
			Date date = formatter.parse(fecha);
			ret = date.before(currentDate);
		}catch (ParseException|NullPointerException e){
			e.printStackTrace();
		}
		return ret;
	}
}
