package tracks;

import interfaces.IParameters;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**Class Track sets, returns and creates track.
 *
 */
public class Track implements IParameters{
    private Date duration;
    private Date year;
    private double size;
    private String track_name;
    private String artist;
    private String album_name;
    private String genre;
    public static DateFormat year_format = new SimpleDateFormat("YYYY");
    public static DateFormat duration_format = new SimpleDateFormat("mm:ss");


    //Getters and setters
    /**
     * Sets track duration.
     * @param duration
     */

    public void setDuration(Date duration){
        this.duration=duration;
    }

    /**
     * @return track duration.
     */

    public Date getDuration(){
        return this.duration;
    }

    /**
     * Sets track year.
     * @param year
     */

    public void setYear(Date year){
        this.year=year;
    }

    /**
     * @return track year.
     */

    public Date getYear(){
        return this.year;
    }

    /**
     * Sets track size.
     * @param size
     */

    public void setSize(double size){
        this.size=size;
    }

    /**
     * @return track size.
     */

    @Override
    public double getSize(){
        return this.size;
    }

    /**
     * Sets track name.
     * @param track_name
     */

    public void setName(String track_name){
        this.track_name=track_name;
    }

    /**
     * @return track name.
     */

    @Override
    public String getName(){
        return this.track_name;
    }

    /**
     * Sets track artist name.
     * @param artist
     */

    public void setArtist(String artist){
        this.artist=artist;
    }

    /**
     * @return artist name.
     */

    public String getArtist(){
        return this.artist;
    }

    /**
     * Sets track album name.
     * @param album_name
     */

    public void setAlbumNameName(String album_name){
        this.album_name=album_name;
    }

    /**
     * @return artist name.
     */

    public String getAlbumName(){
        return this.album_name;
    }

    /**
     * Sets track genre.
     * @param
     */

    public void setGenre(String genre) {
        this.genre=genre;
    }

    /**
     * @return artist genre.
     */

    public String getGenre(){
        return this.genre;
    }

    /**
     * Creates track from given parameters
     * @param track_name
     * @param artist
     * @param album_name
     * @param year
     * @param genre
     * @param duration
     * @param size
     */

    public void createTrack(String track_name,String  artist, String album_name, String year,String genre, String duration, String
            size) throws ParseException {
        this.setName(track_name);
        this.setArtist(artist);
        this.setAlbumNameName(album_name);
        this.setYear(Track.year_format.parse(year));
        this.setGenre(genre);
        this.setDuration(Track.duration_format.parse(duration));
        this.setSize(Double.parseDouble(size));
    }
//Get track information
//    public String TrackInfo(Track track){
//        return "Song: "+getTrackName()+'\n'+"Artist: "+getArtist()+'\n'+"Album: "+getAlbumName()+
//        "\n"+"Year: "+yearFormat.format(getYear())+'\n'+"Duration: "+durationFormat.format(getDuration())+
//        '\n'+"Size: "+getSize()+" Mb";
//    }

    /**Method prints track information in format:
     * 'Song: song_name
     * 'Artist: artist_name
     * 'Album: album_name
     * 'Year: year
     * 'Genre: genre
     * 'Duration: duration
     * 'Size: size
    */
    @Override
    public void trackInfo(){
        System.out.println("\nSong: "+this.getName()+'\n'+"Artist: "+this.getArtist()+'\n'+"Album: "+this.getAlbumName()+
                "\n"+"Year: "+year_format.format(this.getYear())+'\n'+"Genre: "+this.getGenre()+'\n'+"Duration: "+
                duration_format.format(this.getDuration())+
                '\n'+"Size: "+this.getSize()+" Mb"+"\n");
    }

}