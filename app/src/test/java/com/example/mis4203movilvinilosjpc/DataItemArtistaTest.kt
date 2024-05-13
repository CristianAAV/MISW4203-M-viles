import com.example.mis4203movilvinilosjpc.ActivityPrincipal.Artistas.Data.Modelo.*
import org.junit.Assert.assertEquals
import org.junit.Test

class DataItemArtistaTest {

    @Test
    fun testDataItemArtistaToString() {
        val artist = DataItemArtista(
            id = 1,
            name = "John Doe",
            image = "image.jpg",
            description = "Description",
            birthDate = "2000-01-01",
            albums = emptyList(),
            performerPrizes = emptyList()
        )

        val expectedToString = "DataItemArtista(id=1, name=John Doe, image=image.jpg, " +
                "description=Description, birthDate=2000-01-01, albums=[], performerPrizes=[])"

        assertEquals(expectedToString, artist.toString())
    }

    @Test
    fun testDataItemArtistaEquality() {
        val artist1 = DataItemArtista(
            id = 1,
            name = "John Doe",
            image = "image.jpg",
            description = "Description",
            birthDate = "2000-01-01",
            albums = emptyList(),
            performerPrizes = emptyList()
        )

        val artist2 = DataItemArtista(
            id = 1,
            name = "John Doe",
            image = "image.jpg",
            description = "Description",
            birthDate = "2000-01-01",
            albums = emptyList(),
            performerPrizes = emptyList()
        )

        assertEquals(artist1, artist2)
    }

    @Test
    fun testDataItemArtistaHashCode() {
        val artist1 = DataItemArtista(
            id = 1,
            name = "John Doe",
            image = "image.jpg",
            description = "Description",
            birthDate = "2000-01-01",
            albums = emptyList(),
            performerPrizes = emptyList()
        )

        val artist2 = DataItemArtista(
            id = 1,
            name = "John Doe",
            image = "image.jpg",
            description = "Description",
            birthDate = "2000-01-01",
            albums = emptyList(),
            performerPrizes = emptyList()
        )

        assertEquals(artist1.hashCode(), artist2.hashCode())
    }

    @Test
    fun testDataItemArtistaAlbumEquality() {
        val album1 = Album(
            id = 1,
            name = "Album 1",
            cover = "cover.jpg",
            releaseDate = "2022-01-01",
            description = "Description",
            genre = "Pop",
            recordLabel = "Record Label"
        )

        val album2 = Album(
            id = 1,
            name = "Album 1",
            cover = "cover.jpg",
            releaseDate = "2022-01-01",
            description = "Description",
            genre = "Pop",
            recordLabel = "Record Label"
        )

        assertEquals(album1, album2)
    }

    @Test
    fun testDataItemArtistaPerformerPrizeEquality() {
        val prize1 = PerformerPrize(
            id = 1,
            premiationDate = "2022-01-01"
        )

        val prize2 = PerformerPrize(
            id = 1,
            premiationDate = "2022-01-01"
        )

        assertEquals(prize1, prize2)
    }
}