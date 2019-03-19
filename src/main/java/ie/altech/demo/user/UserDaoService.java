package ie.altech.demo.user;

import ie.altech.demo.user.exceptions.UserNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Component
public class UserDaoService {

    private static List<User> users = new ArrayList<>();

    static {
        users.add(new User(1, "Andrej", getDob(1978, Calendar.OCTOBER, 3)));
        users.add(new User(2, "Tatjana", getDob(1976, Calendar.MAY, 3)));
        users.add(new User(3, "Alona", getDob(2007, Calendar.JUNE, 7)));
        users.add(new User(4, "Sophia", getDob(2012, Calendar.OCTOBER, 23)));
    }

    protected static Date getDob(int year, int month, int day){
        Calendar dob = new Calendar.Builder()
                .set(Calendar.YEAR, year)
                .set(Calendar.MONTH, month)
                .set(Calendar.DAY_OF_MONTH, day)
                .build();
        return dob.getTime();
    }

    public static UserDaoService getInstance() {
        return new UserDaoService();
    }

    public List<User> findAll(){
        return users;
    }

    public User saveUser(User user){
        if (user.getId() ==  null) {
            user.setId(users.size() + 1);
        }
        users.add(user);
        return user;
    }

    public User findUser(int id){
        try {
            return users.stream()
                    .filter(user -> user.getId() == id)
                    .findFirst()
                    .orElse(null);
        } catch (NullPointerException e) {
            throw new UserNotFoundException("Can't find user.");
        }
    }

    public boolean deleteUserById(int id){
        return users.removeIf(user -> user.getId() == id);
    }
}
