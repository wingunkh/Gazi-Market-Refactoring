package capstone.capstone.dto;

import capstone.capstone.domain.Location;
import capstone.capstone.domain.Post;
import lombok.Getter;

@Getter
public class PostResponse extends Post {
    String categoryName;

    String pictureUrl;

    Double marketPrice;

    Location location;

    String profileImage;

    String nickname;

    public PostResponse(Post post, String categoryName, String pictureUrl, Double marketPrice, Location location, String profileImage, String nickname) {
        this.postNum = post.getPostNum();
        this.categoryName = categoryName;
        this.model = post.getModel();
        this.postTitle = post.getPostTitle();
        this.postContent = post.getPostContent();
        this.pictureUrl = pictureUrl;
        this.grade = post.getGrade();
        this.price = post.getPrice();
        this.marketPrice = marketPrice;
        this.isCaptured = post.getIsCaptured();
        this.writtenDate = post.getWrittenDate();
        this.location = location;
        this.status = post.getStatus();
        this.userNum = post.getUserNum();
        this.profileImage = profileImage;
        this.nickname = nickname;
    }
}