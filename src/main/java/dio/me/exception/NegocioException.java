package dio.me.exception;

public class NegocioException extends RuntimeException {
    private static final Long serialVersionUID = 1L;
    public NegocioException(String message){
        super(message);
    }
}
