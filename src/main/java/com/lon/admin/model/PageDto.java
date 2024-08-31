package com.lon.admin.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * 分页参数
 */
@Data
@Schema(description = "分页参数")
public class PageDto {

    @NotNull(message = "页数不能为空")
    @Schema(description = "第几页", requiredMode = Schema.RequiredMode.REQUIRED, defaultValue = "1")
    private int pageNum;

    @NotNull(message = "每页条数不能为空")
    @Schema(description = "每页条数", requiredMode = Schema.RequiredMode.REQUIRED, defaultValue = "10")
    private int pageSize;
}
