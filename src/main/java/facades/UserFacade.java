package facades;

//import entities.Address;
//import entities.Hobby;
import entities.User;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import errorhandling.AuthenticationException;

/**
 * @author lam@cphbusiness.dk
 */
public class UserFacade {

    private static EntityManagerFactory emf;
    private static UserFacade instance;

    public UserFacade()
    {
    }

    /**
     *
     * @param _emf
     * @return the instance of this facade.
     */
    public static UserFacade getUserFacade(EntityManagerFactory _emf)
    {
        if (instance == null)
        {
            emf = _emf;
            instance = new UserFacade();
        }
        return instance;
    }

    public User getVeryfiedUser(String username, String password) throws AuthenticationException
    {
        EntityManager em = emf.createEntityManager();
        User user;
        try
        {
            user = em.find(User.class, username);
            if (user == null || !user.verifyPassword(password))
            {
                throw new AuthenticationException("Invalid user name or password");
            }
        } finally
        {
            em.close();
        }
        return user;
    }

    private EntityManager getEntityManager()
    {
        return emf.createEntityManager();
    }

    public User createUser(String userName, String userPass)
    {
        if (userName != null && !userName.isEmpty() && userPass != null && !userPass.isEmpty())
        {
            User u = new User(userName, userPass);
            EntityManager em = getEntityManager();
            try
            {
                em.getTransaction().begin();
                em.persist(u);
                em.getTransaction().commit();
                return u;
            } finally
            {
                em.close();
            }
        }
        return null;
    }

//    public static void main(String[] args)
//    {
//        //UserFacade uf = instance;
//        User user = instance.createUser(user.getUserName(), user.getUserPass());
//        System.out.println(user.getUserName());
//    }
    //create advanced user with all params
//    public User createAdvancedUser(String userName, String userPass, String email, String firstName, String lastName, int phone, Address address, Hobby hobbies)
//    {
//        if (userName != null && !userName.isEmpty() && userPass != null && !userPass.isEmpty())
//        {
//            User u = new User(userName, userPass, email, firstName, lastName, phone, address, hobbies);
//            EntityManager em = getEntityManager();
//            try
//            {
//                em.getTransaction().begin();
//                em.persist(u);
//                em.getTransaction().commit();
//                return u;
//            } finally
//            {
//                em.close();
//            }
//        }
//        return null;
//    }

    public Object getAllUsers()
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Object getAllZips()
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
