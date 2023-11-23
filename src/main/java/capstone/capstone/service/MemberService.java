package capstone.capstone.service;

import capstone.capstone.domain.Member;
import capstone.capstone.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    private final FileHandler fileHandler;

    public Member save(Member member) {
        return memberRepository.save(member);
    }

    public List<Member> findAll() {
        return memberRepository.findAll();
    }

    public Member findById(Integer memberNum) {
        Optional<Member> optionalMember = memberRepository.findById(memberNum);

        if (optionalMember.isPresent())
            return optionalMember.get();
        else
            throw new IllegalArgumentException("해당 사용자가 존재하지 않습니다.");
    }

    public String getProfileImage(Integer memberNum) {
        Optional<Member> optionalMember = memberRepository.findById(memberNum);

        if (optionalMember.isPresent()) {
            Member member = optionalMember.get();

            return (member.getProfileImage() == null) ? "https://gazi-market-bucket.s3.ap-northeast-2.amazonaws.com/profile/default.png" : member.getProfileImage();
        } else
            throw new IllegalArgumentException("해당 사용자가 존재하지 않습니다.");
    }

    @Transactional
    // 트랜잭션 적용
    // 트랜잭션이란? 데이터베이스의 상태를 변화시키기 위해 수행하는 작업의 단위
    // 트랜잭션의 특징 (ACID)
    // 원자성 (Atomicity) : 트랜잭션이 DB에 모두 반영되거나, 혹은 전혀 반영되지 않아야 한다.
    // 일관성 (Consistency) : 트랜잭션의 작업 처리 결과는 항상 일관성 있어야 한다.
    // 독립성 (Isolation) : 둘 이상의 트랜잭션이 동시에 병행 실행되고 있을 때, 타 트랜잭션에 끼어들 수 없다.
    // 지속성 (Durability) : 트랜잭션이 성공적으로 완료되었으면, 결과는 영구적으로 반영되어야 한다.
    public String updateProfileImage(Integer memberNum, MultipartFile file) throws Exception {
        Optional<Member> optionalMember = memberRepository.findById(memberNum);

        if (optionalMember.isPresent()) {
            Member member = optionalMember.get();

            // Amazon S3에 전달받은 사진을 업로드하고 해당 사진의 Url을 반환받아 변수에 저장
            String pictureUrl = fileHandler.saveToS3(file, "profile/");

            member.setMemberNum(memberNum);
            member.setProfileImage(pictureUrl);

            return "해당 사용자의 프로필 이미지 수정 완료";

            // memberRepository.save(member);
            // 영속성 컨텍스트의 Dirty Checking 기능을 통해 변경된 엔티티의 상태가 감지된다.
            // 그 후 트랜잭션 커밋 시 해당 변경 사항이 데이터베이스에 반영되어 업데이트가 자동으로 수행된다.
        } else
            throw new IllegalArgumentException("해당 사용자가 존재하지 않습니다.");
    }
}