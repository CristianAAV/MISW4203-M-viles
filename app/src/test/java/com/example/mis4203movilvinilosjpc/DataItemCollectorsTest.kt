import com.example.mis4203movilvinilosjpc.ActivityPrincipal.Coleccionistas.Data.Modelo.*
import org.junit.Assert.assertNotEquals
import org.junit.Test

class DataItemCollectorsTest {

    @Test
    fun testDataItemCollectorsEquality_DifferentIds() {
        val collector1 = DataItemCollectors(
            id = 1,
            name = "John Doe",
            telephone = "123456789",
            email = "john@example.com",
            comments = emptyList(),
            favoritePerformers = emptyList(),
            collectorAlbums = emptyList()
        )

        val collector2 = DataItemCollectors(
            id = 2, // Different id
            name = "John Doe",
            telephone = "123456789",
            email = "john@example.com",
            comments = emptyList(),
            favoritePerformers = emptyList(),
            collectorAlbums = emptyList()
        )

        assertNotEquals(collector1, collector2)
    }

    @Test
    fun testDataItemCollectorsEquality_DifferentNames() {
        val collector1 = DataItemCollectors(
            id = 1,
            name = "John Doe",
            telephone = "123456789",
            email = "john@example.com",
            comments = emptyList(),
            favoritePerformers = emptyList(),
            collectorAlbums = emptyList()
        )

        val collector2 = DataItemCollectors(
            id = 1,
            name = "Jane Doe", // Different name
            telephone = "123456789",
            email = "john@example.com",
            comments = emptyList(),
            favoritePerformers = emptyList(),
            collectorAlbums = emptyList()
        )

        assertNotEquals(collector1, collector2)
    }

    @Test
    fun testDataItemCollectorsEquality_Null() {
        val collector = DataItemCollectors(
            id = 1,
            name = "John Doe",
            telephone = "123456789",
            email = "john@example.com",
            comments = emptyList(),
            favoritePerformers = emptyList(),
            collectorAlbums = emptyList()
        )

        assertNotEquals(collector, null)
    }

    @Test
    fun testDataItemCollectorsEquality_DifferentType() {
        val collector = DataItemCollectors(
            id = 1,
            name = "John Doe",
            telephone = "123456789",
            email = "john@example.com",
            comments = emptyList(),
            favoritePerformers = emptyList(),
            collectorAlbums = emptyList()
        )

        assertNotEquals(collector, "Not a DataItemCollectors object")
    }
}