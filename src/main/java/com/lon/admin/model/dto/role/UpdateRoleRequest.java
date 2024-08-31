package com.lon.admin.model.dto.role;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Schema(description = "更新角色请求")
@EqualsAndHashCode(callSuper = true)
public class UpdateRoleRequest extends SaveRoleRequest {

    @Schema(description = "角色ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "角色ID不能为空")
    private Long id;
}

