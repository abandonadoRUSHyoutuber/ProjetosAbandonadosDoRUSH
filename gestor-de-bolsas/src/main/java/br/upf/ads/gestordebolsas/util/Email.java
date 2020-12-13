package br.upf.ads.gestordebolsas.util;

import java.util.Properties;

import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Message.RecipientType;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author Mior
 */
public final class Email {

	private static final String HOST = "smtp.gmail.com";
	private static final String PORT = "465";
	private static final String AUTH = "true";
	private static final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";

	private static final RecipientType PARA = RecipientType.TO;

	public static void send(String destinatarioEmail, String assunto, String texto) throws Throwable {

		Properties props = new Properties();
		props.put("mail.smtp.host", HOST);
		props.put("mail.smtp.socketFactory.port", PORT);
		props.put("mail.smtp.socketFactory.class", SSL_FACTORY);
		props.put("mail.smtp.auth", AUTH);
		props.put("mail.smtp.port", PORT);

		Session session = Session.getInstance(props, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("gp.recovery.mail@gmail.com", "Rec_Pass_9876");
			}
		});
		session.setDebug(true);

		Address destinatario = new InternetAddress(destinatarioEmail);
		Address remetente = new InternetAddress("gp.recovery.mail@gmail.com");

		Message message = new MimeMessage(session);
		message.setFrom(remetente);
		message.setRecipient(PARA, destinatario);
		message.setSubject(assunto);
		message.setText(texto);

		Transport.send(message);
	}

}