package com.cqupt.pub.util;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

public class MailUtil {
	public static void main(String[] args) {
		send("413621484@qq.com", "测试邮件", "主题");
	}

	public static void send(String userEmail, String content, String subject) {
		SimpleEmail email = new SimpleEmail();
		email.setTLS(true);
		// 测试用
		// email.setHostName("smtp.163.com");
		// email.setAuthentication("15340528767@163.com", "yang@#123"); //
		// 上线使用
		email.setHostName("lzyj.sc.ctc.com");
		email.setAuthentication("admin@lzyj.sc.ctc.com", "1234"); //
		try {
			email.addTo(userEmail); // 接收方
			email.setFrom("lzlinzezhi@sctel.com.cn"); // 发送方
			// email.setFrom("15340528767@163.com"); // 发送方
			email.setCharset("utf-8");
			email.setSubject(subject);// 标题
			userEmail = DeEncode.encodeString(userEmail);
			email.setMsg(content + "http://133.53.9.247:8080/qdzc?email="
					+ userEmail); // 内容
			email.send();
		} catch (EmailException e) {
			e.printStackTrace();
		}
	}
}
