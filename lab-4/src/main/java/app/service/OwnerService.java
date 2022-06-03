package app.service;

import app.entity.OwnerEntity;
import app.view.OwnerView;

import java.util.List;

public interface OwnerService {

    void saveOwner(OwnerEntity owner);

    List<OwnerEntity> getAllOwners();

    void deleteOwner(int passportCode);

    OwnerEntity findByPassportOwner(int passport);

    List<OwnerView> convert(List<OwnerEntity> owners);

    OwnerView convert(OwnerEntity owner);
}
