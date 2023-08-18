package org.itsci.miniproject.response;

import lombok.Data;

@Data
public class LoginResponse {

    String message;
    boolean status;

    public LoginResponse(String message, boolean status) {
        this.message = message;
        this.status = status;
    }

    public LoginResponse() {
    }

    @Override
    public String toString() {
        return "LoginResponse{" +
                "message='" + message + '\'' +
                ", status=" + status +
                '}';
    }
}
