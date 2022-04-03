package pt.ulusofona.lp2.deisiGreatGame;

public class InvalidInitialBoardException extends Exception {

    private String message;
    private boolean isInvalidAbyss;
    private boolean isEffectType;
    private boolean isEffectID;
    private boolean isInvalidTool;
    private boolean isSizeInvalid;
    private boolean isNameInvalid;
    private boolean isColorInvalid;
    private boolean isPlayerIdInvalid;
    private String typeID;
    InvalidInitialBoardException(String message) {
        this.message = message;
    }

    public InvalidInitialBoardException(String message, boolean isInvalidAbyss, boolean isInvalidTool,
                                        boolean isEffectType,boolean isEffectID, String typeID) {
        this.message = message;
        this.isInvalidAbyss = isInvalidAbyss;
        this.isInvalidTool = isInvalidTool;
        this.isEffectType = isEffectType;
        this.isEffectID = isEffectID;
        this.typeID = typeID;
    }

    public InvalidInitialBoardException(String message, boolean isSizeInvalid, boolean isNameInvalid,
                                        boolean isColorInvalid,boolean isPlayerIdInvalid) {
        this.message = message;
        this.isSizeInvalid = isSizeInvalid;
        this.isNameInvalid = isNameInvalid;
        this.isColorInvalid = isColorInvalid;
        this.isPlayerIdInvalid = isPlayerIdInvalid;
    }

    public String getMessage(){
        return message;
    }

    public Boolean isInvalidAbyss(){
        return isInvalidAbyss;
    }

    public boolean isSizeInvalid() {
        return isSizeInvalid;
    }

    public boolean isNameInvalid() {
        return isNameInvalid;
    }

    public boolean isColorInvalid() {
        return isColorInvalid;
    }

    public boolean isPlayerIdInvalid() {
        return isPlayerIdInvalid;
    }

    public String getTypeID() {
        return typeID;
    }

    public Boolean isInvalidTool(){
        return isInvalidTool;
    }
    public String getTypeId(){
        return typeID;
    }
}