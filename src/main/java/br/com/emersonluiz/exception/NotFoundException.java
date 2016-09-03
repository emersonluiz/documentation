package br.com.emersonluiz.exception;

public class NotFoundException extends Exception {

    private static final long serialVersionUID = -5488213845049942169L;

    public NotFoundException(String message) {
        super(message);
    }
}
