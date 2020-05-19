package com.luteflex.microservices.user;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.luteflex.microservices.user.bean.UserConfiguration;
@RestController
public class UserConfigurationController
{


    @GetMapping("/lute")
    public UserConfiguration retriveLimitsFromConfigurations()
    {
        return new UserConfiguration(1000, 1);
    }
}