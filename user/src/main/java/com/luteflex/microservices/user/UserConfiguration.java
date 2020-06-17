package com.luteflex.microservices.user;


import com.luteflex.microservices.user.DataAccess.UserRepo;
import com.luteflex.microservices.user.Models.TokenRequest;
import com.luteflex.microservices.user.Models.User;
import com.luteflex.microservices.user.rabbitmq.Receiver;
import com.luteflex.microservices.user.rabbitmq.Sender;

import java.io.Console;

public class UserConfiguration
{
   //bla bla get it from the database
   private Sender sender = new Sender();
   private Receiver receiver = new Receiver();
    private UserRepo userRepo = new UserRepo();
    //TokenRequest user = new TokenRequest("200", "Lute", "Admin", "something.png");

    public String signIn(User user) throws Exception {
        User u = userRepo.login(user);
        if (u != null){
            sender.requestToken(new TokenRequest(u.getId(), u.getName(), u.getRole(), u.getAvatar()));
            return Receiver.gettoken(u.getName());
        } else {
            return "De inloggegevens zijn incorrect, probeer het opnieuw of maak een account aan.";
        }
    }

    String register(User user) throws Exception {
        userRepo.create(user);
        User u = userRepo.login(user);
        sender.requestToken(new TokenRequest(u.getId(), u.getName(), u.getRole(), u.getAvatar()));

        return Receiver.gettoken(user.getName()); //return jwt
    }

}