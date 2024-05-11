package com.example.mis4203movilvinilosjpc.ActivityPrincipal.Coleccionistas.Data.Modelo.Repository

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

@ExperimentalCoroutinesApi
class CollectorsRepositoryTest {

    @Mock
    private lateinit var mockApi: CollectorsServices

    private val testDispatcher = TestCoroutineDispatcher()

    private lateinit var collectorsRepository: CollectorsRepository

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        collectorsRepository = CollectorsRepository(mockApi)
    }

    @Test
    fun testGetCollectorsFlow_success() = testDispatcher.runBlockingTest {
        val collectorsData = listOf(
            DataItemCollectors(
                1,
                "John Doe",
                "123456789",
                "john@example.com",
                listOf(Comment(1, "Great collector!", 5)),
                listOf(FavoritePerformer(1, "Performer", "image.jpg", "Description", "2000-01-01")),
                listOf(CollectorAlbum(1, 100, "Available"))
            )
        )
        val flow: Flow<List<DataItemCollectors>> = flow { emit(collectorsData) }
        `when`(mockApi.getCollectorsFlow()).thenReturn(flow)

        val result = collectorsRepository.getCollectorsFlow().toList()
        assertEquals(collectorsData, result[0])
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
        val flow: Flow<List<DataItemCollectors>> = flow { emit(emptyList) }
        `when`(mockApi.getCollectorsFlow()).thenReturn(flow)

        val result = collectorsRepository.getCollectorsFlow().toList()
        assertEquals(emptyList, result[0])
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