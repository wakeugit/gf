package gf.backend;

import lombok.Data;

import java.util.ArrayList;


public class Response<T> {

    T body;
    ArrayList<Exception> exceptions = new ArrayList<>();
	public T getBody() {
		return body;
	}
	public void setBody(T body) {
		this.body = body;
	}
	public ArrayList<Exception> getExceptions() {
		return exceptions;
	}
	public void setExceptions(ArrayList<Exception> exceptions) {
		this.exceptions = exceptions;
	}
    
    
}
