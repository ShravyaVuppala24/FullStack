package org.example;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestBody;


import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*") // Allow requests from all origins with all headers
public class GetUser {
    @GetMapping({"/GetEmail"})
    public String Userdetails(@RequestParam String email) {

        return hibernatejson.details(email);
    }

    @PostMapping({"/Register"})
    public String registerUser(@RequestBody UserInfo userInfo) {
        String s = hibernatejson.registerUser(userInfo);
        return "{\"status\":\"" + s + "\"}";

    }

    @PutMapping({"/CheckRegister"})
    public String checkAndRegister(@RequestBody UserInfo userInfo) {

        boolean op = hibernatejson.userExists(userInfo.getEmail());
        if(op)
        {
            return "{\"status\":\"User already exists\"}";
        }
        else {

            String s = hibernatejson.registerUser(userInfo);
            return "{\"status\":\"" + s + "\"}";

        }
    }

    @PatchMapping({"/update"})
    public String updateUser(@RequestBody UserInfo userInfo) {
        String s = hibernatejson.updateUser(userInfo);
        return "{\"status\":\"" + s + "\"}";

    }

    @DeleteMapping({"/delete"})
    public String deleteUser(@RequestParam String email) {
        String s = hibernatejson.deleteUser(email);
        return "{\"status\":\"" + s + "\"}";

    }
}

