package lk.ijse.cmjd.lostandfoundappbackend.repository;

import lk.ijse.cmjd.lostandfoundappbackend.entity.Item;
import lk.ijse.cmjd.lostandfoundappbackend.enums.ItemStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
    List<Item> findByStatus(ItemStatus status);
    List<Item> findByUserId(Long userId);
    List<Item> findByCategory(String category);

    @Query("SELECT i FROM Item i WHERE i.name LIKE %:keyword% OR i.description LIKE %:keyword%")
    List<Item> findByKeyword(@Param("keyword") String keyword);

    @Query("SELECT i FROM Item i WHERE i.status = :status AND (i.name LIKE %:keyword% OR i.description LIKE %:keyword%)")
    List<Item> findByStatusAndKeyword(@Param("status") ItemStatus status, @Param("keyword") String keyword);
}