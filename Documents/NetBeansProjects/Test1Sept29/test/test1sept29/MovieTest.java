/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test1sept29;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author JWright
 */
public class MovieTest {
    Movie validMovie;
    
    public MovieTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        validMovie = new Movie("test Movie", "coMedy", "funny mOvie!", 2015);
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getMovieName method, of class Movie.
     */
    @Test
    public void testGetMovieName() {
        String expResult = "test Movie";
        String result = validMovie.getMovieName();
        assertEquals(expResult, result);        
    }

    /**
     * Test of setMovieName method, of class Movie.
     */
    @Test
    public void testSetMovieNameValid() {
        try{
            validMovie.setMovieName("Jdubs great adventure");
            assertEquals("Jdubs great adventure", validMovie.getMovieName());
        }
        catch (IllegalArgumentException e)
        {
            System.out.print(e.getMessage());
            fail("Jdubs great adventure is a valid movie name");
        }
    }

    /**
     * Test of setMovieName method, of class Movie.
     */
    @Test
    public void testSetMovieNameInvalid() {
        try{
            validMovie.setMovieName("");
            fail("An empty movie name should trigger an exception");
        }
        catch (IllegalArgumentException e)
        {
            System.out.print(e.getMessage());            
        }
    }

    
    /**
     * Test of getGenre method, of class Movie.
     */
    @Test
    public void testGetGenre() {
        String expResult = "comedy";
        String result = validMovie.getGenre();
        assertEquals(expResult, result);
    }
    
    
    /**
     * Test of setGenre method, of class Movie.
     */
    @Test
    public void testSetGenreComedy() {
        try
        {
            validMovie.setGenre("cOmEdY");
            assertEquals("comedy",validMovie.getGenre());
        }
        catch (IllegalArgumentException e)
        {
            fail("cOmedy is a valid genre-ensure your code is case insensitive");
        }
    }

    
     /**
     * Test of setGenre method, of class Movie.
     */
    @Test
    public void testSetGenreInvalid() {
        try
        {
            validMovie.setGenre("come");
            fail("come is not a valid genre and should have triggered an exception");
        }
        catch (IllegalArgumentException e)
        {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Test of setGenre method, of class Movie.
     */
    @Test
    public void testSetGenreDrama() {
        try
        {
            validMovie.setGenre("DRAMA");
            assertEquals("drama",validMovie.getGenre());
        }
        catch (IllegalArgumentException e)
        {
            fail("DRAMA is a valid genre-ensure your code is case insensitive");
        }
    }

    /**
     * Test of setGenre method, of class Movie.
     */
    @Test
    public void testSetGenreThriller() {
        try
        {
            validMovie.setGenre("thrilLer");
            assertEquals("thriller",validMovie.getGenre());
        }
        catch (IllegalArgumentException e)
        {
            fail("thrilLer is a valid genre-ensure your code is case insensitive");
        }
    }
    
    /**
     * Test of setGenre method, of class Movie.
     */
    @Test
    public void testSetGenreWestern() {
        try
        {
            validMovie.setGenre("western");
            assertEquals("western",validMovie.getGenre());
        }
        catch (IllegalArgumentException e)
        {
            fail("western is a valid genre");
        }
    }
    
    /**
     * Test of setGenre method, of class Movie.
     */
    @Test
    public void testSetGenreAction() {
        try
        {
            validMovie.setGenre("aCTIon");
            assertEquals("action",validMovie.getGenre());
        }
        catch (IllegalArgumentException e)
        {
            fail("aCTIon is a valid genre");
        }
    }
    
    /**
     * Test of setGenre method, of class Movie.
     */
    @Test
    public void testSetGenreAdventure() {
        try
        {
            validMovie.setGenre("Adventure");
            assertEquals("adventure",validMovie.getGenre());
        }
        catch (IllegalArgumentException e)
        {
            fail("Adventure is a valid genre");
        }
    }
    
    
    /**
     * Test of setGenre method, of class Movie.
     */
    @Test
    public void testSetGenreAnimation() {
        try
        {
            validMovie.setGenre("Animation");
            assertEquals("animation",validMovie.getGenre());
        }
        catch (IllegalArgumentException e)
        {
            fail("Animation is a valid genre");
        }
    }
    
    /**
     * Test of setGenre method, of class Movie.
     */
    @Test
    public void testSetGenreDocumentary() {
        try
        {
            validMovie.setGenre("Documentary");
            assertEquals("documentary",validMovie.getGenre());
        }
        catch (IllegalArgumentException e)
        {
            fail("Documentary is a valid genre");
        }
    }
    
    /**
     * Test of getDescription method, of class Movie.
     */
    @Test
    public void testGetDescription() {
        String expResult = "funny mOvie!";
        String result = validMovie.getDescription();
        assertEquals(expResult, result);
    }

    /**
     * Test of setDescription method, of class Movie.
     */
    @Test
    public void testSetDescriptionValid() {
        try
        {
            validMovie.setDescription("Fun times");        
        }
        catch (IllegalArgumentException e)
        {
            fail("Fun times should be a valid description for the movie");
        }        
    }
    
        /**
     * Test of setDescription method, of class Movie.
     */
    @Test
    public void testSetDescriptionInvalid() {
        try
        {
            validMovie.setDescription("");
            fail("An empty description should have triggered an exception");
        }
        catch (IllegalArgumentException e)
        {
            System.out.println(e.getMessage());
        }
    }


    /**
     * Test of getYearReleased method, of class Movie.
     */
    @Test
    public void testGetYearReleased() {
        int expResult = 2015;
        int result = validMovie.getYearReleased();
        assertEquals(expResult, result);
    }

    /**
     * Test of setYearReleased method, of class Movie.
     */
    @Test
    public void testSetYearReleasedTooLow() {
        try
        {
            validMovie.setYearReleased(1887);
            fail("Movies released before 1889 should trigger an exception");
        }
        catch (IllegalArgumentException e)
        {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Test of setYearReleased method, of class Movie.
     */
    @Test
    public void testSetYearReleasedTooHigh() {
        try
        {
            validMovie.setYearReleased(2019);
            fail("Movies released after 2018 should trigger an exception");
        }
        catch (IllegalArgumentException e)
        {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Test of toString method, of class Movie.
     */
    @Test
    public void testToString() {
        String expResult = "test Movie, released in 2015";
        String result = validMovie.toString();
        assertEquals(expResult, result);
    }
}