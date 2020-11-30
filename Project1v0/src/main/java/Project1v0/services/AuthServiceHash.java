package Project1v0.services;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Map;

import Project1v0.daos.EmployeeDao;
import Project1v0.daos.ReimbursementDao;


public class AuthServiceHash {
	
		EmployeeDao employeeDao = new EmployeeDao();
		
		private static byte[] salt = new SecureRandom().getSeed(16);

		private Map<String, String> tokenRepo = new HashMap<>();
		
		public boolean authenticateUser(String email, String credentials) {
		
			return employeeDao.checkEmployee(email, credentials) != 0;
		}

		public String createToken(String email) {
			String token = simpleHash(email);
			tokenRepo.put(token, email);
			return token;
		}

		public boolean validateToken(String token) {
			return tokenRepo.get(token) != null;
		}
		
		private String simpleHash(String email) {
			
			String hash = null;
			
			MessageDigest md;
			try {
				md = MessageDigest.getInstance("SHA-512");
				md.update(salt);
				
				byte[] bytes = md.digest(email.getBytes());

				StringBuilder sb = new StringBuilder();
				
				for (int i = 0; i < bytes.length; i++) {
					sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(0));
				}
				
				hash = sb.toString();
			} catch (NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return hash;
		}

	

}
