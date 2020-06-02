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
        //userRepo.login(user);
        //sender.requestToken(user);
        //check user in database, then create a jwt and send it back to the client.
        return null; //return jwt
    }

    String register(User user) throws Exception {
        userRepo.create(user);
        sender.requestToken(new TokenRequest(user.getId(), user.getName(), user.getRole(), user.getAvatar()));

        return Receiver.gettoken(user.getName()); //return jwt
    }

}