package ie.altech.demo.user;

import java.util.Calendar;
import java.util.Date;

public class UserHelper {

    public static UserHelper getInstance(){
        return new UserHelper();
    }

    public static Date getDob(int year, int month, int day){
        Calendar dob = new Calendar.Builder()
                .set(Calendar.YEAR, year)
                .set(Calendar.MONTH, month)
                .set(Calendar.DAY_OF_MONTH, day)
                .build();
        return dob.getTime();
    }
}
