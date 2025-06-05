package lk.ijse.cmjd.lostandfoundappbackend.dto;

import lombok.Data;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Data
public class ClaimRequest {
    @NotNull(message = "Item ID is required")
    private Long itemId;

    @NotBlank(message = "Description is required")
    private String description;

    @NotBlank(message = "Contact information is required")
    private String contactInfo;
}
