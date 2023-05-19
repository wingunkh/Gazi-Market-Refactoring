package capstone.capstone.domain;

import capstone.capstone.domain.User_Member;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.util.Pair;

import java.math.BigDecimal;


public class BigDecimalToUserMemberConverter implements Converter<Pair<BigDecimal, BigDecimal>, User_Member> {

    @Override
    public User_Member convert(Pair<BigDecimal, BigDecimal> source) {
        if (source == null) {
            return null;
        }

        User_Member userMember = new User_Member();
        BigDecimal latitude = source.getFirst();
        BigDecimal longitude = source.getSecond();

        // Set latitude and longitude properties of the User_Member object using the BigDecimal values
        userMember.setLatitude(latitude);
        userMember.setLongitude(longitude);

        return userMember;
    }
}
