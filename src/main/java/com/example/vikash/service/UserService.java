package com.example.vikash.service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.vikash.domain.AuthenticationToken;
import com.example.vikash.domain.User;
import com.example.vikash.domain.VerificationToken;
import com.example.vikash.enumdata.RoleType;
import com.example.vikash.enumdata.TokenType;
import com.example.vikash.mail.MailSenderClass;
import com.example.vikash.repository.AuthenticationTokenRepository;
import com.example.vikash.repository.UserRepository;
import com.example.vikash.repository.VerificationTokenRepository;

@Service
public class UserService {
	
	
	@Autowired
	UserRepository userRepository;

	@Autowired
	VerificationTokenRepository verificationTokenRepository;
	
	@Autowired
	MailSenderClass sender;
	
	@Autowired
	AuthenticationTokenRepository authenticationTokenRepository;

	public Map userRegister(User user) throws MessagingException {
		Map result = new HashMap();
		if (user != null) {
			User email = userRepository.findByEmail(user.getEmail());
			if (email != null) {
				result.put("Message", "User Allready Exits");
				return result;
			} else {
				user.setRoleType(RoleType.ROLE_USER);
				User data = userRepository.save(user);
				// result.put("data", data);
				String token = UUID.randomUUID().toString();
				VerificationToken verificationToken = new VerificationToken(token, data.getEmail(), data.getUserId(),TokenType.REGISTRATION);
				verificationTokenRepository.save(verificationToken);
				sender.sendEmail(data.getEmail(), "Verify User",
						"http://localhost:8080/api/v1/verify/user" + "?" + "token" + "=" + token);
				result.put("Message", "Send emil to your email please verfiy");

				// TODO Auto-generated catch block
			}
		}

		return result;
	}

	public Map verifyUser(String token) throws Exception {
		VerificationToken dataToken = verificationTokenRepository.findByTokenAndIsDeleted(token, false);
		if (dataToken != null) {
			User user = userRepository.findOne(dataToken.getUserId());
			if (user != null) {
				user.setIsEmailVerified(true);
				User veriFiedUser = userRepository.save(user);
				if (veriFiedUser != null) {
					dataToken.setIsDeleted(true);
					verificationTokenRepository.save(dataToken);
				}
				else {
					throw new Exception("Try Again!");
				}
			}
		} else {
			throw new Exception("Link Expired!");
		}

		return null;

	}
	
	public Map getUserById(Long id) throws Exception {
		Map result=new HashMap();
		User user = userRepository.findOne(id);
		result.put("data", user);

		return result;

	}
	
	public Map updateUser(User user) throws Exception {
		Map result=new HashMap();
	    User userDB = userRepository.findOne(user.getUserId());
        if(userDB!=null){
        	userDB.setFirstName(user.getFirstName());
        	userDB.setMobileNumber(user.getMobileNumber());
        	userRepository.save(userDB);
        }
		return result;

	}
	
	
	public Map getAllUser(Pageable pageable) throws Exception {
		Map result=new HashMap();
		Page<User> userList=userRepository.findByRoleType(pageable,RoleType.ROLE_USER);
		result.put("userList", userList);
		return result;

	}
	
	public Map changePassword(Map map) throws Exception {
		Map result=new HashMap();
		String currentPassword=map.get("currentPassword").toString();
		User user=userRepository.findOne(Long.parseLong(map.get("userId").toString()));
		if(currentPassword.equals(user.getPassword())){
			if(currentPassword.equals(user.getPassword())){
			String newPassword=map.get("newPassword").toString();
			String reEnterNewPassword=map.get("reEnterNewPassword").toString();
			if(newPassword.equals(reEnterNewPassword)){
				user.setPassword(newPassword);
				userRepository.save(user);
			}
			else{
				result.put("Message", "new password and reEnterNewPassword does not match");
			}
		  }
		}
		else{
			result.put("Message", "You have entered wrong password");
		}
		return result;
	}
	
	public Map forgetPassword(String email) throws MessagingException{
		Map result=new HashMap();
		User user=userRepository.findByEmailAndIsEmailVerified(email,true);
		if(user.getEmail()!=null){
			String token = UUID.randomUUID().toString();
			VerificationToken verificationToken = new VerificationToken(token, user.getEmail(), user.getUserId(),TokenType.FORGOT_PASSWORD);
			verificationTokenRepository.save(verificationToken);
			sender.sendEmail(user.getEmail(), "Verify User",
					"http://localhost:8080/api/v1/verify/forget/password" + "?" + "token" + "=" + token);
			result.put("Message", "Mail send to your mail please verfiy");

		}
		else{
			result.put("Message", "You have entered wrong emil");
		}
		
		return result;
	}
	
	public Map verifyForgetPassword(Map map,String token){
		Map result=new HashMap();
		VerificationToken dataToken = verificationTokenRepository.findByTokenAndIsDeleted(token,false);
		if(dataToken!=null){
			if(map.get("newPassword").equals(map.get("conformPassowrd"))){
				User user=userRepository.findOne(dataToken.getUserId());
				user.setPassword(map.get("newPassword").toString());
				userRepository.save(user);
				result.put("Message","Password change successfully");

			}
			else{
				result.put("Message","Password does not match");
				return result;
			}	
		}
		else{
			result.put("Message","please verify again");
		}
		return result;
	}

	public Map facebookSignUp(Map map){
		Map result=new HashMap();
		
		return result;
	}
	
	
	public Map logIn(Map map){
		Map result=new HashMap();
		User user=userRepository.findByEmailAndIsEmailVerified(map.get("email").toString(), true);
		User user1=userRepository.findByEmail(map.get("email").toString());

		if(user!=null){
			if(user.getPassword().equals(map.get("password").toString())){
				String token = UUID.randomUUID().toString();
				AuthenticationToken authToken= new AuthenticationToken();
				authToken.setToken(token);
				authToken.setUser(user);
				authenticationTokenRepository.save(authToken);
				result.put("Message", "log in Success");
				result.put("token", token);


			}
			else{
				
					result.put("Message", "please enter valid password");

			}
		}
		else{
			if(user1.getIsEmailVerified()==false)
			{
				result.put("Message", "please verify email first");

			}
			else{
			result.put("Message", "please enter valid email");
			}
		}
		
		return result;
	}
	
	

}
