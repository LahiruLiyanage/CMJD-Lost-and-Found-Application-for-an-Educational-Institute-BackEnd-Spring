package lk.ijse.cmjd.lostandfoundappbackend.dto;

import lk.ijse.cmjd.lostandfoundappbackend.enums.ItemStatus;
import lombok.Data;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

@Data
public class ItemRequest {
    @NotBlank(message = "Item name is required")
    private String name;

    private String description;
    private String category;
    private String location;
    private String imageUrl;

    @NotNull(message = "Item status is required")
    private ItemStatus status;

    private LocalDateTime dateLostFound;
}
