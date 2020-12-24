package br.com.personalog.service.impl;

import br.com.personalog.constant.ResponseHttpType;
import br.com.personalog.dao.UserDao;
import br.com.personalog.dto.ResponseObject;
import br.com.personalog.model.User;
import br.com.personalog.service.UserService;
import br.com.personalog.util.exception.UserAlreadyExistException;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import static br.com.personalog.constant.ResponseMessage.ERROR_MESSAGE;
import static br.com.personalog.constant.ResponseMessage.SUCCESS_MESSAGE;
import static br.com.personalog.service.impl.ServiceUtils.createResponse;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

	@NonNull
	private UserDao userDao;

	//@Transactional(propagation = Propagation.REQUIRES_NEW)
	@Override
	public ResponseObject registerNewUserAccount(User user)  {
		try {
//			validate(user);
			validate2(user);
			return createResponse(userDao.save(user), SUCCESS_MESSAGE, null);
		} catch (Exception e) {
			e.printStackTrace();
			return createResponse(user, ERROR_MESSAGE, e, ResponseHttpType.BAD_REQUEST);
		}
	}

	private void validate(User user) throws UserAlreadyExistException {
		if (userDao.isEmailExists(user.getEmail())) {
			throw new UserAlreadyExistException("There is an account with that email address: "+user.getEmail());//TODO implement the internationalization
		}
	}

	private void validate2(User user) throws Exception {
		if (userDao.isEmailExists(user.getEmail())) {
			throw new Exception("There is an account with that email address: "+user.getEmail());//TODO implement the internationalization
		}
	}
}
