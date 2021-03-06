package services;

import db.repository.UserRepository;
import interface_pac.ServiceInterface;
import models.UserModel;

import java.util.ArrayList;

public class UserService implements ServiceInterface<UserModel> {
    private UserRepository userSql = null;
    private ArrayList<UserModel> userList = null;

    public UserService() {
        if (userSql == null) {
            userSql = new UserRepository();
        }
        if (userList == null) {
            userList = getAll();
        }
    }

    //getter
    public ArrayList<UserModel> getUserList() {
        return userList;
    }

    @Override
    public ArrayList<UserModel> getAll() {
        return userSql.getAll();
    }

    @Override
    public UserModel getItemById(Integer id) {
        return userSql.searchById(id);
    }

    public UserModel getUserByEmail(String email) {
        return userSql.searchByEmail(email);
    }

    @Override
    public void add(UserModel item) {
        userSql.add(item);
    }

    @Override
    public void update(UserModel item) {
        userSql.update(item);
    }

    @Override
    public void remove(Integer id) {
        userSql.remove(id);
    }

    @Override
    public ArrayList<UserModel> search(String word) {
        return userSql.search(word);
    }

    public void removeBookFromUsersList(Integer bookId, Integer userId) {
        userSql.updateStrList(userSql.searchById(userId).getStringlist().replace(bookId.toString(), ""), userId);

    }
}
