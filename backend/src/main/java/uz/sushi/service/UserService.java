package uz.sushi.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.sushi.payload.ApiResult;
import uz.sushi.payload.dto.UserDTO;
import uz.sushi.repository.UserRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public ApiResult<List<UserDTO>> getAll() {
        List<UserDTO> userDTOList = userRepository.findAll()
                .stream()
                .map(UserDTO::mapping)
                .collect(Collectors.toList());
        return ApiResult.successResponse(userDTOList);
    }

}
