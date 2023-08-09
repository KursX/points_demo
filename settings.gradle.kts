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
rootProject.name = "Chart Demo"
include(":app")

setOf(
    "api",
    "appsflyer",
    "adjust",
    "mixpanel",
    "logcat",
).forEach { module ->
    include(":analytics-$module")
    project(":analytics-$module").projectDir = File("analytics/$module")
}

setOf(
    "ui",
    "presentation",
    "domain",
).forEach { module ->
    include(":common-$module")
    project(":common-$module").projectDir = File("common/$module")
}

setOf(
    "presentation",
    "domain",
    "data",
    "ui-compose",
    "datasource-api",
    "datasource-impl",
).forEach { module ->
    include(":chart-$module")
    project(":chart-$module")
        .projectDir = File("chart/${module.replace("-", "/")}")
}
