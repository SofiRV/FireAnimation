package fire.dto;

public enum BackgroundOptionsDto {
    BACKGROUND_1("Option #1"),
    BACKGROUND_2("Option #2"),
    BACKGROUND_3("Option #3"),
    BACKGROUND_4("Option #4");

    private final String displayName;

    BackgroundOptionsDto(String displayName){
        this.displayName=displayName;
    }

    @Override
    public String toString(){
        return displayName;
    }
}
