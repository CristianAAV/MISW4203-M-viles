import com.example.mis4203movilvinilosjpc.ActivityPrincipal.Albums.Data.Modelo.Daos.TaskDaosAlbums
import com.example.mis4203movilvinilosjpc.ActivityPrincipal.Artistas.Data.Modelo.Repositorio.ArtistRepository
import com.example.mis4203movilvinilosjpc.ActivityPrincipal.Artistas.Data.Modelo.DataPrizesClient
import com.example.mis4203movilvinilosjpc.ActivityPrincipal.Artistas.Data.Network.ArtistService
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.mock
import com.example.mis4203movilvinilosjpc.ActivityPrincipal.Artistas.Data.Modelo.Daos.TaskDaosArtists
import org.mockito.kotlin.whenever
import org.junit.Assert.assertTrue
import java.io.IOException

class ArtistRepositoryTest {

    @Mock
    private lateinit var mockArtistService: ArtistService
    private val mockTaskDaosArtists: TaskDaosArtists = mock()
    private lateinit var artistRepository: ArtistRepository

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        artistRepository = ArtistRepository(mockArtistService, mockTaskDaosArtists)
    }

    @Test
    fun `test getArtistFlow throws exception`() = runBlocking {
        whenever(mockTaskDaosArtists.getAlArtists()).thenReturn(flow { throw Exception("Mocked exception") })

        val result = runCatching {
            artistRepository.getArtistFlow().firstOrNull()
        }

        assertTrue(result.isFailure)
    }

    @Test
    fun `test getArtistDetalleFlow with invalid artistId returns null`() = runBlocking {
        val invalidArtistId = "invalid_id"
        `when`(mockArtistService.getArtistFlow(invalidArtistId)).thenReturn(flowOf(null))

        val result = artistRepository.getArtistDetalleFlow(invalidArtistId).firstOrNull()

        assert(result == null)
    }

    @Test
    fun `test getPrizeFlow with invalid prizeId returns null`() = runBlocking {
        val invalidPrizeId = "invalid_id"
        `when`(mockArtistService.getPrizeFlow(invalidPrizeId)).thenReturn(flowOf(null))

        val result = artistRepository.getPrizeFlow(invalidPrizeId).firstOrNull()

        assert(result == null)
    }

    @Test
    fun `test getPrizeFlow returns prize`() = runBlocking {
        val prizeId = "valid_id"
        val prize = DataPrizesClient(1, "Organization", "Prize Name", "Description", emptyList())
        `when`(mockArtistService.getPrizeFlow(prizeId)).thenReturn(flow { emit(prize) })

        val result = artistRepository.getPrizeFlow(prizeId).firstOrNull()

        assert(result == prize)
    }

    companion object {
        fun <T> flowOf(vararg values: T) = kotlinx.coroutines.flow.flow {
            values.forEach { emit(it) }
        }
    }
}
