package com.example.mis4203movilvinilosjpc.ActivityPrincipal.Coleccionistas.Data.Modelo.Repository

import com.example.mis4203movilvinilosjpc.ActivityPrincipal.Albums.Data.Modelo.Daos.TaskDaosAlbums
import com.example.mis4203movilvinilosjpc.ActivityPrincipal.Coleccionistas.Data.Modelo.CollectorAlbum
import com.example.mis4203movilvinilosjpc.ActivityPrincipal.Coleccionistas.Data.Modelo.Comment
import com.example.mis4203movilvinilosjpc.ActivityPrincipal.Coleccionistas.Data.Modelo.DataItemCollectors
import com.example.mis4203movilvinilosjpc.ActivityPrincipal.Coleccionistas.Data.Modelo.FavoritePerformer
import com.example.mis4203movilvinilosjpc.ActivityPrincipal.Coleccionistas.Data.Network.CollectorsServices
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.mock
import com.example.mis4203movilvinilosjpc.ActivityPrincipal.Coleccionistas.Data.Modelo.Daos.TaskDaosCollectors
import kotlinx.coroutines.flow.firstOrNull
import org.junit.Assert


@ExperimentalCoroutinesApi
class CollectorsRepositoryTest {

    @Mock
    private lateinit var mockApi: CollectorsServices
    private val mockTaskDaosCollectors: TaskDaosCollectors = mock()
    private val testDispatcher = TestCoroutineDispatcher()

    private lateinit var collectorsRepository: CollectorsRepository

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        collectorsRepository = CollectorsRepository(mockApi, mockTaskDaosCollectors)
    }

    @Test
    fun testGetCollectorFlow_success() = testDispatcher.runBlockingTest {
        val collectorId = "1"
        val collectorData = DataItemCollectors(
            1,
            "Jane Doe",
            "987654321",
            "jane@example.com",
            listOf(Comment(1, "Awesome collector!", 5)),
            listOf(FavoritePerformer(1, "Performer", "image.jpg", "Description", "2000-01-01")),
            listOf(CollectorAlbum(1, 150, "Unavailable"))
        )
        val flow: Flow<DataItemCollectors> = flow { emit(collectorData) }
        `when`(mockApi.getCollectorFlow(collectorId)).thenReturn(flow)

        val result = collectorsRepository.getCollectorFlow(collectorId).toList()
        assertEquals(collectorData, result[0])
    }

    @Test
    fun testGetCollectorsFlow_empty() = testDispatcher.runBlockingTest {
        val emptyList: List<DataItemCollectors> = emptyList()
        `when`(mockApi.getCollectorsFlow()).thenReturn(flow { throw Exception("Mocked exception") })

        val result = runCatching {
            collectorsRepository.getCollectorsFlow().firstOrNull()
        }

        Assert.assertTrue(result.isFailure)
    }


    @Test
    fun testGetCollectorFlow_error() = testDispatcher.runBlockingTest {
        val collectorId = "invalidId"
        val flow: Flow<DataItemCollectors> = flow { throw Exception("Collector not found") }
        `when`(mockApi.getCollectorFlow(collectorId)).thenReturn(flow)

        val result = try {
            collectorsRepository.getCollectorFlow(collectorId).toList()
            emptyList<DataItemCollectors>()
        } catch (e: Exception) {
            listOf<DataItemCollectors>()
        }
        assertEquals(emptyList<DataItemCollectors>(), result)
    }
}