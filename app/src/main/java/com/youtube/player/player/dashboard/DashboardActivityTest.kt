package com.youtube.player.player.dashboard

import com.youtube.R
import com.youtube.player.dashboard.DashboardActivity
import com.youtube.player.home.HomeAdapter
import com.youtube.player.home.HomeFragment
import com.youtube.player.library.LibraryFragment
import com.youtube.player.player.VideoDetailsFragment
import com.youtube.player.player.VideoPlayerFragment
import com.youtube.player.subscriptions.SubscriptionFragment
import com.youtube.player.trending.TrendingFragment
import com.youtube.player.useractivity.UserActivityFragment

@RunWith(AndroidJUnit4::class)
class DashboardActivityTest {

    @Rule
    @JvmField
    val rule = ActivityTestRule(DashboardActivity::class.java)
    private lateinit var mDashboardActivity: DashboardActivity

    @Before
    fun setUp() {
        mDashboardActivity = rule.activity
    }

    @Test
    fun testDashboardLaunchedAndHomeFragmentAttachedToDashboard() {
        assertNotNull(mDashboardActivity)
        assertNotNull(mDashboardActivity.findViewById(R.id.rootContainer))

        val homeFragment = mDashboardActivity.supportFragmentManager.findFragmentByTag(HomeFragment.TAG) as HomeFragment
        assertNotNull(homeFragment)
    }

    @Test
    fun testBottomNavigationBarClick() {
        testBottomClickIds(R.id.navigation_trending)
        testBottomClickIds(R.id.navigation_library)
        testBottomClickIds(R.id.navigation_notifications)
        testBottomClickIds(R.id.navigation_subscription)
        testBottomClickIds(R.id.navigation_home)

    }

    @Test
    fun testMovieClickedAndMatchItToItsDetails(){
        val homeFragment=mDashboardActivity.supportFragmentManager.findFragmentByTag(HomeFragment.TAG) as HomeFragment
        IdlingRegistry.getInstance().register(homeFragment.countingIdleResources)
        onView(withId(R.id.rvMovies)).perform(RecyclerViewActions.actionOnItemAtPosition<HomeAdapter.HomeViewHolder>(0, click()))
        assertNotNull(mDashboardActivity.supportFragmentManager.findFragmentByTag(
            VideoPlayerFragment.TAG))
        assertNotNull(mDashboardActivity.supportFragmentManager.findFragmentByTag(
            VideoDetailsFragment.TAG))
        IdlingRegistry.getInstance().unregister(homeFragment.countingIdleResources)
    }

    @After
    fun tearDown() {
    }

    private fun testBottomClickIds(menuId: Int) {
        onView(withId(menuId)).perform(click())

        when (menuId) {
            R.id.navigation_home -> {
                val homeFragment = mDashboardActivity.supportFragmentManager.findFragmentByTag(HomeFragment.TAG) as HomeFragment
                assertNotNull(homeFragment)
            }
            R.id.navigation_trending -> {
                val homeFragment = mDashboardActivity.supportFragmentManager.findFragmentByTag(TrendingFragment.TAG) as TrendingFragment
                assertNotNull(homeFragment)

            }
            R.id.navigation_library -> {

                val homeFragment = mDashboardActivity.supportFragmentManager.findFragmentByTag(LibraryFragment.TAG) as LibraryFragment
                assertNotNull(homeFragment)

            }
            R.id.navigation_notifications -> {

                val homeFragment = mDashboardActivity.supportFragmentManager.findFragmentByTag(
                    UserActivityFragment.TAG) as UserActivityFragment
                assertNotNull(homeFragment)

            }
            R.id.navigation_subscription -> {
                val homeFragment = mDashboardActivity.supportFragmentManager.findFragmentByTag(
                    SubscriptionFragment.TAG) as SubscriptionFragment
                assertNotNull(homeFragment)

            }
        }
    }
}