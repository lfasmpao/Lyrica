package io.github.lfasmpao.lyrica;

public class Mutator {
    private String ID, title, artist, lyrics;

    public String getID(){
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getTitle(){
        return title;
    }

    public void setTitle(String title){
        this.title = title;
    }

    public String getLyrics(){
        return lyrics;
    }

    public void setLyrics(String lyrics){
        this.lyrics = lyrics;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist){
        this.artist = artist;
    }

}
