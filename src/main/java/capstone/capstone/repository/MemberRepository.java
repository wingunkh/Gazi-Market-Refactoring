package capstone.capstone.repository;

import capstone.capstone.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface MemberRepository extends JpaRepository<Member, Integer> {
    List<Member> findAllByLatitudeBetweenAndLongitudeBetween(Double minLatitude, Double maxLatitude, Double minLongitude, Double maxLongitude);
}