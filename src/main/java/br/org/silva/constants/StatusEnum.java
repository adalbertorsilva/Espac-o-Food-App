package br.org.silva.constants;

public enum StatusEnum {
	
	ENABLED("HABILITADO"),
	DISABLED("DESABILITADO");
	
	private String value;
	
	private StatusEnum(String value){
		this.value = value;
	}
	
	public String getValue(){
		return this.value;
	} 

}
