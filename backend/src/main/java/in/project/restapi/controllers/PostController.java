package in.project.restapi.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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


import in.project.restapi.models.Post;
import in.project.restapi.models.UserClientModel;
import in.project.restapi.repositories.PostRepository;
import in.project.restapi.repositories.UserClientRepository;
import in.project.restapi.service.PostService;

@RestController
@CrossOrigin
@RequestMapping("/post")
public class PostController {

    @Autowired
    PostRepository postRepository;
    @Autowired
    UserClientRepository userClientRepository;
    @Autowired 
    private PostService postService;


    @GetMapping()
    public ResponseEntity<?> getAll() {
        return postService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Post> getById(@PathVariable("id") Long id) {
        Optional<Post> existingItemOptional = postRepository.findById(id);

        if (existingItemOptional.isPresent()) {
            return new ResponseEntity<>(existingItemOptional.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //--------------------Created Post--------------
    @PostMapping
    public ResponseEntity<?> create(@RequestBody Post item) {
        try {
            Long count = postRepository.count();
            item.setPostId(count);
            Post savedItem = postRepository.save(item);
            return  ResponseEntity.status(HttpStatus.CREATED).body(savedItem);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.valueOf(400)).body(e.toString());
        }
    }
  
    //---------------------Add supervisor-------

    @PutMapping("/addsupervisor/{adminId}/{userId}/{postId}")
		public ResponseEntity<?> update(@PathVariable("adminId") long adminId,@PathVariable("userId") long userId,@PathVariable("postId") long postId) {
           
           
           
            try{
                UserClientModel admin =userClientRepository.findById(adminId).get();
                Post post=postRepository.findById(postId).get();  
                boolean supervisor = userClientRepository.existsById(userId);
                
                if(!admin.isAdmin())
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("you are not allowed");

                if (post==null) 
                   return ResponseEntity.status(HttpStatus.NOT_FOUND).body("post doesn't exist ");    
                
                if(!supervisor)
                    return ResponseEntity.status(HttpStatus.NOT_FOUND).body("user dosesn't exist ");
                    
                if(post.getSupervisor().contains(userId))
                    return ResponseEntity.status(HttpStatus.ALREADY_REPORTED).body(post);
                    
                
                        
                try{   post.setSupervisor(userId);
                            
                    Post savedPost = postRepository.save(post);
                    return ResponseEntity.status(HttpStatus.OK).body(savedPost+" "+supervisor);

                }catch(Exception e){
                            
                    return ResponseEntity.status(HttpStatus.valueOf(400)).body(e.toString());
                }

            }catch(Exception e){
                  
                return ResponseEntity.status(HttpStatus.valueOf(400)).body(e.toString());
            }
   
        }
//----------------------------------post request --------------------------------



    ///---------------------------------Incremente currentNumber-----------------

    @PutMapping("/incr/{postId}/{superId}")
    public ResponseEntity<?> update(@PathVariable("postId") Long postId,@PathVariable("superId") Long superId) {
        try{
            Post post = postRepository.findById(postId).get();
            Boolean supervisor = post.getSupervisor().contains(superId); 
            if (post!=null && supervisor) {
                Long curNumMod=post.getCurrentNumber()+1;
                post.setCurrentNumber(curNumMod);
                postRepository.save(post);

                return ResponseEntity.status(HttpStatus.valueOf(200)).body(curNumMod);
                
            } else {
                
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("post doesn't exist or the user is not a supervisor!");

            }
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.valueOf(400)).body(e.toString());
        }
    }


    //-------------------------------Réinsialiser currentNumber ---------------
    
    @PutMapping("/reset/{postId}/{superId}")
    public ResponseEntity<?> rest(@PathVariable("postId") Long postId,@PathVariable("superId") Long superId) {
        try{
            Post post = postRepository.findById(postId).get();
            Boolean supervisor = post.getSupervisor().contains(superId); 
            if (post!=null && supervisor) {
                Long curNumMod=0L;
                post.setCurrentNumber(curNumMod);
                postRepository.save(post);

                return ResponseEntity.status(HttpStatus.valueOf(200)).body(curNumMod);
                
            } else {
                
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("post doesn't exist or the user is not a supervisor!");

            }
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.valueOf(400)).body(e.toString());
        }
    }
//-----------------------------------return 
        @GetMapping("/currentnumber/{id}")
        public ResponseEntity<Long> getById(@PathVariable("id") long id) {
            Optional<Post> existingItemOptional = postRepository.findById(id);
    
            if (existingItemOptional.isPresent()) {
                return new ResponseEntity<>(existingItemOptional.get().getCurrentNumber(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }
    


} 

   