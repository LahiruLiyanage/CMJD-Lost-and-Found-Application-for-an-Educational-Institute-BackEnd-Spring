package lk.ijse.cmjd.lostandfoundappbackend.repository;

import lk.ijse.cmjd.lostandfoundappbackend.entity.Request;
import lk.ijse.cmjd.lostandfoundappbackend.enums.RequestStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RequestRepository extends JpaRepository<Request, Long> {
    List<Request> findByStatus(RequestStatus status);
    List<Request> findByUserId(Long userId);
    List<Request> findByItemId(Long itemId);
    List<Request> findByUserIdAndStatus(Long userId, RequestStatus status);
}