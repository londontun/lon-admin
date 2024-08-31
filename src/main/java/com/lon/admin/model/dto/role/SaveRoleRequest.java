package com.lon.admin.model.dto.role;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
@Schema(description = "添加角色请求")
public class SaveRoleRequest {

    @Schema(description = "角色名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "角色名称不能为空")
    @Length(max = 16, message = "角色名称最大长度为16个字符")
    private String roleName;

    @Schema(description = "角色编码", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "角色编码不能为空")
    @Length(max = 32, message = "角色编码最大长度为32个字符")
    private String roleCode;

    @Schema(description = "角色描述")
    private String roleDesc;
}
