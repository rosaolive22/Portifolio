package TREVO.api.infra;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import static org.springframework.util.ReflectionUtils.getField;

@RestControllerAdvice
public class TratadorDeErros {
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity tratarErro404(){
        //Obs: sem tratamento envia erro 500
        //return ResponseEntity.notFound().build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Página não encontrada");
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity tratarerro400(MethodArgumentNotValidException ex){
        var erros = ex.getFieldErrors();

        return ResponseEntity.badRequest().body(erros.stream().map(MensagemErroValidacao::new).toList());
    }
    /*private record MensagemErroValidacao(String campo,String mensagem) {
        public MensagemErroValidacao(FieldError erros) {
            this(erros.getField(),erros.getDefaultMessage());*/

    private record MensagemErroValidacao(String campo,String mensagem) {
        public MensagemErroValidacao (FieldError erros) {
            this(erros.getField(),erros.getDefaultMessage());

     /* //  public static MensagemErroValidacao registroDuplicado (FieldError erros){
             if ("cnpj".equals(campo)) {
                 return new MensagemErroValidacao("Cnpj já cadastrado.");
             } else if ("name".equals(campo)) {
                 return new MensagemErroValidacao("Name já Existente.");
             } else {
                 return new MensagemErroValidacao("Campo " + campo + " já cadastrado.");
             }// OPÇÃO: } else if (campo.contains("nome")) {*/
         }
     }

}




