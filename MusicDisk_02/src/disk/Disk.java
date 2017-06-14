package disk;

import annotations.FindByName;
import annotations.FindBySize;
import exceptions.DiskTypeException;
import interfaces.IParameters;
import tracks.Track;
import tracks.Tracks;
import java.lang.annotation.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.*;

/**
 * Class disk creates, set, returns disk.
 */
public class Disk implements IParameters {

    private String disk_name;
    private Tracks tracks;
    //private List<Track> tracks = new ArrayList();
    private String disk_type;
    public static DateFormat disk_duration_format = new SimpleDateFormat("H:mm:ss");
    private RewrittenDisk rewrittenDisk;

    public enum DiskType {
        CD, VINYL, DVD;
    }

    /**
     * Method returns disk name
     * @return disk name
     */
    @Override
    public String getName() {
        return this.disk_name;
    }

    /**
     * Method returns summary size of tracks on disk
     * @return size of tracks
     */
    @Override
    public double getSize() {
        double whole_size=0;
        Iterator<Track> trackIterator = this.tracks.getTrackList().iterator();
        while (trackIterator.hasNext()){
            whole_size+=trackIterator.next().getSize();
        }
        return whole_size;
    }

    /**
     * Method sets disk name
     * @param name
     */
    @Override
    public void setName(String name) {
        this.disk_name=name;
    }

    /**
     * Prints information of tracks in disks track list
     */
    @Override
    public void trackInfo() {
        Iterator<Track> trackIterator = this.tracks.getTrackList().iterator();
        while (trackIterator.hasNext()){
            trackIterator.next().trackInfo();
        }
    }


    /**
     * Method checks if we can write give tracklist to the disk
     * @param diskType
     * @param tracks
     * @return true or false
     * @throws DiskTypeException
     * @throws ParseException
     */
    public boolean checkDisk(DiskType diskType,Tracks tracks) throws DiskTypeException,ParseException {
        switch (diskType){
            case CD:
                if(tracks.getSize()<=700){
                    this.disk_type="CD";
                    return true;
                } else{
                    throw new DiskTypeException("Tracks size is more than 700 Mb.\nTrack size is: "+tracks.getSize());
                }
            case VINYL:
                LocalDateTime vinyl_duration=LocalDateTime.of(1,1,1,1,30,0,0);
                //System.out.println("vin "+vinyl_duration);
                Instant instant = vinyl_duration.toInstant(ZoneOffset.ofHours(3));
                if(checkDurations(tracks.getTracksDuration(),Date.from(instant) )==1){
                    throw new DiskTypeException("Tracks duration is more than 90 minutes.\nTracks " +
                            "duration is: "+tracks.getTracksDurationString());
                } else
                {this.disk_type="Vinyl";
                    return true;
                }
            case DVD:
                if(tracks.getSize()<=4000){
                    this.disk_type="DVD";
                    return true;
                } else {
                    throw new DiskTypeException("Tracks size is more than 4000 Mb.\nTrack size is: "+tracks.getSize());
                }
        }
        return false;
    }

    /**
     * Method compare durations and return equal(0),more(1) or less(-1)
     * @param first_date
     * @param second_date
     * @return equal(0),more(1) or less(-1)
     */
    public int checkDurations(Date first_date,Date second_date){
        Instant instant=Instant.ofEpochMilli(first_date.getTime());
        LocalDateTime first_datetime =LocalDateTime.ofInstant(instant,ZoneOffset.ofHours(3));
        instant=Instant.ofEpochMilli(second_date.getTime());
        LocalDateTime second_datetime =LocalDateTime.ofInstant(instant,ZoneOffset.ofHours(3));
        //System.out.println("firs " + first_datetime+" "+"sec " +second_datetime);
        //System.out.println(first_datetime.compareTo(second_datetime));
        return first_datetime.compareTo(second_datetime);
    }


    /**
     * Method sorts disk track list by size
     */
    public void sortBySize(){
        Collections.sort(this.tracks.getTrackList(), new Comparator<Track>() {
            @Override
            public int compare(Track o1, Track o2) {
                double size1 = o1.getSize();
                double size2 = o2.getSize();
                if (size1 > size2) {
                    return 1;
                } else if (size1 < size2) {
                    return -1;
                } else {
                    return 0;
                }
            }
        });
    }

    /**
     * Method finds track by size or by name, depending on annotation before method
     * @param track_parameter
     * @return
     * @throws NoSuchMethodException
     */
    @FindByName
    public Track findTrack(String track_parameter) throws NoSuchMethodException {
        Iterator<Track> trackIterator = this.tracks.getTrackList().iterator();
        Annotation[] annotations = this.getClass().getMethod("findTrack", new Class[]{String.class}).getAnnotations();
        Annotation annotation = annotations[0];
        if (annotation.annotationType().getSimpleName().equals("FindByName")) {
            while (trackIterator.hasNext()){
                Track track=trackIterator.next();
                if(track.getName().equals(track_parameter)){
                    System.out.println("Track was found by name");
                    return track;
                }
            }
        } else if (annotation.annotationType().getSimpleName().equals("FindBySize")) {
            while (trackIterator.hasNext()){
                System.out.println("Track was found by size");
                Track track=trackIterator.next();
                if(track.getSize()==Double.parseDouble(track_parameter)){
                    return track;
                }
            }
        }
        return null;
    }

    /**
     * Method print short information about disk
     * @return disk info
     */
    public void diskInfo(){
        System.out.println("\nDisk name is:"+this.disk_name+"\nNumber of tracks: "
                + this.tracks.getTrackList().size()+"\nType of disk: "+this.disk_type);
    }

    /**
     * Method returns track list
     * @return track list
     */
    public Tracks getTrackList(){
        return this.tracks;
    }

    /**
     * Method creates and returns instance of inner class RewrittenDisk if disk type allows. If not, then
     * method returns null
     * @param tracks
     * @param rewritten_name
     * @return Rewritten disk or null
     */
    public RewrittenDisk rewriteDisk(String rewritten_name,Tracks tracks){
        if(this.disk_type=="CD"|this.disk_type=="DVD"){
            this.rewrittenDisk=new RewrittenDisk();
            rewrittenDisk.rewrite(rewritten_name,this,tracks);
            System.out.println("\nDisk has been successfully rewritten. \n");
            return rewrittenDisk;
        } else {
            System.out.println("\nCan't rewrite disk.\n");
            return null;
        }
    }

    /**
     * Inner class rewritten disk
     */
    class RewrittenDisk{
        private Tracks tracks=new Tracks();
        private Disk parent;
        private String name;

        /**
         * Sets rewritten disk name, tracks and parent.
         * @param name
         * @param disk
         * @param tracks
         */
        public void rewrite(String name,Disk disk,Tracks tracks){
            this.name=name;
            this.tracks=tracks;
            this.parent=disk;
        }

    }


    public void rewrittenDiskInformation(){
        System.out.println("This disk is rewritten after " + this.rewrittenDisk.parent.getName()+" and called \n"
                +this.rewrittenDisk.name);
        this.rewrittenDisk.tracks.showArtistsAndTracksName();
    }


    /**
     * Method writes disk
     * @param name
     * @param diskType
     * @param tracks
     * @throws DiskTypeException
     * @throws ParseException
     */
    public void writeDisk(String name,DiskType diskType, Tracks tracks) throws DiskTypeException,ParseException {
        if(checkDisk(diskType,tracks)&&this.tracks==null){
            this.disk_name=name;
            this.tracks=tracks;
        } else System.out.println("\nНе удалось записать диск.\n");
    }

}
