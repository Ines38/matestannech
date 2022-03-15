package in.project.restapi.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import in.project.restapi.models.Post;
import in.project.restapi.repositories.PostRepository;

@Service
public class PostService {
    @Autowired
    PostRepository postRepository;
    public ResponseEntity<?> getAll() {
        try {
            List<Post> items = new ArrayList<Post>();

            postRepository.findAll().forEach(items::add);

            if (items.isEmpty())
                 return ResponseEntity.status(HttpStatus.NO_CONTENT).body("no post is existed");
               
            return ResponseEntity.status(HttpStatus.OK).body(items);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.valueOf(400)).body(e.toString());
        }
    }
}
