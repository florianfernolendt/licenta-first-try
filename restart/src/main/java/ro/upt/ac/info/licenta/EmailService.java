package ro.upt.ac.info.licenta;

import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

@Service
public class EmailService {
    JavaMailSender emailSender;

    public EmailService(JavaMailSender emailSender){
        this.emailSender = emailSender;
    }

    void trimitere(String to,String subject, String text, String atasament) throws MessagingException {

        MimeMessage mimeMessage = emailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
        mimeMessageHelper.setFrom("test.ifmflorian@gmail.com");
        mimeMessageHelper.setTo(to);
        mimeMessageHelper.setText(text);
        mimeMessageHelper.setSubject(subject);

        FileSystemResource fileSystemResource= new FileSystemResource(new File(atasament));
        mimeMessageHelper.addAttachment(fileSystemResource.getFilename(), fileSystemResource);
        emailSender.send(mimeMessage);

    }

    void respingere(String to,String subject, String text) throws MessagingException {

        MimeMessage mimeMessage = emailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
        mimeMessageHelper.setFrom("test.ifmflorian@gmail.com");
        mimeMessageHelper.setTo(to);
        mimeMessageHelper.setText(text);
        mimeMessageHelper.setSubject(subject);
        emailSender.send(mimeMessage);

    }
}
