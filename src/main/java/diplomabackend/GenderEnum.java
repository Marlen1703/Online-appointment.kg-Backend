package diplomabackend;

public enum GenderEnum {
    MAN((short)0),
    WOMAN((short)1);


    short value;

    GenderEnum(short i) {
        this.value  = i;
    }
}
