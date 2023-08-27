package com.example.movieApp.email;
import com.example.movieApp.appUser.AppUser;
import com.example.movieApp.entities.Booking;
import org.thymeleaf.TemplateEngine;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import org.thymeleaf.context.Context;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.List;

@Service
public class EmailService {


    private final JavaMailSender javaMailSender;
    private final TemplateEngine templateEngine;

    private final Environment environment;

    @Autowired
    public EmailService(JavaMailSender javaMailSender, TemplateEngine templateEngine, Environment environment) {
        this.javaMailSender = javaMailSender;
        this.templateEngine = templateEngine;
        this.environment = environment;
    }

    @Async
    public void send(String to, String email,String subject) {
        try {
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper helper =
                    new MimeMessageHelper(mimeMessage, "utf-8");

            String from = environment.getProperty("app.email-from");
            String fromName = environment.getProperty("app.email-from-name");

            helper.setText(email, true);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setFrom(from,fromName);
            System.out.println("here");
            javaMailSender.send(mimeMessage);
        } catch (MessagingException | UnsupportedEncodingException e) {

            throw new IllegalStateException("failed to send email");
        }
    }
    public String generateTicketPurchaseEmail(AppUser user, List<Booking> bookings, BigDecimal totalPrice,String paymentMethod) {
        Context context = new Context();
        context.setVariable("user", user);
        context.setVariable("bookings", bookings);
        context.setVariable("totalPrice", totalPrice);
        context.setVariable("paymentMethod", paymentMethod);

        return templateEngine.process("ticket-purchase-email", context);
    }
    public String generateConfirmationEmail(String link, String name) {
        Context context = new Context();
        context.setVariable("link", link);
        context.setVariable("name", name);

        return templateEngine.process("confirmation-email-template", context);
    }

    public String generateReservationExpiredEmail(String username) {
        Context context = new Context();
        context.setVariable("username", username);

        return templateEngine.process("reservation-expired-template", context);
    }

}

