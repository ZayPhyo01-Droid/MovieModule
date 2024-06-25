pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "Movie"
include(":app")
include(":core")

include(":core:design")
include(":feature")

include(":core:domain")
include(":core:network")
include(":feature:home")
include(":feature:poster")
include(":core:common")
include(":feature:upcoming")
include(":api")
include(":api:movie")
include(":annotations")
include(":baselineprofile")
include(":processor")
include(":ktor")
include(":core:navigation")
