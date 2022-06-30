package com.duth.engapp.controller;

import com.duth.engapp.DTO.TopScoreUserDTO;
import com.duth.engapp.DTO.UserDTO;
import com.duth.engapp.JWT.JwtTokenProvider;
import com.duth.engapp.entity.CustomUserDetails;
import com.duth.engapp.entity.User;
import com.duth.engapp.model.UserModel;
import com.duth.engapp.payload.*;
import com.duth.engapp.repository.RoleRepository;
import com.duth.engapp.repository.UserRepository;
import com.duth.engapp.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController  extends RootController{
    private UserRepository userRepository;
    private UserService userService;
    private RoleRepository roleRepository;
    private AuthenticationManager authenticationManager;
    private JwtTokenProvider jwtTokenProvider;
    private Logger logger = LoggerFactory.getLogger(UserController.class);
    private JwtTokenProvider tokenProvider;
    @Autowired
    public UserController(UserRepository userRepository,
                          UserService userService,
                          RoleRepository roleRepository,
                          AuthenticationManager authenticationManager,
                          JwtTokenProvider jwtTokenProvider,
                          JwtTokenProvider tokenProvider) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.tokenProvider = tokenProvider;
        this.userService = userService;
    }

    @GetMapping("/get")
    public UserDTO get()
    {
        CustomUserDetails userDetails= (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userRepository.findUserByEmail(userDetails.getUser().getEmail());
        return new UserDTO(user);
    }
    @PostMapping("/login")
    public LoginResponse login(@Valid @RequestBody LoginRequest loginRequest) {
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =  new UsernamePasswordAuthenticationToken(
                loginRequest.getEmail(),
                loginRequest.getPassword()
        );
        Authentication authentication = authenticationManager.authenticate(
                usernamePasswordAuthenticationToken
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = tokenProvider.generateToken((CustomUserDetails) authentication.getPrincipal());
        logger.info(String.format("Login; Email: %s; Password: %s; JWT: %s", loginRequest.getEmail(), loginRequest.getPassword(), jwt));
        return new LoginResponse(jwt);
    }
    @GetMapping("/top-score")
    public ApiResponse getTopScore()
    {
        ApiResponse response = new ApiResponse(200, "Get top score user success",null );
        List<UserModel>  user = userService.getTopScore();
        if(user == null )
        {
            response.setStatus(400);
            response.setMessage("Not found user");
            return response;
        }
        response.setResult(user.stream().map(TopScoreUserDTO::new).toList());
        return response;
    }
    @PostMapping("/changepassword")
    public ApiResponse changePassword(@Valid @RequestBody PasswordChangeRequest passwordChangeRequest)
    {
        ApiResponse response = new ApiResponse(200, "Change password success",null );
        CustomUserDetails userDetails= (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        User user = userRepository.findUserByEmail(userDetails.getUser().getEmail());
        if(user == null )
        {
            response.setStatus(400);
            response.setMessage("Not found user");
            return response;
        }
        try {
            user.setPassword(encoder.encode(passwordChangeRequest.getPassword()));
            user = userRepository.save(user);
            response.setResult(new UserDTO(user));
            return response;
        }catch (Exception ex)
        {
            response.setStatus(500);
            response.setMessage("Server error");
            return response;
        }
    }
    @PostMapping("/update")
    public ApiResponse update(@Valid @RequestBody UpdateUserRequest updateUserRequest)
    {
        ApiResponse response = new ApiResponse(200, "Update success",null );
        CustomUserDetails userDetails= (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        User user = userRepository.findUserByEmail(userDetails.getUser().getEmail());
        if(user == null )
        {
            response.setStatus(400);
            response.setMessage("Not found user");
            return response;
        }
        try {
            user.setFirstname(updateUserRequest.getFirstname());
            user.setLastname(updateUserRequest.getLastname());
            if(updateUserRequest.getAvatar() != null)
                user.setAvatar(updateUserRequest.getAvatar());
            user = userRepository.save(user);
            response.setResult(new UserDTO(user));
            return response;
        }catch (Exception ex)
        {
            response.setStatus(500);
            response.setMessage("Server error");
            return response;
        }
    }
    @PostMapping("/register")
    public RegisterResponse register(@Valid @RequestBody RegisterRequest registerRequest)
    {
        logger.info(registerRequest.toString());
        User user =  userRepository.findUserByEmail(registerRequest.getEmail());
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        if(user != null || registerRequest.getEmail().isBlank() || registerRequest.getPassword().isBlank())
            return new RegisterResponse(0, "Email đã tồn tại");  // email đã tồn tại
        if(registerRequest.getEmail().isBlank() || registerRequest.getPassword().isBlank())
            return new RegisterResponse(-2, "Thiếu thông tin người dùng");  // email đã tồn tại
        try {
            user = new User();
            user.setEmail(registerRequest.getEmail());
            user.setPassword(encoder.encode(registerRequest.getPassword()));
            user.setFirstname(registerRequest.getFirstname());
            user.setLastname(registerRequest.getLastname());
            user.setRoleid(roleRepository.getById(2));
            user.setActive(true);
            user.setAvatar(registerRequest.getAvatar());
            userRepository.save(user);
            return new RegisterResponse(1, "Thêm thành công"); // email đã tồn tại
        }catch (Exception ex)
        {
            logger.error(ex.toString());
            return new RegisterResponse(-1, "Lỗi ngoài ý muốn");// email đã tồn tại
        }
    }
    @PostMapping("/random")
    public String random() {
      return "success";
    }
}
