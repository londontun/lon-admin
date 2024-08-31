package com.lon.admin.model.dto.user;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
@Schema(description = "新增用户请求")
public class SaveUserRequest {

    @Schema(description = "昵称")
    private String nickName;

    @Length(min = 6, max = 16, message = "用户名长度在6-16位字符之间")
    @NotBlank(message = "用户名不能为空")
    @Schema(description = "用户名")
    private String username;

    @Length(min = 6, max = 64, message = "密码长度在6-64位字符之间")
    @NotBlank(message = "密码不能为空")
    @Schema(description = "密码")
    private String password;

    @Schema(description = "邮箱")
    private String email;

    @Schema(description = "电话")
    private String mobile;
}
