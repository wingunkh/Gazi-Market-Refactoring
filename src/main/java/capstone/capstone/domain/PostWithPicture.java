package capstone.capstone.domain;

import java.util.ArrayList;
import java.util.List;

public class PostWithPicture extends Post {
    String nickname;
    String category_name;
    List<String> pictureURL;

    public PostWithPicture(Post post) {
        this.post_num = post.getPost_num();
        this.model_name = post.getModel_name();
        this.user_num = post.getUser_num();
        this.grade = post.getGrade();
        this.status = post.getStatus();
        this.price = post.getPrice();
        this.post_title = post.getPost_title();
        this.post_content = post.getPost_content();
        this.written_date = post.getWritten_date();
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setCategory_name(String category_name){
        this.category_name = category_name;
    }

    public void setPictureURL(List<String> picture_location) {
        pictureURL = new ArrayList<String>();

        for(String pl : picture_location) {
            this.pictureURL.add(pl);
        }
    }
}