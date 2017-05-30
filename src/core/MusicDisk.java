package core;

import exceptions.DiskTypeException;
import tracks.Track;
import tracks.Tracks;
import disk.Disk;
import java.io.IOException;
import java.text.ParseException;

/**
 * Main class, creates music disk.
 */
public class MusicDisk  {


    public static void main(String[] args) throws DiskTypeException, ParseException, IOException, NoSuchMethodException {
        MusicDisk musicDisk = new MusicDisk();
        Track track= new Track();

        System.out.println("We create track and show it's information");

        track.createTrack("Flow","Garbage","Love","1998","pop","10:00","50");

        track.trackInfo();
        Tracks tracks = new Tracks();
        //tracks.addTrackToList(null);

        System.out.println("We create tracklist from file and show it's short information\n");

        tracks.createTrackListFromFile(Tracks.file);
        tracks.showArtistsAndTracksName();

        System.out.println("\nNow we write DVD disk with this tracklist");
        Disk disk = new Disk();
        disk.writeDisk("My Songs", Disk.DiskType.DVD,tracks);
        disk.diskInfo();
        System.out.println("\nNow we add track thar we created to our tracklist and get number of tracks, size and total duration\n");
        tracks.addTrackToList(track);
        System.out.println("Number of tracks: " +tracks.numberOfTracks()+"\nSize: "+tracks.getSize()+"\nTotal duration: "+
                tracks.getTracksDurationString());

        System.out.println("\nNow we will find track \"Ruiner\" and show information about it\n");

        disk.findTrack("Reptile").trackInfo();

        System.out.println("Now we will sort songs on disk by size\n");
        disk.sortBySize();
        disk.getTrackList().showArtistsAndTracksName();
        System.out.println("Now we rewrite disk with new tracklist");
        disk.rewriteDisk("My Songs Two",tracks);
        disk.rewrittenDiskInformation();

        System.out.println("\nNow we will try to write track list to the Vinyl Disk\n");
        disk.writeDisk("My vinyl", Disk.DiskType.VINYL,tracks);

    }
}
