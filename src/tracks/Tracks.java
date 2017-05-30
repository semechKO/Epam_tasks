package tracks;

import exceptions.NullAddException;

import java.io.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.util.Scanner;
import java.util.*;

/**Class Tracks sets,returns and creates track lists. It connects Track and Disk classes.
 *
 */
public class Tracks extends Track {

    private  List<Track> tracks = new ArrayList<>();
    public static File file = new File("tracks.txt");
    public static DateFormat big_duration_format = new SimpleDateFormat("H:mm:ss");


    /**
     * Method adds tracks from file to the track list.
     * @param file
     * @throws IOException
     * @throws ParseException
     */
    public void createTrackListFromFile(File file) throws IOException, ParseException {
        if(file==null){
            throw new NullAddException("Add not-null value ");
        }
        Scanner scanner = new Scanner(file);
        scanner.useDelimiter(";");
        while (scanner.hasNextLine()) {
            Track track = new Track();
            track.setArtist(scanner.next());
            track.setName(scanner.next());
            track.setAlbumNameName(scanner.next());
            track.setYear(year_format.parse(scanner.next()));
            track.setGenre(scanner.next());
            track.setDuration(duration_format.parse(scanner.next()));
            track.setSize(Double.parseDouble(scanner.next()));
            if(scanner.hasNextLine()){
                scanner.nextLine();
            }
            this.tracks.add(track);
        }
    }

    /**
     * Method returns track list
     * @return track list
     */
    public List<Track> getTrackList(){
        return this.tracks;
    }

    /**
     * Method prints atrist name and track name
     */

    public void showArtistsAndTracksName(){
        for(Track track:this.getTrackList()){
            System.out.println(track.getArtist()+" "+track.getName());
        }
    }

    /**
     * Method returns number of tracks.
     * @return tracks number
     */
    public int numberOfTracks(){
        return this.getTrackList().size();
    }

    /**
     * Method returns summary size of tracks in track list.
     * @return whole_size
     */
    public double getSize(){
        double whole_size=0;
        for(Track track:this.getTrackList()){
            whole_size+=track.getSize();
        }
        return whole_size;
    }

    /**
     * Method returns summary duration of tracks.
     * @return tracks duration
     */

    public Date getTracksDuration(){
        LocalDateTime whole_duration=LocalDateTime.of(1,1,1,0,0,0,0);
        LocalDateTime this_date;
        for(Track track:this.getTrackList()){
            this_date=track.getDuration().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
            whole_duration=whole_duration.plusHours(this_date.getHour()).plusMinutes(this_date.getMinute()).
                    plusSeconds(this_date.getSecond());
        }
        Instant instant = whole_duration.toInstant(ZoneOffset.ofHours(3));
        //System.out.println(duration_format.format(Date.from(instant)));
        return Date.from(instant);

    }

    public String getTracksDurationString(){
        LocalDateTime whole_duration=LocalDateTime.of(1,1,1,0,0,0,0);
        LocalDateTime this_date;
        for(Track track:this.getTrackList()){
            this_date=track.getDuration().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
            whole_duration=whole_duration.plusHours(this_date.getHour()).plusMinutes(this_date.getMinute()).
                    plusSeconds(this_date.getSecond());
        }
        Instant instant = whole_duration.toInstant(ZoneOffset.ofHours(3));
        return big_duration_format.format(Date.from(instant));
    }

    public String dateToString(Date date){
        return date.getHours()+"";
    }

    /**
     * Method adds track to track list
     * @param track
     */
    public void addTrackToList(Track track){
        if(track==null){
            throw new NullAddException("Add not-null value ");
        }
      this.tracks.add(track);
    }

    public static void main(String[] args) throws IOException, ParseException {
        Tracks tracks = new Tracks();
        tracks.createTrackListFromFile(file);
        System.out.println(tracks.getTracksDurationString());
        System.out.println(tracks.numberOfTracks());

    }
}
