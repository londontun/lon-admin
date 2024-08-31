package com.lon.admin.model.vo.user;

import com.lon.admin.model.entity.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Schema(description = "用户信息")
@EqualsAndHashCode(callSuper = true)
public class UserVo extends BaseEntity {

    @Schema(description = "昵称")
    private String nickName;

    @Schema(description = "邮箱")
    private String email;

    @Schema(description = "状态")
    private Integer status;

    @Schema(description = "电话")
    private String mobile;
}

