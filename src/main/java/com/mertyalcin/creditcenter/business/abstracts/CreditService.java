package com.mertyalcin.creditcenter.business.abstracts;

import com.mertyalcin.creditcenter.business.dto.CreditDto;
import com.mertyalcin.creditcenter.business.dto.CreditResultDto;
import com.mertyalcin.creditcenter.business.requests.creditRequests.CreateCreditRequest;
import com.mertyalcin.creditcenter.business.requests.creditRequests.UpdateCreditRequest;
import com.mertyalcin.creditcenter.core.utils.results.DataResult;
import com.mertyalcin.creditcenter.core.utils.results.Result;

import java.util.List;


public interface CreditService {

    DataResult<List<CreditDto>> findAll(int pageNo,int pageSize);

    DataResult<CreditDto> findById(long id);

    Result delete(long id);

    Result update(UpdateCreditRequest updateCreditRequest);

    DataResult<CreditResultDto> create(CreateCreditRequest createCreditRequest);

    DataResult<List<CreditDto>> findAllByNationalityNo(String nationalityNo);

}
