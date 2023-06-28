package com.work.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


import com.work.model.Admin;
import com.work.service.AdminService;
import com.work.utils.ServiceResponse;

@RestController
public class AdminController {
	
	@Autowired
	private AdminService adminService;
	
	@PostMapping("/registeradmin")
	@CrossOrigin(origins = "http://localhost:4200")
	public ServiceResponse registerAdmin(@RequestBody Admin admin) {
		ServiceResponse response = new ServiceResponse();
		try {
			response = adminService.fetchAdminByEmailId(admin);
		}catch  (Exception e) {
			e.printStackTrace();
			response.setServiceStatus("Fail");	
		}
		return response;
		
	}
	
	@PostMapping("/login")
	@CrossOrigin(origins = "http://localhost:4200")
	public ServiceResponse loginAdmin(@RequestBody Admin admin)throws Exception {
		String tempEmailId = admin.getEmailId();
		String tempPass = admin.getPassword();
		
		ServiceResponse response = null;
		
		if(tempEmailId != null && tempPass != null) {
			response = adminService.fetchAdminByEmailAndPassword(tempEmailId, tempPass);
			
		}
		if(response == null) {
			return null;
		}
		return response;
	}
	
	 @PostMapping("/verify-otp")
	 @CrossOrigin(origins = "http://localhost:4200")
	  public ServiceResponse verifyOtp(@RequestBody Admin admin) {
	      return adminService.generateOtp(admin);
	  }
	 
	 @GetMapping("/admin")
	 @CrossOrigin(origins = "http://localhost:4200")
	 public ServiceResponse getAllEntries() {	
		 ServiceResponse response = adminService.getAllEntries();	 
		 return response;
	}
	 
	 
	 @GetMapping("/employee/{id}")
	 @CrossOrigin(origins = "http://localhost:4200")
	 public ServiceResponse getEmployeeById(@PathVariable((String) "id") int id) {
		 ServiceResponse response = adminService.getEmployeeById(id);
		 return response;
		}
	 
	 @DeleteMapping("/deleteuser/{id}")
	 @CrossOrigin(origins = "http://localhost:4200")
	 public ServiceResponse deleteEntriesById(@PathVariable((String) "id") int id){
		 ServiceResponse response = adminService.deleteEntriesById(id);
		 return response;
		}
	 @PutMapping("/updatedata/{id}")
	 @CrossOrigin(origins = "http://localhost:4200")
	 public Admin updateEntries(@PathVariable int id ,@RequestBody Admin admin) {
		 
		 return adminService.updateEntries(admin);
	 }

}
