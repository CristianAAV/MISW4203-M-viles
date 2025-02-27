import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.flow
import com.example.mis4203movilvinilosjpc.ActivityPrincipal.Albums.Data.Modelo.DataItemAlbums
import com.example.mis4203movilvinilosjpc.ActivityPrincipal.Albums.Data.Modelo.Repositorio.AlbumsRepository
import com.example.mis4203movilvinilosjpc.ActivityPrincipal.Albums.Data.network.AlbumsService
import com.example.mis4203movilvinilosjpc.ActivityPrincipal.Albums.Data.Modelo.Daos.TaskDaosAlbums
import com.example.mis4203movilvinilosjpc.ActivityPrincipal.Albums.Data.Modelo.DataitemCommentsAlbum
import com.example.mis4203movilvinilosjpc.ActivityPrincipal.Albums.Data.Modelo.DataItemsCreacionAlbum
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.Mock
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever
import org.mockito.kotlin.verify
import org.mockito.kotlin.times

class AlbumsRepositoryTest {

    @Mock
    private val mockAlbumsService: AlbumsService = mock()
    private val mockTaskDaosAlbums: TaskDaosAlbums = mock()
    private val albumsRepository = AlbumsRepository(mockAlbumsService, mockTaskDaosAlbums, mock())

    @Test
    fun `getAlbumFlow should return single album`() = runBlockingTest {
        val albumId = "1"
        val mockAlbum = DataItemAlbums(albumId, "Album 1")
        whenever(mockAlbumsService.getAlbumFlow(albumId)).thenReturn(flow { emit(mockAlbum) })
        val albumFlow = albumsRepository.getAlbumFlow(albumId)
        albumFlow.collect { album ->
            assertEquals(mockAlbum, album)
        }
    }

    @Test
    fun `getAlbumsFlow should return empty list`() = runBlockingTest {
        whenever(mockAlbumsService.getAlbumsFlow()).thenReturn(flow { throw Exception("Mocked exception") })
        val result = runCatching {
            albumsRepository.getAlbumsFlow().firstOrNull()
        }
        Assert.assertTrue(result.isFailure)
    }

    @Test
    fun `postCommentsAlbums should call AlbumsService postCommentsAlbums`() = runBlockingTest {
        val albumId = "1"
        val comment = DataitemCommentsAlbum(description = "Great album!", rating = 5, collector = 1)

        albumsRepository.postCommentsAlbums(albumId, comment)

        verify(mockAlbumsService, times(1)).postCommentsAlbums(albumId, comment)
    }

    @Test
    fun `postCommentsAlbums should pass correct data to service`() = runBlockingTest {
        val albumId = "2"
        val comment = DataitemCommentsAlbum(description = "Amazing sound quality!", rating = 4, collector = 2)

        albumsRepository.postCommentsAlbums(albumId, comment)

        verify(mockAlbumsService, times(1)).postCommentsAlbums(albumId, comment)
        }

    @Test
    fun `postCreacionAlbums should pass correct data to service`() = runBlockingTest {
        val newAlbum = DataItemsCreacionAlbum(
            name = "Another New Album",
            cover = "https://example.com/cover.jpg",
            releaseDate = "2024-01-01",
            description = "An amazing new album",
            genre = "Salsa",
            recordLabel = "EMI"
        )

        albumsRepository.postCreacionAlbums(newAlbum)

        verify(mockAlbumsService, times(1)).CreateAlbums(newAlbum)
    }

    @Test
    fun `postCreacionAlbums should call AlbumsService CreateAlbums`() = runBlockingTest {
        val newAlbum = DataItemsCreacionAlbum(
            name = "New Album",
            cover = "https://upload.wikimedia.org/wikipedia/commons/b/b6/12in-Vinyl-LP-Record-Angle.jpg",
            releaseDate = "2023-05-20",
            description = "A great new album",
            genre = "Rock",
            recordLabel = "Sony Music"
        )

        albumsRepository.postCreacionAlbums(newAlbum)

        verify(mockAlbumsService, times(1)).CreateAlbums(newAlbum)
    }

}
