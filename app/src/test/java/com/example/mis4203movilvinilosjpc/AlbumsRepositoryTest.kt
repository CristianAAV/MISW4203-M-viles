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
        whenever(mockAlbumsService.getAlbumsFlow()).thenReturn(flow { emit(emptyList()) })
        val albumsFlow = albumsRepository.getAlbumsFlow()
        albumsFlow.collect { albums ->
            assertEquals(emptyList<DataItemAlbums>(), albums)
        }
    }

    @Test
    fun `all albums should have specified properties`() = runBlockingTest {
        // Mock album list with albums having specified properties
        val mockAlbumList = listOf(
            DataItemAlbums("1", "Album 1", "CoverURL1", "2024-04-26", "AlbumDescription1", "Rock1", "RecordLabel1"),
            DataItemAlbums("2", "Album 2", "CoverURL2", "2024-04-27", "AlbumDescription2", "Rock2", "RecordLabel2"),
            DataItemAlbums("3", "Album 3", "CoverURL3", "2024-04-28", "AlbumDescription3", "Rock3", "RecordLabel3")
        )
        whenever(mockAlbumsService.getAlbumsFlow()).thenReturn(flow { emit(mockAlbumList) })

        val albumsFlow = albumsRepository.getAlbumsFlow()
        albumsFlow.collect { albums ->
            // Verify that all albums have specified properties
            albums.forEachIndexed { index, album ->
                assertEquals(mockAlbumList[index].id, album.id)
                assertEquals(mockAlbumList[index].name, album.name)
                assertEquals(mockAlbumList[index].cover, album.cover)
                assertEquals(mockAlbumList[index].releaseDate, album.releaseDate)
                assertEquals(mockAlbumList[index].description, album.description)
                assertEquals(mockAlbumList[index].genre, album.genre)
                assertEquals(mockAlbumList[index].recordLabel, album.recordLabel)
            }
        }
    }
}
