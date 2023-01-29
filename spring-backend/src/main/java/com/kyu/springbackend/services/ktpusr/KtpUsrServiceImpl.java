package com.kyu.springbackend.services.ktpusr;

import com.kyu.springbackend.model.KtpUsr;
import com.kyu.springbackend.repositories.KtpUsrRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KtpUsrServiceImpl implements KtpUsrService{

    private final KtpUsrRepository repository;

    public KtpUsrServiceImpl(KtpUsrRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<KtpUsr> findAll() {
        return repository.findAll();
    }

    @Override
    public KtpUsr findById(String s) {
        return repository.findById(s).orElse(null);
    }

    @Override
    public KtpUsr save(KtpUsr object) {
        return repository.save(object);
    }

    @Override
    public KtpUsr update(String id, KtpUsr newKtpUsr) {
        return repository.findById(id)
                .map(ktpUsr -> {
                    ktpUsr.update(newKtpUsr);
                    return repository.save(ktpUsr);
                })
                .orElseGet(() -> {
                    newKtpUsr.setId(id);
                    return repository.save(newKtpUsr);
                });
    }

    @Override
    public void delete(KtpUsr object) {
        repository.delete(object);
    }

    @Override
    public void deleteById(String s) {
        repository.deleteById(s);
    }
}
