package com.example.library.service;

import com.example.library.entity.KozhanovAbdualimLoan;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class KozhanovAbdualimNotificationService {

    private final JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String fromEmail;

    @Async
    public void sendSimpleEmail(String to, String subject, String body) {
        log.info("[ASYNC] Sending simple email to {}. Thread: {}", to, Thread.currentThread().getName());

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(fromEmail);
        message.setTo(to);
        message.setSubject(subject);
        message.setText(body);

        try {
            mailSender.send(message);
            log.info("[ASYNC] Simple email sent to {}", to);
        } catch (Exception e) {
            log.error("[ASYNC] Failed to send email to {}: {}", to, e.getMessage());
        }
    }

    @Async
    public void sendHtmlEmail(String to, String subject, String htmlBody) {
        log.info("[ASYNC] Sending HTML email to {}. Thread: {}", to, Thread.currentThread().getName());

        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
            helper.setFrom(fromEmail);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(htmlBody, true);

            mailSender.send(message);
            log.info("[ASYNC] HTML email sent to {}", to);
        } catch (MessagingException e) {
            log.error("[ASYNC] Failed to send HTML email to {}: {}", to, e.getMessage());
        }
    }

    @Async
    public void notifyBookCreated(String adminEmail, String bookTitle) {
        String subject = "New Book Added to Library";
        String body = "A new book has been added to the library: " + bookTitle;
        sendSimpleEmail(adminEmail, subject, body);
    }

    @Async
    public void notifyBorrowConfirmation(String userEmail, String username, String bookTitle, String dueDate) {
        String subject = "Book Borrowed: " + bookTitle;
        String htmlBody = "<h2>Hello, " + username + "!</h2>" +
                "<p>You have successfully borrowed: <b>" + bookTitle + "</b></p>" +
                "<p>Please return it by: <b>" + dueDate + "</b></p>" +
                "<p>Thank you for using our library.</p>";
        sendHtmlEmail(userEmail, subject, htmlBody);
    }

    @Async
    public void notifyOverdueReminder(KozhanovAbdualimLoan loan) {
        String userEmail = loan.getUser().getEmail();
        String username = loan.getUser().getUsername();
        String bookTitle = loan.getBook().getTitle();
        String dueDate = loan.getDueDate().toString();

        String subject = "Overdue Reminder: " + bookTitle;
        String htmlBody = "<h2 style='color: red;'>Reminder for " + username + "</h2>" +
                "<p>You have an overdue book: <b>" + bookTitle + "</b></p>" +
                "<p>It was due on: <b>" + dueDate + "</b></p>" +
                "<p>Please return it as soon as possible.</p>";
        sendHtmlEmail(userEmail, subject, htmlBody);
    }
}