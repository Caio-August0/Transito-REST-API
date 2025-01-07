package com.transito.transito_api.api.exceptionhandler;

import java.net.URI;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.transito.transito_api.domain.exception.ApreensaoVeiculoException;
import com.transito.transito_api.domain.exception.EntidadeNaoEncontrada;
import com.transito.transito_api.domain.exception.NegotionException;

@RestControllerAdvice
public class GlobalHandlerExceptions extends ResponseEntityExceptionHandler{
	
	private MessageSource messageSource;
	
	public GlobalHandlerExceptions(MessageSource messageSource) {
		this.messageSource = messageSource;
	}
	
	/* Essa classe é compativél com as conformidadades do RFC 
	 * além de tratar todas as requisições lançadas pelo 
	 * Spring MVC e envia como corpo de resposta*/
	
	/* Devemos customizar o objeto ProblemDetail de acordo 
	 * com as possivéis exceções que podem ser lançadas pelo
	 * Spring MVC.
	 * 
	 * A classe ResponseEntityExceptionHandler possui métodos 
	 * que realiza o tratamento dessas exceções, como queremos 
	 * customizar o ProblemDetails devemos sobrescreve-los.
	 * 
	 * Criamos um instancia de ProblemDetails */
		
	
	/*	O getDefaultMessage() não nos retorna uma mensagem resolvida,
	 * mas sim uma mensagem padrão. Para usarmos mensagens resolvidas
	 * precisamos de um arquivo de configuração e uma Interface do Spring
	 * chamada de MessageSource para processar essa mensagens.
	 * 
	 * O própio Bean messageSource busca o arquivo de configuração para 
	 * seguir os padrões de coriguração especificados.
	 * 
	 * Uma mensagem resolvida é um mensagem obtida através de um arquivo 
	 * de customização 
	 * 
	 * O método handleMethodArgumentNotValid() */
	
	@Override// Método usado para manipula exceções não validação
	// lança um object ProblemDatiels
	protected ResponseEntity<Object> handleMethodArgumentNotValid(
			MethodArgumentNotValidException exception,
			HttpHeaders headers, HttpStatusCode status, WebRequest request) {
		
		// Cria uma instância de ProblemDetails com o código de status 
		ProblemDetail problemDetail = ProblemDetail.forStatus(status);
		
		problemDetail.setTitle("Um ou mais campos estão inválidos.");
		problemDetail.setType(URI.create("https://transito.com/erros/campo-invalidos"));
		problemDetail.setDetail(PAGE_NOT_FOUND_LOG_CATEGORY);
		
		/* Podemos ter mais de um campo customizado pelo motivo 
		 * de termos mais de erro 
		 * 
		 * Se fosse para trabalhar com lista é necessario criar objeto*/
		
		Map<String, String> fields = 
			exception.getBindingResult().getAllErrors()//Conseguimos uma lista de todos os ObjectError/FieldError
			.stream().collect(
					Collectors.toMap( // Cria um map com chave e valor
					objectError -> ((FieldError) objectError).getObjectName(),// Retorna o nome do o objeto para ser a chave
					objectError -> messageSource.getMessage(objectError, LocaleContextHolder.getLocale())));//objectError -> objectError.getDefaultMessage()
					//retorna  LocaleContextHolder.getLocale() a localização e a lingua patrão
		
		// Cria um novo campo no problemDetail
		problemDetail.setProperty("fields", fields);
		// Passamos o problemaDetails que instanciamos;
		return handleExceptionInternal(exception, problemDetail, headers, status, request);
	}
	
	/*Customizar uma quando uma regra é quebrada*/
	@ExceptionHandler(NegotionException.class)
	public ProblemDetail capturar(NegotionException exception) {
		
		ProblemDetail problemDetail = 
				ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
		
		problemDetail.setType(URI.create("https://transito.com/erros/campo-invalidos"));
		problemDetail.setTitle(exception.getMessage());
		
		return problemDetail;
	}
	
	@ExceptionHandler(EntidadeNaoEncontrada.class)
	public ProblemDetail capturar(EntidadeNaoEncontrada exception) {
		
		ProblemDetail problemDetail =
				ProblemDetail.forStatus(HttpStatus.NOT_FOUND);
		
		problemDetail.setTitle(exception.getMessage());
		problemDetail.setType(URI.create(PAGE_NOT_FOUND_LOG_CATEGORY));
		
		return problemDetail;
	}
	
	@ExceptionHandler(ApreensaoVeiculoException.class)
	public ProblemDetail capturar(ApreensaoVeiculoException exception) {
		ProblemDetail problemDetail = 
				ProblemDetail.forStatus(HttpStatus.CONFLICT);
		
		problemDetail.setTitle(exception.getMessage());
		problemDetail.setType(URI.create("https://transito.com/erros/campo-invalidos"));
		
		return problemDetail;
	}
	
}
