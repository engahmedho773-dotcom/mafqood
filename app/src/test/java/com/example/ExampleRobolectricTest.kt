package com.example

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import com.example.data.CaseStatus
import com.example.data.MafqoodRepository
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [36])
class ExampleRobolectricTest {

    @Test
    fun `read string from context`() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        val appName = context.getString(R.string.app_name)
        assertEquals("MAFQOOD", appName)
    }

    @Test
    fun `verify repository initial state and demo data`() {
        val activeReports = MafqoodRepository.activeReports.value
        assertTrue(activeReports.isNotEmpty())
        assertEquals("White AirPods case", activeReports.first().title)
        assertEquals(CaseStatus.MatchFound, activeReports.first().status)

        val topMatch = MafqoodRepository.demoMatchCandidate
        assertNotNull(topMatch)
        assertEquals(93, topMatch.overallScore)
        assertEquals("Apple", topMatch.foundReport.brand)

        val campusBuildings = MafqoodRepository.campusBuildings
        assertTrue(campusBuildings.size >= 9)
    }

    @Test
    fun `verify case recovery transition`() {
        MafqoodRepository.markCaseRecovered("MFQ-2841")
        val updatedReport = MafqoodRepository.activeReports.value.firstOrNull { it.referenceCode == "MFQ-2841" }
        assertNotNull(updatedReport)
        assertEquals(CaseStatus.Recovered, updatedReport?.status)
        assertEquals(6, updatedReport?.currentJourneyStep)
    }
}
