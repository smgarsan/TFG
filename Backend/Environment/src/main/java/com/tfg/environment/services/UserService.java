package com.tfg.environment.services;

import com.tfg.environment.dtos.UserRequestDto;
import com.tfg.environment.dtos.UserIdDto;
import com.tfg.environment.dtos.UserResponseDto;
import com.tfg.environment.models.UserModel;
import com.tfg.environment.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public UserIdDto createUser(UserRequestDto data) {
        UserModel userModel = new UserModel();
        userModel.setName(data.getName());
        userModel.setSurname(data.getSurname());
        userModel.setDni(data.getDni());
        userModel.setGroup(data.getGroup());

        return new UserIdDto(userRepository.save(userModel).getIdentityChip());
    }

    public Optional<UserResponseDto> readUser(UserIdDto data) {
        return userRepository.findById(data.getId())
                .map(userModel -> new UserResponseDto(
                        userModel.getName(),
                        userModel.getSurname(),
                        userModel.getDni(),
                        userModel.getGroup()
                ));
    }

    public boolean deleteUser(UserIdDto data) {
        if (!userRepository.existsById(data.getId())) {
            return false;
        }

        userRepository.deleteById(data.getId());
        return true;
    }

    public boolean updateUser(UserIdDto id, UserRequestDto data) {
        Optional<UserModel> optionalModel = userRepository.findById(id.getId());
        if (optionalModel.isPresent()) {
            UserModel model = optionalModel.get();
            if (data.getName() != null) model.setName(data.getName());
            if (data.getSurname() != null) model.setSurname(data.getSurname());
            if (data.getDni() != null) model.setDni(data.getDni());
            if (data.getGroup() != null) model.setGroup(data.getGroup());
            userRepository.save(model);
            return true;
        } else {
            return false;
        }
    }
}
