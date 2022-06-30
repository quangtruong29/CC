package com.duth.engapp.payload;

import lombok.Data;

@Data
public class ApiResponse {
	private int status;
	private String message;
	private Object result;

    private Object error;
	public ApiResponse(int status, String message, Object result){
	    this.status = status;
	    this.message = message;
	    this.result = result;
    }

    public ApiResponse(int status, String message, Object result, Object error) {
        this.status = status;
        this.message = message;
        this.result = result;
        this.error = error;
    }

    public ApiResponse(){
    }
    public ApiResponse(int status, String message){
        this.status = status;
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "ApiResponse{" +
                "status=" + status +
                ", message='" + message + '\'' +
                ", result=" + result.toString() +
                '}';
    }
}