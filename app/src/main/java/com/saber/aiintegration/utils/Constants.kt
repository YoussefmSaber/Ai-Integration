package com.saber.aiintegration.utils

const val ONBOARDING_TEXT =
    "Discover the world around you with ease\njust scan a landmark and instantly learn its name"
const val ONBOARDING_DESC = "Onboarding Image"
const val HOME_TEXT = "You don’t have any taken images,\ntry take some"
const val HOME_DESC = "Home Image"

val REGION_LIST = listOf(
    "Asia",
    "Africa",
    "Europe",
    "North America",
    "South America",
    "Oceania"
)

val MODELS = mapOf(
    "Europe" to "landmarks-europe.tflite",
    "Asia" to "landmarks-asia.tflite",
    "Africa" to "landmarks-africa.tflite",
    "North America" to "landmarks-north-america.tflite",
    "South America" to "landmarks-south-america.tflite",
    "Oceania" to "landmarks-oceania-antarctica.tflite",
)