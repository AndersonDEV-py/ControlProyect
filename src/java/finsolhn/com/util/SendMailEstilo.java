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
import org.apache.poi.poifs.nio.DataSource;
/**
 *
 * @author ds010106
 */
public class SendMailEstilo {
    
    public SendMailEstilo()
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

    //
    MimeMultipart multipart = new MimeMultipart("related");
    
    public void Enviar(String para,String asunto,String mensaje) throws Exception
    {
        
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
            
        ///    
        String cabecera ="<HTML><head><title>HTML Reference</title></head><BODY>"; //"<HTML><BODY><img src='cid:cidcabecera' /> <br/> <br/>";
        String pie = "<br/> <br/> <img src='cid:cidpie' /></BODY></HTML>";
        String boton = "<form method='post' target='blank' action='http://192.168.40.118:8080/ControlProyect/'> <input name='miBoton' type='submit' value='Boton' /></form>";
        String formulario = cabecera+Mensage+boton+pie;//String.format("%s%s%s%s%s", cabecera, Mensage, "<br/> <br/>",boton, pie);
            
        addContent(formulario);
 
        //añadir imagenes
       // addCID("cidcabecera", "c:/firma/rcardona.jpg");
       
       
       //addCID("cidpie","\\\\Helpdesk\\Angie\\Prueba_firmas\\firma_req.jpg");
        ///    
            
            
            
            
            

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(Username,"Gestion Requerimientos"));
            message.setRecipients(Message.RecipientType.TO,
            InternetAddress.parse(To));
            message.setSubject(Subject);
            //message.setText(Mensage);
            
            message.setContent(multipart);
            
            Transport.send(message);
            //JOptionPane.showMessageDialog(this, "Su mensaje ha sido enviado");
            System.out.println("Correo Enviado (OK)");
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "AVISO", "CORREO ENVIADO"));

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
        
        
    }
    
    

    public void addContent(String htmlText) throws Exception
    {
        // first part (the html)
        BodyPart messageBodyPart = new MimeBodyPart();
        messageBodyPart.setContent(htmlText, "text/html");
        // add it
        this.multipart.addBodyPart(messageBodyPart);
    }
/*
    public void addCID(String cidname,String pathname) throws Exception
    {
        DataSource fds = new FileDataSource(pathname);
        BodyPart messageBodyPart = new MimeBodyPart();
        messageBodyPart.setDataHandler(new DataHandler(fds));
        messageBodyPart.setHeader("Content-ID","<"+cidname+">");
        this.multipart.addBodyPart(messageBodyPart);
    }*/
    
    
    
}
