package com.lon.admin.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lon.admin.model.R;
import com.lon.admin.model.dto.user.QueryUserRequest;
import com.lon.admin.model.dto.user.SaveUserRequest;
import com.lon.admin.model.dto.user.UpdateUserRequest;
import com.lon.admin.model.vo.user.UserVo;
import com.lon.admin.service.IUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@Tag(name = "用户管理")
@RequestMapping("/user")
public class UserController {

    private final IUserService userService;

    public UserController(IUserService userService) {
        this.userService = userService;
    }

    @GetMapping
    @Operation(summary = "查询用户列表")
    public R<Page<UserVo>> queryUserList(@Parameter(description = "查询用户请求") QueryUserRequest request) {
        return R.ok(userService.queryUserList(request));
    }

    @GetMapping("/{userId}")
    @Operation(summary = "查询用户")
    public R<UserVo> queryUser(@PathVariable("userId") @Parameter(description = "用户id") Long userId) {
        return R.ok(userService.queryUser(userId));
    }

    @DeleteMapping
    @Operation(summary = "删除用户")
    public R<Void> deleteUser(Long userId) {
        userService.deleteUser(userId);
        return R.ok();
    }

    @PostMapping
    @Operation(summary = "新增用户")
    public R<Void> saveUser(@RequestBody @Validated SaveUserRequest request) {
        userService.saveUser(request);
        return R.ok();
    }

    @PutMapping
    @Operation(summary = "更新用户")
    public R<Void> updateUser(@RequestBody @Validated UpdateUserRequest request) {
        userService.updateUser(request);
        return R.ok();
    }
}
