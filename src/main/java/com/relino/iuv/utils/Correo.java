/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.relino.iuv.utils;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

/**
 *
 * @author Ivan
 */
public class Correo {
	
	/**
	 *  Método para enviar un correo electrónico
	 * @param correoDestino	Correo al que será enviado el email
	 * @param correoCC		Correo indicado como copia para
	 * @param contenido		Se refiere al cuerpo del email
	 * @param asunto			Se refiere a la descripción breve del email
	 * @return 
	 */
	public boolean enviarCorreo(String correoDestino, String correoCC, String contenido, String asunto) {
		boolean envio = false;
		final String username = "scgd.dgdii@gmail.com";//jan_9983@hotmail.com
		final String password = "scgd_DGDII1";//
		final String host = "smtp.gmail.com";//smtp.office365.com
		final String port = "587";//
		Session ses;

		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.transport.protocol", "smtp");
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.port", port);
		try {
			ses = Session.getInstance(props, new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(username, password);
				}
			});
			ses.setDebug(true);
			MimeMessage mail = new MimeMessage(ses);
			mail.setSubject(asunto);
			mail.setFrom(new InternetAddress(username));
			mail.addRecipients(Message.RecipientType.TO, InternetAddress.parse(correoDestino));
			if (!correoCC.trim().equals("")) {
				mail.setRecipients(Message.RecipientType.CC, InternetAddress.parse(correoCC));
			}
			//msg.setText(detalle);
			Multipart mp = new MimeMultipart("related");
			MimeBodyPart messageBodyPart = new MimeBodyPart();
			messageBodyPart.setContent(contenido, "text/html");
			
			mp.addBodyPart(messageBodyPart);
			mail.setContent(mp, "text/html");
			Transport.send(mail);
			//System.out.println("ENVIADO");
			envio = true;
		} catch (MessagingException e) {
			envio = false;
			//System.out.println("\n\n***** Excepcion envioCorreo: " + e.getMessage()+"\n\n");
		}   finally {
			
		}
		return envio;
	}
	/**
	 * Rutina creada por Pack
	 * http://10.10.20.25:9000/notificador/enviar
	 * @param correoDestino
	 * @param asunto
	 * @param contenido
	 * El método devuelve verdadero si el correo fue enviado correctamente
	 */
	public boolean sendPostMail(String correoDestino, String asunto, String contenido)  {
		boolean envio = false;
		try {
			java.net.URL url = new java.net.URL("http://10.10.20.25:9000/notificador/enviar");

			java.util.Map<String, Object> params = new java.util.HashMap<String, Object>();
			params.put("destinatario",correoDestino);
			params.put("titulo", asunto);
			params.put("mensaje", contenido);

			StringBuilder postData = new StringBuilder();
			for (java.util.Map.Entry<String, Object> param : params.entrySet()) {
				if (postData.length() != 0) {
					postData.append('&');
				}
				postData.append(java.net.URLEncoder.encode(param.getKey(), "UTF-8"));
				postData.append('=');
				postData.append(java.net.URLEncoder.encode(String.valueOf(param.getValue()), "UTF-8"));
			}
			byte[] postDataBytes = postData.toString().getBytes("UTF-8");
			java.net.HttpURLConnection conn = (java.net.HttpURLConnection) url.openConnection();
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			conn.setRequestProperty("Content-Length", String.valueOf(postDataBytes.length));
			conn.setDoOutput(true);
			conn.getOutputStream().write(postDataBytes);
			java.io.Reader in = new java.io.BufferedReader(new java.io.InputStreamReader(conn.getInputStream(), "UTF-8"));
			StringBuilder data = new StringBuilder();
			for (int c; (c = in.read()) >= 0;) {
				data.append((char) c);
			}
			String intentData = data.toString();
			//System.out.println(intentData);
			envio = true;
		}catch (Exception io) {
			envio = false;
		}
		return envio;
	}
}