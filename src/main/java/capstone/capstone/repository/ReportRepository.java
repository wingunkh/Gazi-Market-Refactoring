package capstone.capstone.repository;

import capstone.capstone.domain.Report_list;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

public interface ReportRepository extends JpaRepository<Report_list, Integer> {
    // 해당 신고 게시글 숨김 처리
    @Modifying
    @Transactional
    @Query(value = "UPDATE Post SET status = '숨김' WHERE post_num = (SELECT post_num FROM report_list WHERE report_num = :report_num)", nativeQuery = true)
    void hideReportedPost(@Param("report_num") Integer report_num);

    // 해당 숨김 처리된 게시글 공개 처리
    @Modifying
    @Transactional
    @Query(value = "UPDATE Post SET status = '판매중' WHERE post_num = (SELECT post_num FROM report_list WHERE report_num = :report_num)", nativeQuery = true)
    void exposureReportedPost(@Param("report_num") Integer report_num);

    // 해당 신고 게시글 삭제 처리
    @Modifying
    @Transactional
    @Query(value = "Delete from Post WHERE post_num = (SELECT post_num FROM report_list WHERE report_num = :report_num)", nativeQuery = true)
    void deleteReportedPost(@Param("report_num") Integer report_num);

    // 해당 신고 게시글 번호 리턴
    @Query(value = "SELECT post_num FROM post WHERE report_num = :report_num", nativeQuery = true)
    Integer getPostNoByReportNum(@Param("report_num") Integer report_num);

    // 신고 목록 데이터 리턴
    @Query(value="select * from report_list", nativeQuery = true)
    List<Report_list> getAllReportList();

    // 신고 기록 삭제 처리
    @Modifying
    @Transactional
    @Query(value = "delete from report_list WHERE report_num = :report_num", nativeQuery = true)
    void deleteReportList(@Param("report_num") Integer report_num);
}
