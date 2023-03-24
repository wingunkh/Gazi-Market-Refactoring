package capstone.capstone.domain;

import org.springframework.core.io.UrlResource;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

public class PostWithPicture extends Posts {
    String absolutePath = new File("").getAbsolutePath() + "/";
    List<UrlResource> pictureURL;

    public PostWithPicture(Posts post) throws IOException {
        this.post_no = post.getPost_no();
        this.model_name = post.getModel_name();
        this.user_no = post.getUser_no();
        this.grade = post.getGrade();
        this.status = post.getStatus();
        this.price = post.getPrice();
        this.post_content = post.getPost_content();
        this.updateat = post.getUpdateat();
    }

    public void setPictureURL(List<String> picture_location) throws MalformedURLException {
        pictureURL = new ArrayList<UrlResource>();
        for(String pl : picture_location) {
            this.pictureURL.add( new UrlResource("file:" + absolutePath + pl));
        }
    }

    public List<UrlResource> getPictureURL() {
        return pictureURL;
    }
}