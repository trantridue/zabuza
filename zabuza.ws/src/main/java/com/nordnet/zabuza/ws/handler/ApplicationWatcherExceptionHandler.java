package com.nordnet.zabuza.ws.handler;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.NOT_FOUND;

import java.net.URISyntaxException;

import javax.servlet.http.HttpServletRequest;

import nordnet.tools.converter.exceptions.ConverterException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.nordnet.common.alert.ws.client.SendAlert;
import com.nordnet.common.valueObject.exception.InfoErreur;
import com.nordnet.common.valueObject.exception.NotFoundArgumentException;
import com.nordnet.common.valueObject.exception.NullArgumentException;

@ControllerAdvice
public class ApplicationWatcherExceptionHandler {

	/** {@link SendAlert} sendAlert. */
	@Autowired
	private SendAlert sendAlert;

	private void sendAlert(final HttpServletRequest request, final Throwable exception) {
		sendAlert.send("Unable to perform request from : " + request.getRemoteAddr(), "error occurs during call of "
				+ request.getRequestURI(), exception);
	}

	@ResponseStatus(value = NOT_FOUND)
	@ExceptionHandler({ NotFoundArgumentException.class })
	@ResponseBody
	InfoErreur handleNotFoundArgumentException(final HttpServletRequest request,
			final NotFoundArgumentException exception) {
		// call the alert service
		sendAlert(request, exception.getThrowable());

		return InfoErreur.builder().url(request.getRequestURI()).errorCode("0.1.2")
				.errorMessage(exception.getLocalizedMessage()).build();
	}

	@ResponseStatus(value = BAD_REQUEST)
	@ExceptionHandler({ NullArgumentException.class })
	@ResponseBody
	InfoErreur handleNullArgumentException(final HttpServletRequest req, final NullArgumentException ex) {
		// call the alert service
		sendAlert(req, ex.getThrowable());

		return InfoErreur.builder().url(req.getRequestURI()).errorCode("0.1.4").errorMessage(ex.getLocalizedMessage())
				.build();
	}

	@ResponseStatus(value = BAD_REQUEST)
	@ExceptionHandler({ ConverterException.class })
	@ResponseBody
	InfoErreur handleOpaleException(final HttpServletRequest req, final ConverterException ex) {
		// call the alert service
		sendAlert(req, ex);

		// if cause error have personalised handler
		if (ex.getCause() instanceof NullArgumentException) {
			return handleNullArgumentException(req, (NullArgumentException) ex.getCause());
		}
		if (ex.getCause() instanceof NotFoundArgumentException) {
			return handleNotFoundArgumentException(req, (NotFoundArgumentException) ex.getCause());
		}

		return InfoErreur.builder().url(req.getRequestURI()).errorCode("0.15")
				.errorMessage(ex.getLocalizedMessage() + ": " + ex.getCause().getLocalizedMessage()).build();
	}

	/**
	 *
	 * Gerer le cas ou on a une {@link Exception}.
	 *
	 * @param req
	 *            requete HttpServletRequest.
	 * @param ex
	 *            exception
	 * @return {@link InfoErreur}
	 * @throws URISyntaxException
	 */
	@ResponseStatus(value = INTERNAL_SERVER_ERROR)
	@ExceptionHandler({ Exception.class })
	@ResponseBody
	InfoErreur handleException(final HttpServletRequest req, final Exception ex) {
		// call the alert service
		sendAlert(req, ex);

		return InfoErreur.builder().url(req.getRequestURI()).errorCode("0.3")
				.errorMessage("erreur technique non catché").build();
	}

}
