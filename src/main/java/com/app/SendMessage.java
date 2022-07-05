package com.app;

import com.app.model.scope.GlobalScope;
import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.Transport;
import jakarta.mail.Session;
import jakarta.mail.PasswordAuthentication;

import java.net.PortUnreachableException;
import java.util.Properties;
//final String username = "010102tranvanhieu@gmail.com";
//final String password = "xddguvuwufvskwdf";


public class SendMessage {
    private String toEmail;
    private String header = "Email từ du lịch siêu cấp vjpro";
    private String content = "<H1>Chung toi rat vui dc gap ban</H1>";

    public SendMessage(String toEmail,String content){
        this.setToEmail(toEmail);
        this.setContent(content);
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public void setToEmail(String toEmail) {
        this.toEmail = toEmail;
    }

    public String getContent() {
        return content;
    }

    public String getHeader() {
        return header;
    }

    public String getToEmail() {
        return toEmail;
    }

    public Properties setupProperties (){
        Properties prop = new Properties();
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true"); //TLS
        return  prop;
    }
    public void sendMail (Session session){
        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("010102tranvanhieu@gmail.com"));
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(this.toEmail)
            );
            message.setSubject(this.header);
            message.setContent(this.content,"text/html");
            Transport.send(message);

            System.out.println("Done");

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    public Session createSession (Properties prop){
        Session session = Session.getInstance(prop,
                new Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(GlobalScope.username_GMAIL, GlobalScope.password_GMAIL);
                    }
                });
        return  session;
    }
    public static void main(String[] args) {

//        SendMessage sendMessage = new SendMessage();
//        Properties prop = sendMessage.setupProperties();
//        Session session = sendMessage.createSession(prop);
//        sendMessage.sendMail(session);

    }

}