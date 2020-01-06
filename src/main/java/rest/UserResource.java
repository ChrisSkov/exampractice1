package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import entities.Role;
import entities.User;
import utils.EMF_Creator;
import facades.UserFacade;
import javax.annotation.security.RolesAllowed;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

//Todo Remove or change relevant parts before ACTUAL use
@Path("user")
public class UserResource {

    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory(
            "pu",
            "jdbc:mysql://localhost:3307/practice1",
            "dev",
            "ax2",
            EMF_Creator.Strategy.CREATE);
    private static final UserFacade FACADE = UserFacade.getUserFacade(EMF);
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    @GET
    @Produces(
            {
                MediaType.APPLICATION_JSON
            })
    public String demo()
    {
        return "{\"msg\":\"Hello World\"}";
    }

    @Path("spells")
    @GET
    @Produces(
            {
                MediaType.APPLICATION_JSON
            })
    @RolesAllowed("user")
    public String findSpells()
    {
        return "spells";
    }

    @Path("delete")
    @GET
    @Produces(
            {
                MediaType.APPLICATION_JSON
            })
    @RolesAllowed("admin")
    public String removeUser()
    {
        return "remove";
    }

    @Path("add/User")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String creatUser(String user)
    {   
        Role role = new Role("User");
        User newUser = (GSON.fromJson(user,User.class));
        newUser.addRole(role);
        return GSON.toJson(newUser);
    }
}
