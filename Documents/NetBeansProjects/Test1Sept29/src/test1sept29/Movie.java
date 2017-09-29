/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test1sept29;

import java.time.LocalDate;

/**
 *
 * @author Fir3AtWill
 */
public class Movie {
      String MovieName, genre, description;
      int yearReleased;

    public Movie(String MovieName, String genre, String description, int yearReleased) {
        this.MovieName = MovieName;
        this.genre = genre;
        this.description = description;
        this.yearReleased = yearReleased;
    }


    
      
      public static void main(String[] args) {
          
        // TODO code application logic here
    }
      
    void setMovieName(String MovieName) {
        if("".equals(MovieName)){
            throw new IllegalArgumentException("Movie name can not be empty");
        }
        else{
            this.MovieName = MovieName;
        }
    }

    void setGenre(String genre) {
        if("action".equals(genre.toLowerCase())){
            this.genre = genre.toLowerCase();
        }
        else if("comedy".equals(genre.toLowerCase())){
            this.genre = genre.toLowerCase();
        }
        else if("thriller".equals(genre.toLowerCase())){
            this.genre = genre.toLowerCase();
        }
        else if("action".equals(genre.toLowerCase())){
            this.genre = genre.toLowerCase();
        }
        else if("animation".equals(genre.toLowerCase())){
            this.genre = genre.toLowerCase();
        }
        else if("documentary".equals(genre.toLowerCase())){
            this.genre = genre.toLowerCase();
        }
        else if("western".equals(genre.toLowerCase())){
            this.genre = genre.toLowerCase();
        }
        else if("drama".equals(genre.toLowerCase())){
            this.genre = genre.toLowerCase();
        }
        else if("adventure".equals(genre.toLowerCase())){
            this.genre = genre.toLowerCase();
        }
        else{
            throw new IllegalArgumentException("Genre was incorrent please put in another one");
        }
    }
    public void setDescription(String description) {
        if(description.isEmpty()){
            throw new IllegalArgumentException("Description can not be empty");
        }
        this.description = description;
    }

    public void setYearReleased(int yearReleased) {
        if (yearReleased >= 1889){
        if (yearReleased <= LocalDate.now().getYear() + 1){
        }
        else{
            throw new IllegalArgumentException("release date is to far in the future");
        }
        }
        else{
            throw new IllegalArgumentException("release date is to far in the future");
        }
        this.yearReleased = yearReleased;
    }

    public String getMovieName() {
        return MovieName;
    }

    public String getGenre() {
        setGenre(this.genre);
        return genre.toLowerCase();
    }

    public String getDescription() {
        return description;
    }

    public int getYearReleased() {
        return yearReleased;
    }
    

    @Override
    public String toString() {
        return MovieName + ", released in " + this.yearReleased;
    }
    

    
}
