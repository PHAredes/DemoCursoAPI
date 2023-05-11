package br.com.pedro.DemoCursoAPI.api.exception;

public class RecursoNaoEncontradoException extends RuntimeException{

    private static final long serialVersionUID = 1L;

    public RecursoNaoEncontradoException(){
        super("Recurso(s) não encontrado(s).");
    }
}