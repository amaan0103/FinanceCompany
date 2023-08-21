package com.java.businesslayer.implementations;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.java.businesslayer.contracts.LoanApplicationBusinessContract;
import com.java.dataaccess.contracts.CustomerContract;
import com.java.dataaccess.contracts.DocumentContract;
import com.java.dataaccess.contracts.LoanApplicationContract;
import com.java.dataaccess.implementations.CustomerDataAccess;
import com.java.dataaccess.implementations.DocumentDataAccess;
import com.java.entities.Documents;
import com.java.entities.FullApplication;
import com.java.entities.LoanApplication;

public class LoanApplicationComponent<TContract, TImplementation> implements LoanApplicationBusinessContract {

	TContract dao;
	
	public LoanApplicationComponent(TContract dao) {
		super();
		this.dao = dao;
	}

	@Override
	public boolean addApplication(FullApplication app) throws Exception {
		// TODO Auto-generated method stub
		app.setApplicationNumber(System.currentTimeMillis());
		LoanApplication loanApplication = new LoanApplication(app.getApplicationNumber(),app.getCustomerId(),app.getLoanId(),
		app.getLoanAmount(),app.getLoanStatus(),app.getApplyDate(),app.getLoanTenure(),app.getLoanEmi());
		boolean flag = ((LoanApplicationContract) dao).addApplication(loanApplication);
		if(!flag)	return false;
		Documents docs = new Documents(app.getApplicationNumber(),app.getDocuments());
		DocumentContract docdao = new DocumentDataAccess();
		flag = docdao.addDocument(docs);
		return flag;
	}

	@Override
	public List<FullApplication> getAllApplications() throws Exception {
		// TODO Auto-generated method stub
		return  ((LoanApplicationContract) dao).getAllApplications();
	}

	@Override
	public List<FullApplication> getAllApplicationsSorted(int sort) throws Exception {
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

	@Override
	public List<FullApplication> getAllApplications(long customerId) throws Exception {
		// TODO Auto-generated method stub
		return  ((LoanApplicationContract) dao).getApplicationsById(customerId);
	}

	@Override
	public boolean cancelLoan(long applicationNumber) throws Exception {
		// TODO Auto-generated method stub
		return ((LoanApplicationContract) dao).removeApplication(applicationNumber);
	}

	@Override
	public List<FullApplication> getAllApplications(long customerId, int sort) throws Exception {
		// TODO Auto-generated method stub
		List<FullApplication> list = ((LoanApplicationContract) dao).getApplicationsById(customerId);
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
		boolean flag = false;
		if(status.toLowerCase().equals("approved")) {
			flag = ((LoanApplicationContract) dao).approve(application_number);
			mailBody+="approved !!";
			sendEmail(email, subject, mailBody);
		}
		else {
			flag = ((LoanApplicationContract) dao).reject(application_number);
			mailBody+=" rejected.";
			sendEmail(email, subject, mailBody);
		}
		return flag;
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
                        return new PasswordAuthentication("amaanoracletest@outlook.com", "OracleProject");
                    }
                });

        try {
            // Create a new MimeMessage object
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("amaanoracletest@outlook.com"));
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

	@Override
	public List<FullApplication> getStatus(String status) throws Exception {
		// TODO Auto-generated method stub
		return ((LoanApplicationContract) dao).getStatus(status);
	}

}
