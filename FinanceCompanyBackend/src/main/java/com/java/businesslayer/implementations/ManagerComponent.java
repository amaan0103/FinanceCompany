package com.java.businesslayer.implementations;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Properties;

import com.java.businesslayer.contracts.ManagerBusinessContract;
import com.java.dataaccess.contracts.DocumentContract;
import com.java.dataaccess.contracts.CustomerContract;
import com.java.dataaccess.contracts.LoanApplicationContract;
import com.java.dataaccess.implementations.CustomerDataAccess;
import com.java.entities.Documents;
import com.java.entities.FullApplication;
import com.java.entities.LoanApplication;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class ManagerComponent<TContract,TImplementation> implements ManagerBusinessContract {
	TContract dao;
	
	
	public ManagerComponent(TContract dao) {
		this.dao = dao;
	}

	@Override
	public List<FullApplication> getAllApplications() throws Exception {
		// TODO Auto-generated method stub
		return  ((LoanApplicationContract) dao).getAllApplications();
	}

	@Override
	public boolean updataStatus(long application_number, String status) throws Exception {
		// TODO Auto-generated method stub
		LoanApplication app = ((LoanApplicationContract) dao).getApplication(application_number);
		long customerId = app.getCustomerId();
		CustomerContract cdao = new CustomerDataAccess();
		String email = (cdao.getCustomerById(customerId)).getEmail();
		System.out.println("\n\n\n"+email+"\n\n\n");
		String subject = "Regarding your Loan Application";
		String mailBody = " This email is to inform you that your application: "+application_number+" has been ";
		if(status.toLowerCase().equals("approved")) {
			mailBody+="approved !!";
			sendEmail(email, subject, mailBody);
			return ((LoanApplicationContract) dao).approve(application_number);
		}
		else {
			mailBody+=" rejected.";
			sendEmail(email, subject, mailBody);
			return ((LoanApplicationContract) dao).reject(application_number);
		}
	}

	@Override
	public List<FullApplication> getAllApplications(int sort) throws Exception {
		// TODO Auto-generated method stub
		List<FullApplication> list = ((LoanApplicationContract) dao).getAllApplications();
		Collections.sort(list, new Comparator<FullApplication>() {

			@Override
			public int compare(FullApplication o1, FullApplication o2) {
				// TODO Auto-generated method stub
				if(sort==1)	return o1.getLoanId()-o2.getLoanId();
				else if(sort==2)	return o1.getApplyDate().compareTo(o2.getApplyDate());
				else if(sort==3)	return o1.getLoanTenure()-o2.getLoanTenure();
				else	return (int) (o1.getLoanAmount()-o2.getLoanAmount());
			}
			
		});
		return list;
	}
    public void sendEmail(String to, String subject, String content) throws Exception{
        // Set up properties for the mail session
    	Properties properties = new Properties();
    	properties.put("mail.smtp.host", "smtp.office365.com"); 
    	properties.put("mail.smtp.port", "587");
    	properties.put("mail.smtp.auth", "true");
    	properties.put("mail.smtp.starttls.enable", "true");
    	properties.put("mail.smtp.ssl.trust", "smtp.office365.com");
//    	properties.put("mail.smtp.ssl.protocols", "TLSv1.2");

        
        // Create a mail session with authentication
        Session session = Session.getInstance(properties,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication("amaanandmourya@outlook.com", "OracleProject");
                    }
                });

        try {
            // Create a new MimeMessage object
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("amaanandmourya@outlook.com"));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            message.setSubject(subject);
            message.setText(content);

            // Send the message
            Transport.send(message);
            
            System.out.println("Email sent successfully.");
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
