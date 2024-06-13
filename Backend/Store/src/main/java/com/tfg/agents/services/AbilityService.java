package com.tfg.agents.services;

import com.tfg.agents.dtos.AbilityIdResponseDto;
import com.tfg.agents.dtos.AbilityRequestDto;
import com.tfg.agents.dtos.AbilityResponseDto;
import com.tfg.agents.models.AbilityModel;
import com.tfg.agents.repositories.AbilityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AbilityService {

    @Autowired
    AbilityRepository abilityRepository;

    public List<AbilityResponseDto> getAbilities() {
        return abilityRepository.findAll().stream()
                .map(abilityModel -> new AbilityResponseDto(
                        abilityModel.getAbilityId(),
                        abilityModel.getName(),
                        abilityModel.getDescription()
                ))
                .collect(Collectors.toList());
    }

    public AbilityIdResponseDto saveAbility(AbilityRequestDto dto) {
        AbilityModel model = new AbilityModel();
        model.setName(dto.getName());
        model.setDescription(dto.getDescription());
        ;
        return new AbilityIdResponseDto(abilityRepository.save(model).getAbilityId());

    }

    public Optional<AbilityResponseDto> getAbility(Long id) {
        return abilityRepository.findById(id)
                .map(abilityModel -> new AbilityResponseDto(
                        abilityModel.getAbilityId(),
                        abilityModel.getName(),
                        abilityModel.getDescription()
        ));
    }

    public boolean deleteAbility(Long id) {
        if (!abilityRepository.existsById(id)) {
            return false;
        }

        abilityRepository.deleteById(id);
        return true;
    }

    public boolean updateAbility(Long id, AbilityRequestDto data) {
        Optional<AbilityModel> optionalModel = abilityRepository.findById(id);
        if (optionalModel.isPresent()) {
            AbilityModel model = optionalModel.get();
            if (data.getName() != null) model.setName(data.getName());
            if (data.getDescription() != null) model.setDescription(data.getDescription());
            abilityRepository.save(model);
            return true;
        } else {
            return false;
        }
    }

}
