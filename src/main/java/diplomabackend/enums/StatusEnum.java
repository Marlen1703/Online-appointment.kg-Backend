package diplomabackend;

public enum StatusEnum {
    UNEXPLORED((short)0),
    EXPLORED((short)1);


    short value;

    StatusEnum(short i) {
        this.value  = i;
    }

}