package com.mertyalcin.creditcenter.controller;

import com.mertyalcin.creditcenter.business.abstracts.CreditService;
import com.mertyalcin.creditcenter.business.dto.CreditDto;
import com.mertyalcin.creditcenter.business.dto.CreditResultDto;
import com.mertyalcin.creditcenter.business.requests.creditRequests.CreateCreditRequest;
import com.mertyalcin.creditcenter.business.requests.creditRequests.UpdateCreditRequest;
import com.mertyalcin.creditcenter.core.utils.results.DataResult;
import com.mertyalcin.creditcenter.core.utils.results.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/credits")
@CrossOrigin
public class CreditsController {

    private CreditService creditService;
    @Autowired
    public CreditsController(CreditService creditService) {
        this.creditService = creditService;
    }
    @GetMapping("/find-all")
    public DataResult<List<CreditDto>> findAll(@RequestParam(defaultValue = "1") int pageNo,@RequestParam(defaultValue = "100") int pageSize){
        return creditService.findAll(pageNo,pageSize);
    }

    @GetMapping("/find-all-by-nationality-no/{nationalityNo}")
    public DataResult<List<CreditDto>> findAllByNationalityNo(@PathVariable String nationalityNo){
        return creditService.findAllByNationalityNo(nationalityNo);
    }
    @GetMapping("/find-by-id/{id}")
    public DataResult<CreditDto> findById(@PathVariable long id){
        return creditService.findById(id);
    }

    @PostMapping("/create")
    public DataResult<CreditResultDto> create(@RequestBody @Valid CreateCreditRequest createCreditRequest){
        return creditService.create(createCreditRequest);
    }
    @PutMapping("/update")
    public Result update(@RequestBody @Valid UpdateCreditRequest updateCreditRequest){
        return creditService.update(updateCreditRequest);
    }
    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable long id){
        return creditService.delete(id);
    }


}
