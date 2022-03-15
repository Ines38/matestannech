package in.project.restapi.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import in.project.restapi.models.Post;



@Repository
public interface PostRepository extends MongoRepository<Post, Long> {
  
  public  List<Post> findByPostAdress(String postAdress);
  public List<Post> findByPostId(Long postId);
  


}



/////////////////////////

/*@GetMapping("/login/{username}/{username}")
public ResponseEntity<?> login( @PathVariable("username") String username,@PathVariable("username") String userPassword ) {
  try {
    System.out.println("\n\n --------------------------work------------------------------------------- \n\n");
    UserClientModel opuser = userClientRepository.findByUsername(username);
    if(opuser==null)
      return new ResponseEntity<>(null,HttpStatus.NOT_FOUND) ;
      String opuserPassword=opuser.getUserPassword();
    if(!(opuserPassword==userPassword))
    return new ResponseEntity<>(null,HttpStatus.FORBIDDEN);
    
    return new ResponseEntity<>(opuser,HttpStatus.OK);
    
  } catch (Exception e) {
    return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
  }
}*/