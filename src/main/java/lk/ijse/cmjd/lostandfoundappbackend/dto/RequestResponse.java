package lk.ijse.cmjd.lostandfoundappbackend.dto;

import lk.ijse.cmjd.lostandfoundappbackend.enums.RequestStatus;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class RequestResponse {
    private Long id;
    private String description;
    private String contactInfo;
    private RequestStatus status;
    private LocalDateTime createdAt;
    private String requesterUsername;
    private String itemName;
    private Long itemId;
}
