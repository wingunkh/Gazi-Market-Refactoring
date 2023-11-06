package capstone.capstone.extendedDomain;

import capstone.capstone.domain.Location;
import capstone.capstone.domain.Post;
import java.util.ArrayList;
import java.util.List;

public class PostWithPicture extends Post {
    String nickname;

    String category_name;

    List<String> pictureURL;

    Double fairPrice;

    Location location;

    String profile_image;

    public PostWithPicture(Post post) {
        this.postNum = post.getPostNum();
        this.modelName = post.getModelName();
        this.userNum = post.getUserNum();
        this.grade = post.getGrade();
        this.status = post.getStatus();
        this.price = post.getPrice();
        this.postTitle = post.getPostTitle();
        this.postContent = post.getPostContent();
        this.writtenDate = post.getWrittenDate();
        this.isCaptured = post.getIsCaptured();
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

    public void setFairPrice(Double fairPrice) {
        this.fairPrice = fairPrice;
    }

    public void setLocation(double latitude, double longitude) {
        this.location = new Location(latitude, longitude);
    }

    public void setProfile_image(String profile_image) {
        this.profile_image = profile_image;
    }

    public String getNickname() {
        return nickname;
    }

    public String getCategory_name() {
        return category_name;
    }

    public List<String> getPictureURL() {
        return pictureURL;
    }

    public Double getFairPrice() { return fairPrice; }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public String getProfile_image() { return profile_image; }
}