/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package finsolhn.com.util;

/**
 *
 * @author DS010108
 */
import java.util.Properties;
import jakarta.faces.event.AjaxBehaviorEvent;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeBodyPart;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.internet.MimeMultipart;
import jakarta.mail.BodyPart;
/**
 *
 * @author ds010106
 */
public class SendMailNormal {
    
    public SendMailNormal()
    {
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        //props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.host", "mail.finsolhn.com");
        //props.put("mail.smtp.port", "587");
        props.put("mail.smtp.port", "587");
    }
    
    public static String Username = Constantes.usr_correo;
    public static String PassWord = Constantes.pass_correo;
    
    Properties props = new Properties();
    String Mensage = "";
    String To = "";
    String Subject = "";

    public void Enviar(String para,String asunto,String mensaje) throws Exception
    {
        System.out.println("Enviando..para "+para);
        To=para;
        Subject=asunto;
        Mensage=mensaje;
        
        Session session = Session.getInstance(props,
        new jakarta.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(Username, PassWord);
            }
        });

        try {
            
      

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(Username,"Gestion Requerimientos"));
            message.setRecipients(Message.RecipientType.TO,
            InternetAddress.parse(To));
            message.setSubject(Subject);
            message.setText(Mensage);
            
            
            Transport.send(message);
            //JOptionPane.showMessageDialog(this, "Su mensaje ha sido enviado");
            System.out.println("Correo Enviado (OK)");
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "AVISO", "CORREO ENVIADO"));

        } catch (MessagingException e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "AVISO", "CORREO NO ENVIADO"));
            throw new RuntimeException(e);
        }
        
        
    }
    
    
    
}
