package com.mertyalcin.creditcenter.business.concrates;

import com.mertyalcin.creditcenter.business.abstracts.CustomerService;
import com.mertyalcin.creditcenter.business.requests.customerRequests.CreateCustomerRequest;
import com.mertyalcin.creditcenter.business.requests.customerRequests.UpdateCustomerRequest;

import com.mertyalcin.creditcenter.core.utils.business.BusinessRules;
import com.mertyalcin.creditcenter.core.utils.messages.Messages;
import com.mertyalcin.creditcenter.business.dto.CustomerDto;
import com.mertyalcin.creditcenter.core.utils.mappers.ModelMapperService;
import com.mertyalcin.creditcenter.core.utils.results.*;
import com.mertyalcin.creditcenter.dataAccess.CustomerDao;
import com.mertyalcin.creditcenter.entities.Customer;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Log4j2
public class CustomerManager implements CustomerService {
    private CustomerDao customerDao;
    private ModelMapperService modelMapperService;
    @Autowired
    public CustomerManager(CustomerDao customerDao, ModelMapperService modelMapperService) {
        this.customerDao = customerDao;
        this.modelMapperService = modelMapperService;
    }

    @Override
    public DataResult<List<CustomerDto>> findAll() {
        List<Customer> customerList = this.customerDao.findAll();
        List<CustomerDto> response = customerList.stream()
                .map(customer -> modelMapperService.forDto().map(customer, CustomerDto.class)).collect(Collectors.toList());
        log.info("all customers listed");
        return new SuccessDataResult<>(response, Messages.CUSTOMER_LIST);
    }

    @Override
    public DataResult<CustomerDto> findById(long id) {
        Result result = BusinessRules.run(checkIfCustomerExistById(id));

        if (result != null) {

            return new ErrorDataResult<>(result.getMessage());
        }

        Customer customer = customerDao.findById(id).get();
        CustomerDto response = modelMapperService.forDto().map(customer, CustomerDto.class);
        log.info("customer found:"+response.toString());
        return new SuccessDataResult<>(response, Messages.CUSTOMER_LIST);
    }

    @Override
    public DataResult<CustomerDto> findByNationalityNo(String nationalityNo) {
        Customer customer = customerDao.findByNationalityNo(nationalityNo);
        if(customer!=null){
            CustomerDto result = modelMapperService.forRequest().map(customer,CustomerDto.class);
            log.info("customer found:"+result.toString());
            return new SuccessDataResult<>(result,Messages.CUSTOMER_LIST);
        }
        else{
            log.warn("customer not found: "+nationalityNo);
            return new ErrorDataResult<>(Messages.CUSTOMER_NOT_FOUND);
        }
    }


    @Override
    public Result create(CreateCustomerRequest createCustomerRequest) {
        Result result = BusinessRules.run(checkIfCustomerExistByNationalityNo(createCustomerRequest.getNationalityNo()),
                checkIfCustomerExistByPhoneNumber(createCustomerRequest.getPhoneNumber())
        );
        if (result != null) {
            log.warn("customer not created:"+result.getMessage()+" for : "+createCustomerRequest);
            return result;
        }
        Customer customer = this.modelMapperService.forRequest().map(createCustomerRequest, Customer.class);
        this.customerDao.save(customer);
        log.info("customer created:"+customer);
        return new SuccessResult(Messages.CUSTOMER_CREATE);
    }

    @Override
    public Result update(UpdateCustomerRequest updateCustomerRequest) {
        Customer customer = this.modelMapperService.forRequest().map(updateCustomerRequest, Customer.class);
        this.customerDao.save(customer);
        log.info("customer updated:"+customer);
        return new SuccessResult(Messages.CUSTOMER_UPDATE);
    }

    @Override
    public Result delete(long id) {
        Result result = BusinessRules.run(checkIfCustomerExistById(id));
        if (result != null) {
            log.warn("customer not found with id: "+id);
            return result;
        }
        customerDao.deleteById(id);
        log.info("customer deleted: "+id);
        return new SuccessResult(Messages.CUSTOMER_DELETE);
    }

    private Result checkIfCustomerExistById(long id) {
        if (!this.customerDao.existsById(id)) {
            return new ErrorResult(Messages.CUSTOMER_NOT_FOUND);
        }
        return new SuccessResult();
    }

    private Result checkIfCustomerExistByNationalityNo(String nationalityNo) {
        if (this.customerDao.findByNationalityNo(nationalityNo) != null) {
            return new ErrorResult(Messages.CUSTOMER_EXIST_WITH_NATIONALITY_NO);
        }
        return new SuccessResult();
    }
    private Result checkIfCustomerExistByPhoneNumber(String phoneNumber) {
        if (this.customerDao.findByPhoneNumber(phoneNumber)!=null) {
            return new ErrorResult(Messages.CUSTOMER_EXIST_WITH_PHONE_NUMBER);
        }
        return new SuccessResult();
    }

}
