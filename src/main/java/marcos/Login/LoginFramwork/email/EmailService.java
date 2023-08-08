package marcos.Login.LoginFramwork.email;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class EmailService implements EmailSender{
	private final JavaMailSender mailSender;
	
	public EmailService(JavaMailSender mailSender) {
		super();
		this.mailSender = mailSender;
	}
	private final static Logger LOGGER = LoggerFactory.getLogger(EmailService.class); 
	@Override
	@Async
	public void send(String to, String Email) {
		
		try {
			MimeMessage message = mailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(message,"UTF-8");
			helper.setText(Email,true);
			helper.setTo(to);
			helper.setSubject("Confirm your Email");
			helper.setFrom("k.hrushikesh007@gmail.com");
			mailSender.send(message);
		} catch (MessagingException e) {
			LOGGER.error("Failed to send email",e);
			throw new IllegalStateException("Failed to send email");
		}
		
	}
	

}
