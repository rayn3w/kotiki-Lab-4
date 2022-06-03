package app.service;

import app.repository.OwnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import app.entity.OwnerEntity;
import app.view.OwnerView;

import java.util.ArrayList;
import java.util.List;

@Service
public class OwnerServiceImpl implements OwnerService {

    @Autowired
    private OwnerRepository ownerRepository;

    public void saveOwner(OwnerEntity owner) {
        try {
            ownerRepository.save(owner);
        } catch (Exception e) {
            throw new RuntimeException("Exception " + e.getMessage());
        }
    }

    public List<OwnerEntity> getAllOwners() {
        try {
            return ownerRepository.findAll();
        } catch (Exception e) {
            throw new RuntimeException("Exception " + e.getMessage());
        }
    }

    public void deleteOwner(int passportCode) {
        try {
            ownerRepository.delete(findByPassportOwner(passportCode));
        } catch (Exception e) {
            throw new RuntimeException("Exception " + e.getMessage());
        }
    }

    public OwnerEntity findByPassportOwner(int passport) {
        try {
            return ownerRepository.findOwnerByPassport(passport);
        } catch (Exception e) {
            throw new RuntimeException("Exception " + e.getMessage());
        }
    }

    public List<OwnerView> convert(List<OwnerEntity> owners) {
        List<OwnerView> catViews = new ArrayList<>();
        for (int i = 0; i < owners.size(); i++) {
            OwnerEntity owner = owners.get(i);
            catViews.add(new OwnerView(owner.getName(), owner.getDate(), owner.getRoles()));
        }
        return catViews;
    }

    public OwnerView convert(OwnerEntity owner) {
        OwnerView ownerView = new OwnerView(owner.getName(), owner.getDate(), owner.getRoles());
        return ownerView;
    }
}
