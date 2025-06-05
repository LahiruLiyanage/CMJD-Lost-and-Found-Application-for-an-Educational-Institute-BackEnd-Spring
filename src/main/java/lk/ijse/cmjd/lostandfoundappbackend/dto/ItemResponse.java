package lk.ijse.cmjd.lostandfoundappbackend.dto;

import lk.ijse.cmjd.lostandfoundappbackend.enums.ItemStatus;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ItemResponse {
    private Long id;
    private String name;
    private String description;
    private String category;
    private String location;
    private String imageUrl;
    private ItemStatus status;
    private LocalDateTime dateLostFound;
    private LocalDateTime createdAt;
    private String ownerUsername;
    private Long ownerId;
}
