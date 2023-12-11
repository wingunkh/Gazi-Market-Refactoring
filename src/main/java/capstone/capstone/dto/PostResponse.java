package capstone.capstone.dto;

import capstone.capstone.domain.Post;
import lombok.Getter;

@Getter
public class PostResponse extends Post {
    private final String categoryName;

    private final String pictureUrl;

    private final Double marketPrice;

    private final Location location;

    private final String profileImage;

    private final String nickname;

    public PostResponse(Post post, String categoryName, String pictureUrl, Double marketPrice, Location location, String profileImage, String nickname) {
        super(
                post.getPostNum(),
                post.getModel(),
                post.getPostTitle(),
                post.getPostContent(),
                post.getIsCaptured(),
                post.getGrade(),
                post.getPrice(),
                post.getWrittenDate(),
                post.getStatus(),
                post.getMember()
        );

        this.categoryName = categoryName;
        this.pictureUrl = pictureUrl;
        this.marketPrice = marketPrice;
        this.location = location;
        this.profileImage = profileImage;
        this.nickname = nickname;
    }
}