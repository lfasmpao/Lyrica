package io.github.lfasmpao.lyrica;

class Mutator {
    private Integer ID;
    private String title, artist, lyrics;

    Integer getID(){
        return ID;
    }

    void setID(Integer ID) {
        this.ID = ID;
    }

    public String getTitle(){
        return title;
    }

    public void setTitle(String title){
        this.title = title;
    }

    String getLyrics(){
        return lyrics;
    }

    void setLyrics(String lyrics){
        this.lyrics = lyrics;
    }

    String getArtist() {
        return artist;
    }

    void setArtist(String artist){
        this.artist = artist;
    }

}
