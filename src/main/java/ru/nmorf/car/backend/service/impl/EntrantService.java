package ru.nmorf.car.backend.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.nmorf.car.backend.dao.repository.IEntrantEntityRepo;
import ru.nmorf.car.backend.dao.repository.mapper.IEntrantEntityMapper;
import ru.nmorf.car.backend.entity.Entrant;
import ru.nmorf.car.backend.service.IEntrantService;

@Service
public class EntrantService implements IEntrantService {

    private final IEntrantEntityRepo repo;
    private final IEntrantEntityMapper mapper;

    @Autowired
    public EntrantService(IEntrantEntityRepo repo,
                          IEntrantEntityMapper mapper) {
        this.repo = repo;
        this.mapper = mapper;
    }

    @Override
    @Transactional
    public Entrant patchEntrant(Entrant info) {
        int rows = repo.patch(info.getName(), info.getDriverCategory(), info.getEmail());
        if(rows == 0) throw new UsernameNotFoundException("User with this email not found");
        return mapper.toEntrant(repo.findByEmail(info.getEmail()));
    }
}
