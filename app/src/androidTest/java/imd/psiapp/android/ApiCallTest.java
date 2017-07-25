package imd.psiapp.android;

import android.support.test.runner.AndroidJUnit4;

import org.hamcrest.collection.IsMapContaining;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

import java.util.HashMap;

import imd.psiapp.android.models.PSI;
import imd.psiapp.android.models.PSIReadingsItem;
import imd.psiapp.android.services.api.ApiCallback;
import imd.psiapp.android.services.api.ApiService;
import imd.psiapp.android.utils.TestResponse;

/**
 * test case for api call
 * Created by egiadtya on 7/24/17.
 */

@RunWith(AndroidJUnit4.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ApiCallTest {

    @Test
    public void test1_shouldSuccessGetPSI() throws Exception {
        final TestResponse<PSI> testResponse = new TestResponse<>();
        testResponse.setIdle(true);
        ApiService.getInstance().getPsi(new ApiCallback<PSI>() {
            @Override
            public void onSuccess(PSI data) {
                testResponse.setData(data);
                testResponse.setIdle(false);
            }

            @Override
            public void onFailed(String message) {
                testResponse.setErrorMessage(message);
                testResponse.setIdle(false);
            }
        });
        while (testResponse.isIdle()) {
            //wait request api until success/failed
        }
        Assert.assertNotNull(testResponse.getData()); // PSI data should be not null
        Assert.assertNull(testResponse.getErrorMessage()); // error message  should be null
        Assert.assertNotNull(testResponse.getData().getRegionMetadata()); // region metadata should be not null
        Assert.assertNotEquals(testResponse.getData().getRegionMetadata().size(), 0); // region metadata list should be more than 0
        Assert.assertNotNull(testResponse.getData().getPsiItems()); // psi item should be not null
        Assert.assertNotEquals(testResponse.getData().getPsiItems().size(), 0); // psiItem list should be more than 0
    }

    @Test
    public void test2_shouldSuccessGetPSIByDate() throws Exception {
        final TestResponse<PSI> testResponse = new TestResponse<>();
        testResponse.setIdle(true);
        ApiService.getInstance().getPsiByDate("2017-06-20", new ApiCallback<PSI>() {
            @Override
            public void onSuccess(PSI data) {
                testResponse.setData(data);
                testResponse.setIdle(false);
            }

            @Override
            public void onFailed(String message) {
                testResponse.setErrorMessage(message);
                testResponse.setIdle(false);
            }
        });
        while (testResponse.isIdle()) {
            //wait request api until success/failed
        }
        Assert.assertNotNull(testResponse.getData()); // PSI data should be not null
        Assert.assertNull(testResponse.getErrorMessage()); // error message  should be null
        Assert.assertNotNull(testResponse.getData().getRegionMetadata()); // region metadata should be not null
        Assert.assertNotEquals(testResponse.getData().getRegionMetadata().size(), 0); // region metadata list should be more than 0
        Assert.assertNotNull(testResponse.getData().getPsiItems()); // psi item should be not null
        Assert.assertNotEquals(testResponse.getData().getPsiItems().size(), 0); // psiItem list should be more than 0
    }

    @Test
    public void test3_shouldSuccessGetPSIByDateTime() throws Exception {
        final TestResponse<PSI> testResponse = new TestResponse<>();
        testResponse.setIdle(true);
        ApiService.getInstance().getPsiByDateTime("2016-12-12T09:45:00", new ApiCallback<PSI>() {
            @Override
            public void onSuccess(PSI data) {
                testResponse.setData(data);
                testResponse.setIdle(false);
            }

            @Override
            public void onFailed(String message) {
                testResponse.setErrorMessage(message);
                testResponse.setIdle(false);
            }
        });
        while (testResponse.isIdle()) {
            //wait request api until success/failed
        }
        Assert.assertNotNull(testResponse.getData()); // PSI data should be not null
        Assert.assertNull(testResponse.getErrorMessage()); // error message  should be null
        Assert.assertNotNull(testResponse.getData().getRegionMetadata()); // region metadata should be not null
        Assert.assertNotEquals(testResponse.getData().getRegionMetadata().size(), 0); // region metadata list should be more than 0
        Assert.assertNotNull(testResponse.getData().getPsiItems()); // psi item should be not null
        Assert.assertNotEquals(testResponse.getData().getPsiItems().size(), 0); // psiItem list should be more than 0
    }

    @Test
    public void test4_shouldFailedGetPSIIfDateFormatWrong() throws Exception {
        final TestResponse<PSI> testResponse = new TestResponse<>();
        testResponse.setIdle(true);
        ApiService.getInstance().getPsiByDate("iutiuei393", new ApiCallback<PSI>() {
            @Override
            public void onSuccess(PSI data) {
                testResponse.setData(data);
                testResponse.setIdle(false);
            }

            @Override
            public void onFailed(String message) {
                testResponse.setErrorMessage(message);
                testResponse.setIdle(false);
            }
        });
        while (testResponse.isIdle()) {
            //wait request api until success/failed
        }
        Assert.assertNotNull(testResponse.getErrorMessage()); // error message  should be not null
    }

    @Test
    public void test4_shouldFailedGetPSIIfDateTimeFormatWrong() throws Exception {
        final TestResponse<PSI> testResponse = new TestResponse<>();
        testResponse.setIdle(true);
        ApiService.getInstance().getPsiByDate("2017-07-21T3", new ApiCallback<PSI>() {
            @Override
            public void onSuccess(PSI data) {
                testResponse.setData(data);
                testResponse.setIdle(false);
            }

            @Override
            public void onFailed(String message) {
                testResponse.setErrorMessage(message);
                testResponse.setIdle(false);
            }
        });
        while (testResponse.isIdle()) {
            //wait request api until success/failed
        }
        Assert.assertNotNull(testResponse.getErrorMessage()); // error message  should be not null
    }

    @Test
    public void test5_shouldSuccessGetPSIBasedOnRegion() throws Exception {
        final TestResponse<PSI> testResponse = new TestResponse<>();
        testResponse.setIdle(true);
        ApiService.getInstance().getPsi(new ApiCallback<PSI>() {
            @Override
            public void onSuccess(PSI data) {
                testResponse.setData(data);
                testResponse.setIdle(false);
            }

            @Override
            public void onFailed(String message) {
                testResponse.setErrorMessage(message);
                testResponse.setIdle(false);
            }
        });
        while (testResponse.isIdle()) {
            //wait request api until success/failed
        }
        Assert.assertNotNull(testResponse.getData()); // PSI data should be not null
        Assert.assertNull(testResponse.getErrorMessage()); // error message  should be null
        Assert.assertNotNull(testResponse.getData().getRegionMetadata()); // region metadata should be not null
        Assert.assertNotEquals(testResponse.getData().getRegionMetadata().size(), 0); // region metadata list should be more than 0
        Assert.assertNotNull(testResponse.getData().getPsiItems()); // psi item should be not null
        Assert.assertNotEquals(testResponse.getData().getPsiItems().size(), 0); // psiItem list should be more than 0

        HashMap<String, Double> psiBaseOnRegion = testResponse.getData().getPsiItems().get(0).getPsiReadings().getPSIBaseOnRegion(PSIReadingsItem.WEST);
        Assert.assertEquals(psiBaseOnRegion.size(), 12);
        Assert.assertThat(psiBaseOnRegion, IsMapContaining.hasKey("o3SubIndex"));
        Assert.assertThat(psiBaseOnRegion, IsMapContaining.hasKey("pm10TwentyFourHourly"));
        Assert.assertThat(psiBaseOnRegion, IsMapContaining.hasKey("pm10SubIndex"));
        Assert.assertThat(psiBaseOnRegion, IsMapContaining.hasKey("coSubIndex"));
        Assert.assertThat(psiBaseOnRegion, IsMapContaining.hasKey("pm25TwentyFourHourly"));
        Assert.assertThat(psiBaseOnRegion, IsMapContaining.hasKey("so2SubIndex"));
        Assert.assertThat(psiBaseOnRegion, IsMapContaining.hasKey("coEightHourMax"));
        Assert.assertThat(psiBaseOnRegion, IsMapContaining.hasKey("no2OneHourMax"));
        Assert.assertThat(psiBaseOnRegion, IsMapContaining.hasKey("so2TwentyFourHourly"));
        Assert.assertThat(psiBaseOnRegion, IsMapContaining.hasKey("pm25SubIndex"));
        Assert.assertThat(psiBaseOnRegion, IsMapContaining.hasKey("psiTwentyFourHourly"));
        Assert.assertThat(psiBaseOnRegion, IsMapContaining.hasKey("o3EightHourMax"));
    }
}
