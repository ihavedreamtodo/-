package com.hui.bookstore.utils;
import java.util.Properties;
import javax.mail.Address;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/*发送邮件的时候：
 * 向运维：为呼呼服务器Linux
 * 索要：
 *  	1、SMTP服务器地址，smtp.163.com   QQ的太麻烦了，还是用网易的把
 *  	2、发送邮件的端口号：默认25
 *  	3、发件人的账号密码
 * 
 */
public class SendJMail {
/**
 * 
 * @param email	接收人的邮箱
 * @param emailMsg  邮件信息
 * @return
 */
	static final String smtp = "smtp.163.com";		//SMTP服务器地址
	static final String from = "huihui2804@163.com"; 		// 邮件发送人的邮件地址
	static final String username = "huihui2804@163.com";  	//发件人的邮件帐户
	static final String password = "110070hui";   					//发件人的邮件密码

		public static boolean sendMail(String email, String emailMsg) {
				
		String to = email; 										// 邮件接收人的邮件地址
	
		//定义Properties对象,设置环境信息
		Properties props = System.getProperties();

		//设置邮件服务器的地址    mail.smtp.host   是域名		smtp.163.com  是邮箱服务器
		props.setProperty("mail.smtp.host",smtp); // 指定的smtp服务器
		props.setProperty("mail.smtp.auth", "true");
		props.setProperty("mail.transport.protocol", "smtp");//设置发送邮件使用的协议
		//创建Session对象,session对象表示整个邮件的环境信息
		Session session = Session.getInstance(props);
		//设置输出调试信息
		session.setDebug(true);
		try {
			//Message的实例对象表示一封电子邮件
			MimeMessage message = new MimeMessage(session);
			//设置发件人的地址
			message.setFrom(new InternetAddress(from));
			//设置主题
			message.setSubject("用户激活");
			//设置邮件的文本内容
			//message.setText("Welcome to JavaMail World!");
			message.setContent((emailMsg),"text/html;charset=utf-8");
			
			//设置附件
			//message.setDataHandler(dh);
			
			//从session的环境中获取发送邮件的对象
			Transport transport=session.getTransport();
			//连接邮件服务器
			transport.connect(smtp,25, username,password);
			//设置收件人地址,并发送消息
			transport.sendMessage(message,new Address[]{new InternetAddress(to)});
			transport.close();
			return true;
		} catch (MessagingException e) {
			e.printStackTrace();
			return false;
		}
	}
}
