package com.rentx.businessservices;

import com.rentx.businessservices.interfaces.IUserProfileService;
import com.rentx.dataaccess.repository.UserRepository;
import com.rentx.entities.User;
import lombok.RequiredArgsConstructor;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserProfileService implements IUserProfileService {
    /**
     * autowire User Repository
     */
    @Autowired
    private UserRepository userRepository;

    /**
     * method to find user by id
     *
     * @param userId user id
     * @return result from user repo by id
     */
    @Override
    public User findUserById(int userId) {
        Optional<User> user = userRepository.findById(userId);

        if (user != null && user.isPresent())
            return user.get();

        throw new ResourceNotFoundException("User not found with id " + userId);
    }

    /**
     * method to update user profile
     *
     * @param userId user id
     * @param user   user object
     */
    @Override
    public void updateUserProfile(int userId, User user) {
        User existingUser = findUserById(userId);
        existingUser.setPhone(user.getPhone());
        existingUser.setLastName(user.getLastName());
        existingUser.setFirstName(user.getFirstName());
        existingUser.setPrefLanguage(user.getPrefLanguage());
        existingUser.setAreaId(user.getAreaId());
        existingUser.setPrivacy(user.getPrivacy());
        existingUser.setStatus(user.getStatus());
        userRepository.save(existingUser);
    }


}
