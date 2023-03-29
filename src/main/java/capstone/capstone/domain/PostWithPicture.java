package capstone.capstone.domain;

import capstone.capstone.service.ModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.UrlResource;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

public class PostWithPicture extends Posts {
    String absolutePath = new File("").getAbsolutePath() + "/";
    List<UrlResource> pictureURL;
    String category_name;


    public PostWithPicture(Posts post) throws IOException {
        this.post_no = post.getPost_no();
        this.model_name = post.getModel_name();
        this.user_no = post.getUser_no();
        this.grade = post.getGrade();
        this.status = post.getStatus();
        this.price = post.getPrice();
        this.post_title = post.getPost_title();
        this.post_content = post.getPost_content();
        this.updateat = post.getUpdateat();
    }

    public void setPictureURL(List<String> picture_location) throws MalformedURLException {
        pictureURL = new ArrayList<UrlResource>();
        for(String pl : picture_location) {
            this.pictureURL.add( new UrlResource("file:" + absolutePath + pl));
        }
    }

    public void setCategory_name(String category_name){
        this.category_name = category_name;
    }

    public String getCategory_name() { return category_name; }
    public List<UrlResource> getPictureURL() {
        return pictureURL;
    }
}