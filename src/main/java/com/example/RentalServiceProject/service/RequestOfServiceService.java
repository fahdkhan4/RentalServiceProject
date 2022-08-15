package com.example.RentalServiceProject.service;

import com.example.RentalServiceProject.configuration.exception.ContentNotFoundException;
import com.example.RentalServiceProject.model.User;
import com.example.RentalServiceProject.model.enums.InitialStatus;
import com.example.RentalServiceProject.dto.RequestOfServiceDto;
import com.example.RentalServiceProject.model.RequestOfService;
import com.example.RentalServiceProject.repo.RequestOfServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RequestOfServiceService {

    @Autowired
    RequestOfServiceRepository serviceRepository;
    @Autowired
    UserService userService;


    public List<RequestOfService> getAllRequestOfService() {
        return serviceRepository.findAll();
    }

    public List<RequestOfService> getRequestOfServiceByStatus() {
        List<RequestOfService> requestOfServices = serviceRepository.findByStatus(InitialStatus.Published);
        if(!requestOfServices.isEmpty()){
            return requestOfServices;
        }
        throw  new ContentNotFoundException("No Request exist in the Record");
    }

    public RequestOfServiceDto addRequestOfService_In_db(RequestOfServiceDto requestOfServiceDto) {
        User user = userService.getUsersbyStatus().stream().filter(user1 -> user1.getId().equals(requestOfServiceDto.getUser().getId())).findAny().get();
        requestOfServiceDto.setUser(user);
        return todto(serviceRepository.save(dto(requestOfServiceDto)));
    }

    public Optional<RequestOfService> getRequestOfService_ById(Long id) {
        Optional<RequestOfService> requestOfService = serviceRepository.findById(id);
        if(requestOfService.isPresent()){
            return requestOfService;
        }
        throw new ContentNotFoundException("No request Found having id "+id);
    }

    public void deleteRequestOfService_byId(Long id) {
        serviceRepository.deleteById(id);
    }

    public RequestOfServiceDto updateRequestOfService_byId(Long id, RequestOfServiceDto assetDto) {
        RequestOfService requestOfService = getAllRequestOfService().stream().filter(el->el.getId().equals(id)).findAny().get();
        if(requestOfService != null){
            requestOfService.setDetails(assetDto.getDetails());
            requestOfService.setType(assetDto.getType());
//            requestOfService.setUser(assetDto.getUser());
        }
        return todto(serviceRepository.save(requestOfService));
    }

    public RequestOfService dto(RequestOfServiceDto requestOfServiceDto){
        return RequestOfService.builder().id(requestOfServiceDto.getId()).status(requestOfServiceDto.getStatus())
                .details(requestOfServiceDto.getDetails()).type(requestOfServiceDto.getType()).user(requestOfServiceDto.getUser()).build();
    }

    public RequestOfServiceDto todto(RequestOfService requestOfService){
        return RequestOfServiceDto.builder().id(requestOfService.getId()).status(requestOfService.getStatus())
                .details(requestOfService.getDetails()).type(requestOfService.getType()).user(requestOfService.getUser()).build();
    }


}
