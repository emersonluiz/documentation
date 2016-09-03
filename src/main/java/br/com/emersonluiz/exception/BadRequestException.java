package br.com.emersonluiz.exception;

public class BadRequestException extends Exception {

    private static final long serialVersionUID = -7649585623194339795L;

    public BadRequestException(String message) {
        super(message);
    }
}
