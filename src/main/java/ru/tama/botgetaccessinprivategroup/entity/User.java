package ru.tama.botgetaccessinprivategroup.entity;


/**
 * Created by tama on 11.06.17.
 */
public class User {
    private long idUser;
    private long idUserTelegram;
    private String nameUser;
    private String phoneUser;
    private String dateBirthdayUser;
    private int isAccessForGroup;
    private Integer stateGettingInformation;

    public long getIdUser() {
        return idUser;
    }

    public void setIdUser(long idUser) {
        this.idUser = idUser;
    }

    public long getIdUserTelegram() {
        return idUserTelegram;
    }

    public void setIdUserTelegram(long idUserTelegram) {
        this.idUserTelegram = idUserTelegram;
    }

    public String getNameUser() {
        return nameUser;
    }

    public void setNameUser(String nameUser) {
        this.nameUser = nameUser;
    }

    public String getPhoneUser() {
        return phoneUser;
    }

    public void setPhoneUser(String phoneUser) {
        this.phoneUser = phoneUser;
    }

    public String getDateBirthdayUser() {
        return dateBirthdayUser;
    }

    public void setDateBirthdayUser(String dateBirthdayUser) {
        this.dateBirthdayUser = dateBirthdayUser;
    }

    public int getAccessForGroup() {
        return isAccessForGroup;
    }

    public void setAccessForGroup(int accessForGroup) {
        isAccessForGroup = accessForGroup;
    }

    public Integer getStateGettingInformation() {
        return stateGettingInformation;
    }

    public void setStateGettingInformation(Integer stateGettingInformation) {
        this.stateGettingInformation = stateGettingInformation;
    }

}
