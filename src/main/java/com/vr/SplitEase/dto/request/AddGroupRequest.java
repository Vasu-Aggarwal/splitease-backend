package com.vr.SplitEase.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AddGroupRequest {
    private Integer groupId;
    @NotNull(message = "Group name cannot be null")
    @NotBlank(message = "Group name cannot be empty")
    private String name;
}
