package com.lon.admin.model.dto.user;

import com.lon.admin.model.PageDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Schema(description = "查询用户请求")
@EqualsAndHashCode(callSuper = true)
public class QueryUserRequest extends PageDto {

    @Schema(description = "昵称")
    private String nickName;

    @Schema(description = "电话")
    private String mobile;

    @Schema(description = "邮箱")
    private String email;

    @Schema(description = "状态")
    private Integer status;
}

