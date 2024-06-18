package com.elice.slowslow.user.mapper;

import com.elice.slowslow.user.User;
import com.elice.slowslow.user.dto.MembershipDTO;
import com.elice.slowslow.user.dto.MypageResponseDTO;
import com.elice.slowslow.user.dto.UserDTO;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-06-18T03:38:44+0900",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.10 (Oracle Corporation)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public User userDTOToUser(UserDTO userDTO) {
        if ( userDTO == null ) {
            return null;
        }

        User user = new User();

        user.setId( userDTO.getId() );
        user.setUsername( userDTO.getUsername() );
        user.setPassword( userDTO.getPassword() );
        user.setName( userDTO.getName() );
        user.setPhoneNumber( userDTO.getPhoneNumber() );

        return user;
    }

    @Override
    public UserDTO userToUserDTO(User user) {
        if ( user == null ) {
            return null;
        }

        UserDTO userDTO = new UserDTO();

        userDTO.setId( user.getId() );
        userDTO.setUsername( user.getUsername() );
        userDTO.setPassword( user.getPassword() );
        userDTO.setName( user.getName() );
        userDTO.setPhoneNumber( user.getPhoneNumber() );

        return userDTO;
    }

    @Override
    public User mypageDtoToUser(MypageResponseDTO mypageResponseDTO) {
        if ( mypageResponseDTO == null ) {
            return null;
        }

        User user = new User();

        user.setId( mypageResponseDTO.getId() );
        user.setUsername( mypageResponseDTO.getUsername() );
        user.setName( mypageResponseDTO.getName() );
        user.setPhoneNumber( mypageResponseDTO.getPhoneNumber() );

        return user;
    }

    @Override
    public MypageResponseDTO userToMypageDto(User user) {
        if ( user == null ) {
            return null;
        }

        MypageResponseDTO mypageResponseDTO = new MypageResponseDTO();

        mypageResponseDTO.setId( user.getId() );
        mypageResponseDTO.setUsername( user.getUsername() );
        mypageResponseDTO.setName( user.getName() );
        mypageResponseDTO.setPhoneNumber( user.getPhoneNumber() );

        return mypageResponseDTO;
    }

    @Override
    public User membershipDtoToUser(MembershipDTO membershipDTO) {
        if ( membershipDTO == null ) {
            return null;
        }

        User user = new User();

        user.setUsername( membershipDTO.getUsername() );
        user.setPassword( membershipDTO.getPassword() );
        user.setName( membershipDTO.getName() );
        user.setPhoneNumber( membershipDTO.getPhoneNumber() );

        return user;
    }
}
