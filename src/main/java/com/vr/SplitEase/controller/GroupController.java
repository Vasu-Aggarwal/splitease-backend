package com.vr.SplitEase.controller;

import com.vr.SplitEase.dto.request.AddGroupRequest;
import com.vr.SplitEase.dto.request.AddUserToGroupRequest;
import com.vr.SplitEase.dto.response.AddGroupResponse;
import com.vr.SplitEase.dto.response.AddUserToGroupResponse;
import com.vr.SplitEase.dto.response.CreateUserResponse;
import com.vr.SplitEase.dto.response.DeleteResponse;
import com.vr.SplitEase.service.EmailService;
import com.vr.SplitEase.service.GroupService;
import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("api/group")
public class GroupController {
    @Autowired
    private GroupService groupService;

    @PostMapping("/addUpdateGroup")
    public ResponseEntity<AddGroupResponse> addUpdateGroup(@RequestBody @Valid AddGroupRequest addGroupRequest){
        AddGroupResponse addGroupResponse = groupService.addUpdateGroup(addGroupRequest);
        return new ResponseEntity<>(addGroupResponse, HttpStatus.OK);
    }

    @PostMapping("/addUsersToGroup")
    public ResponseEntity<AddUserToGroupResponse> addUserToGroup(@RequestBody @Valid AddUserToGroupRequest addUserToGroupRequest) throws MessagingException {
        AddUserToGroupResponse addUserToGroupResponse = groupService.addUsersToGroup(addUserToGroupRequest);
        return new ResponseEntity<>(addUserToGroupResponse, HttpStatus.OK);
    }

    @GetMapping("/export/{groupId}")
    public ResponseEntity<InputStreamResource> exportExcel(@PathVariable Integer groupId) throws IOException {
        ByteArrayInputStream in = groupService.generateExcelForGroupTransactions(groupId);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=transactions.xlsx");

        return ResponseEntity
                .ok()
                .headers(headers)
                .contentType(MediaType.parseMediaType("application/vnd.ms-excel"))
                .body(new InputStreamResource(in));
    }

    @PostMapping("/removeUserFromGroup/{groupId}/{userUuid}")
    public ResponseEntity<DeleteResponse> removeUserFromGroup(@PathVariable Integer groupId, @PathVariable String userUuid){
        DeleteResponse deleteResponse = groupService.removeUserFromGroup(groupId, userUuid);
        return new ResponseEntity<>(deleteResponse, HttpStatus.OK);
    }

    @GetMapping("/getGroupMembers/{groupId}")
    public ResponseEntity<Set<CreateUserResponse>> getGroupMembers(@PathVariable Integer groupId){
        Set<CreateUserResponse> createUserResponses = groupService.getGroupMembers(groupId);
        return new ResponseEntity<>(createUserResponses, HttpStatus.OK);
    }

    @GetMapping("/getGroupsByUser/{userUuid}")
    public ResponseEntity<List<AddGroupResponse>> getGroupsByUser(@PathVariable String userUuid){
        List<AddGroupResponse> addGroupResponses = groupService.getGroupsByUserUuid(userUuid);
        return new ResponseEntity<>(addGroupResponses, HttpStatus.OK);
    }
}
