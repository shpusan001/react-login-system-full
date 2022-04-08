package testserver.common.reacttestserver;

import org.springframework.stereotype.Repository;
import testserver.common.reacttestserver.dto.UserDto;

import java.util.ArrayList;
import java.util.List;

public class UserRepository {
    public static UserRepository instance = new UserRepository();

    UserRepository(){}

    public List<UserDto> store = new ArrayList<>();
}
