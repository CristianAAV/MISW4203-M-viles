package com.example.mis4203movilvinilosjpc

import com.example.mis4203movilvinilosjpc.ActivityPrincipal.Albums.Data.Modelo.DataItemAlbums
import com.example.mis4203movilvinilosjpc.ActivityPrincipal.Albums.Data.Modelo.Track
import com.example.mis4203movilvinilosjpc.ActivityPrincipal.Albums.Data.Modelo.Performer
import com.example.mis4203movilvinilosjpc.ActivityPrincipal.Albums.Data.Modelo.Comment
import org.junit.Assert.assertEquals
import org.junit.Test
import java.util.Date


class DataItemAlbumsTests {

    @Test
    fun testDataItemAlbums() {
        val album = DataItemAlbums(
            id = "1",
            name = "AlbumName",
            cover = "CoverURL",
            releaseDate = "2024-04-26",
            description = "AlbumDescription",
            genre = "Rock",
            recordLabel = "RecordLabel"
        )

        assertEquals("1", album.id)
        assertEquals("AlbumName", album.name)
        assertEquals("CoverURL", album.cover)
        assertEquals("2024-04-26", album.releaseDate)
        assertEquals("AlbumDescription", album.description)
        assertEquals("Rock", album.genre)
        assertEquals("RecordLabel", album.recordLabel)
    }

    @Test
    fun testTrack() {
        val track = Track(
            id = 1,
            name = "TrackName",
            duration = "3:45"
        )

        assertEquals(1, track.id)
        assertEquals("TrackName", track.name)
        assertEquals("3:45", track.duration)
    }

    @Test
    fun testPerformer() {
        val performer = Performer(
            id = 1,
            name = "PerformerName",
            image = "ImageURL",
            description = "PerformerDescription",
            birthDate = Date()
        )

        assertEquals(1, performer.id)
        assertEquals("PerformerName", performer.name)
        assertEquals("ImageURL", performer.image)
        assertEquals("PerformerDescription", performer.description)
        // Add assertions for birthDate if required
    }
    @Test
    fun testComment() {
        val comment = Comment(
            id = 1,
            description = "CommentDescription",
            rating = 5
        )

        assertEquals(1, comment.id)
        assertEquals("CommentDescription", comment.description)
        assertEquals(5, comment.rating)
    }

}
