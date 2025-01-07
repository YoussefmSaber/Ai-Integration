package com.saber.aiintegration.data.sharedpreference

import android.content.Context

object OnboardingPreference {
    private const val PREF_NAME = "onboarding_pref"
    private const val KEY_ONBOARDING_COMPLETE = "onboarding_complete"

    fun isOnboardingComplete(context: Context): Boolean {
        val sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        return sharedPreferences.getBoolean(KEY_ONBOARDING_COMPLETE, false)
    }

    fun setOnboardingComplete(context: Context) {
        val sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        sharedPreferences.edit().putBoolean(KEY_ONBOARDING_COMPLETE, true).apply()
    }
}