package app.service;

import app.entity.FriendsEntity;
import app.repository.CatRepository;
import app.repository.FriendsRepository;
import app.repository.OwnerRepository;
import app.view.CatView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import app.entity.CatEntity;

import java.util.ArrayList;
import java.util.List;

@Service
public class CatServiceImpl implements CatService{

    @Autowired
    private CatRepository catRepository;
    @Autowired
    private FriendsRepository friendsRepository;
    @Autowired
    private OwnerRepository ownerRepository;

    public List<CatEntity> getAllCats() {
        try {
            return catRepository.getAll();
        } catch (Exception e) {
            throw new RuntimeException("Exception " + e.getMessage());
        }
    }

    public boolean saveCat(CatEntity cat) {
        try {
            if (ownerRepository.findOwnerByPassport(cat.getPassportOwner()) != null) {
                catRepository.save(cat);
                return true;
            }
            return false;
        } catch (Exception e) {
            throw new RuntimeException("Exception " + e.getMessage());
        }
    }

    public CatEntity findByPassportCat(int passport) {
        try {
            return catRepository.findCatByPassport(passport);
        } catch (Exception e) {
            throw new RuntimeException("Exception " + e.getMessage());
        }
    }

    public void deleteCat(int passportCode) {
        try {
            catRepository.delete(findByPassportCat(passportCode));
        } catch (Exception e) {
            throw new RuntimeException("Exception " + e.getMessage());
        }
    }

    public ArrayList<CatEntity> getFriendsCat(int passportCode) {
        try {
            CatEntity cat = findByPassportCat(passportCode);
            List<CatEntity> friends = new ArrayList<>();
            List<FriendsEntity> allPairs = friendsRepository.findAll();
            for (FriendsEntity allPair : allPairs) {
                if (allPair.getFirst() == passportCode) {
                    friends.add(findByPassportCat(allPair.getSecond()));
                }

                if (allPair.getSecond() == passportCode) {
                    friends.add(findByPassportCat(allPair.getFirst()));
                }
            }

            return (ArrayList<CatEntity>) friends;
        } catch (Exception e) {
            throw new RuntimeException("Exception " + e.getMessage());
        }
    }

    public List<CatEntity> getOwnerCats(int passportCode) {
        try {
            List<CatEntity> cats = catRepository.findOwnerCats(passportCode);

            return cats;
        } catch (Exception e) {
            throw new RuntimeException("Exception " + e.getMessage());
        }
    }

    public void deletePairFriends(FriendsEntity friends) {
        try {
            friendsRepository.delete(friends);
        } catch (Exception e) {
            throw new RuntimeException("Exception " + e.getMessage());
        }
    }

    public boolean addPairFriend(FriendsEntity friends) {
        try {
            var all = getAllFriends();
            for (FriendsEntity value : all) {
                boolean firstFriend = (value.getSecond() == friends.getSecond() && value.getFirst() == friends.getFirst());
                boolean secondFriend = (value.getSecond() == friends.getFirst() && value.getFirst() == friends.getSecond());
                if (firstFriend || secondFriend) {
                    return false;
                }
            }

            friendsRepository.save(friends);
            return true;
        } catch (Exception e) {
            throw new RuntimeException("Exception " + e.getMessage());
        }
    }

    public List<FriendsEntity> getAllFriends() {
        try {
            List<FriendsEntity> friends = friendsRepository.findAll();
            return friends;
        } catch (Exception e) {
            throw new RuntimeException("Exception " + e.getMessage());
        }
    }

    public List<CatView> convert(List<CatEntity> cats) {
        List<CatView> catViews = new ArrayList<>();
        for (int i = 0; i < cats.size(); i++) {
            CatEntity cat = cats.get(i);
            catViews.add(new CatView(cat.getDate(), cat.getBreed(), cat.getColor()));
        }
        return catViews;
    }

    public CatView convert(CatEntity cat) {
        CatView catView = new CatView(cat.getDate(), cat.getBreed(), cat.getColor());
        return catView;
    }
}
