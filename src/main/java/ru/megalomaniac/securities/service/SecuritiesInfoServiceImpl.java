package ru.megalomaniac.securities.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.megalomaniac.securities.exceptions.SecuritiesNotFoundException;
import ru.megalomaniac.securities.model.SecuritiesInfo;
import ru.megalomaniac.securities.repository.SecuritiesInfoRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class SecuritiesInfoServiceImpl implements SecuritiesInfoService{

    @Autowired
    private SecuritiesInfoRepository securitiesInfoRepository;

    @Override
    public List<SecuritiesInfo> findAll() {
        return securitiesInfoRepository.findAll();
    }

    @Override
    public SecuritiesInfo findById(int id){
        Optional<SecuritiesInfo> result = securitiesInfoRepository.findById(id);
        SecuritiesInfo securitiesInfo;
        if(result.isPresent()){
            securitiesInfo = result.get();
        }
        else{
            throw new SecuritiesNotFoundException("securities info id "+id+" not found");
        }
        return securitiesInfo;
    }

    @Override
    @Transactional
    public void save(SecuritiesInfo securitiesInfo) {
        if((securitiesInfo.getId()>0)&&(!securitiesInfoRepository.existsById(securitiesInfo.getId()))) {
            securitiesInfoRepository.saveWithCustomId(
                    securitiesInfo.getId(),
                    securitiesInfo.getSecid(),
                    securitiesInfo.getRegnumber(),
                    securitiesInfo.getName(),
                    securitiesInfo.getEmitentTitle()
            );
        }
        else
            securitiesInfoRepository.save(securitiesInfo);
    }

    @Override
    public void deleteById(int id) {
        securitiesInfoRepository.deleteById(id);
    }

    @Override
    public Boolean existById(int id) {
        return securitiesInfoRepository.existsById(id);
    }

    @Override
    public Boolean existsBySecid(String secid) {
        return securitiesInfoRepository.existsBySecid(secid);
    }

    @Override
    public SecuritiesInfo findBySecid(String secid) {
        return securitiesInfoRepository.findBySecid(secid);
    }


}
