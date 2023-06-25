package capstone.capstone.repository;

import capstone.capstone.domain.Report_list;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

public interface ReportRepository extends JpaRepository<Report_list, Integer> {
    @Query(value = "SELECT * FROM Report_list ORDER BY report_date desc", nativeQuery = true)
    List<Report_list> getAllReportList();

    @Query(value = "SELECT post_num FROM post WHERE report_num = :report_num", nativeQuery = true)
    Integer getPostNumByReportNum(@Param("report_num") Integer report_num);

    @Modifying
    @Transactional
    @Query(value = "UPDATE Post SET status = '숨김' WHERE post_num = (SELECT post_num FROM Report_list WHERE report_num = :report_num)", nativeQuery = true)
    void hideReportedPost(@Param("report_num") Integer report_num);

    @Modifying
    @Transactional
    @Query(value = "UPDATE Post SET status = '판매중' WHERE post_num = (SELECT post_num FROM Report_list WHERE report_num = :report_num)", nativeQuery = true)
    void exposureReportedPost(@Param("report_num") Integer report_num);

    @Modifying
    @Transactional
    @Query(value = "Delete FROM Post WHERE post_num = (SELECT post_num FROM Report_list WHERE report_num = :report_num)", nativeQuery = true)
    void deleteReportedPost(@Param("report_num") Integer report_num);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM Report_list WHERE post_num = (SELECT post_num FROM Report_list WHERE report_num = :report_num)", nativeQuery = true)
    void deleteReportList(@Param("report_num") Integer report_num);
}