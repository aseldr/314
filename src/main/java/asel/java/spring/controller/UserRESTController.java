package asel.java.spring.controller;


import asel.java.spring.DTO.UserDTO;
import asel.java.spring.model.Role;
import asel.java.spring.model.User;
import asel.java.spring.repository.RoleRepository;
import asel.java.spring.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api")

public class UserRESTController {
    private final UserService userService;
    private final RoleRepository roleRepository;
    private final ModelMapper modelMapper;


    @Autowired
    public UserRESTController(UserService Userservice, ModelMapper modelMapper, RoleRepository roleRepository) {
        this.userService = Userservice;
        this.modelMapper = modelMapper;
        this.roleRepository = roleRepository;
    }

    private UserDTO convertToUserDTO(User user) {
        return modelMapper.map(user, UserDTO.class);
    }

    private User convertToUser(UserDTO userDTO) {
        return modelMapper.map(userDTO, User.class);
    }

    @GetMapping("/user")
    public ResponseEntity<UserDTO> userPage(Principal principal) {
        User user = userService.getByFirstname(principal.getName());
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return ResponseEntity.ok(convertToUserDTO(user));
        }
    }

    @GetMapping("/admin")
    public ResponseEntity<List<User>> getUserList() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/admin/{id}")
    public ResponseEntity<UserDTO> getUser(@PathVariable Long id) {
        User user = userService.getById(id);
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return ResponseEntity.ok(convertToUserDTO(user));
        }
    }

    @GetMapping("/roles")
    public ResponseEntity<List<Role>> allRoles() {
        return ResponseEntity.ok(roleRepository.findAll());
    }

    @PostMapping("/admin")
    public ResponseEntity<HttpStatus> saveUser(@RequestBody User userDTO) {
        userService.saveUser(null, userDTO);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PatchMapping("/admin/{id}")
    public ResponseEntity<HttpStatus> updateUser(@RequestBody User user, @PathVariable("id") Long id) {
        userService.saveUser(userService.getById(id), user);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @DeleteMapping("/admin/{id}")
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable Long id) {
        User user = userService.getById(id);
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        userService.deleteUser(user);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
