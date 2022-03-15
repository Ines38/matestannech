package in.project.restapi.models;

import java.util.*;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("Post")
public class Post {
    public static final String SEQUENCE_NAME = "employees_sequence";
    @Id
    private Long postId;
    @Indexed(unique=true)
    private String postName;
    private String gouvernement ;
    @Indexed(unique=true)
    private String postAdress;
    private long currentNumber;
    private Long lastNumber;
    private List<Long> supervisor;


    public Post() {
        this.supervisor = new ArrayList<>();
    }

    public Post(Long postId, String postName, String postAdress, long currentNumber, Long lastNumber, List<Long> supervisor,String gouvernement) {
        this.postId = postId;
        this.postName = postName;
    
        this.postAdress = postAdress;
        this.currentNumber = currentNumber;
        this.lastNumber = lastNumber;
        this.supervisor = new ArrayList<>();
        this.gouvernement= gouvernement;
    }

    public long getCurrentNumber() {
        return this.currentNumber;
    }

    public Post(Long postId, String postName, String gouvernement, String postAdress, long currentNumber, Long lastNumber, List<Long> supervisor) {
        this.postId = postId;
        this.postName = postName;
        this.gouvernement = gouvernement;
        this.postAdress = postAdress;
        this.currentNumber = currentNumber;
        this.lastNumber = lastNumber;
        this.supervisor = supervisor;
    }

    public String getGouvernement() {
        return this.gouvernement;
    }

    public void setGouvernement(String gouvernement) {
        this.gouvernement = gouvernement;
    }

    public Post gouvernement(String gouvernement) {
        setGouvernement(gouvernement);
        return this;
    }

    public void setCurrentNumber(long currentNumber) {
        this.currentNumber = currentNumber;
    }

    public Long getLastNumber() {
        return this.lastNumber;
    }

    public void setLastNumber(Long lastNumber) {
        this.lastNumber = lastNumber;
    }

    public List<Long> getSupervisor() {
        return this.supervisor;
    }

    public void setSupervisor(long supervisorId) {
        this.supervisor.add(supervisorId);
    }

    public Post currentNumber(long currentNumber) {
        setCurrentNumber(currentNumber);
        return this;
    }

    public Post lastNumber(Long lastNumber) {
        setLastNumber(lastNumber);
        return this;
    }

    public Post supervisor(long supervisorId) {
        setSupervisor(supervisorId);
        return this;
    }

   

    public Long getPostId() {
        return this.postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }

    public String getPostName() {
        return this.postName;
    }

    public void setPostName(String postName) {
        this.postName = postName;
    }

    public String getPostAdress() {
        return this.postAdress;
    }

    public void setPostAdress(String postAdres) {
        this.postAdress = postAdres;
    }

    public Post postId(Long postId) {
        setPostId(postId);
        return this;
    }

    public Post postName(String postName) {
        setPostName(postName);
        return this;
    }
    
    public Post postAdress(String postAdres) {
        setPostAdress(postAdres);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Post)) {
            return false;
        }
        Post post = (Post) o;
        return Objects.equals(postId, post.postId) && Objects.equals(postName, post.postName) && Objects.equals(postAdress, post.postAdress);
    }

    @Override
    public int hashCode() {
        return Objects.hash(postId, postName, postAdress);
    }

    @Override
    public String toString() {
        return "{" +
            " postId='" + getPostId() + "'" +
            ", postName='" + getPostName() + "'" +
            ", postAdress='" + getPostAdress() + "'" +
            "}";
    }
   
   
}
