package com.vr.SplitEase.controller;

import com.vr.SplitEase.dto.request.CreateUserRequest;
import com.vr.SplitEase.dto.response.CreateUserResponse;
import com.vr.SplitEase.dto.response.DeleteResponse;
import com.vr.SplitEase.dto.response.FriendsListResponse;
import com.vr.SplitEase.dto.response.GetTotalNetBalance;
import com.vr.SplitEase.service.UserService;
import jakarta.validation.Valid;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/addUpdateUser")
    public ResponseEntity<CreateUserResponse> addUpdateUser(@RequestBody @Valid CreateUserRequest createUserRequest){
        CreateUserResponse createUserResponse = userService.addUpdateUser(createUserRequest);
        return new ResponseEntity<>(createUserResponse, HttpStatus.OK);
    }

    @DeleteMapping("/deleteUser/{userUuid}")
    public ResponseEntity<DeleteResponse> deleteUser(@PathVariable String userUuid){
        userService.deleteUser(userUuid);
        return new ResponseEntity<>(new DeleteResponse("User deleted"), HttpStatus.OK);
    }

    @GetMapping("/getUserByUuid/{userUuid}")
    public ResponseEntity<CreateUserResponse> getUserByUuid(@PathVariable String userUuid){
        CreateUserResponse createUserResponse = userService.getUserByUuid(userUuid);
        return new ResponseEntity<>(createUserResponse, HttpStatus.OK);
    }

    @GetMapping("/getOverallUserBalance/{userUuid}")
    public ResponseEntity<GetTotalNetBalance> getTotalNetBalanceResponseEntity(@PathVariable String userUuid){
        GetTotalNetBalance getTotalNetBalance = userService.getTotalNetBalanceByUserUuid(userUuid);
        return ResponseEntity.ok(getTotalNetBalance);
    }

    @GetMapping("/userFriendsList/{userUuid}")
    public ResponseEntity<List<FriendsListResponse>> userFriendsList(@PathVariable String userUuid){
        List<FriendsListResponse> friendsListResponses = userService.userFriendsList(userUuid);
        return ResponseEntity.ok(friendsListResponses);
    }

}
