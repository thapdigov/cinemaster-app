package az.turing.cinemamasterapp.controller;

import az.turing.cinemamasterapp.model.dto.request.CreateUserRequest;
import az.turing.cinemamasterapp.model.dto.request.UpdateUserRequest;
import az.turing.cinemamasterapp.model.dto.response.UserDto;
import az.turing.cinemamasterapp.service.UserService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("v1/users")
@RequiredArgsConstructor
@Valid
public class UserController {

    private final UserService userService;

    @GetMapping("/all")
    public ResponseEntity<List<UserDto>> getAll() {
        return ResponseEntity.ok(userService.findAllUser());
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<UserDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(userService.findUserById(id));
    }

    @PostMapping
    public ResponseEntity<UserDto> create(@Validated @RequestBody CreateUserRequest request) {
        return ResponseEntity.ok(userService.createUser(request));
    }

    @PutMapping("{id}")
    public ResponseEntity<UserDto> update(@PathVariable @Min(1) Long id,
                                          @Validated @RequestBody UpdateUserRequest request) {
        return ResponseEntity.ok(userService.updateUserById(id, request));
    }

    @PutMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable @Min(1) Long id) {
        userService.deleteUserById(id);
        return ResponseEntity.noContent().build();
    }
}