package com.mertyalcin.creditcenter.business.abstracts;

import com.mertyalcin.creditcenter.business.dto.CustomerDto;
import com.mertyalcin.creditcenter.business.requests.customerRequests.CreateCustomerRequest;
import com.mertyalcin.creditcenter.business.requests.customerRequests.UpdateCustomerRequest;
import com.mertyalcin.creditcenter.core.utils.results.DataResult;
import com.mertyalcin.creditcenter.core.utils.results.Result;

import java.util.List;

public interface CustomerService {
    DataResult<List<CustomerDto>> findAll();

    DataResult<CustomerDto> findById(long id);

    DataResult<CustomerDto> findByNationalityNo(String nationalityNo);

    Result create(CreateCustomerRequest createCustomerRequest);

    Result update(UpdateCustomerRequest updateCustomerRequest);

    Result delete(long id);
}
