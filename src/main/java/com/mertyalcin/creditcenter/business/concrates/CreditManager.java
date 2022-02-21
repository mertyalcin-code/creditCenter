package com.mertyalcin.creditcenter.business.concrates;

import com.mertyalcin.creditcenter.business.abstracts.BaseCreditCalculatorFactory;
import com.mertyalcin.creditcenter.business.abstracts.CreditService;
import com.mertyalcin.creditcenter.business.abstracts.CustomerService;
import com.mertyalcin.creditcenter.business.dto.CreditDto;
import com.mertyalcin.creditcenter.business.dto.CreditResultDto;
import com.mertyalcin.creditcenter.business.dto.CustomerDto;
import com.mertyalcin.creditcenter.business.requests.creditRequests.CreateCreditRequest;
import com.mertyalcin.creditcenter.business.requests.creditRequests.UpdateCreditRequest;
import com.mertyalcin.creditcenter.business.requests.customerRequests.CreateCustomerRequest;
import com.mertyalcin.creditcenter.core.adapters.CreditScoreService;
import com.mertyalcin.creditcenter.core.utils.business.BusinessRules;
import com.mertyalcin.creditcenter.core.utils.mappers.ModelMapperService;
import com.mertyalcin.creditcenter.core.utils.messages.Messages;
import com.mertyalcin.creditcenter.core.utils.results.*;
import com.mertyalcin.creditcenter.core.utils.sms.SmsService;
import com.mertyalcin.creditcenter.dataAccess.CreditDao;
import com.mertyalcin.creditcenter.entities.Credit;
import com.mertyalcin.creditcenter.entities.Customer;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Log4j2
public class CreditManager implements CreditService {
    private CreditDao creditDao;
    private ModelMapperService modelMapperService;
    private BaseCreditCalculatorFactory creditCalculatorFactory;
    private CreditScoreService creditScoreService;
    private CustomerService customerService;
    private SmsService smsService;



    @Autowired
    public CreditManager(CreditDao creditDao, ModelMapperService modelMapperService, BaseCreditCalculatorFactory creditCalculatorFactory, CreditScoreService creditScoreService, CustomerService customerService, SmsService smsService) {
        this.creditDao = creditDao;
        this.modelMapperService = modelMapperService;
        this.creditCalculatorFactory = creditCalculatorFactory;
        this.creditScoreService = creditScoreService;
        this.customerService = customerService;
        this.smsService = smsService;
    }


    @Override
    public DataResult<List<CreditDto>> findAll(int pageNo,int pageSize) {
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
        List<Credit> creditList = this.creditDao.findAll(pageable).getContent();
        List<CreditDto> response = creditList.stream()
                .map(credit -> modelMapperService.forDto().map(credit, CreditDto.class)).collect(Collectors.toList());
        log.info("all customers found with page no: "+pageNo+" page size: "+pageSize);
        return new SuccessDataResult<>(response, Messages.CREDIT_LIST);
    }

    @Override
    public DataResult<CreditDto> findById(long id) {
        Result result = BusinessRules.run(checkIfCreditIdExist(id));
        if (result != null) {
            return new ErrorDataResult<>(result.getMessage());
        }
        Credit city = creditDao.findById(id).get();
        CreditDto response = modelMapperService.forDto().map(city, CreditDto.class);
        log.info("customer found with id: "+response);
        return new SuccessDataResult<>(response, Messages.CREDIT_LIST);
    }


    @Override
    public DataResult<CreditResultDto> create(CreateCreditRequest createCreditRequest) {
       int creditScore= this.creditScoreService.getPersonsCreditScore(createCreditRequest.getNationalityNo());
       double creditAmount= this.creditCalculatorFactory.calculateCreditAmount(creditScore,createCreditRequest.getMonthlySalary());

       CustomerDto customerDto= customerService.findByNationalityNo(createCreditRequest.getNationalityNo()).getData();
       Customer customer = new Customer();
       if(customerDto != null){
           customer.setId(customerDto.getId());
       }
       else{
           CreateCustomerRequest createCustomerRequest = new CreateCustomerRequest();
           createCustomerRequest.setFirstName(createCreditRequest.getFirstName());
           createCustomerRequest.setLastName(createCreditRequest.getLastName());
           createCustomerRequest.setNationalityNo(createCreditRequest.getNationalityNo());
           createCustomerRequest.setMonthlySalary(createCreditRequest.getMonthlySalary());
           createCustomerRequest.setPhoneNumber(createCreditRequest.getPhoneNumber());
          if(!customerService.create(createCustomerRequest).isSuccess()){
              return  new ErrorDataResult<>();
          }else{
            customer=modelMapperService.forRequest().map(customerService.findByNationalityNo(createCreditRequest.getNationalityNo()).getData(),Customer.class);
              customerDto = modelMapperService.forDto().map(customerService.findByNationalityNo(createCreditRequest.getNationalityNo()).getData(),CustomerDto.class);
       }}
        Credit credit = Credit.builder()
                .id(0)
                .creditAmount(creditAmount)
                .creationTime(LocalDateTime.now())
                .customer(customer)
                .build();
        creditDao.save(credit);


        CreditResultDto response = CreditResultDto.builder()
                .id(credit.getId())
                .creditAmount(creditAmount)
                .isApproved(creditAmount != 0)
                .firstName(customerDto.getFirstName())
                .creditScore(String.valueOf(creditScore))
                .lastName(customerDto.getLastName())
                .nationalityNo(customerDto.getNationalityNo())
                .phoneNumber(customerDto.getPhoneNumber())
                .build();
        log.info("New Credit "+response);
        sendApplicationStatusSMS(credit);
       return new SuccessDataResult<>(response,Messages.CREDIT_APPLIED_SUCCESS);
    }

    @Override
    public DataResult<List<CreditDto>> findAllByNationalityNo(String nationalityNo) {
        List<Credit> creditList = this.creditDao.findAllByCustomer_NationalityNoOrderByCreationTimeDesc(nationalityNo);
        List<CreditDto> response = creditList.stream()
                .map(credit -> modelMapperService.forDto().map(credit, CreditDto.class)).collect(Collectors.toList());
        log.info("Credits found by nationality Id: "+nationalityNo);
        return new SuccessDataResult<>(response, Messages.CREDIT_LIST);
    }

    @Override
    public Result update(UpdateCreditRequest updateCreditRequest) {
        Credit credit =this.creditDao.findById(updateCreditRequest.getId()).get();
        credit.setCreditAmount(updateCreditRequest.getCreditAmount());
        this.creditDao.save(credit);
       log.info("Credit updated: "+this.findById(credit.getId()));
        return new SuccessResult(Messages.CREDIT_UPDATED);
    }

    @Override
    public Result delete(long id) {
        Result result = BusinessRules.run(checkIfCreditIdExist(id));
        if (result != null) {
            return result;
        }
        creditDao.deleteById(id); // if there is a relation it will give error
        log.info("Customer Deleted with id: "+id);
        return new SuccessResult(Messages.CREDIT_DELETE);
    }
    private Result checkIfCreditIdExist(long id) {
        if (!creditDao.existsById(id)) {
            return new ErrorResult(Messages.CREDIT_NOT_FOUND);
        }
        return new SuccessResult();
    }
    private void sendApplicationStatusSMS(Credit credit){
        if (credit.getCreditAmount() == 0) {
            smsService.sendUnapprovedCreditResultSMS(credit);
        } else {
            smsService.sendApprovedCreditResultSMS(credit);
        }
    }


}
