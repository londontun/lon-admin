package com.lon.admin.model.dto.role;

import com.lon.admin.model.PageDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Schema(description = "查询角色请求")
@EqualsAndHashCode(callSuper = true)
public class QueryRoleRequest extends PageDto {

    @Schema(description = "角色名称")
    private String roleName;

    @Schema(description = "角色状态 0正常 1冻结")
    private Integer status;
}
