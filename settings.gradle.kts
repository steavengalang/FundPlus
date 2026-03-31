pluginManagement {
    repositories {
        google()
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

rootProject.name = "FundPlus"

// App Module
include(":app")

// Core Modules
include(":core:network")
include(":core:database")
include(":core:ui")
include(":core:common")
include(":core:security")
include(":core:network")

// Feature Modules
include(":feature:dashboard")
include(":feature:input")
include(":feature:gamification")
include(":feature:export")
include(":feature:settings")
