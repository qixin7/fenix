/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */

package org.mozilla.fenix.settings

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import org.mozilla.fenix.R
import org.mozilla.fenix.utils.Settings

class SearchEngineFragment : PreferenceFragmentCompat() {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.search_engine_preferences, rootKey)
    }

    override fun onResume() {
        super.onResume()
        (activity as AppCompatActivity).title = getString(R.string.preferences_search_engine)
        (activity as AppCompatActivity).supportActionBar?.show()

        val searchSuggestionsPreference =
            findPreference<Preference>(getString(R.string.pref_key_show_search_suggestions))
        searchSuggestionsPreference?.setOnPreferenceChangeListener { preference, newValue ->
            Settings.getInstance(preference.context).preferences.edit().putBoolean(preference.key, newValue as Boolean)
                .apply()
            true
        }

        val showVisitedSitesBookmarks =
            findPreference<Preference>(getString(R.string.pref_key_show_visited_sites_bookmarks))
        showVisitedSitesBookmarks?.setOnPreferenceChangeListener { preference, newValue ->
            Settings.getInstance(preference.context).preferences.edit().putBoolean(preference.key, newValue as Boolean)
                .apply()
            true
        }
    }
}
