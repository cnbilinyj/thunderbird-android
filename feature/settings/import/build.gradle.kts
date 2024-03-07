plugins {
    id(ThunderbirdPlugins.Library.androidCompose)
    alias(libs.plugins.kotlin.parcelize)
}

android {
    // Using "importing" because "import" is not allowed in Java package names (it's fine with Kotlin, though)
    namespace = "app.k9mail.feature.settings.importing"
    resourcePrefix = "settings_import_"

    buildTypes {
        debug {
            manifestPlaceholders["appAuthRedirectScheme"] = "FIXME: override this in your app project"
        }
        release {
            manifestPlaceholders["appAuthRedirectScheme"] = "FIXME: override this in your app project"
        }
    }

    lint {
        baseline = file("lint-baseline.xml")
    }
}

dependencies {
    implementation(projects.app.core)
    implementation(projects.app.ui.base)
    implementation(projects.core.ui.compose.designsystem)

    implementation(projects.feature.account.oauth)

    implementation(libs.androidx.navigation.fragment)
    implementation(libs.android.material)
    implementation(libs.appauth)

    implementation(libs.androidx.constraintlayout)
    implementation(libs.fastadapter)

    implementation(libs.timber)
}
