import com.example.mis4203movilvinilosjpc.ActivityPrincipal.Albums.Data.Modelo.DataItemAlbums
import com.example.mis4203movilvinilosjpc.ActivityPrincipal.Albums.Data.Modelo.Repositorio.AlbumsRepository
import com.example.mis4203movilvinilosjpc.ActivityPrincipal.Albums.Data.network.AlbumsService
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runBlockingTest
import okio.IOException
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

@ExperimentalCoroutinesApi
class AlbumsRepositoryTest {

    private val mockAlbumsService: AlbumsService = mock()

    private val albumsRepository = AlbumsRepository(mockAlbumsService)

    @Test
    fun `getAlbumsFlow should return list of albums`() = runBlockingTest {
        val mockAlbumList = listOf(DataItemAlbums("1", "Album 1"), DataItemAlbums("2", "Album 2"))
        whenever(mockAlbumsService.getAlbumsFlow()).thenReturn(flow { emit(mockAlbumList) })
        val albumsFlow = albumsRepository.getAlbumsFlow()
        albumsFlow.collect { albums ->
            assertEquals(mockAlbumList, albums)
        }
    }

}
