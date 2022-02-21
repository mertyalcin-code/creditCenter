package com.mertyalcin.creditcenter.core.utils.mappers;

import org.modelmapper.ModelMapper;

public interface ModelMapperService {

	ModelMapper forDto();

	ModelMapper forRequest();
}