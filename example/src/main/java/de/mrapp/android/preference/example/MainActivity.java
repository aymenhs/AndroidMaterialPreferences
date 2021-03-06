/*
 * Copyright 2014 - 2020 Michael Rapp
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */
package de.mrapp.android.preference.example;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.appcompat.app.AppCompatActivity;

/**
 * The main activity of the example app.
 *
 * @author Michael Rapp
 */
public class MainActivity extends AppCompatActivity {

    /**
     * The tag, which is used to show the activity's fragment.
     */
    private static final String FRAGMENT_TAG = MainActivity.class.getSimpleName() + "::fragmentTag";

    @Override
    public final void setTheme(final int resid) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        String key = getString(R.string.theme_preference_key);
        String defaultValue = getString(R.string.theme_preference_default_value);
        int theme = Integer.valueOf(sharedPreferences.getString(key, defaultValue));

        if (theme != 0) {
            super.setTheme(R.style.DarkTheme);
        } else {
            super.setTheme(R.style.LightTheme);
        }
    }

    @Override
    protected final void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Fragment fragment = getSupportFragmentManager().findFragmentByTag(FRAGMENT_TAG);

        if (fragment == null) {
            fragment = Fragment.instantiate(this, PreferenceFragment.class.getName());
        }

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment, fragment);
        transaction.commit();
    }

}