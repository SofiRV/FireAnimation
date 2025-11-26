package fire.dto;

public enum BackgroundOptionsDto {
    BACKGROUND_1("Witch house"),
    BACKGROUND_2("Modern fireplace"),
    BACKGROUND_3("Medieval tavern"),
    BACKGROUND_4("Ice castle");

    private final String displayName;

    BackgroundOptionsDto(String displayName){
        this.displayName=displayName;
    }

    @Override
    public String toString(){
        return displayName;
    }
}
