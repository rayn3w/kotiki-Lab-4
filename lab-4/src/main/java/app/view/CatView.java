package app.view;

import app.enums.CatType;
import app.enums.CatColor;

public class CatView {
    public String date;
    public CatType breed;
    public CatColor color;

    public CatView(String date, CatType breed, CatColor color) {
        this.date = date;
        this.breed = breed;
        this.color = color;
    }
}
