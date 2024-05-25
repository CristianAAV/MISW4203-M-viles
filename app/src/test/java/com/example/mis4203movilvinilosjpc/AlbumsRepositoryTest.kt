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

}
