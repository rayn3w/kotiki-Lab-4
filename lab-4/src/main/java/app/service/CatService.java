package app.service;

import app.entity.FriendsEntity;
import app.view.CatView;
import app.entity.CatEntity;

import java.util.ArrayList;
import java.util.List;

public interface CatService {
    List<CatEntity> getAllCats();

    boolean saveCat(CatEntity cat);

    CatEntity findByPassportCat(int passport);

    void deleteCat(int passportCode);

    ArrayList<CatEntity> getFriendsCat(int passportCode);

    List<CatEntity> getOwnerCats(int passportCode);

    void deletePairFriends(FriendsEntity friends);

    boolean addPairFriend(FriendsEntity friends);

    List<FriendsEntity> getAllFriends();

    List<CatView> convert(List<CatEntity> cats);

    CatView convert(CatEntity cat);
}
