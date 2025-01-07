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

val MODELS_URL = mapOf(
    "Europe" to "https://drive.usercontent.google.com/u/0/uc?id=1BNRTjgMbEJYQ0dDRWJpNj8ChDWUWqmfj&export=download",
    "Asia" to "https://drive.usercontent.google.com/u/0/uc?id=1N6ol2Rj1PhC5dsv9Dumznx6Va_CZtc5S&export=download",
    "Africa" to "https://drive.usercontent.google.com/u/0/uc?id=1Gvwq5aOI1vr0CCvfiB0NidsXcC2ptD8C&export=download",
    "North America" to "https://drive.usercontent.google.com/u/0/uc?id=1hSvrBH5Ysi9g5sbfQkZk7aWBHixoIfNG&export=download",
    "South America" to "https://drive.usercontent.google.com/u/0/uc?id=1Fzo15xUdFemTtxqPUUfm8OgzRjfCht7V&export=download",
    "Oceania" to "https://drive.usercontent.google.com/u/0/uc?id=1v0x1uvpsez4wQcoMRDboBSCtpDFqgpM3&export=download",
)