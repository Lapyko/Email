import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;

public class SSLEmail {

    public static void main(String[] args) {
        final String fromEmail = "f0fif@yandex.ru"; //requires valid gmail id
        final String password = "qcdjrnjbszxobope"; // correct password for gmail id
        final String toEmail = "kirill.lapyko@yandex.ru"; // can be any email id

        System.out.println("Запуск электронной почты SSL");
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.yandex.ru"); //SMTP Host
        props.put("mail.smtp.socketFactory.port", "465"); //SSL Port
        props.put("mail.smtp.socketFactory.class",
                "javax.net.ssl.SSLSocketFactory"); //SSL Factory Class
        props.put("mail.smtp.auth", "true"); //Enabling SMTP Authentication
        props.put("mail.smtp.port", "465"); //SMTP Port
        Authenticator auth = new Authenticator() {
            //override the getPasswordAuthentication method
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(fromEmail, password);
            }
        };
        Session session = Session.getDefaultInstance(props, auth);
        System.out.println("Сессия создана");
        EmailUtil.sendEmail(session, toEmail,"Ваш код авторизации", "Ваш код авторизации: 123-123,\n Если вы не выполняли авторизацию, смените пароль.");
    }
}