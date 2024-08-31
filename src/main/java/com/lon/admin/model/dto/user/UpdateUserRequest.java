package com.lon.admin.model.dto.user;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "更新用户请求")
public class UpdateUserRequest {

    @Schema(description = "用户id")
    private Long id;

    @Schema(description = "昵称")
    private String nickName;

    @Schema(description = "邮箱")
    private String email;

    @Schema(description = "电话")
    private String mobile;
}
