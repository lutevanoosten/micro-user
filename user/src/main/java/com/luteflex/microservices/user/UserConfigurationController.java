package com.luteflex.microservices.user;
import com.luteflex.microservices.user.Models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import com.luteflex.microservices.user.UserConfiguration;

@CrossOrigin(value = "*")
@RestController
public class UserConfigurationController
{




    @PostMapping(path = "/user/login", produces = MediaType.APPLICATION_JSON_VALUE)
    public String login(@RequestBody User user) throws Exception {

        return new UserConfiguration().signIn(user);
    }

    @PostMapping(path = "/user/register", produces = MediaType.APPLICATION_JSON_VALUE)
    public String register(@RequestBody User user) throws Exception {

        return new UserConfiguration().register(user);
    }
}