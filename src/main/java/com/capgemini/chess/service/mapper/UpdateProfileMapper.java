package com.capgemini.chess.service.mapper;

import java.util.List;
import java.util.stream.Collectors;

import com.capgemini.chess.dataaccess.entities.UpdateProfileEntity;
import com.capgemini.chess.service.to.UpdateProfileTO;

public class UpdateProfileMapper {

	
	public static UpdateProfileTO map(UpdateProfileEntity updateProfileEntity) {
		if (updateProfileEntity != null) {
			UpdateProfileTO updateProfileTO = new UpdateProfileTO();
			updateProfileTO.setAboutMe(updateProfileEntity.getAboutMe());
			updateProfileTO.setEmail(updateProfileEntity.getEmail());
			updateProfileTO.setId(updateProfileEntity.getId());
			updateProfileTO.setLifeMotto(updateProfileEntity.getLifeMotto());
			updateProfileTO.setLogin(updateProfileEntity.getLogin());
			updateProfileTO.setName(updateProfileEntity.getName());
			updateProfileTO.setPassword(updateProfileEntity.getPassword());
			updateProfileTO.setSurname(updateProfileEntity.getSurname());
			return updateProfileTO;
		}
		return null;
	}

	public static UpdateProfileEntity map(UpdateProfileTO updateProfileTO) {
		if (updateProfileTO != null) {
			UpdateProfileEntity updateProfileEntity = new UpdateProfileEntity();
			updateProfileEntity.setAboutMe(updateProfileTO.getAboutMe());
			updateProfileEntity.setEmail(updateProfileTO.getEmail());
			updateProfileEntity.setId(updateProfileTO.getId());
			updateProfileEntity.setLifeMotto(updateProfileTO.getLifeMotto());
			updateProfileEntity.setLogin(updateProfileTO.getLogin());
			updateProfileEntity.setName(updateProfileTO.getName());
			updateProfileEntity.setPassword(updateProfileTO.getPassword());
			updateProfileEntity.setSurname(updateProfileTO.getSurname());
			return updateProfileEntity;
		}
		return null;
	}
	
	public static UpdateProfileEntity update(UpdateProfileEntity updateProfileEntity, UpdateProfileTO updateProfileTO) {
		if (updateProfileTO != null && updateProfileEntity != null) {
			updateProfileEntity.setAboutMe(updateProfileTO.getAboutMe());
			updateProfileEntity.setEmail(updateProfileTO.getEmail());
			updateProfileEntity.setId(updateProfileTO.getId());
			updateProfileEntity.setLifeMotto(updateProfileTO.getLifeMotto());
			updateProfileEntity.setName(updateProfileTO.getName());
			updateProfileEntity.setPassword(updateProfileTO.getPassword());
			updateProfileEntity.setSurname(updateProfileTO.getSurname());
		}
		return updateProfileEntity;
	}
	
	public static List<UpdateProfileTO> map2TOs(List<UpdateProfileEntity> updateProfileEntities) {
		return updateProfileEntities.stream().map(UpdateProfileMapper::map).collect(Collectors.toList());
	}

	public static List<UpdateProfileEntity> map2Entities(List<UpdateProfileTO> updateProfileTOs) {
		return updateProfileTOs.stream().map(UpdateProfileMapper::map).collect(Collectors.toList());
	}
}
