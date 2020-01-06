package entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.mindrot.jbcrypt.BCrypt;

@Entity
@Table(name = "users")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "user_name", length = 25)
    private String userName;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "user_pass")
    private String userPass;
    @JoinTable(name = "user_roles", joinColumns =
    {
        @JoinColumn(name = "user_name", referencedColumnName = "user_name")
    }, inverseJoinColumns =
    {
        @JoinColumn(name = "role_name", referencedColumnName = "role_name")
    })
    @ManyToMany
    private List<Role> roleList = new ArrayList();

    private String email, firstName, lastName;
    private int phone;
 //   @ManyToMany(cascade = CascadeType.PERSIST, targetEntity = Address.class)
   // private Address address;
   // @ManyToMany(cascade = CascadeType.PERSIST, targetEntity = User.class)
   // private Hobby hobbies;

    
    //Username/PW constructor. create simple user without fluff. 
    public User(String userName, String userPass)
    {
        this.userName = userName;
        this.userPass = BCrypt.hashpw(userPass, BCrypt.gensalt());
    }

    public User(String userName, String userPass, String email, String firstName, String lastName, int phone /*,Address address, Hobby hobbies*/)
    {
        this.userName = userName;
        this.userPass = BCrypt.hashpw(userPass, BCrypt.gensalt());
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
      //  this.address = address;
       // this.hobbies = hobbies;
    }

//    public Address getAddress()
//    {
//        return address;
//    }
//
//    public void setAddress(Address address)
//    {
//        this.address = address;
//    }

//    public Hobby getHobbies()
//    {
//        return hobbies;
//    }
//
//    public void setHobbies(Hobby hobbies)
//    {
//        this.hobbies = hobbies;
//    }

    public List<String> getRolesAsStrings()
    {
        if (roleList.isEmpty())
        {
            return null;
        }
        List<String> rolesAsStrings = new ArrayList();
        for (Role role : roleList)
        {
            rolesAsStrings.add(role.getRoleName());
        }
        return rolesAsStrings;
    }

    public User()
    {
    }

    public boolean verifyPassword(String pw)
    {
        if (BCrypt.checkpw(pw, userPass))
        {
            System.out.println("It matches");
            return true;
        }
        else
        {
            System.out.println("It does not match");
        }
        return false;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getFirstName()
    {
        return firstName;
    }

    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }

    public String getLastName()
    {
        return lastName;
    }

    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }

    public int getPhone()
    {
        return phone;
    }

    public void setPhone(int phone)
    {
        this.phone = phone;
    }

    public String getUserName()
    {
        return userName;
    }

    public void setUserName(String userName)
    {
        this.userName = userName;
    }

    public String getUserPass()
    {
        return this.userPass;
    }

    public void setUserPass(String userPass)
    {
        this.userPass = userPass;
    }

    public List<Role> getRoleList()
    {
        return roleList;
    }

    public void setRoleList(List<Role> roleList)
    {
        this.roleList = roleList;
    }

    public void addRole(Role userRole)
    {
        roleList.add(userRole);
    }

}
