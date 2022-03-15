package in.project.restapi.controllers;

import java.io.Console;
import java.util.List;
import java.util.Optional;
import java.util.*;

import com.google.gson.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.project.restapi.models.UserClientModel;
import in.project.restapi.repositories.UserClientRepository;
import in.project.restapi.service.UserClientService;


@CrossOrigin(origins = "*")

@RestController
@RequestMapping("/user")
public class UserClientController {

	@Autowired
	UserClientRepository userClientRepository;
	
	@Autowired
	UserClientService seqGeneratorService;

	////---------------------------------create user---------------
	
	@PostMapping("/create")
	public ResponseEntity<?> create(@RequestBody  UserClientModel obj) {

		System.out.print("/n/n"+obj+"/n/n")	;	
		UserClientModel userClientModel=userClientRepository.findByUsername(obj.getUsername()).get();
		System.out.print("/n/n"+userClientModel+"/n/n")	;	
		if(userClientModel==null)//findOne(new Query(where("username").is(userClientModel.getUsername())), UserClientModel.class)){
		{try{
            
			userClientModel.setUsername(obj.getUsername());
			userClientModel.setUserPassword(obj.getUserPassword());
			userClientModel.setUserId(userClientRepository.count());
			userClientModel.setAdmin(false);
			userClientModel.setSupervisor(false);
			UserClientModel userClient=userClientRepository.save(userClientModel);
		    return ResponseEntity.status(HttpStatus.valueOf(200)).body(userClient);
	    }catch(Exception e){
		return ResponseEntity.status(HttpStatus.valueOf(500)).body(e.toString());
	    }}
        else{
			return ResponseEntity.status(HttpStatus.valueOf(200)).body(null);
		}
	}


		@PostMapping("/creating")
		public ResponseEntity<?> createuser(@RequestBody UserClientModel item) {
			
			Boolean exist = userClientRepository.existsByUsername(item.getUsername());
			if(exist)
				return ResponseEntity.status(HttpStatus.valueOf(401)).body("username allready exist");
			try {
				UserClientModel userClientModel=new UserClientModel();
					userClientModel.setUsername(item.getUsername());
					userClientModel.setUserPassword(item.getUserPassword());
					userClientModel.setUserId(userClientRepository.count());
					userClientModel.setAdmin(false);
					userClientModel.setSupervisor(false);
					UserClientModel userClient=userClientRepository.save(userClientModel);
					return ResponseEntity.status(HttpStatus.valueOf(200)).body(userClient);

			} catch (Exception e) {
				return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
			}
		}
	
	
	//------------------------Get list of users-------------------
	@CrossOrigin(origins = "*")
	@GetMapping("/read")
	public ResponseEntity<?>/*List<UserClientModel>*/ read(){
			try{
				List<UserClientModel> userList=userClientRepository.findAll();
				return ResponseEntity.status(HttpStatus.valueOf(200)).body(userList);
			}catch(Exception e){
				return ResponseEntity.status(HttpStatus.valueOf(400)).body(e.toString());
			}
		
	}
	//--------------------------exemple--------
	@GetMapping("/log/{username}/{userPassword}")
	public ResponseEntity<?> log( @PathVariable("username") String username,@PathVariable("userPassword") String userPassword ) 
	{
		try {
			System.out.println("\n\n --------------------------work------------------------------------------- \n\n");
			UserClientModel opuser = userClientRepository.findByUsername(username);
			if(opuser==null)
				return new ResponseEntity<>(null,HttpStatus.NOT_FOUND) ;
				String opuserPassword=opuser.getUserPassword();
			if(!(opuserPassword.toString()==userPassword.toString())){
			    return new ResponseEntity<>(opuserPassword,HttpStatus.FORBIDDEN);}
			
			return new ResponseEntity<>(opuser,HttpStatus.OK);
			
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
		}
	}
	
	//------------- Get a user by its id--------------

		@PostMapping("/login")
		public ResponseEntity<?> login(@RequestBody UserClientModel item) {
			try {
				System.out.println("\n\n --------------------------work------------------------------------------- \n\n");
				UserClientModel opuser = userClientRepository.findByUsername(item.getUsername());
				if(opuser==null)
					return new ResponseEntity<>(null,HttpStatus.NOT_FOUND) ;
					String itemPassword=opuser.getUserPassword();
					String opuserPassword=opuser.getUserPassword();
				if(!(opuserPassword==itemPassword))
				return new ResponseEntity<>(null,HttpStatus.FORBIDDEN);
				
				return new ResponseEntity<>(opuser,HttpStatus.OK);
				
			} catch (Exception e) {
				return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
			}
		}
	


	
	///------------------------------------is admin--------------

	@PostMapping("loging")
	public ResponseEntity<?> getById(@RequestBody UserClientModel item) {
	UserClientModel user = userClientRepository.findByUsername(item.getUsername());
     System.out.println("\n\nhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhh\n");
	 System.out.print(user);
	 System.out.println("\n\nhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhh\n");
		if (user==null) 
			return ResponseEntity.status(HttpStatus.valueOf(403)).body(null);
		
		return ResponseEntity.status(HttpStatus.valueOf(200)).body(user);
		
	}


	//----------------Update a user------------

	@PutMapping("/update")
	public UserClientModel update(@RequestBody UserClientModel modifiedEmployeeObject) {
		return userClientRepository.save(modifiedEmployeeObject);
	}

	//----------------Delete user by its id--------------------

	@DeleteMapping("/delete/{id}")
	public String delete(@PathVariable Long id) {
		Optional<UserClientModel> employeeObj = userClientRepository.findById(id);
		if(employeeObj.isPresent()) {
			userClientRepository.delete(employeeObj.get());
			return "Employee deleted with id "+id;
		}else {
			throw new RuntimeException("User not found for id "+id);
		}
	}
	
	///------------------Difine a user as a supervisor---------------

	@PutMapping("/addsupervisor/{adminId}/{userId}")
	public ResponseEntity<?> update(@PathVariable("adminId") long adminId,@PathVariable("userId") long userId) {
	   
	   
	   
		try{
			UserClientModel admin =userClientRepository.findById(adminId).get();
			UserClientModel supervisor = userClientRepository.findById(userId).get();
			
			if(!admin.isAdmin())
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("you are not allowed");  
			
			if(supervisor==null)
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("user dosesn't exist ");
			
			try{  
				supervisor.setSupervisor(true);
				UserClientModel savedSupervisor=userClientRepository.save(supervisor);
				return ResponseEntity.status(HttpStatus.OK).body(savedSupervisor);

			}catch(Exception e){
						
				return ResponseEntity.status(HttpStatus.valueOf(400)).body(e.toString());
			}

		}catch(Exception e){
			  
			return ResponseEntity.status(HttpStatus.valueOf(400)).body(e.toString());
		}
	}


//------------------------------------add ticket------------

			@PutMapping("addTicket/{userId}/{ticket}")
			public ResponseEntity<?> addTicket (@PathVariable("userId") long userId,@PathVariable("ticket") long ticket) {
				
				try{

					Optional<UserClientModel> user = userClientRepository.findById(userId);
					if(user.get()==null)
						return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body("you are not a user");
					UserClientModel userClient= user.get();
					userClient.setTicket(ticket);
					userClientRepository.save(userClient);
					return ResponseEntity.status(HttpStatus.OK).body("your ticket");
				}catch(Exception e){
                            
                    return ResponseEntity.status(HttpStatus.valueOf(400)).body(e.toString());
                }		
			}
		

	
}

